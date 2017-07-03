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

package org.openo.nfvo.resmanagement.service.entity;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import net.sf.json.JSONObject;

public class SitesEntityTest {

    private SitesEntity sitesEntity;

    @Before
    public void setUp() {
        sitesEntity = new SitesEntity();
    }

    @Test
    public void testSitesEntity() {
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
        assertTrue(sitesEntity.toString() != null);
        assertTrue(SitesEntity.toEntity(new JSONObject()) != null);
        SitesEntity.checkResource(sitesEntity);
        SitesEntity.checkResourceIsZero(sitesEntity);
    }

    @Test
    public void testSitesEntityBranch() {
        sitesEntity.setCountry("country");
        sitesEntity.setId("id");
        sitesEntity.setLocation("location");
        sitesEntity.setName("name");
        sitesEntity.setStatus("siteStatus");
        sitesEntity.setTotalCPU("");
        sitesEntity.setTotalDisk("");
        sitesEntity.setTotalMemory("");
        sitesEntity.setUsedCPU("");
        sitesEntity.setUsedDisk("");
        sitesEntity.setUsedMemory("");
        sitesEntity.setVimId("siteVimId");
        sitesEntity.setVimName("siteVimName");
        SitesEntity.checkResource(sitesEntity);
        SitesEntity.checkResourceIsZero(sitesEntity);
    }

    @Test
    public void testSitesEntityBranch1() {
        sitesEntity.setCountry("country");
        sitesEntity.setId("id");
        sitesEntity.setLocation("location");
        sitesEntity.setName("name");
        sitesEntity.setStatus("siteStatus");
        sitesEntity.setTotalCPU("s");
        sitesEntity.setTotalDisk("");
        sitesEntity.setTotalMemory("s");
        sitesEntity.setUsedCPU("s");
        sitesEntity.setUsedDisk("s");
        sitesEntity.setUsedMemory("s");
        sitesEntity.setVimId("siteVimId");
        sitesEntity.setVimName("siteVimName");
        SitesEntity.checkResource(sitesEntity);
    }

    @Test
    public void testSitesEntityBranch2() {
        sitesEntity.setCountry("country");
        sitesEntity.setId("id");
        sitesEntity.setLocation("location");
        sitesEntity.setName("name");
        sitesEntity.setStatus("siteStatus");
        sitesEntity.setTotalCPU("23");
        sitesEntity.setTotalDisk("");
        sitesEntity.setTotalMemory("s");
        sitesEntity.setUsedCPU("1");
        sitesEntity.setUsedDisk("s");
        sitesEntity.setUsedMemory("s");
        sitesEntity.setVimId("siteVimId");
        sitesEntity.setVimName("siteVimName");
        SitesEntity.checkResource(sitesEntity);
    }
}
