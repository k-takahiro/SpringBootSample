package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

public class PostNumberTest {
    static PostNumber target;

    @BeforeClass
    public static void init() {
        target = new PostNumber("979-0201");
    }
    @Test
    public void testGetFormatedValue() {
        assertEquals("979-0201", target.getFormatedValue());
        assertTrue("979-0201".equals(target.getFormatedValue())); // 検証を書き換え
        assertNotNull(target.getFormatedValue());
        assertNotNull("target.getFormatedValue()がnullです。", target.getFormatedValue());
//        assertNull("target.getFormatedValue()がnullです。", target.getFormatedValue());
    }

    @Test
    public void testGetValue() {
        assertEquals("9790201", target.getValue());
        assertFalse(!"9790201".equals(target.getValue()));
        assertNotNull(target.getValue());
        assertNotNull("target.getValue()がnullです。", target.getValue());
        
    }

    @Test
    public void testValueOf() {

    }
}
