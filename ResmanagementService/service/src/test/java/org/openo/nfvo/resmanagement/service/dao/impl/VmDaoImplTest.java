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
package org.openo.nfvo.resmanagement.service.dao.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.nfvo.resmanagement.service.entity.VmEntity;
import org.openo.nfvo.resmanagement.service.mapper.SitesMapper;
import org.openo.nfvo.resmanagement.service.mapper.VmMapper;

import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;

public class VmDaoImplTest {
    @Mocked
    VmMapper mapper;
    @Test
    public void TestaddVm() throws ServiceException {
            new MockUp<AbstractDao>() {

            @Mock
             public <T> T getMapperManager(Class<T> type) {
                return (T) mapper;

            }
        };
        VmDaoImpl impl = new VmDaoImpl();
        VmEntity vmEntity =new VmEntity();
        vmEntity.setVmId("1");
        //assertEquals(0, impl.addVm(vmEntity));

    }


}
