package com.esen.youngcms.service;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.esen.youngcms.bean.LoginLog;
import com.esen.youngcms.dao.LoginLogMapper;
@Service
public class LoginLogService extends ServiceImpl<LoginLogMapper,LoginLog> {
}
