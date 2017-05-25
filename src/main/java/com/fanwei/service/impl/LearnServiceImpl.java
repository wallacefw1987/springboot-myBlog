package com.fanwei.service.impl;

import com.fanwei.dao.LearnDao;
import com.fanwei.pojo.LearnResource;
import com.fanwei.service.LearnService;
import com.fanwei.tools.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/29.
 */
@Service
public class LearnServiceImpl implements LearnService {

    Logger logger = LoggerFactory.getLogger(LearnServiceImpl.class);
    @Autowired
    private LearnDao learnDaoImpl;

    @Override
    @Transactional
    public Integer add(LearnResource learnResource) {
        return this.learnDaoImpl.add(learnResource);
    }

    @Override
    @Transactional
    public Integer update(LearnResource learnResource) {
        return this.learnDaoImpl.update(learnResource);
    }

    @Override
    @Transactional
    public Integer deleteByIds(String ids) {
        return this.learnDaoImpl.deleteByIds(ids);
    }

    @Override
    public LearnResource queryLearnResourceById(Integer id) {
        return this.learnDaoImpl.queryLearnResourceById(id);
    }

    @Override
    public Page queryLearnResourceList(Map<String, Object> params) {
        return this.learnDaoImpl.queryLearnResourceList(params);
    }
}
