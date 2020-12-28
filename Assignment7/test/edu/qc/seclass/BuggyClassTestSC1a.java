package edu.qc.seclass;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BuggyClassTestSC1a {
    private BuggyClass buggyClass;

    @Before
    public void setup(){
        buggyClass = new BuggyClass();
    }

    @After
    public void tearDown(){
        buggyClass = null;
    }

    @Test
    public void buggyClassSC1a1(){
        assertEquals(0,buggyClass.buggyMethod1(-5,10),0);
    }

    @Test
    public void buggyClassSC1a2(){
        assertEquals(1,buggyClass.buggyMethod1(10,10), 0);
    }

    @Test
    public void buggyClassSC1a3(){
        assertEquals(0, buggyClass.buggyMethod1(0,10), 0);
    }
}
