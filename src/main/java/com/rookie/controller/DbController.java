package com.rookie.controller;

import com.rookie.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by rookie on 2018/1/17.
 */
@RestController
@RequestMapping("/db")
public class DbController {

    @Autowired
    private DbService dbService;

    @RequestMapping("/query")
    public void query() {

        String sql = "select PRODUCT_CODE as productCode,PRODUCT_NAME as productName from product where id = '5';";
        dbService.query(sql);

    }
}
