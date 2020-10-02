package com.stack.operation.stackpop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stack.operation.stackpop.entity.StackData;

public interface StackElementRepository extends JpaRepository<StackData, Long>  {
//    public List<StackData> findAllByIdDesc();
    public List<StackData> findAllByOrderByIdDesc();
}

