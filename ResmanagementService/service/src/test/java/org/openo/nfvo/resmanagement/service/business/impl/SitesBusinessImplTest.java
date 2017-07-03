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

package org.openo.nfvo.resmanagement.service.business.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.nfvo.resmanagement.service.dao.impl.SitesDaoImpl;
import org.openo.nfvo.resmanagement.service.entity.SitesEntity;

import mockit.Mock;
import mockit.MockUp;

/**
 * <br/>
 * <p>
 * </p>
 *
 * @author
 * @version NFVO 0.5 2016年8月16日
 */
public class SitesBusinessImplTest {

    private SitesBusinessImpl sitesBusinessImpl;

    @Before
    public void setUp() {
        sitesBusinessImpl = new SitesBusinessImpl();
        sitesBusinessImpl.setSitesDao(new SitesDaoImpl());
    }

    @Test
    public void testGetSitesIdIsNull() throws ServiceException {
        try {
            sitesBusinessImpl.getSite(null);
        } catch(ServiceException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testGetSite() throws ServiceException {
        SitesEntity sitesEntity = new SitesEntity();
        sitesEntity.setId("123");
        new MockUp<SitesDaoImpl>() {

            @Mock
            public SitesEntity getSite(String id) {
                SitesEntity sitesEntity = new SitesEntity();
                sitesEntity.setId("123");
                return sitesEntity;
            }
        };
        SitesEntity result = sitesBusinessImpl.getSite("id");
        SitesEntity expectedResult = sitesEntity;
        assertEquals(expectedResult.toString(), result.toString());
    }

    @Test
    public void testGetSites() throws ServiceException {
        Map<String, Object> condition = new HashMap<String, Object>();
        SitesBusinessImpl sitesBusinessImpl = new SitesBusinessImpl();
        sitesBusinessImpl.setSitesDao(new SitesDaoImpl());
        new MockUp<SitesDaoImpl>() {

            @Mock
            public List<SitesEntity> getSites(Map<String, Object> condition) {
                return null;
            }
        };
        List<SitesEntity> result = sitesBusinessImpl.getSites(condition);
        assertNull(result);
    }

    @Test
    public void testDeleteSiteEmpty() throws ServiceException {
        SitesBusinessImpl sitesBusinessImpl = new SitesBusinessImpl();
        sitesBusinessImpl.setSitesDao(new SitesDaoImpl());
        try {
            sitesBusinessImpl.deleteSite("");
        } catch(ServiceException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testDeleteSite() throws ServiceException {
        new MockUp<SitesDaoImpl>() {

            @Mock
            public SitesEntity getSite(String id) {
                return null;
            }

            @Mock
            public int deleteSite(String id) {
                return 1;
            }
        };
        int result = sitesBusinessImpl.deleteSite("007");
        int expectedResult = 1;
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAddSiteSitesEntityIsNull() throws ServiceException {
        try {
            sitesBusinessImpl.addSite(null);
        } catch(ServiceException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testCheckSiteExceptions() throws ServiceException {
        SitesEntity sitesEntity = new SitesEntity();
        sitesEntity.setCountry("country");
        sitesEntity.setId("id");
        sitesEntity.setLocation("location");
        sitesEntity.setName("name");
        sitesEntity.setStatus("siteStatus");
        sitesEntity.setTotalCPU("12");
        sitesEntity.setTotalDisk("12");
        sitesEntity.setTotalMemory("134");
        sitesEntity.setUsedCPU("12");
        sitesEntity.setUsedDisk("23");
        sitesEntity.setUsedMemory("10");
        sitesEntity.setVimId("siteVimId");
        sitesEntity.setVimName("siteVimName");
        new MockUp<SitesDaoImpl>() {

            int count1 = 0;

            @Mock
            public SitesEntity getSite(String id) {
                return null;
            }

            @Mock
            public List<SitesEntity> getSites(Map<String, Object> condition) {
                if(count1 == 0) {
                    count1 += 1;
                    return null;
                }
                return new ArrayList<SitesEntity>();

            }
        };
        new MockUp<SitesEntity>() {

            @Mock
            public boolean checkResource(SitesEntity siteEntity) {
                return false;
            }
        };
        try {
            sitesBusinessImpl.addSite(sitesEntity);
        } catch(ServiceException se) {
            assertTrue(true);
        }
    }

    @Test
    public void testCheckSiteExceptions1() throws ServiceException {
        SitesEntity sitesEntity = new SitesEntity();
        sitesEntity.setCountry("country");
        sitesEntity.setId("id");
        sitesEntity.setLocation("location");
        sitesEntity.setName("name");
        sitesEntity.setStatus("siteStatus");
        sitesEntity.setTotalCPU("12");
        sitesEntity.setTotalDisk("12");
        sitesEntity.setTotalMemory("134");
        sitesEntity.setUsedCPU("12");
        sitesEntity.setUsedDisk("23");
        sitesEntity.setUsedMemory("10");
        sitesEntity.setVimId("siteVimId");
        sitesEntity.setVimName("siteVimName");
        new MockUp<SitesDaoImpl>() {

            int count1 = 0;

            @Mock
            public SitesEntity getSite(String id) {
                return null;
            }

            @Mock
            public List<SitesEntity> getSites(Map<String, Object> condition) {

                return null;

            }
        };
        new MockUp<SitesEntity>() {

            @Mock
            public boolean checkResource(SitesEntity siteEntity) {
                return false;
            }
        };
        try {
            sitesBusinessImpl.addSite(sitesEntity);
        } catch(ServiceException se) {
            assertTrue(true);
        }
    }

    @Test
    public void testCheckSiteExceptions2() throws ServiceException {
        SitesEntity sitesEntity = new SitesEntity();
        sitesEntity.setCountry("");
        sitesEntity.setId("id");
        sitesEntity.setLocation("");
        sitesEntity.setName("");
        sitesEntity.setStatus("siteStatus");
        sitesEntity.setTotalCPU("12");
        sitesEntity.setTotalDisk("12");
        sitesEntity.setTotalMemory("134");
        sitesEntity.setUsedCPU("12");
        sitesEntity.setUsedDisk("23");
        sitesEntity.setUsedMemory("10");
        sitesEntity.setVimId("siteVimId");
        sitesEntity.setVimName("siteVimName");
        new MockUp<SitesDaoImpl>() {

            int count1 = 0;

            @Mock
            public SitesEntity getSite(String id) {
                return null;
            }

            @Mock
            public List<SitesEntity> getSites(Map<String, Object> condition) {

                return null;

            }
        };
        new MockUp<SitesEntity>() {

            @Mock
            public boolean checkResource(SitesEntity siteEntity) {
                return false;
            }
        };
        try {
            sitesBusinessImpl.addSite(sitesEntity);
        } catch(ServiceException se) {
            assertTrue(true);
        }
    }

    @Test
    public void testCheckSiteNameExceptions() throws ServiceException {
        SitesEntity sitesEntity = new SitesEntity();
        sitesEntity.setId("123");
        sitesEntity.setCountry("china");
        sitesEntity.setName("openstack");
        sitesEntity.setLocation("xian");
        new MockUp<SitesDaoImpl>() {

            @Mock
            public SitesEntity getSite(String id) {
                return null;
            }

            @Mock
            public List<SitesEntity> getSites(Map<String, Object> condition) {
                List<SitesEntity> list = new ArrayList<SitesEntity>();
                list.add(new SitesEntity());
                return list;

            }
        };
        try {
            sitesBusinessImpl.addSite(sitesEntity);
        } catch(ServiceException se) {
            assertTrue(true);
        }
    }

    @Test
    public void testCheckIdExceptions() throws ServiceException {
        SitesEntity sitesEntity = new SitesEntity();
        sitesEntity.setId("123");
        sitesEntity.setCountry("china");
        sitesEntity.setName("openstack");
        sitesEntity.setLocation("xian");
        new MockUp<SitesDaoImpl>() {

            @Mock
            public SitesEntity getSite(String id) {
                return new SitesEntity();
            }
        };
        try {
            sitesBusinessImpl.addSite(sitesEntity);
        } catch(ServiceException se) {
            assertTrue(true);
        }
    }

    @Test
    public void testAddSiteSelectiveSitesEntityIsNull() throws ServiceException {
        try {
            sitesBusinessImpl.addSiteSelective(null);
        } catch(ServiceException se) {
            assertTrue(true);
        }
    }

    @Test
    public void testUpdateSiteSelectiveSitesEntityIsNull() throws ServiceException {
        try {
            sitesBusinessImpl.updateSiteSelective(null);
        } catch(ServiceException se) {
            assertTrue(true);
        }
    }

    @Test
    public void testUpdateSiteSitesEntityIsNull() throws ServiceException {
        try {
            sitesBusinessImpl.updateSite(null);
        } catch(ServiceException se) {
            assertTrue(true);
        }
    }

    @Test
    public void testUpdateSiteByVimIdSitesEntityIsNull() throws ServiceException {
        try {
            sitesBusinessImpl.updateSiteByVimId(null);
        } catch(ServiceException se) {
            assertTrue(true);
        }
    }

    @Test
    public void testUpdateSiteByVimId() throws ServiceException {
        SitesEntity sitesEntity = new SitesEntity();
        sitesEntity.setCountry("china");
        sitesEntity.setName("openstack");
        sitesEntity.setLocation("xian");
        new MockUp<SitesDaoImpl>() {

            @Mock
            public int updateSiteByVimId(SitesEntity sitesEntity) {
                return 1;
            }
        };
        int result = sitesBusinessImpl.updateSiteByVimId(sitesEntity);
        int expectedResult = 1;
        assertEquals(expectedResult, result);
    }

    @Test
    public void testUpdateSiteResourceSitesEntityIsNull() throws ServiceException {
        try {
            sitesBusinessImpl.updateSiteResource(null);
        } catch(ServiceException se) {
            assertTrue(true);
        }
    }

    @Test
    public void testUpdateSiteResource() throws ServiceException {
        SitesEntity sitesEntity = new SitesEntity();
        sitesEntity.setCountry("china");
        sitesEntity.setName("openstack");
        sitesEntity.setLocation("xian");
        new MockUp<SitesDaoImpl>() {

            @Mock
            public int updateSiteSelective(SitesEntity sitesEntity) {
                return 1;
            }
        };
        int result = sitesBusinessImpl.updateSiteResource(sitesEntity);
        int expectedResult = 1;
        assertEquals(expectedResult, result);
    }
}
