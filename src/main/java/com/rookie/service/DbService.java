package com.rookie.service;

import com.rookie.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by rookie on 2018/1/17.
 */
@Service
public class DbService {

    @Autowired
    @Qualifier("vpsJdbcTemplate")
    private JdbcTemplate vpsJdbcTemplate;

    @Autowired
    @Qualifier("testJdbcTemplate")
    private JdbcTemplate jdbcTemplate2;

    /**
     * 数据库查询操作
     * @param querySql 查询sql
     */
    public void query(String querySql) {

        List<ProductDTO> productDTOList = vpsJdbcTemplate.query(querySql, new BeanPropertyRowMapper<>(ProductDTO.class));

        System.out.println(productDTOList.get(0).getProductName());

    }

    /**
     * 数据库更新操作
     * @param updateSql 更新sql
     */
    public void update(String updateSql) {

        vpsJdbcTemplate.update(updateSql);

    }
}
