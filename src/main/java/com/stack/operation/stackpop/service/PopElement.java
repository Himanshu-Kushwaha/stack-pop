package com.stack.operation.stackpop.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stack.operation.stackpop.entity.ResponseData;
import com.stack.operation.stackpop.entity.StackData;
import com.stack.operation.stackpop.repository.mysql.StackElementRepositoryMysql;
import com.stack.operation.stackpop.repository.psql.StackElementRepositoryPsql;

@Service
public class PopElement {
    
    Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    StackElementRepositoryMysql stackElementRepositoryMysql;
    @Autowired
    StackElementRepositoryPsql stackElementRepositoryPsql;
    
    @Autowired
    ResponseData responseData;

    public ResponseData pop(String datasource) throws Exception {
        responseData.setOldState(getOldStateFromDb(datasource));
        responseData.setNewState(getNewStateFromDb(datasource));
        responseData.setDatasource(datasource);
        return responseData;
    }

    private List<String> getNewStateFromDb(String datasource) {
        List<String> newStackState = new ArrayList<>();
        logger.info("PopElement():: getNewState {}", datasource);
        if ("psql".equalsIgnoreCase(datasource)) {
            for (StackData data : stackElementRepositoryPsql.findAllByOrderByIdDesc()) {
                System.out.println(data.getStackElements() + ": id is: " + data.getId());
                responseData.setPopedElement(data.getStackElements());
                stackElementRepositoryPsql.deleteById(data.getId());
                break;
            }
            for (StackData allData : stackElementRepositoryPsql.findAll()) {
                newStackState.add(allData.getStackElements());
            }
        } else {
            for (StackData data : stackElementRepositoryMysql.findAllByOrderByIdDesc()) {
                System.out.println(data.getStackElements() + ": id is: " + data.getId());
                responseData.setPopedElement(data.getStackElements());
                stackElementRepositoryMysql.deleteById(data.getId());
                break;
            }

            for (StackData allData : stackElementRepositoryMysql.findAll()) {
                newStackState.add(allData.getStackElements());
            }
        }

        return newStackState;
    }

    private List<String> getOldStateFromDb(String datasource) {
        List<String> oldStackState = new ArrayList<>();
        logger.info("PopElement():: getOldState {}", datasource);
        if ("psql".equalsIgnoreCase(datasource)) {
            for (StackData allData : stackElementRepositoryPsql.findAll()) {
                oldStackState.add(allData.getStackElements());
            }

        } else {
            for (StackData allData : stackElementRepositoryMysql.findAll()) {
                oldStackState.add(allData.getStackElements());
            }
        }
        return oldStackState;
    }

}


