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

import mockit.Mock;
import mockit.MockUp;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtilTest {

    @Test
    public void testGetJsonFieldStr() {
        JSONObject jsonObj = new JSONObject();
        String fieldName = "a";
        jsonObj.put("a", "1");
        jsonObj.put("b", "2");
        String result = JsonUtil.getJsonFieldStr(jsonObj, fieldName);
        String expectedResult = "1";
        assertEquals(expectedResult, result);

    }

    @Test
    public void testGetJsonFieldInt() {
        JSONObject jsonObj = new JSONObject();
        String fieldName = "a";
        jsonObj.put("a", "1");
        jsonObj.put("b", "2");
        int result = JsonUtil.getJsonFieldInt(jsonObj, fieldName);
        int expectedResult = 1;
        assertEquals(expectedResult, result);

    }

    @Test
    public void testGetJsonFieldArr() {
        JSONObject jsonObj = new JSONObject();
        String fieldName = "a";
        jsonObj.put("a", new JSONArray());
        jsonObj.put("b", "2");
        JSONArray result = JsonUtil.getJsonFieldArr(jsonObj, fieldName);
        JSONArray expectedResult = new JSONArray();
        assertEquals(expectedResult, result);

    }

    @Test
    public void testGetJsonFieldJson() {
        JSONObject jsonObj = new JSONObject();
        String fieldName = "a";
        jsonObj.put("a", new JSONObject());
        jsonObj.put("b", "2");
        JSONObject result = JsonUtil.getJsonFieldJson(jsonObj, fieldName);
        JSONObject expectedResult = new JSONObject();
        assertEquals(expectedResult, result);

    }

    @Test
    public void testGetJsonFieldLong() {
        JSONObject jsonObj = new JSONObject();
        String fieldName = "a";
        jsonObj.put("a", 1);
        jsonObj.put("b", 2);
        Long result = JsonUtil.getJsonFieldLong(jsonObj, fieldName);
        Long expectedResult = new Long(1);
        assertEquals(expectedResult, result);

    }

    @Test
    public void testGetJsonFieldObjectException() {
        JSONObject jsonObj = new JSONObject();
        String fieldName = "a";
        jsonObj.put("a", "1");
        jsonObj.put("b", "2");
        JSONObject result = JsonUtil.getJsonFieldJson(jsonObj, fieldName);
        JSONObject expectedResult = null;
        assertEquals(expectedResult, result);

    }

    @Test
    public void testIsNullJson1() {
        JSONObject jsonObj = new JSONObject();
        assertTrue(JsonUtil.isNullJson(jsonObj));
    }

    @Test
    public void testIsNullJson2() {
        assertTrue(JsonUtil.isNullJson(null));
    }

    @Test
    public void testIsNullJson3() {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("a", "1");
        assertFalse(JsonUtil.isNullJson(jsonObj));
    }

    @Test
    public void testGetStrValueByjsonNULL() {
        JSONObject jsonObj = new JSONObject();
        String key = "a";
        String result = JsonUtil.getStrValueByjson(jsonObj, key);
        String expectedResult = null;
        assertEquals(expectedResult, result);

    }

    @Test
    public void testGetStrValueByjson() {
        JSONObject jsonObj = new JSONObject();
        String key = "a";
        jsonObj.put("a", "1");
        jsonObj.put("b", "2");
        new MockUp<JSONObject>() {

            @SuppressWarnings("static-access")
            @Mock
            public JSONObject optJSONObject(String key) {
                return new JSONObject().fromObject("{\"a\":\"1\"}");
            }

            @Mock
            public JSONObject getJSONObject(String key) {
                return new JSONObject();
            }
        };
        String result = JsonUtil.getStrValueByjson(jsonObj, key);
        String expectedResult = "1";
        assertEquals(expectedResult, result);

    }

    @Test
    public void testGetStrValueByjson1() {
        JSONObject jsonObj = new JSONObject();
        String key = "a";
        jsonObj.put("a", "1");
        jsonObj.put("b", "2");
        new MockUp<JSONObject>() {

            @Mock
            public JSONObject optJSONObject(String key) {
                return null;
            }

            @SuppressWarnings("static-access")
            @Mock
            public JSONArray optJSONArray(String key) {
                return new JSONArray().fromObject("[\"a\",\"1\"]");
            }

            @Mock
            public JSONArray getJSONArray(String key) {
                return new JSONArray();
            }
        };
        String result = JsonUtil.getStrValueByjson(jsonObj, key);
        String expectedResult = "1";
        assertEquals(expectedResult, result);

    }

    @Test
    public void testGetStrValueByjson2() {
        JSONObject jsonObj = new JSONObject();
        String key = "a";
        jsonObj.put("a", "1");
        jsonObj.put("b", "2");
        String result = JsonUtil.getStrValueByjson(jsonObj, key);
        String expectedResult = "1";
        assertEquals(expectedResult, result);

    }

    @Test
    public void testGetStrValueByJArray() {
        JSONObject jsonObj = new JSONObject();
        String key = "a";
        jsonObj.put("a", "1");
        jsonObj.put("b", "2");
        new MockUp<JSONObject>() {

            @Mock
            public JSONObject optJSONObject(String key) {
               return null;
            }

            @SuppressWarnings("static-access")
            @Mock
            public JSONArray optJSONArray(String key) {
                return new JSONArray().fromObject("[\"a\",\"1\"]");
            }

            @SuppressWarnings("static-access")
            @Mock
            public JSONArray getJSONArray(String key) {
                return new JSONArray().fromObject("[\"a\",\"1\"]");
            }


        };

        String result = JsonUtil.getStrValueByjson(jsonObj, key);

    }

    @Test
    public void testGetStrValueByJArray1() {
        JSONObject jsonObj = new JSONObject();
        String key = "a";
        jsonObj.put("a", "1");
        jsonObj.put("b", "2");
        new MockUp<JSONObject>() {

            int count = 1;

            @SuppressWarnings("static-access")
            @Mock
            public JSONObject optJSONObject(String key) {
                if (count == 1) {
                    count += 1;
                    return null;
                } else
                    return new JSONObject().fromObject("{\"a\":\"1\"}");
            }

            @SuppressWarnings("static-access")
            @Mock
            public JSONArray optJSONArray(String key) {
                return new JSONArray().fromObject("[\"a\",\"1\"]");
            }

            @SuppressWarnings("static-access")
            @Mock
            public JSONArray getJSONArray(String key) {
                return new JSONArray().fromObject("[\"a\",\"1\"]");
            }
        };
        String result = JsonUtil.getStrValueByjson(jsonObj, key);
        String expectedResult = "1";
        assertEquals(expectedResult, result);

    }

    @Test
    public void testGetJsonValueByjson() {
        JSONObject jsonObj = new JSONObject();
        String key = "a";
        jsonObj.put("a", "1");
        jsonObj.put("b", "2");
        String result = JsonUtil.getJsonValueByjson(jsonObj, key).toString();
        String expectedResult = "{\"a\":\"1\"}";
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetJsonValueByjsonResultIsNull() {
        JSONObject jsonObj = new JSONObject();
        String key = "c";
        jsonObj.put("a", "1");
        jsonObj.put("b", "2");
        JSONObject result = JsonUtil.getJsonValueByjson(jsonObj, key);
        String expectedResult = null;
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetStrValueByJsonParentKeyIsNull() {
        JSONObject jsonObj = new JSONObject();
        String key = "a";
        jsonObj.put("a", "1");
        jsonObj.put("b", "2");
        String parentKey = "";
        String result = JsonUtil.getStrValueByJson(jsonObj, parentKey, key);
        String expectedResult = "1";
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetStrValueByJsonParentJsonIsNull() {
        JSONObject jsonObj = new JSONObject();
        String key = "a";
        jsonObj.put("a", "1");
        jsonObj.put("b", "2");
        String parentKey = "b";
        new MockUp<JsonUtil>() {

            @Mock
            public JSONObject getJsonValueByjson(JSONObject json, String key) {
                return new JSONObject();
            }
        };
        String result = JsonUtil.getStrValueByJson(jsonObj, parentKey, key);
        String expectedResult = null;
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetStrValueByJson() {
        JSONObject jsonObj = new JSONObject();
        String key = "a";
        jsonObj.put("a", "1");
        jsonObj.put("b", "2");
        String parentKey = "b";
        String result = JsonUtil.getStrValueByJson(jsonObj, parentKey, key);
        String expectedResult = null;
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetResponseDataRetcodeError1() {
        new MockUp<JsonUtil>() {

            @Mock
            public Integer getJsonFieldInt(JSONObject jsonObj, String fieldName) {
                return null;
            }
        };
        JSONObject result = JsonUtil.getResponseData(null);
        String expectedResult = null;
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetResponseDataRetcodeError2() {
        new MockUp<JsonUtil>() {

            @Mock
            public Integer getJsonFieldInt(JSONObject jsonObj, String fieldName) {
                return -1;
            }
        };
        JSONObject result = JsonUtil.getResponseData(null);
        String expectedResult = null;
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetResponseDataResultIsEmpty() {
        JSONObject obj = new JSONObject();
        obj.put("data", "1");
        obj.put("retCode", "1");
        JSONObject result = JsonUtil.getResponseData(obj);
        String expectedResult = null;
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetResponseData() {
        JSONObject obj = new JSONObject();
        obj.put("data", new JSONObject());
        obj.put("retCode", "1");
        new MockUp<JSONObject>() {

            @SuppressWarnings("static-access")
            @Mock
            public JSONObject optJSONObject(String key) {
                return new JSONObject().fromObject("{\"a\":\"1\"}");
            }
        };
        JSONObject result = JsonUtil.getResponseData(obj);
        String expectedResult = null;
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetResponseData1() {
        JSONObject obj = new JSONObject();
        obj.put("data", JSONArray.fromObject("[{\"a\":\"1\"},\"1\"]"));
        obj.put("retCode", "1");
        new MockUp<JSONObject>() {

            @Mock
            public JSONObject optJSONObject(String key) {
                return null;
            }

            @SuppressWarnings("static-access")
            @Mock
            public JSONArray optJSONArray(String key) {
                return new JSONArray().fromObject("[\"a\",\"1\"]");
            }
        };
        JSONObject result = JsonUtil.getResponseData(obj);
        String expectedResult = "{\"a\":\"1\"}";
        assertEquals(expectedResult, result.toString());
    }

    @Test
    public void testGetResponseData2() {
        JSONObject obj = new JSONObject();
        JSONObject json = new JSONObject();
        json.put("retCode", "1");
        obj.put("data", json);
        obj.put("retCode", "1");
        JSONObject result = JsonUtil.getResponseData(obj);
        String expectedResult = null;
        assertEquals(expectedResult, result);
    }
    @Test
    public void testPrivateConstructor() throws Exception {
        Constructor constructor = JsonUtil.class.getDeclaredConstructor();
        assertTrue("Constructor is not private", Modifier.isPrivate(constructor.getModifiers()));

        constructor.setAccessible(true);
        constructor.newInstance();
    }
}
