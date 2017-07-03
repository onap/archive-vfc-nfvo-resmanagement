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

package org.openo.nfvo.resmanagement.service.rest;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.nfvo.resmanagement.common.util.request.RequestUtil;
import org.openo.nfvo.resmanagement.service.entity.VnfEntity;
import org.openo.nfvo.resmanagement.service.group.impl.VnfServiceImpl;
import org.openo.nfvo.resmanagement.service.group.inf.VnfService;
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
 * @version NFVO 0.5 Feb 10, 2017
 */
public class VnfRoaTest {

    private VnfRoa roa;

    private VnfService vnfService;

    @Before
    public void setUp() {
        roa = new VnfRoa();
        vnfService = new VnfServiceImpl();
        roa.setVnfService(vnfService);
    }

    @Test
    public void testGetVnfs() throws ServiceException {
        new MockUp<VnfServiceImpl>() {

            @Mock
            public List<VnfEntity> getList(Map<String, Object> map) throws ServiceException {
                return new ArrayList<VnfEntity>();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.getVnfs(mock);
        assertNotNull(result);
    }

    @Test
    public void testGetVnf() throws ServiceException {
        new MockUp<VnfServiceImpl>() {

            @Mock
            public List<VnfEntity> getList(Map<String, Object> map) throws ServiceException {
                return new ArrayList<VnfEntity>();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.getVnf(mock, "id");
        assertNotNull(result);
    }

    @Test
    public void testAddVnf() throws ServiceException {
        new MockUp<RequestUtil>() {

            @Mock
            public JSONObject getJsonRequestBody(HttpServletRequest context) {
                return new JSONObject();
            }
        };
        new MockUp<VnfEntity>() {

            @Mock
            public VnfEntity toEntity(JSONObject jsonObject) {
                return new VnfEntity();
            }
        };
        new MockUp<VnfServiceImpl>() {

            @Mock
            public JSONObject addVnf(VnfEntity vnfEntity) throws ServiceException {
                return new JSONObject();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.addVnf(mock);
        assertNotNull(result);
    }

    @Test
    public void testAddVnfByException() throws ServiceException {
        new MockUp<RequestUtil>() {

            @Mock
            public JSONObject getJsonRequestBody(HttpServletRequest context) {
                return new JSONObject();
            }
        };
        new MockUp<VnfEntity>() {

            @Mock
            public VnfEntity toEntity(JSONObject jsonObject) {
                return new VnfEntity();
            }
        };
        new MockUp<VnfServiceImpl>() {

            @Mock
            public JSONObject addVnf(VnfEntity vnfEntity) throws ServiceException {
                throw new ServiceException();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.addVnf(mock);
        assertNotNull(result);
    }

    @Test(expected = ServiceException.class)
    public void testAddVnfByNull() throws ServiceException {
        new MockUp<RequestUtil>() {

            @Mock
            public JSONObject getJsonRequestBody(HttpServletRequest context) {
                return null;
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        roa.addVnf(mock);
    }

    @Test
    public void testDeleteVnf() throws ServiceException {
        new MockUp<VnfServiceImpl>() {

            @Mock
            public int delete(String id) throws ServiceException {
                return 1;
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.deleteVnf(mock, "id");
        assertNotNull(result);
    }

    @Test
    public void testDeleteVnfByException() throws ServiceException {
        new MockUp<VnfServiceImpl>() {

            @Mock
            public int delete(String id) throws ServiceException {
                throw new ServiceException();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.deleteVnf(mock, "id");
        assertNotNull(result);
    }

    @Test(expected = ServiceException.class)
    public void testDeleteVnfByNull() throws ServiceException {
        HttpServletRequest mock = new MockHttpServletRequest();
        roa.deleteVnf(mock, null);
    }
}
