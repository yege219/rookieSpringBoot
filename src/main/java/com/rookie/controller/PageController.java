package com.rookie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhangyechen on 2017/12/7.
 * 此Controller用于返回html页面
 */
@Controller
public class PageController {

    @RequestMapping("/index")
    public String index() {
        return "market";
    }
}
