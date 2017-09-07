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

package org.onap.vfc.nfvo.resmanagement.common.constant;

import org.onap.vfc.nfvo.resmanagement.common.ResourceUtil;

/**
 * Response constants.<br/>
 * <p>
 * </p>
 *
 * @author
 * @version VFC 1.0 Aug 25, 2016
 */
public class ResponseConstant {

    public static final String ADD_SUCESS_MSG = ResourceUtil.getMessage("add success");

    public static final String ADD_FAIL_MSG = ResourceUtil.getMessage("add error");

    public static final String DEL_SUCESS_MSG = ResourceUtil.getMessage("delete success");

    public static final String DEL_FAIL_MSG = ResourceUtil.getMessage("delete error");

    public static final String MOD_SUCESS_MSG = ResourceUtil.getMessage("update success");

    public static final String MOD_FAIL_MSG = ResourceUtil.getMessage("update error");

    public static final String QUERY_SUCESS_MSG = ResourceUtil.getMessage("query success");

    public static final String QUERY_FAIL_MSG = ResourceUtil.getMessage("query error");

    public static final String DELSODORES_FAIL_MSG = "delete sodores error";

    public static final String DELVNFMINFO_FAIL_MSG = "delete vnfm error";

    public static final String RES_VERIFY_SUCESS_MSG = "nsdata verify sucessfully";

    public static final String RES_VERIFY_FAIL_MSG = "res verify failed";

    private ResponseConstant() {
        // private constructor
    }
}
