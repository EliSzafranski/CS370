package edu.qc.seclass;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BuggyClassTestBC2 {
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
    public void buggyClassBC2_1(){
        double a = buggyClass.buggyMethod2(0,4);
    }
}
