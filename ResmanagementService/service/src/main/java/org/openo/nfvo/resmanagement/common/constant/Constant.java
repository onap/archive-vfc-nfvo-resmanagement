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

package org.openo.nfvo.resmanagement.common.constant;

/**
 * HTTP constants<br/>
 * <p>
 * </p>
 *
 * @author
 * @version NFVO 0.5 Aug 25, 2016
 */
public class Constant {

    public static final String RES_MANAGEMENT_DB = "resmanagementdb";

    /**
     * Database Delete/Modify/Add fail.
     */
    public static final int ERROR_CODE = -1;

    /**
     * Database Delete/Modify/Add success.
     */
    public static final int OK_CODE = 1;

    /**
     * Lack of resource.
     */
    public static final int RES_NOT_ENOUGH_CODE = -2;

    /**
     * Module name.
     */
    public static final String MODULE_NAME = "Resmanagement";

    public static final String RESPONSE_CONTENT = "responseContent";

    public static final String STATUS_CODE = "statusCode";

    /**
     * Format Time
     */
    public static final String DATE_FORMATE = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_UTC_FORMATE = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    public static final String UTC_FORMATE = "UTC";

    public static final String DATE_DAY_FORMATE = "yyyy-MM-dd 00:00:00";

    /**
     * IAM
     */

    public static final String HTTP_CONTENT_TYPE = "Content-Type";

    public static final String HTTP_CONTENT_TYPE_VALUE = "application/json;charset=UTF-8";

    public static final String X_TENANT_ID = "X-Tenant-Id";

    public static final String IAM_TOKEN = "x-auth-token";

    public static final String IAM_AUTH_TOKEN = "X-Auth-Token";

    public static final String IAM_USER_ID = "X-User-Id";

    public static final String IAM_USER_NAME = "X-User-Name";

    public static final String IAM_DOMAIN_NAME = "X-Domain-Name";

    public static final String HEADER_SUBJECT_TOKEN = "X-Subject-Token";

    public static final int REPEAT_REG_TIME = 60 * 1000;

    private Constant() {
        // private constants
    }
}
