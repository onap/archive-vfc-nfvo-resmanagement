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

package org.openo.nfvo.resmanagement.common.constant;

/**
 *
 * Http constant class.<br>
 * <p>
 * </p>
 *
 * @author
 * @version     NFVO 0.5  Sep 10, 2016
 */
public class HttpConstant {

    public static final int ERROR_CODE = -1;

    public static final int OK_CODE = 1;

    public static final int HTTP_OK = 200;

    public static final int HTTP_CREATED = 201;

    public static final int HTTP_ACCEPTED = 202;

    public static final int HTTP_NOCONTENT = 204;

    public static final int HTTP_UNAUTHORIZED = 401;

    public static final int HTTP_BAD_REQUEST = 400;

    public static final int HTTP_NOTFOUND_CODE = 404;

    public static final int HTTP_CONFLICT_CODE = 409;

    public static final int HTTP_INVALID_PARAMETERS = 415;

    public static final int HTTP_INNERERROR_CODE = 500;

    public static final int INTERNAL_EXCEPTION_CODE = 600;

    public static final int TOKEN_HEAD_NULL_CODE = 601;

    public static final int TOKEN_USER_NULL_CODE = 602;

    public static final int SERVICE_URL_ERROR_CODE = 603;

    public static final int ACCESS_OBJ_NULL_CODE = 604;

    public static final int CONNECT_NOT_FOUND_CODE = 605;

    public static final int VCENTER_PARA_ERROR_CODE = 606;

    public static final int TYPE_PARA_ERROR_CODE = 607;

    public static final int CONNECT_FAIL_CODE = 608;

    public static final int DIS_CONNECT_FAIL_CODE = 609;

    public static final int HANDSHAKE_FAIL_CODE = 610;

    private HttpConstant() {
        //private constructor
    }
}
