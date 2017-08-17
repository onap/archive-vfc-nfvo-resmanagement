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

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.onap.vfc.nfvo.resmanagement.service.business.impl.LimitsBusinessImpl;
import org.onap.vfc.nfvo.resmanagement.service.business.inf.LimitsBusiness;
import org.onap.vfc.nfvo.resmanagement.service.rest.LimitsRoa;
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
public class LimitsRoaTest {

    private LimitsRoa roa;

    private LimitsBusiness limitsBusiness;

    @Before
    public void setUp() {
        roa = new LimitsRoa();
        limitsBusiness = new LimitsBusinessImpl();
        roa.setLimitsBusiness(limitsBusiness);
    }

    @Test
    public void testGetLimitsResource() throws ServiceException {
        new MockUp<LimitsBusinessImpl>() {

            @Mock
            public JSONObject getLimits(String vimId) throws ServiceException {
                return new JSONObject();
            }
        };
        HttpServletRequest context = new MockHttpServletRequest();
        JSONObject result = roa.getLimits(context, "vimId");
        assertNotNull(result);
    }
}
