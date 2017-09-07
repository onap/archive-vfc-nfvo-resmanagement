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

package org.onap.vfc.nfvo.resmanagement.service.rest;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.onap.vfc.nfvo.resmanagement.common.util.request.RequestUtil;
import org.onap.vfc.nfvo.resmanagement.service.entity.VnfStatusEntity;
import org.onap.vfc.nfvo.resmanagement.service.group.impl.VnfStatusServiceImpl;
import org.onap.vfc.nfvo.resmanagement.service.group.inf.VnfStatusService;
import org.onap.vfc.nfvo.resmanagement.service.rest.VnfStatusRoa;
import org.onap.vfc.nfvo.resmanagement.common.util.restclient.ServiceException;
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
 * @version VFC 1.0 Feb 10, 2017
 */
public class VnfStatusRoaTest {

    private VnfStatusRoa roa;

    private VnfStatusService vnfStatusService;

    @Before
    public void setUp() {
        roa = new VnfStatusRoa();
        vnfStatusService = new VnfStatusServiceImpl();
        roa.setVnfStatusService(vnfStatusService);
    }

    @Test
    public void testGetVnfStatuss() throws ServiceException {
        new MockUp<VnfStatusServiceImpl>() {

            @Mock
            public List<VnfStatusEntity> getList(Map<String, Object> map) throws ServiceException {
                return new ArrayList<VnfStatusEntity>();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.getVnfStatuss(mock);
        assertNotNull(result);
    }

    @Test
    public void testGetVnfStatus() throws ServiceException {
        new MockUp<VnfStatusServiceImpl>() {

            @Mock
            public List<VnfStatusEntity> getList(Map<String, Object> map) throws ServiceException {
                return new ArrayList<VnfStatusEntity>();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.getVnfStatus(mock, "id");
        assertNotNull(result);
    }

    @Test
    public void testAddVnfStatus() throws ServiceException {
        new MockUp<RequestUtil>() {

            @Mock
            public JSONObject getJsonRequestBody(HttpServletRequest context) {
                return new JSONObject();
            }
        };
        new MockUp<VnfStatusServiceImpl>() {

            @Mock
            public JSONObject addVnfStatus(JSONObject object) throws ServiceException {
                return new JSONObject();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.addVnfStatus(mock);
        assertNotNull(result);
    }

    @Test
    public void testAddVnfStatusByException() throws ServiceException {
        new MockUp<RequestUtil>() {

            @Mock
            public JSONObject getJsonRequestBody(HttpServletRequest context) {
                return new JSONObject();
            }
        };
        new MockUp<VnfStatusServiceImpl>() {

            @Mock
            public JSONObject addVnfStatus(JSONObject object) throws ServiceException {
                throw new ServiceException();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.addVnfStatus(mock);
        assertNotNull(result);
    }

    @Test(expected = ServiceException.class)
    public void testAddVnfStatusByNull() throws ServiceException {
        new MockUp<RequestUtil>() {

            @Mock
            public JSONObject getJsonRequestBody(HttpServletRequest context) {
                return null;
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        roa.addVnfStatus(mock);
    }

    @Test
    public void testDeleteVnfStatus() throws ServiceException {
        new MockUp<VnfStatusServiceImpl>() {

            @Mock
            public int delete(String id) throws ServiceException {
                return 1;
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.deleteVnfStatus(mock, "id");
        assertNotNull(result);
    }

    @Test
    public void testDeleteVnfStatusByException() throws ServiceException {
        new MockUp<VnfStatusServiceImpl>() {

            @Mock
            public int delete(String id) throws ServiceException {
                throw new ServiceException();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.deleteVnfStatus(mock, "id");
        assertNotNull(result);
    }

    @Test(expected = ServiceException.class)
    public void testDeleteVnfStatusByNull() throws ServiceException {
        HttpServletRequest mock = new MockHttpServletRequest();
        roa.deleteVnfStatus(mock, null);
    }

}
