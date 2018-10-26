package com.esen.youngcms.service;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.esen.youngcms.bean.Advert;
import com.esen.youngcms.dao.AdvertMapper;

@Service
public class AdvertService extends ServiceImpl<AdvertMapper, Advert> {
}
