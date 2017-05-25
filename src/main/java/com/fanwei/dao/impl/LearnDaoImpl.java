package com.fanwei.dao.impl;

import com.fanwei.dao.LearnDao;
import com.fanwei.pojo.LearnResource;
import com.fanwei.tools.Page;
import com.fanwei.tools.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/29.
 */
@Repository
public class LearnDaoImpl implements LearnDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public Integer add(LearnResource learnResource) {
        return jdbcTemplate.update("INSERT INTO learn_resource (author, title, url) VALUES (?,?,?)",new Object[]{learnResource.getAuthor(),learnResource.getTitle(),learnResource.getUrl()});
    }

    @Override
    public Integer update(LearnResource learnResource) {
        return jdbcTemplate.update("UPDATE learn_resource SET author=?,title=?,url=? WHERE id = ?",new Object[]{learnResource.getAuthor(),learnResource.getTitle(),learnResource.getUrl(),learnResource.getId()});
    }

    @Override
    public Integer deleteByIds(String ids) {
        return jdbcTemplate.update("DELETE FROM learn_resource WHERE id IN ("+ids+")");
    }

    @Override
    public LearnResource queryLearnResourceById(Integer id) {
        List<LearnResource> learnResources = jdbcTemplate.query("SELECT id,author,title,url FROM learn_resource WHERE id = ?",new Object[]{id},new BeanPropertyRowMapper<>(LearnResource.class));
        return learnResources.size()>0?learnResources.get(0):null;
    }

    @Override
    public Page queryLearnResourceList(Map<String, Object> params) {
        StringBuffer sql =new StringBuffer();
        sql.append("select * from learn_resource where 1=1");
        if(!StringUtil.isNull((String)params.get("author"))){
            sql.append(" and author like '%").append((String)params.get("author")).append("%'");
        }
        if(!StringUtil.isNull((String)params.get("title"))){
            sql.append(" and title like '%").append((String)params.get("title")).append("%'");
        }
        Page page = new Page(sql.toString(), Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("rows").toString()), jdbcTemplate);
        return page;
    }
}
