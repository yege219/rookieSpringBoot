package com.rookie.controller;

import com.rookie.dto.PersonDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhangyechen on 2017/12/10.
 */
@RestController
@RequestMapping("/person")
public class PersonController {

    @RequestMapping("/getPerson")
    public PersonDTO getPerson() {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setName("March");
        personDTO.setAge("2");
        personDTO.setAddress("season");
        personDTO.setPhoneNum("13910699468");
        return personDTO;
    }
}
