/*
 * Copyright 2017 Huawei Technologies Co., Ltd.
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

package org.onap.vfc.nfvo.res.service.rest;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.onap.vfc.nfvo.res.common.util.request.RequestUtil;
import org.onap.vfc.nfvo.res.service.entity.VnfInfoEntity;
import org.onap.vfc.nfvo.res.service.group.impl.VnfInfoServiceImpl;
import org.onap.vfc.nfvo.res.service.group.inf.VnfInfoService;
import org.onap.vfc.nfvo.res.service.rest.VnfInfoRoa;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.springframework.mock.web.MockHttpServletRequest;

import mockit.Mock;
import mockit.MockUp;
import net.sf.json.JSONObject;

/**
 * <br>
 * <p>
 * </p>
 * 
 * @author
 * @version NFVO 0.5 Feb 9, 2017
 */
public class VnfInfoRoaTest {

    private VnfInfoRoa roa;

    private VnfInfoService vnfInfoService;

    @Before
    public void setUp() {
        roa = new VnfInfoRoa();
        vnfInfoService = new VnfInfoServiceImpl();
        roa.setVnfInfoService(vnfInfoService);
    }

    @Test
    public void testGetVnfInfos() throws ServiceException {
        new MockUp<VnfInfoServiceImpl>() {

            @Mock
            public List<VnfInfoEntity> getList(Map<String, Object> map) throws ServiceException {
                return new ArrayList<VnfInfoEntity>();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.getVnfInfos(mock);
        assertNotNull(result);
    }

    @Test
    public void testGetVnfInfo() throws ServiceException {
        new MockUp<VnfInfoServiceImpl>() {

            @Mock
            public List<VnfInfoEntity> getList(Map<String, Object> map) throws ServiceException {
                return new ArrayList<VnfInfoEntity>();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.getVnfInfo(mock, "id");
        assertNotNull(result);
    }

    @Test
    public void testAddVnfInfo() throws ServiceException {
        new MockUp<RequestUtil>() {

            @Mock
            public JSONObject getJsonRequestBody(HttpServletRequest context) {
                return new JSONObject();
            }
        };
        new MockUp<VnfInfoServiceImpl>() {

            @Mock
            public JSONObject addVnfInfo(JSONObject object) throws ServiceException {
                return new JSONObject();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.addVnfInfo(mock);
        assertNotNull(result);
    }

    @Test
    public void testAddVnfInfoByException() throws ServiceException {
        new MockUp<RequestUtil>() {

            @Mock
            public JSONObject getJsonRequestBody(HttpServletRequest context) {
                return new JSONObject();
            }
        };
        new MockUp<VnfInfoServiceImpl>() {

            @Mock
            public JSONObject addVnfInfo(JSONObject object) throws ServiceException {
                throw new ServiceException();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.addVnfInfo(mock);
        assertNotNull(result);
    }

    @Test(expected = ServiceException.class)
    public void testAddVnfInfoByNull() throws ServiceException {
        new MockUp<RequestUtil>() {

            @Mock
            public JSONObject getJsonRequestBody(HttpServletRequest context) {
                return null;
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        roa.addVnfInfo(mock);
    }

    @Test
    public void testDeleteVnfInfo() throws ServiceException {
        new MockUp<VnfInfoServiceImpl>() {

            @Mock
            public int delete(String id) throws ServiceException {
                return 1;
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.deleteVnfInfo(mock, "id");
        assertNotNull(result);
    }

    @Test
    public void testDeleteVnfInfoByException() throws ServiceException {
        new MockUp<VnfInfoServiceImpl>() {

            @Mock
            public int delete(String id) throws ServiceException {
                throw new ServiceException();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.deleteVnfInfo(mock, "id");
        assertNotNull(result);
    }

    @Test(expected = ServiceException.class)
    public void testDeleteVnfInfoByNull() throws ServiceException {
        HttpServletRequest mock = new MockHttpServletRequest();
        roa.deleteVnfInfo(mock, null);
    }

}
