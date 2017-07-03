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

public class NetworkEntityTest {

    private NetworkEntity networkEntity;

    @Before
    public void setUp() {
        networkEntity = new NetworkEntity();
    }

    @Test
    public void testSetId() {
        networkEntity.setId("123");
        networkEntity.setName("name");
        networkEntity.setNetworkType("networkType");
        networkEntity.setPhysicalNetwork("physicalNetwork");
        networkEntity.setSegmentationId("segmentationId");
        networkEntity.setStatus("networkStatus");
        networkEntity.setTenantId("tenantId");
        networkEntity.setVimId("networkVimId");
        networkEntity.setVimName("networkVimName");
        assertTrue(networkEntity.toString() != null);
        assertTrue(NetworkEntity.toEntity(new JSONObject()) != null);
    }
}
