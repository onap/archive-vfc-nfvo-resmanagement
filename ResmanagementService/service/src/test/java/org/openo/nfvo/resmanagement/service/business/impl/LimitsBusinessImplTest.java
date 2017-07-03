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

import org.junit.Before;
import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.baseservice.roa.util.restclient.HttpRest;
import org.openo.baseservice.roa.util.restclient.RestfulOptions;
import org.openo.baseservice.roa.util.restclient.RestfulParametes;
import org.openo.baseservice.roa.util.restclient.RestfulResponse;

import mockit.Mock;
import mockit.MockUp;
import net.sf.json.JSONObject;

public class LimitsBusinessImplTest {

    private LimitsBusinessImpl limitsBusinessImpl;

    @Before
    public void setUp() throws ServiceException {
        limitsBusinessImpl = new LimitsBusinessImpl();
    }

    @Test
    public void testGetCpuLimits() throws ServiceException {
        new MockUp<HttpRest>() {

            @Mock
            public RestfulResponse get(String servicePath, RestfulParametes restParametes, RestfulOptions option)
                    throws ServiceException {
                RestfulResponse rsp = new RestfulResponse();
                rsp.setStatus(200);
                return rsp;
            }
        };
        JSONObject paramJson = new JSONObject();
        paramJson.put("vimId", "vimId");
        paramJson.put("tenantId", "tenantId");
    }

    @Test
    public void testgetLimits() throws ServiceException {
        new MockUp<HttpRest>() {

            @Mock
            public RestfulResponse get(String servicePath, RestfulParametes restParametes, RestfulOptions option)
                    throws ServiceException {
                RestfulResponse rsp = new RestfulResponse();
                rsp.setStatus(200);
                return rsp;
            }

        };
    }
}
