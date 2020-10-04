package com.stack.operation.stackpop.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stack.operation.stackpop.entity.ResponseData;
import com.stack.operation.stackpop.entity.StackData;
import com.stack.operation.stackpop.repository.mysql.StackElementRepositoryMysql;
import com.stack.operation.stackpop.repository.psql.StackElementRepositoryPsql;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class PopElementTest {

    @Mock
    StackElementRepositoryMysql stackElementRepositoryMysql;
    @Mock
    StackElementRepositoryPsql stackElementRepositoryPsql;
    @Mock
    ResponseData responseData;

    @InjectMocks
    PopElement popElement;

    @Test
    public void popTestMysql() throws Exception {
        when(stackElementRepositoryMysql.findAll()).thenReturn(getStackList());
        when(stackElementRepositoryMysql.findAllByOrderByIdDesc()).thenReturn(getStackList());

        popElement.pop("mysql");
    }

    @Test
    public void popTestPsql() throws Exception {
        when(stackElementRepositoryPsql.findAllByOrderByIdDesc()).thenReturn(getStackList());
        when(stackElementRepositoryPsql.findAll()).thenReturn(getStackList());

        popElement.pop("psql");
    }

    private List<StackData> getStackList() {
        StackData stackdata = new StackData();
        stackdata.setId(1L);
        stackdata.setPushData("3");
        stackdata.setStackElements("34");

        StackData stackdata1 = new StackData();
        stackdata1.setId(2L);
        stackdata1.setPushData("4");
        stackdata1.setStackElements("34");

        StackData stackdata3 = new StackData();
        stackdata3.setId(3L);
        stackdata3.setPushData("5");
        stackdata3.setStackElements("34");

        List<StackData> stackList = new ArrayList<StackData>();
        stackList.add(stackdata);
        stackList.add(stackdata1);
        stackList.add(stackdata3);
        return stackList;
    }

}
