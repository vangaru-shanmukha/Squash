package com.zemoso.githubtask.assignment.services;

import com.zemoso.githubtask.assignment.services.impl.PostfixCalculationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class CalculationServiceTest {

    private CalculationService calculationService;

    @Before
    public void setup(){
        calculationService = new PostfixCalculationService();
    }

    @Test
    public void testResultInt(){
        String query = "";
        Assert.assertEquals(0, calculationService.calculateInt(query));
    }

    @Test
    public void testResultFloat(){
        String query = "";
        Assert.assertEquals(0f, calculationService.calculateFloat(query), 0f);
    }

    @Test
    public void testResultLong(){
        String query = "";
        Assert.assertEquals(0, calculationService.calculateLong(query));
    }

    @Test
    public void testResultDouble(){
        String query = "";
        Assert.assertEquals(0, calculationService.calculateDouble(query), 0);
    }

}
