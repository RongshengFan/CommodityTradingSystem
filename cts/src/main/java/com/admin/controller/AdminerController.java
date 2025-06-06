package com.admin.controller;

import com.admin.dto.AdminUpdateDTO;
import com.admin.entity.Adminer;
import com.admin.enums.HTTPCodeEnum;
import com.admin.service.IAdminerService;
import com.admin.vo.PageResult;
import com.admin.vo.Result;
import com.admin.vo.AdminerVO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adminer")
public class AdminerController {

    @Autowired
    private IAdminerService adminerService;

    @PostMapping("/register")
    public Result<String> register(@RequestBody Adminer user) {
        return adminerService.register(user);
    }

    @PostMapping("/login")
    public Result<Adminer> login(@RequestBody LoginRequest request) {
        try {
            Adminer adminer = adminerService.login(request.getUsername(), request.getPassword());
            adminer.setPassword(null); // 不返回密码信息
            return Result.ok(adminer);
        } catch (RuntimeException e) {
            return Result.fail(HTTPCodeEnum.NOT_FOUND);
        }
    }
    @GetMapping("/{id}")
    public Result<Adminer> getAdminer(@PathVariable Long id) {
        Adminer adminer = adminerService.getById(id);
        return adminer != null
                ? Result.ok(adminer)
                : Result.fail(HTTPCodeEnum.NOT_FOUND);
    }
    /**
     * 分页查询管理员列表
     *
     * @param page     页码
     * @param size     每页数量
     * @param username 搜索用户名
     * @return 分页结果
     */
    @GetMapping("/page")
    public Result getAdminerList(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String username) {

        if (page < 1) page = 1;
        if (size < 1 || size > 100) size = 10;

        PageResult<Adminer> result = adminerService.getAdminerPage(page, size, username);
        return Result.success(result);
    }

    /**
     * 查看管理员详情
     * @param id 用户ID
     * @return 用户详情
     */
    @GetMapping("/{id}")
    public Result<AdminerVO> getAdminerDetail(@PathVariable Long id) {
        AdminerVO adminer = adminerService.getAdminerDetail(id);
        return Result.success(adminer);
    }

    /**
     * 修改当前管理员个人信息（包含密码）
     * @param updateDTO 更新信息
     * @return 操作结果
     */
    @PutMapping("/profile")
    public Result<Adminer> updateAdminProfile(
            @RequestBody @Validated AdminUpdateDTO updateDTO,
            HttpServletRequest request) {

        // 从请求属性中获取当前登录的管理员ID
        Long adminId = getCurrentAdminId(request);

        // 执行更新
        Adminer updatedAdmin = adminerService.updateAdminInfo(adminId, updateDTO);

        // 清空密码后再返回（安全考虑）
        updatedAdmin.setPassword(null);
        return Result.success(updatedAdmin);
    }

    /**
     * 从认证信息中获取当前管理员ID
     */
    private Long getCurrentAdminId(HttpServletRequest request) {
        // 这里根据实际的安全框架实现获取当前用户ID
        // 例如：从JWT token或session中获取
        // 伪代码实现：
        String token = request.getHeader("Authorization");
        // 实际应从token中解析用户ID
        return 1L; // 示例返回
    }

    // 登录请求对象
    static class LoginRequest {
        private String username;
        private String password;

        // Getters
        public String getUsername() { return username; }
        public String getPassword() { return password; }

        // Setters
        public void setUsername(String username) { this.username = username; }
        public void setPassword(String password) { this.password = password; }
    }
}