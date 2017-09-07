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
import org.onap.vfc.nfvo.resmanagement.service.entity.VmEntity;
import org.onap.vfc.nfvo.resmanagement.service.group.impl.VmServiceImpl;
import org.onap.vfc.nfvo.resmanagement.service.group.inf.VmService;
import org.onap.vfc.nfvo.resmanagement.service.rest.VmRoa;
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
 * @version VFC 1.0 Feb 9, 2017
 */
public class VmRoaTest {

    private VmRoa roa;

    private VmService vmService;

    @Before
    public void setUp() {
        roa = new VmRoa();
        vmService = new VmServiceImpl();
        roa.setVmService(vmService);
    }

    @Test
    public void testGetVms() throws ServiceException {
        new MockUp<VmServiceImpl>() {

            @Mock
            public List<VmEntity> getList(Map<String, Object> map) throws ServiceException {
                return new ArrayList<VmEntity>();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.getVms(mock);
        assertNotNull(result);
    }

    @Test
    public void testGetVm() throws ServiceException {
        new MockUp<VmServiceImpl>() {

            @Mock
            public List<VmEntity> getList(Map<String, Object> map) throws ServiceException {
                return new ArrayList<VmEntity>();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.getVm(mock, "id");
        assertNotNull(result);
    }

    @Test
    public void testAddVm() throws ServiceException {
        new MockUp<RequestUtil>() {

            @Mock
            public JSONObject getJsonRequestBody(HttpServletRequest context) {
                return new JSONObject();
            }
        };
        new MockUp<VmEntity>() {

            @Mock
            public VmEntity toEntity(JSONObject jsonObject) {
                return new VmEntity();
            }
        };
        new MockUp<VmServiceImpl>() {

            @Mock
            public JSONObject addVm(VmEntity vmEntity) throws ServiceException {
                return new JSONObject();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.addVm(mock);
        assertNotNull(result);
    }

    @Test
    public void testAddVmByException() throws ServiceException {
        new MockUp<RequestUtil>() {

            @Mock
            public JSONObject getJsonRequestBody(HttpServletRequest context) {
                return new JSONObject();
            }
        };
        new MockUp<VmEntity>() {

            @Mock
            public VmEntity toEntity(JSONObject jsonObject) {
                return new VmEntity();
            }
        };
        new MockUp<VmServiceImpl>() {

            @Mock
            public JSONObject addVm(VmEntity vmEntity) throws ServiceException {
                throw new ServiceException();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.addVm(mock);
        assertNotNull(result);
    }

    @Test(expected = ServiceException.class)
    public void testAddVmByNull() throws ServiceException {
        new MockUp<RequestUtil>() {

            @Mock
            public JSONObject getJsonRequestBody(HttpServletRequest context) {
                return null;
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        roa.addVm(mock);
    }

    @Test
    public void testDeleteVm() throws ServiceException {
        new MockUp<VmServiceImpl>() {

            @Mock
            public int delete(String id) throws ServiceException {
                return 1;
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.deleteVm(mock, "id");
        assertNotNull(result);
    }

    @Test
    public void testDeleteVmByException() throws ServiceException {
        new MockUp<VmServiceImpl>() {

            @Mock
            public int delete(String id) throws ServiceException {
                throw new ServiceException();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.deleteVm(mock, "id");
        assertNotNull(result);
    }

    @Test(expected = ServiceException.class)
    public void testDeleteVmByNull() throws ServiceException {
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.deleteVm(mock, null);
        assertNotNull(result);
    }

}
