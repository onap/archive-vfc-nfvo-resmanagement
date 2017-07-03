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

package org.openo.nfvo.resmanagement.service.base.openstack.impl;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.nfvo.resmanagement.service.business.impl.LocationBusinessImpl;
import org.openo.nfvo.resmanagement.service.business.impl.SitesBusinessImpl;
import org.openo.nfvo.resmanagement.service.dao.impl.LocationDaoImpl;
import org.openo.nfvo.resmanagement.service.entity.LocationEntity;
import org.openo.nfvo.resmanagement.service.entity.SitesEntity;

import mockit.Mock;
import mockit.MockUp;
import net.sf.json.JSONObject;

public class LocationImplTest {

    private LocationImpl locationImpl;

    @Before
    public void setUp() throws ServiceException {
        locationImpl = new LocationImpl();
        LocationBusinessImpl locationBusinessImpl = new LocationBusinessImpl();
        locationBusinessImpl.setLocationDao(new LocationDaoImpl());
        SitesImpl sitesImpl = new SitesImpl();
        sitesImpl.setSitesBusiness(new SitesBusinessImpl());
        locationImpl.setSites(sitesImpl);
        locationImpl.setLocationBusiness(locationBusinessImpl);

    }

    @Test
    public void testLocationImpl() throws ServiceException {
        new MockUp<LocationBusinessImpl>() {

            @Mock
            public List<LocationEntity> getLocations(Map<String, Object> condition) throws ServiceException {
                List<LocationEntity> list = new ArrayList<>();
                LocationEntity localEntity = new LocationEntity();

                list.add(localEntity);
                return null;
            }
        };
        new MockUp<LocationDaoImpl>() {

            @Mock
            public int addLocation(LocationEntity locationEntity) {
                return 1;
            }
        };
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", "id");
        jsonObject.put("country", "country");
        jsonObject.put("location", "location");
        jsonObject.put("latitude", "3");
        jsonObject.put("longitude", "12");
        jsonObject.put("description", "description");
        assertTrue(locationImpl.add(jsonObject) == 1);
    }

    @Test
    public void testLocationImplBranch() throws ServiceException {
        new MockUp<LocationBusinessImpl>() {

            @Mock
            public List<LocationEntity> getLocations(Map<String, Object> condition) throws ServiceException {
                List<LocationEntity> list = new ArrayList<>();

                // list.add(localEntity);
                return list;
            }
        };
        new MockUp<LocationDaoImpl>() {

            @Mock
            public int addLocation(LocationEntity locationEntity) {
                return 1;
            }
        };
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", "");
        jsonObject.put("country", "country");
        jsonObject.put("location", "location");
        jsonObject.put("latitude", "90");
        jsonObject.put("longitude", "180");
        jsonObject.put("description", "description");
        assertTrue(locationImpl.add(jsonObject) == 1);
    }

    @Test
    public void testLocationImplBranch1() throws ServiceException {
        new MockUp<LocationBusinessImpl>() {

            @Mock
            public List<LocationEntity> getLocations(Map<String, Object> condition) throws ServiceException {
                List<LocationEntity> list = new ArrayList<>();
                LocationEntity localEntity = new LocationEntity();

                list.add(localEntity);
                return null;
            }
        };
        new MockUp<LocationDaoImpl>() {

            @Mock
            public int addLocation(LocationEntity locationEntity) {
                return 1;
            }
        };
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", null);
        jsonObject.put("country", "country");
        jsonObject.put("location", "location");
        jsonObject.put("latitude", "3");
        jsonObject.put("longitude", "12");
        jsonObject.put("description", "description");
        assertTrue(locationImpl.add(jsonObject) == 1);
    }

    @Test(expected = ServiceException.class)
    public void testLocationImplException() throws ServiceException {
        new MockUp<LocationBusinessImpl>() {

            @Mock
            public List<LocationEntity> getLocations(Map<String, Object> condition) throws ServiceException {
                List<LocationEntity> list = new ArrayList<>();
                LocationEntity localEntity = new LocationEntity();

                list.add(localEntity);
                return list;
            }
        };

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", "2");
        jsonObject.put("country", "country");
        jsonObject.put("location", "location");
        jsonObject.put("latitude", "3");
        jsonObject.put("longitude", "12");
        jsonObject.put("description", "description");
        locationImpl.add(jsonObject);
    }

