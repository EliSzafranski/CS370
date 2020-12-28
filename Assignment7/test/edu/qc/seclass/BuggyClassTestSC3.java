package edu.qc.seclass;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class BuggyClassTestSC3 {
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
    public void buggyClassSC3_1(){
        double a = buggyClass.buggyMethod3(-5,0);
    }
}
