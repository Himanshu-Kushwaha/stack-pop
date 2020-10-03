package com.stack.operation.stackpop.repository.mysql;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stack.operation.stackpop.entity.StackData;

public interface StackElementRepositoryMysql extends JpaRepository<StackData, Long>  {
    public List<StackData> findAllByOrderByIdDesc();
}


