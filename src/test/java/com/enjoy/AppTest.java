package com.enjoy;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.HashMap;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }


    @Test
    public void play(){
        HashMap<String,String> map=new HashMap<>();
        map.put("1","2");
        map.put("1","3");
        System.out.println(map.get("1"));
    }
}
