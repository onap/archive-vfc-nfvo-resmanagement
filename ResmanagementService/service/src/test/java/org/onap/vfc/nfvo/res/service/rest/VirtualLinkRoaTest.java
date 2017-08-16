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
import org.onap.vfc.nfvo.res.service.entity.VirtualLinkEntity;
import org.onap.vfc.nfvo.res.service.group.impl.VirtualLinkServiceImpl;
import org.onap.vfc.nfvo.res.service.group.inf.VirtualLinkService;
import org.onap.vfc.nfvo.res.service.rest.VirtualLinkRoa;
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
public class VirtualLinkRoaTest {

    private VirtualLinkRoa roa;

    private VirtualLinkService virtualLink;

    @Before
    public void setUp() {
        roa = new VirtualLinkRoa();
        virtualLink = new VirtualLinkServiceImpl();
        roa.setVirtualLink(virtualLink);
    }

    @Test
    public void testGetVls() throws ServiceException {
        new MockUp<VirtualLinkServiceImpl>() {

            @Mock
            public List<VirtualLinkEntity> getList(Map<String, Object> map) throws ServiceException {
                return new ArrayList<VirtualLinkEntity>();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.getVls(mock);
        assertNotNull(result);
    }

    @Test
    public void testGetVl() throws ServiceException {
        new MockUp<VirtualLinkServiceImpl>() {

            @Mock
            public List<VirtualLinkEntity> getList(Map<String, Object> map) throws ServiceException {
                return new ArrayList<VirtualLinkEntity>();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.getVl(mock, "id");
        assertNotNull(result);
    }

    @Test
    public void testAddVl() throws ServiceException {
        new MockUp<RequestUtil>() {

            @Mock
            public JSONObject getJsonRequestBody(HttpServletRequest context) {
                return new JSONObject();
            }
        };
        new MockUp<VirtualLinkEntity>() {

            @Mock
            public VirtualLinkEntity toEntity(JSONObject jsonObject) {
                return new VirtualLinkEntity();
            }
        };
        new MockUp<VirtualLinkServiceImpl>() {

            @Mock
            public JSONObject addVl(VirtualLinkEntity virtualLinkEntity) throws ServiceException {
                return new JSONObject();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.addVl(mock);
        assertNotNull(result);
    }

    @Test
    public void testAddVlByException() throws ServiceException {
        new MockUp<RequestUtil>() {

            @Mock
            public JSONObject getJsonRequestBody(HttpServletRequest context) {
                return new JSONObject();
            }
        };
        new MockUp<VirtualLinkEntity>() {

            @Mock
            public VirtualLinkEntity toEntity(JSONObject jsonObject) {
                return new VirtualLinkEntity();
            }
        };
        new MockUp<VirtualLinkServiceImpl>() {

            @Mock
            public JSONObject addVl(VirtualLinkEntity virtualLinkEntity) throws ServiceException {
                throw new ServiceException();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.addVl(mock);
        assertNotNull(result);
    }

    @Test(expected = ServiceException.class)
    public void testAddVlByExceptionByNull() throws ServiceException {
        new MockUp<RequestUtil>() {

            @Mock
            public JSONObject getJsonRequestBody(HttpServletRequest context) {
                return null;
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        roa.addVl(mock);
    }

    @Test
    public void testDeleteVl() throws ServiceException {
        new MockUp<VirtualLinkServiceImpl>() {

            @Mock
            public int delete(String id) throws ServiceException {
                return 1;
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.deleteVl(mock, "id");
        assertNotNull(result);
    }

    @Test
    public void testDeleteVlByException() throws ServiceException {
        new MockUp<VirtualLinkServiceImpl>() {

            @Mock
            public int delete(String id) throws ServiceException {
                throw new ServiceException();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.deleteVl(mock, "id");
        assertNotNull(result);
    }

    @Test(expected = ServiceException.class)
    public void testDeleteVlByNull() throws ServiceException {
        HttpServletRequest mock = new MockHttpServletRequest();
        roa.deleteVl(mock, null);
    }

}
