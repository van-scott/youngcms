package com.esen.youngcms.service;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.esen.youngcms.bean.Site;
import com.esen.youngcms.dao.SiteMapper;
@Service
public class SiteService extends ServiceImpl<SiteMapper,Site> {
}
