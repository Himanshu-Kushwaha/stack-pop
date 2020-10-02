package com.stack.operation.stackpop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stack.operation.stackpop.entity.ResponseData;
import com.stack.operation.stackpop.entity.StackData;
import com.stack.operation.stackpop.repository.StackElementRepository;

@Service
public class PopElement {
    
    @Autowired
    StackElementRepository stackElementRepository;
    
    @Autowired
    ResponseData responseData;

    public ResponseData pop() {

        List<Integer> oldStackState = new ArrayList<>();
        List<Integer> newStackState = new ArrayList<>();
        
        for (StackData allData : stackElementRepository.findAll()) {
            oldStackState.add(allData.getStackElements());
        }
        responseData.setOldState(oldStackState);
        
        for(StackData data: stackElementRepository.findAllByOrderByIdDesc()) {
            System.out.println(data.getStackElements()+": id is: "+data.getId());
            responseData.setPopedElement(data.getStackElements());
            stackElementRepository.deleteById(data.getId());
            break;
        }
        
        for (StackData allData : stackElementRepository.findAll()) {
            newStackState.add(allData.getStackElements());
        }
        responseData.setNewState(newStackState);
        
        return responseData;
    }

}

