package com.stack.operation.stackpop.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.stack.operation.stackpop.entity.ResponseData;
import com.stack.operation.stackpop.entity.StackData;
import com.stack.operation.stackpop.service.PopElement;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class PopStackElementControllerTest {

    @Mock
    PopElement popElement;
    
    @InjectMocks
    PopStackElementController controller;
    
    @Test
    public void testSuccessScenario() throws Exception {
        StackData stackdata = new StackData();
        stackdata.setId(1L);
        stackdata.setPushData("12");
        stackdata.setStackElements("13");
        List<StackData> stackDataList = new ArrayList<StackData>();
        List<String> listNew = Arrays.asList(new String[] { "12", "13"});
        List<String> listOld = Arrays.asList(new String[] { "12", "13", "14"});
        stackDataList.add(stackdata);

        ResponseData responseData = new ResponseData();
        responseData.setDatasource("mysql");
        responseData.setNewState(listNew);
        responseData.setOldState(listOld);
        responseData.setPopedElement("14");
        
        Mockito.when(popElement.pop("mysql")).thenReturn(responseData);
        ResponseEntity<ResponseData> responseEntity = controller.popElementMysql("mysql");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    
    @Test(expected = Exception.class)
    public void testFailure() throws Exception {
        Mockito.when(popElement.pop(null)).thenThrow(Exception.class);
        controller.popElementMysql(null);
    }
}
