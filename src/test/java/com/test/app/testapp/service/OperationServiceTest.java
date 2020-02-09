package com.test.app.testapp.service;


import com.test.app.testapp.exceptions.InvalidDataException;
import com.test.app.testapp.model.request.TaskRequest;
import com.test.app.testapp.model.response.LogResponse;
import com.test.app.testapp.model.response.TaskResponse;
import com.test.app.testapp.repository.dto.UserSession;
import com.test.app.testapp.utils.UtilsTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;

import static org.junit.Assert.fail;

public class OperationServiceTest {

    @InjectMocks
    OperationService service;

    @Mock
    private ActionLogService logService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void execute() {
        Mockito.when(logService.log(Mockito.any(),Mockito.anyLong(),Mockito.any(UserSession.class))).thenReturn(true);
        doOp();
        Mockito.when(logService.log(Mockito.any(),Mockito.anyLong(),Mockito.any(UserSession.class))).thenReturn(false);
        doOp();
    }

    private void doOp() {
        UserSession sampleSession=new UserSession();
        TaskRequest req=new TaskRequest("ADD", Arrays.asList("2","3"));
        try {
            TaskResponse execute = service.execute(req, sampleSession);
            Assert.assertNotNull(execute.getOutput());
            Assert.assertEquals("5",execute.getOutput());
        } catch (InvalidDataException e) {
            fail("exception not expected");
        }
    }

    @Test(expected = InvalidDataException.class)
    public void executeExecption() throws InvalidDataException {
        service.execute(null,new UserSession());
        fail("did not throw execption");
    }

    @Test
    public void fetchHistory() {
        Mockito.when(logService.fetch(Mockito.anyInt())).thenReturn(UtilsTest.mockData());

        UserSession userSession = new UserSession();
        userSession.setId(23);

        LogResponse logResponse = service.fetchHistory(userSession);
        Assert.assertNotNull(logResponse);
        Assert.assertFalse(CollectionUtils.isEmpty(logResponse.getLogs()));
        Assert.assertEquals(3,logResponse.getLogs().size());
    }
}