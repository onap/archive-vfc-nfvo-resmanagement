/*
 * Copyright 2016-2017 Huawei Technologies Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.openo.nfvo.resmanagement.common.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import org.junit.Test;

public class StringUtilTest {

    @Test
    public void testisValidString() {
        assertTrue(StringUtil.isValidString("abc"));
    }

    @Test
    public void testisValidString1() {
        assertFalse(StringUtil.isValidString(null));
    }

    @Test
    public void testisValidString2() {
        assertFalse(StringUtil.isValidString(""));
    }

    @Test
    public void testIsAnyLargeThanZero() {
        assertFalse(StringUtil.isAnyLargeThanZero(""));
    }

    @Test
    public void testIsAnyLargeThanZero1() {
        assertTrue(StringUtil.isAnyLargeThanZero("123"));
    }

    @Test
    public void testIsIntegerExceptions() {
        assertFalse(StringUtil.isInteger("asd"));
    }

    @Test
    public void testIsInteger() {
        assertTrue(StringUtil.isInteger("123"));
    }

    @Test
    public void testIsInteger1() {
        assertFalse(StringUtil.isInteger("-1"));
    }

    @Test
    public void testIsNumericExceptions() {
        assertFalse(StringUtil.isNumeric("abc"));
    }

    @Test
    public void testIsNumeric() {
        assertTrue(StringUtil.isNumeric("1.456"));
    }

    @Test
    public void testIsNumeric1() {
        assertFalse(StringUtil.isNumeric("-1.456"));
    }

    @Test
    public void testCompareZeroByFloat() {
        assertTrue(StringUtil.compareZeroByFloat("3.0", "1.0", "2.0"));
    }

    @Test
    public void testCompareZeroByFloat1() {
        assertFalse(StringUtil.compareZeroByFloat("3.0", "1.2", "2.5"));
    }

    @Test
    public void testCompareZeroByInteger() {
        assertTrue(StringUtil.compareZeroByInteger("3", "1", "2"));
    }

    @Test
    public void testCompareZeroByInteger1() {
        assertFalse(StringUtil.compareZeroByInteger("3", "1", "3"));
    }

    @Test
    public void testNumFormatDataIsNull() {
        String result = StringUtil.numFormat(null);
        assertEquals(null, result);
    }

    @Test
    public void testNumFormatDataIsEmpty() {
        String result = StringUtil.numFormat("");
        assertEquals(null, result);
    }

    @Test
    public void testNumFormatInteger() {
        String result = StringUtil.numFormat("12");
        String expectedResult = "12";
        assertEquals(expectedResult, result);
    }

    @Test
    public void testNumFormatFloat() {
        String result = StringUtil.numFormat("12.5");
        String expectedResult = "12.5";
        assertEquals(expectedResult, result);
    }

    @Test
    public void testCheckXss() {
        assertTrue(StringUtil.checkXss("123"));
    }
    @Test
    public void testPrivateConstructor() throws Exception {
        Constructor<StringUtil> constructor = StringUtil.class.getDeclaredConstructor();
        assertTrue("Constructor is not private", Modifier.isPrivate(constructor.getModifiers()));

        constructor.setAccessible(true);
        constructor.newInstance();
    }
}
