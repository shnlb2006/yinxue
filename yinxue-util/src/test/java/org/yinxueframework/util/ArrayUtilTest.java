package org.yinxueframework.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ArrayUtilTest {

    @Test
    public void convertToArray() {

        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("helli");
        list.add("world");
        Assert.assertEquals("[hello, helli, world]",
                Arrays.toString(ArrayUtil.convertToArray(list)));
    }
}