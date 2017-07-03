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

package org.openo.nfvo.resmanagement.common.util.request;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.cxf.jaxrs.impl.HttpServletRequestFilter;
import org.junit.Test;

import javassist.Modifier;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class RequestUtilTest {

    @Test
    public void testGetStringRequestBody() {
        HttpServletRequestFilter context = new MockUp<HttpServletRequestFilter>() {

            @Mocked
            ServletInputStream input;

            @Mock
            public ServletInputStream getInputStream() throws IOException {
                return input;
            }
        }.getMockInstance();
        new MockUp<IOUtils>() {

            String data = "{\"NETWORK\":{\"id\": \"123\"}}";

            @Mock
            public String toString(InputStream input) throws IOException {
                return data;
            }
        };
        String result = RequestUtil.getStringRequestBody(context);
        String expectedResult = "{\"NETWORK\":{\"id\": \"123\"}}";
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetStringRequestBodyException() {
        HttpServletRequestFilter context = new MockUp<HttpServletRequestFilter>() {

            @Mock
            public ServletInputStream getInputStream() throws IOException {
                throw new IOException();
            }
        }.getMockInstance();
        String result = RequestUtil.getStringRequestBody(context);
        String expectedResult = null;
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetJsonRequestBody() {
        HttpServletRequestFilter context = new MockUp<HttpServletRequestFilter>() {

            @Mocked
            ServletInputStream input;

            @Mock
            public ServletInputStream getInputStream() throws IOException {
                return input;
            }
        }.getMockInstance();
        new MockUp<IOUtils>() {

            String data = "{\"NETWORK\":{\"id\": \"123\"}}";

            @Mock
            public String toString(InputStream input) throws IOException {
                return data;
            }
        };
        JSONObject result = RequestUtil.getJsonRequestBody(context);
        String data1 = "{\"NETWORK\":{\"id\": \"123\"}}";
        JSONObject expectedResult = JSONObject.fromObject(data1);
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetJsonRequestBody1() {
        new MockUp<RequestUtil>() {

            String data1 = "{\"NETWORK\":{\"id\": \"123\"}}";

            @Mock
            public String getStringRequestBody(HttpServletRequest context) {
                return data1;
            }
        };
        JSONObject result = RequestUtil.getJsonRequestBody(null);
        String data1 = "{\"NETWORK\":{\"id\": \"123\"}}";
        JSONObject expectedResult = JSONObject.fromObject(data1);
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetJsonRequestBodyException() {
        HttpServletRequestFilter context = new MockUp<HttpServletRequestFilter>() {

            @Mocked
            ServletInputStream input;

            @Mock
            public ServletInputStream getInputStream() throws JSONException {
                throw new JSONException();
            }
        }.getMockInstance();
        JSONObject result = RequestUtil.getJsonRequestBody(context);
        JSONObject expectedResult = null;
        assertEquals(expectedResult, result);
    }

    @SuppressWarnings("rawtypes")
    @Test
    public void testGetAllJsonRequestBodyRequestBodyIsNull() {
        HttpServletRequestFilter context = new MockUp<HttpServletRequestFilter>() {

            @Mocked
            ServletInputStream input;

            @Mock
            public ServletInputStream getInputStream() throws IOException {
                return input;
            }

            @Mock
            public Enumeration getHeaderNames() {
                return new Enumeration() {

                    List<String> a = Arrays.asList(new String[] { "1", "2" });

                    @Override
                    public boolean hasMoreElements() {
                        return false;
                    }

                    @Override
                    public Object nextElement() {
                        return null;
                    }

                };
            }

        }.getMockInstance();
        new MockUp<RequestUtil>() {

            @Mock
            public JSONObject getJsonRequestBody(HttpServletRequest context) {
                return null;
            }
        };
        JSONObject result = RequestUtil.getAllJsonRequestBody(context);
        JSONObject expectedResult = new JSONObject();
        expectedResult.put("header", new HashMap<String, String>());
        assertEquals(expectedResult, result);
    }

    @SuppressWarnings("rawtypes")
    @Test
    public void testGetContextHeader() {
        HttpServletRequestFilter context = new MockUp<HttpServletRequestFilter>() {

            @Mock
            public String getHeader(String name) {
                return "1";
            }

            @Mock
            public Enumeration getHeaderNames() {
                return new Enumeration() {

                    List<String> a = Arrays.asList(new String[] { "1", "2" });

                    int count = 1;

                    @Override
                    public boolean hasMoreElements() {
                        if (count == 1) {
                            count += 1;
                            return true;
                        } else
                            return false;
                    }

                    @Override
                    public Object nextElement() {
                        return "1";
                    }

                };
            }

        }.getMockInstance();
        new MockUp<RequestUtil>() {

            @Mock
            public JSONObject getJsonRequestBody(HttpServletRequest context) {
                return null;
            }
        };
        JSONObject result = RequestUtil.getAllJsonRequestBody(context);
        JSONObject expectedResult = new JSONObject();
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "1");
        expectedResult.put("header", map);
        assertEquals(expectedResult, result);
    }
    @Test
    public void testPrivateConstructor() throws Exception {
        Constructor constructor = RequestUtil.class.getDeclaredConstructor();
        assertTrue("Constructor is  private", Modifier.isPrivate(constructor.getModifiers()));

        constructor.setAccessible(true);
        constructor.newInstance();
    }

}
