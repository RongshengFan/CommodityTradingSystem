package com.shanzhu.em.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shanzhu.em.entity.Message;

import java.util.List;


/**
 * 留言 服务层
 *
 * @author: ShanZhu
 * @date: 2023-11-20
 */
public interface MessageService {

    /**
     * 查询所有评论
     *
     * @return 评论
     */
    List<Message> findAll();

    /**
     * 留言查询 分页
     *
     * @param page 分页内容
     * @return 留言数据
     */
    IPage<Message> findPage(Page page, Long goodId);

    /**
     * 通过id查询留言
     *
     * @param id 留言id
     * @return 留言内容
     */
    Message findById(Integer id);

    /**
     * 删除留言
     *
     * @param id 留言id
     * @return 删除成功数量
     */
    Integer delete(Integer id);

    /**
     * 更新留言
     *
     * @param message 留言信息
     * @return 结果
     */
    Integer update(Message message);

    /**
     * 添加留言
     *
     * @param message 留言信息
     * @return 结果
     */
    Integer add(Message message);
}
