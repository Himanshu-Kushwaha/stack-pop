package com.stack.operation.stackpop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stack.operation.stackpop.entity.ResponseData;
import com.stack.operation.stackpop.service.PopElement;


@Controller
public class PopStackElementController {

    @Autowired
    PopElement popElement;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/pop", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<ResponseData> pushElement() throws Exception {
        logger.info("HottestRepoController:getHottestRepo():: RepoCount request for hottest repos {}");
        return new ResponseEntity<ResponseData>(popElement.pop(), HttpStatus.OK);

    }

}

