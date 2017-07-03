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

package org.openo.nfvo.resmanagement.service.adapter.impl;

import java.io.IOException;

import org.junit.Test;

import mockit.Mock;
import mockit.MockUp;

/**
 * <br>
 * <p>
 * </p>
 * 
 * @author
 * @version NFVO 0.5 Sep 24, 2016
 */
public class ResmgrAdapterMgrServiceTest {

    @Test
    public void testRegister() {
        new MockUp<ResmgrAdapterMgrService>() {

            @Mock
            public String readVimAdapterInfoFromJson() throws IOException {
                return "{\"serviceName\": \"resmgr\",\"version\": \"v1\",\"url\": \"/openoapi/resmgr/v1\",\"protocol\": \"REST\",\"visualRange\": \"1\",\"nodes\": [{\"ip\": \"127.0.0.1\",\"port\": \"8080\",\"ttl\": 0}]}";
            }
        };

        ResmgrAdapterMgrService resmgrService = new ResmgrAdapterMgrService();
        resmgrService.register();
    }

    @Test
    public void testRegisterByIOException() {
        new MockUp<ResmgrAdapterMgrService>() {

            @Mock
            public String readVimAdapterInfoFromJson() throws IOException {
                throw new IOException();
            }
        };
        ResmgrAdapterMgrService resmgrService = new ResmgrAdapterMgrService();
        resmgrService.register();
    }

    @Test
    public void testRegisterByNoFile() {
        ResmgrAdapterMgrService resmgrService = new ResmgrAdapterMgrService();
        resmgrService.register();
    }
}
