package com.test.app.testapp.utils;

import com.test.app.testapp.model.response.LogResponse;
import com.test.app.testapp.repository.dto.ActionLog;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UtilsTest {

    @Test
    public void mapToResponse() {
        LogResponse data = Utils.mapToResponse(mockData());
        Assert.assertNotNull(data.getLogs());
        Assert.assertEquals(3,data.getLogs().size());

    }

    public static List<ActionLog> mockData() {

        ArrayList<ActionLog> actionLogs = new ArrayList<>();
        ActionLog d1=new ActionLog();
        d1.setUserId("88");
        d1.setRequest("12,7,1");
        d1.setOperation("ADD");
        d1.setTime(System.currentTimeMillis());
        d1.setResponse("20");
        actionLogs.add(d1);

        ActionLog d2=new ActionLog();
        d2.setUserId("88");
        d2.setRequest("3,8");
        d2.setOperation("ADD");
        d2.setTime(System.currentTimeMillis());
        d2.setResponse("11");
        actionLogs.add(d2);

        ActionLog d3=new ActionLog();
        d3.setUserId("88");
        d3.setRequest("7,-77");
        d3.setOperation("ADD");
        d3.setTime(System.currentTimeMillis());
        d3.setResponse("-70");
        actionLogs.add(d3);

        return actionLogs;
    }
}