    @Test(expected = ServiceException.class)
    public void testLocationImplException1() throws ServiceException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", null);
        jsonObject.put("country", "");
        jsonObject.put("location", "location");
        jsonObject.put("latitude", "81");
        jsonObject.put("longitude", "12");
        jsonObject.put("description", "description");
        locationImpl.add(jsonObject);

    }

    @Test(expected = ServiceException.class)
    public void testLocationImplException2() throws ServiceException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", null);
        jsonObject.put("country", "country");
        jsonObject.put("location", "");
        jsonObject.put("latitude", "81");
        jsonObject.put("longitude", "12");
        jsonObject.put("description", "description");
        locationImpl.add(jsonObject);
    }

    @Test(expected = ServiceException.class)
    public void testLocationImplException3() throws ServiceException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", null);
        jsonObject.put("country", "country");
        jsonObject.put("location", "location");
        jsonObject.put("latitude", "");
        jsonObject.put("longitude", "12");
        jsonObject.put("description", "description");
        locationImpl.add(jsonObject);
    }

    @Test(expected = ServiceException.class)
    public void testLocationImplException4() throws ServiceException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", null);
        jsonObject.put("country", "country");
        jsonObject.put("location", "location");
        jsonObject.put("latitude", "latitude");
        jsonObject.put("longitude", "");
        jsonObject.put("description", "description");
        locationImpl.add(jsonObject);
    }

    @Test(expected = ServiceException.class)
    public void testLocationImplException5() throws ServiceException {
        new MockUp<LocationBusinessImpl>() {

            @Mock
            public List<LocationEntity> getLocations(Map<String, Object> condition) throws ServiceException {
                List<LocationEntity> list = new ArrayList<>();
                LocationEntity localEntity = new LocationEntity();

                list.add(localEntity);
                return null;
            }
        };
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", "id");
        jsonObject.put("country", "country");
        jsonObject.put("location", "location");
        jsonObject.put("latitude", "95");
        jsonObject.put("longitude", "185");
        jsonObject.put("description", "description");
        locationImpl.add(jsonObject);
    }

    @Test(expected = ServiceException.class)
    public void testLocationImplException6() throws ServiceException {
        new MockUp<LocationBusinessImpl>() {

            @Mock
            public List<LocationEntity> getLocations(Map<String, Object> condition) throws ServiceException {
                List<LocationEntity> list = new ArrayList<>();
                LocationEntity localEntity = new LocationEntity();

                list.add(localEntity);
                return null;
            }
        };
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", "id");
        jsonObject.put("country", "country");
        jsonObject.put("location", "location");
        jsonObject.put("latitude", "80");
        jsonObject.put("longitude", "185");
        jsonObject.put("description", "description");
        locationImpl.add(jsonObject);
    }

    @Test(expected = ServiceException.class)
    public void testLocationImplException7() throws ServiceException {
        new MockUp<LocationBusinessImpl>() {

            @Mock
            public List<LocationEntity> getLocations(Map<String, Object> condition) throws ServiceException {
                List<LocationEntity> list = new ArrayList<>();
                LocationEntity localEntity = new LocationEntity();

                list.add(localEntity);
                return null;
            }
        };
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", "id");
        jsonObject.put("country", "country");
        jsonObject.put("location", "location");
        jsonObject.put("latitude", "95");
        jsonObject.put("longitude", "175");
        jsonObject.put("description", "description");
        locationImpl.add(jsonObject);
    }

    @Test
    public void testUpdate() throws ServiceException {
        new MockUp<LocationDaoImpl>() {

            @Mock
            public LocationEntity getLocation(String id) {
                LocationEntity localEntity = new LocationEntity();
                localEntity.setCountry("country");
                localEntity.setLocation("location");

                return localEntity;
            }
        };
        new MockUp<LocationDaoImpl>() {

            @Mock
            public int updateLocationSelective(LocationEntity locationEntity) {
                return 1;
            }
        };
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", "id");
        jsonObject.put("country", "country");
        jsonObject.put("location", "location");
        jsonObject.put("latitude", "14");
        jsonObject.put("longitude", "12");
        jsonObject.put("description", "description");
        locationImpl.update(jsonObject);
    }

    @Test(expected = ServiceException.class)
    public void testUpdateException() throws ServiceException {

        new MockUp<LocationDaoImpl>() {

            @Mock
            public int updateLocationSelective(LocationEntity locationEntity) {
                return 1;
            }
        };
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", "id");
        jsonObject.put("country", "country");
        jsonObject.put("location", "location");
        jsonObject.put("latitude", "91");
        jsonObject.put("longitude", "12");
        jsonObject.put("description", "description");
        locationImpl.update(jsonObject);
    }

    @Test(expected = ServiceException.class)
    public void testUpdateException1() throws ServiceException {
        new MockUp<LocationDaoImpl>() {

            @Mock
            public LocationEntity getLocation(String id) {
                LocationEntity localEntity = new LocationEntity();
                localEntity.setCountry("countryNew");
                localEntity.setLocation("location");

                return localEntity;
            }
        };

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", "id");
        jsonObject.put("country", "country");
        jsonObject.put("location", "location");
        jsonObject.put("latitude", "10");
        jsonObject.put("longitude", "12");
        jsonObject.put("description", "description");
        locationImpl.update(jsonObject);
    }

    @Test(expected = ServiceException.class)
    public void testUpdateException2() throws ServiceException {
        new MockUp<LocationDaoImpl>() {

            @Mock
            public LocationEntity getLocation(String id) {
                LocationEntity localEntity = new LocationEntity();
                localEntity.setCountry("country");
                localEntity.setLocation("locationNew");

                return localEntity;
            }
        };
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", "id");
        jsonObject.put("country", "country");
        jsonObject.put("location", "location");
        jsonObject.put("latitude", "10");
        jsonObject.put("longitude", "12");
        jsonObject.put("description", "description");
        locationImpl.update(jsonObject);
    }

    @Test
    public void testDelete() throws ServiceException {

        new MockUp<LocationDaoImpl>() {

            @Mock
            public int deleteLocation(String id) {
                return 1;
            }
        };
        assertTrue(locationImpl.delete("location") == 1);
    }

    @Test(expected = ServiceException.class)
    public void testDeleteException() throws ServiceException {

        locationImpl.delete("");
    }

    @Test
    public void testComputeSite() {
        JSONObject total = new JSONObject();
        total.put("vcpus", "12");
        total.put("memory", "23");
        total.put("disk", "23");
        JSONObject used = new JSONObject();
        used.put("vcpus", "12");
        used.put("memory", "23");
        used.put("disk", "23");
        locationImpl.getLocationBusiness();

        assertTrue(locationImpl.computingSite(total, used) != null);
    }

    @Test
    public void testGetLocation() throws ServiceException {
        new MockUp<LocationDaoImpl>() {

            @Mock
            public List<LocationEntity> getLocations(Map<String, Object> condition) {
                List<LocationEntity> list = new ArrayList<>();
                LocationEntity localEntity = new LocationEntity();
                localEntity.setCountry("country");
                localEntity.setLocation("locationNew");
                list.add(localEntity);
                return list;
            }
        };
        assertTrue(locationImpl.getLocation(new HashMap<>()) != null);
    }

    @Test
    public void testGetLocationBranch() throws ServiceException {
        new MockUp<LocationDaoImpl>() {

            @Mock
            public List<LocationEntity> getLocations(Map<String, Object> condition) {
                List<LocationEntity> list = new ArrayList<>();
                LocationEntity localEntity = new LocationEntity();
                localEntity.setCountry("country");
                localEntity.setLocation("locationNew");
                return list;
            }
        };
        locationImpl.getLocation(new HashMap<>());
    }

    @Test
    public void testGetLocationInfo() throws ServiceException {
        new MockUp<SitesBusinessImpl>() {

            @Mock
            public List<SitesEntity> getSites(Map<String, Object> condition) {
                List<SitesEntity> siteList = new ArrayList<>();
                SitesEntity site = new SitesEntity();

                siteList.add(site);
                return siteList;
            }
        };

        new MockUp<JSONObject>() {

            @Mock
            public JSONObject getJSONObject(String key) {
                JSONObject total = new JSONObject();
                total.put("vcpus", "12");
                total.put("memory", "23");
                total.put("disk", "23");
                return total;
            }
        };
        List<LocationEntity> locationInfo = new ArrayList<>();
        LocationEntity localEntity = new LocationEntity();
        locationInfo.add(localEntity);
        locationImpl.getLocationInfo(locationInfo);
    }

    @Test
    public void testGet() throws ServiceException {
        new MockUp<LocationBusinessImpl>() {

            @Mock
            public List<LocationEntity> getLocations(Map<String, Object> condition) throws ServiceException {
                List<LocationEntity> list = new ArrayList<>();
                LocationEntity localEntity = new LocationEntity();

                list.add(localEntity);
                return null;
            }
        };
        locationImpl.get(new HashMap<>());
        assertTrue(locationImpl.get("id") != null);
    }

}
