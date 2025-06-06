package com.admin.mapper;

import com.admin.dto.UserPageDTO;
import com.admin.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author chaoheng
 * @since 2025-06-06
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 分页查询用户列表（注解方式）
     * 使用@Select注解编写动态SQL
     */
    @Select("<script>" +
            "SELECT " +
            "   user_id, username, avatar, phone, email, balance, create_time, status " +
            "FROM `user` " +
            "<where>" +
            "   <if test='dto.username != null and dto.username != \"\"'>" +
            "       AND username LIKE CONCAT('%', #{dto.username}, '%')" +
            "   </if>" +
            "   <if test='dto.phone != null and dto.phone != \"\"'>" +
            "       AND phone LIKE CONCAT('%', #{dto.phone}, '%')" +
            "   </if>" +
            "   <if test='dto.status != null'>" +
            "       AND status = #{dto.status}" +
            "   </if>" +
            "</where>" +
            "ORDER BY create_time DESC" +
            "</script>")
    IPage<User> selectPageByDto(IPage<User> page, @Param("dto") UserPageDTO dto);

    /**
     * 更新用户状态
     * @param userId 用户ID
     * @param status 新状态：0-禁用，1-启用
     * @return 更新行数
     */
    @Update("UPDATE `user` SET status = #{status} WHERE user_id = #{userId}")
    int updateStatus(@Param("userId") String userId, @Param("status") Byte status);


}
