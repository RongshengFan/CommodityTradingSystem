package com.admin.service.impl;

import com.admin.entity.Message;
import com.admin.mapper.MessageMapper;
import com.admin.service.IMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chaoheng
 * @since 2025-05-30
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {

}
