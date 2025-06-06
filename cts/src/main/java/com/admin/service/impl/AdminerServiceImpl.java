package com.admin.service.impl;

import com.admin.dto.AdminUpdateDTO;
import com.admin.entity.Adminer;
import com.admin.mapper.AdminerMapper;
import com.admin.service.IAdminerService;
import com.admin.vo.AdminerVO;
import com.admin.vo.BusinessException;
import com.admin.vo.PageResult;
import com.admin.vo.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

@Service
public class AdminerServiceImpl extends ServiceImpl<AdminerMapper, Adminer>
        implements IAdminerService {

    @Autowired
    private AdminerMapper adminerMapper;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Adminer login(String username, String password) {
        // 1. 根据用户名查找用户
        Adminer adminer = adminerMapper.findByUsername(username);
        if (adminer == null) {
            throw new RuntimeException("用户不存在");
        }

        // 2. 对密码进行MD5加密（实际项目中应采用更安全的加密方式）
        String encryptedPassword = DigestUtils.md5DigestAsHex(password.getBytes());

        // 3. 验证密码
        if (!encryptedPassword.equals(adminer.getPassword())) {
            throw new RuntimeException("密码错误");
        }

        // 4. 返回管理员信息
        return adminer;
    }

    @Override
    public Result<String> register(Adminer user) {
        // 1. 验证参数
        if (!StringUtils.hasText(user.getUsername())) {
            return Result.fail("用户名不能为空");
        }
        if (!StringUtils.hasText(user.getPassword())) {
            return Result.fail("密码不能为空");
        }

        // 2. 检查用户名是否已存在
        if (adminerMapper.checkUsernameExists(user.getUsername()) > 0) {
            return Result.fail("用户名已存在");
        }

        // 3. 密码加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // 4. 保存用户
        if (this.save(user)) {
            return Result.ok("注册成功");
        }

        return Result.fail("注册失败，请重试");
    }
    @Override
    public PageResult<Adminer> getAdminerPage(Integer page, Integer size, String username) {
        Page<Adminer> pageInfo = new Page<>(page, size);
        IPage<Adminer> adminerPage = baseMapper.selectAdminerPage(pageInfo, username);
        return new PageResult<>(adminerPage.getRecords(), adminerPage.getTotal());
    }

    @Override
    public AdminerVO getAdminerDetail(Long id) {
        Adminer adminer = getById(id);

        // 转换为VO对象（隐藏敏感信息）
        AdminerVO vo = new AdminerVO();
        BeanUtils.copyProperties(adminer, vo);
        return vo;
    }

    @Override
    @Transactional
    public Adminer updateAdminInfo(Long adminId, AdminUpdateDTO updateDTO) {
        // 1. 验证原密码
        if (!checkOldPassword(adminId, updateDTO.getOldPassword())) {
            throw new BusinessException("原密码错误，请确认后重试");
        }

        // 2. 密码加密处理
        String encryptedPwd = DigestUtils.md5DigestAsHex(updateDTO.getPassword().getBytes());
        updateDTO.setPassword(encryptedPwd);

        // 3. 执行更新
        int updatedRows = baseMapper.updateAdminInfo(adminId, updateDTO);

        // 4. 验证更新结果
        if (updatedRows == 0) {
            throw new BusinessException("更新个人信息失败");
        }

        // 5. 返回更新后的管理员信息
        return this.getById(adminId);
    }

    /**
     * 验证原密码是否匹配
     */
    private boolean checkOldPassword(Long adminId, String oldPassword) {
        Adminer admin = this.getById(adminId);
        if (admin == null) {
            throw new BusinessException("管理员不存在");
        }

        // 对原密码加密后比较
        String encryptedOldPwd = DigestUtils.md5DigestAsHex(oldPassword.getBytes());
        return encryptedOldPwd.equals(admin.getPassword());
    }

}