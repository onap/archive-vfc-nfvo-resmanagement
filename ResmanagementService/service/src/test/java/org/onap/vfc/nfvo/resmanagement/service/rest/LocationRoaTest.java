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
import org.onap.vfc.nfvo.resmanagement.service.base.openstack.impl.LocationImpl;
import org.onap.vfc.nfvo.resmanagement.service.base.openstack.impl.SitesImpl;
import org.onap.vfc.nfvo.resmanagement.service.base.openstack.inf.Location;
import org.onap.vfc.nfvo.resmanagement.service.base.openstack.inf.Sites;
import org.onap.vfc.nfvo.resmanagement.service.entity.LocationEntity;
import org.onap.vfc.nfvo.resmanagement.service.entity.SitesEntity;
import org.onap.vfc.nfvo.resmanagement.service.rest.LocationRoa;
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
 * @version NFVO 0.5 Feb 9, 2017
 */
public class LocationRoaTest {

    private LocationRoa roa;

    private Location location;

    private Sites sites;

    @Before
    public void setUp() {
        roa = new LocationRoa();
        location = new LocationImpl();
        sites = new SitesImpl();
        roa.setLocation(location);
        roa.setSites(sites);
    }

    @Test
    public void testGetLocationsbase() throws ServiceException {
        new MockUp<LocationImpl>() {

            @Mock
            public List<LocationEntity> get(Map<String, Object> condition) throws ServiceException {
                return new ArrayList<LocationEntity>();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.getLocationsbase(mock);
        assertNotNull(result);
    }

    @Test
    public void testGetLocationbase() throws ServiceException {
        new MockUp<LocationImpl>() {

            @Mock
            public List<LocationEntity> get(Map<String, Object> condition) throws ServiceException {
                return new ArrayList<LocationEntity>();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.getLocationbase(mock, "id");
        assertNotNull(result);
    }

    @Test
    public void testGetCountry() throws ServiceException {
        new MockUp<LocationImpl>() {

            @Mock
            public List<String> getCountry() throws ServiceException {
                return new ArrayList<String>();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.getCountry(mock);
        assertNotNull(result);
    }

    @Test
    public void testGetLocationByCountry() throws ServiceException {
        new MockUp<LocationImpl>() {

            @Mock
            public List<String> getLocationByCountry(Map<String, Object> condition) throws ServiceException {
                return new ArrayList<String>();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.getLocationByCountry(mock, "country");
        assertNotNull(result);
    }

    @Test
    public void testGetLocation() throws ServiceException {
        new MockUp<LocationImpl>() {

            @Mock
            public List<LocationEntity> get(Map<String, Object> condition) throws ServiceException {
                return new ArrayList<LocationEntity>();
            }

            @Mock
            public List<JSONObject> getLocationInfo(List<LocationEntity> locationInfo) throws ServiceException {
                return new ArrayList<JSONObject>();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.getLocation(mock, "locations");
        assertNotNull(result);
    }

    @Test
    public void testAddLocation() throws ServiceException {
        new MockUp<RequestUtil>() {

            @Mock
            public JSONObject getJsonRequestBody(HttpServletRequest context) {
                return new JSONObject();
            }
        };
        new MockUp<LocationImpl>() {

            @Mock
            public int add(JSONObject jsonObject) throws ServiceException {
                return 1;
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.addLocation(mock);
        assertNotNull(result);
    }

    @Test
    public void testAddLocationByException() throws ServiceException {
        new MockUp<RequestUtil>() {

            @Mock
            public JSONObject getJsonRequestBody(HttpServletRequest context) {
                return new JSONObject();
            }
        };
        new MockUp<LocationImpl>() {

            @Mock
            public int add(JSONObject jsonObject) throws ServiceException {
                throw new ServiceException();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.addLocation(mock);
        assertNotNull(result);
    }

    @Test
    public void testDeleteLocationbase() throws ServiceException {
        new MockUp<LocationImpl>() {

            @Mock
            public int delete(String location) throws ServiceException {
                return 1;
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.deleteLocationbase(mock, "locations");
        assertNotNull(result);
    }

    @Test
    public void testDeleteLocationbaseByException() throws ServiceException {
        new MockUp<LocationImpl>() {

            @Mock
            public int delete(String location) throws ServiceException {
                throw new ServiceException();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.deleteLocationbase(mock, "locations");
        assertNotNull(result);
    }

    @Test
    public void testDeleteLocation() throws ServiceException {
        new MockUp<RequestUtil>() {

            @Mock
            public JSONObject getJsonRequestBody(HttpServletRequest context) {
                JSONObject object = new JSONObject();
                object.put("location", "location");
                object.put("id", "id");
                return object;
            }
        };
        new MockUp<SitesImpl>() {

            @Mock
            public SitesEntity get(Map<String, Object> condition) throws ServiceException {
                return null;
            }
        };
        new MockUp<LocationImpl>() {

            @Mock
            public int delete(String location) throws ServiceException {
                return 1;
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.deleteLocation(mock);
        assertNotNull(result);
    }

    @Test
    public void testDeleteLocationByException() throws ServiceException {
        new MockUp<RequestUtil>() {

            @Mock
            public JSONObject getJsonRequestBody(HttpServletRequest context) {
                JSONObject object = new JSONObject();
                object.put("location", "location");
                object.put("id", "id");
                return object;
            }
        };
        new MockUp<SitesImpl>() {

            @Mock
            public SitesEntity get(Map<String, Object> condition) throws ServiceException {
                return null;
            }
        };
        new MockUp<LocationImpl>() {

            @Mock
            public int delete(String location) throws ServiceException {
                throw new ServiceException();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.deleteLocation(mock);
        assertNotNull(result);
    }

    @Test
    public void testDeleteLocationBySitesEntity() throws ServiceException {
        new MockUp<RequestUtil>() {

            @Mock
            public JSONObject getJsonRequestBody(HttpServletRequest context) {
                JSONObject object = new JSONObject();
                object.put("location", "location");
                object.put("id", "id");
                return object;
            }
        };
        new MockUp<SitesImpl>() {

            @Mock
            public SitesEntity get(Map<String, Object> condition) throws ServiceException {
                return new SitesEntity();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.deleteLocation(mock);
        assertNotNull(result);
    }

    @Test
    public void testUpdateLocation() throws ServiceException {
        new MockUp<RequestUtil>() {

            @Mock
            public JSONObject getJsonRequestBody(HttpServletRequest context) {
                JSONObject object = new JSONObject();
                object.put("location", "location");
                return object;
            }
        };
        new MockUp<SitesImpl>() {

            @Mock
            public SitesEntity get(Map<String, Object> condition) throws ServiceException {
                return null;
            }
        };
        new MockUp<LocationImpl>() {

            @Mock
            public int update(JSONObject jsonObject) throws ServiceException {
                return 1;
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.updateLocation(mock);
        assertNotNull(result);
    }

    @Test
    public void testUpdateLocationByException() throws ServiceException {
        new MockUp<RequestUtil>() {

            @Mock
            public JSONObject getJsonRequestBody(HttpServletRequest context) {
                JSONObject object = new JSONObject();
                object.put("location", "location");
                return object;
            }
        };
        new MockUp<SitesImpl>() {

            @Mock
            public SitesEntity get(Map<String, Object> condition) throws ServiceException {
                return null;
            }
        };
        new MockUp<LocationImpl>() {

            @Mock
            public int update(JSONObject jsonObject) throws ServiceException {
                throw new ServiceException();
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.updateLocation(mock);
        assertNotNull(result);
    }

    @Test
    public void testUpdateLocationBySitesEntity() throws ServiceException {
        new MockUp<RequestUtil>() {

            @Mock
            public JSONObject getJsonRequestBody(HttpServletRequest context) {
                JSONObject object = new JSONObject();
                object.put("location", "location");
                return object;
            }
        };
        new MockUp<SitesImpl>() {

            @Mock
            public SitesEntity get(Map<String, Object> condition) throws ServiceException {
                return new SitesEntity();
            }
        };
        new MockUp<LocationImpl>() {

            @Mock
            public int update(JSONObject jsonObject) throws ServiceException {
                return 1;
            }
        };
        HttpServletRequest mock = new MockHttpServletRequest();
        JSONObject result = roa.updateLocation(mock);
        assertNotNull(result);
    }

}
