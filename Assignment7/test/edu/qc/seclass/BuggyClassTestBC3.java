package edu.qc.seclass;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BuggyClassTestBC3 {
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
    public void buggyClassBC3_1(){
        assertEquals(0.0, buggyClass.buggyMethod3(-5,5), 0);
    }

    @Test
    public void buggyClassBC3_2(){
        assertEquals(0, buggyClass.buggyMethod3(5,5), 0);
    }

}
