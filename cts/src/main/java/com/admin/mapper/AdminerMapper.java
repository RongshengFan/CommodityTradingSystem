package com.admin.mapper;

import com.admin.dto.AdminUpdateDTO;
import com.admin.entity.Adminer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chaoheng
 * @since 2025-05-30
 */

@Mapper
public interface AdminerMapper extends BaseMapper<Adminer> {
    @Select("SELECT * FROM adminer WHERE username = #{username}")
    Adminer findByUsername(@Param("username") String username);

    @Select("SELECT COUNT(*) FROM adminer WHERE username =#{username}")
    int checkUsernameExists(@Param("username") String username) ;

    // 分页查询（使用MyBatis-Plus自带的分页功能）
    IPage<Adminer> selectAdminerPage(Page<Adminer> page, @Param("username") String username);

    @Update("UPDATE adminer SET " +
            "username = #{dto.username}, " +
            "avatar = #{dto.avatar}, " +
            "phone = #{dto.phone}, " +
            "email = #{dto.email}, " +
            "password = #{dto.password} " +
            "WHERE id = #{adminId} AND password = #{dto.oldPassword}")
    int updateAdminInfo(@Param("adminId") Long adminId,
                        @Param("dto") AdminUpdateDTO dto);
}
