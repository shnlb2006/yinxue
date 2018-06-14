package org.yinxueframework.util;


import org.junit.Assert;
import org.junit.Test;

public class ValidateUtilTest {

    @Test
    public void testIsEmpty() throws Exception {
        boolean flag = ValidateUtil.isEmpty(new StringBuffer("  "));
        Assert.assertEquals(flag, true);
    }

    @Test
    public void testIsNotEmpty() throws Exception {
    }

    @Test
    public void testIsEmpty1() throws Exception {
    }

    @Test
    public void testIsNotEmpty1() throws Exception {
    }
}