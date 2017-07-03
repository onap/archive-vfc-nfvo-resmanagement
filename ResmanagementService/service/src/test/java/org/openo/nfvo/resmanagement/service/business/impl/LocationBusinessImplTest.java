/*
 * Copyright 2016 Huawei Technologies Co., Ltd.
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

package org.openo.nfvo.resmanagement.service.business.impl;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.nfvo.resmanagement.service.dao.impl.LocationDaoImpl;
import org.openo.nfvo.resmanagement.service.entity.LocationEntity;

import mockit.Mock;
import mockit.MockUp;

public class LocationBusinessImplTest {

    private LocationBusinessImpl locationBusinessImpl;

    @Before
    public void setUp() throws ServiceException {
        locationBusinessImpl = new LocationBusinessImpl();
        locationBusinessImpl.setLocationDao(new LocationDaoImpl());
    }

    @Test
    public void testGet() throws ServiceException {
        new MockUp<LocationDaoImpl>() {

            @Mock
            public LocationEntity getLocation(String id) {
                LocationEntity localEntity = new LocationEntity();
                localEntity.setCountry("country");
                localEntity.setLocation("location");

                return localEntity;
            }
        };
        assertTrue(locationBusinessImpl.getLocation("id") != null);
    }

    @Test
    public void testGetBranch() throws ServiceException {

        assertTrue(locationBusinessImpl.getLocation("") == null);
    }

    @Test(expected = ServiceException.class)
    public void testAddLocationSelectiveExceptio() throws ServiceException {
        locationBusinessImpl.addLocationSelective(null);
    }

    @Test
    public void testAddLocationSelective() throws ServiceException {
        new MockUp<LocationDaoImpl>() {

            @Mock
            public int addLocationSelective(LocationEntity locationEntity) {
                return 1;
            }
        };
        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setCountry("country");
        locationEntity.setDescription("description");
        locationEntity.setId("id");
        locationEntity.setLatitude("1");
        locationEntity.setLongitude("12");
        locationEntity.setLocation("location");
        locationBusinessImpl.addLocationSelective(locationEntity);
    }

    @Test
    public void testAddLocationSelectiveBranch() throws ServiceException {
        new MockUp<LocationDaoImpl>() {

            @Mock
            public int addLocationSelective(LocationEntity locationEntity) {
                return 1;
            }
        };
        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setCountry("country");
        locationEntity.setDescription("description");
        locationEntity.setId("");
        locationEntity.setLatitude("1");
        locationEntity.setLongitude("12");
        locationEntity.setLocation("location");
        locationBusinessImpl.addLocationSelective(locationEntity);
    }

    @Test
    public void testAddLocationSelectiveBranch1() throws ServiceException {
        new MockUp<LocationDaoImpl>() {

            @Mock
            public int addLocationSelective(LocationEntity locationEntity) {
                return 1;
            }
        };
        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setCountry("country");
        locationEntity.setDescription("description");
        locationEntity.setId(null);
        locationEntity.setLatitude("1");
        locationEntity.setLongitude("12");
        locationEntity.setLocation("location");
        locationBusinessImpl.addLocationSelective(locationEntity);
    }

    @Test(expected = ServiceException.class)
    public void testAddLocationSelectiveException1() throws ServiceException {
        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setCountry("");
        locationEntity.setDescription("description");
        locationEntity.setId("id");
        locationEntity.setLatitude("1");
        locationEntity.setLongitude("12");
        locationEntity.setLocation("location");
        locationBusinessImpl.addLocationSelective(locationEntity);
    }

    @Test(expected = ServiceException.class)
    public void testAddLocationSelectiveException2() throws ServiceException {
        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setCountry("country");
        locationEntity.setDescription("description");
        locationEntity.setId("id");
        locationEntity.setLatitude("1");
        locationEntity.setLongitude("12");
        locationEntity.setLocation("");
        locationBusinessImpl.addLocationSelective(locationEntity);
    }

    @Test(expected = ServiceException.class)
    public void testAddLocationSelectiveException3() throws ServiceException {
        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setCountry("country");
        locationEntity.setDescription("description");
        locationEntity.setId("id");
        locationEntity.setLatitude("");
        locationEntity.setLongitude("12");
        locationEntity.setLocation("location");
        locationBusinessImpl.addLocationSelective(locationEntity);
    }

    @Test(expected = ServiceException.class)
    public void testAddLocationSelectiveException4() throws ServiceException {
        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setCountry("country");
        locationEntity.setDescription("description");
        locationEntity.setId("id");
        locationEntity.setLatitude("1");
        locationEntity.setLongitude("");
        locationEntity.setLocation("location");
        locationBusinessImpl.addLocationSelective(locationEntity);
    }

    @Test(expected = ServiceException.class)
    public void testUpdateLocationSelective() throws ServiceException {
        locationBusinessImpl.updateLocationSelective(null);
    }

    @Test(expected = ServiceException.class)
    public void testAddLocation() throws ServiceException {
        locationBusinessImpl.addLocation(null);
    }

    @Test
    public void testUpdateLocation() throws ServiceException {
        new MockUp<LocationDaoImpl>() {

            @Mock
            public int updateLocation(LocationEntity locationEntity) {
                return 1;
            }
        };
        locationBusinessImpl.getLocationDao();
        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setLatitude("1");
        locationEntity.setLongitude("2");
        locationBusinessImpl.updateLocation(locationEntity);
    }

    @Test(expected = ServiceException.class)
    public void testUpdateLocationException() throws ServiceException {
        new MockUp<LocationDaoImpl>() {

            @Mock
            public int updateLocation(LocationEntity locationEntity) {
                return 1;
            }
        };
        locationBusinessImpl.getLocationDao();
        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setLatitude("100");
        locationEntity.setLongitude("2");
        locationBusinessImpl.updateLocation(locationEntity);
    }

    @Test(expected = ServiceException.class)
    public void testUpdateLocationException1() throws ServiceException {
        locationBusinessImpl.getLocationDao();
        locationBusinessImpl.updateLocation(null);
    }
}
