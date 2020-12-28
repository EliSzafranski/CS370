package edu.qc.seclass;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class BuggyClassTestSC1b {
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
    public void buggyClassSC1b1(){
        buggyClass.buggyMethod1(-5,0);
    }
}
