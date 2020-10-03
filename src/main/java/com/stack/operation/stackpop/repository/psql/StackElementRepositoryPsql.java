package com.stack.operation.stackpop.repository.psql;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stack.operation.stackpop.entity.StackData;

public interface StackElementRepositoryPsql extends JpaRepository<StackData, Long> {
    public List<StackData> findAllByOrderByIdDesc();
}

