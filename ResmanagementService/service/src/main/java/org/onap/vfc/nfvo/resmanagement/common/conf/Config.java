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

package org.onap.vfc.nfvo.resmanagement.common.conf;

import java.io.IOException;
import java.util.Properties;

import org.onap.vfc.nfvo.resmanagement.common.constant.Constant;

public class Config {

    private static Properties prps = new Properties();

    /**
     * Private constructor.
     */
    private Config() {

    }

    static {
        try {
            prps.load(Config.class.getClassLoader().getResourceAsStream(Constant.CONF));
        } catch(IOException e) {
            throw new RuntimeException(e); // NOSONAR
        }
    }

    public static String getHost() {
        return prps.getProperty(Constant.HOST);
    }

    public static String getPort() {
        return prps.getProperty(Constant.PORT);
    }

    public static String getCloudRegionId() {
        return prps.getProperty(Constant.CLOUD_REGION_ID);
    }

    public static String getCloudOwner() {
        return prps.getProperty(Constant.CLOUD_OWNER);
    }

    public static String getTenantId() {
        return prps.getProperty(Constant.TENANT_ID);
    }
}
