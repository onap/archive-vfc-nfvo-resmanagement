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
 * <br/>
 * <p>
 * Constant for REST URL.
 * </p>
 *
 * @author
 * @version NFVO 0.5 2016-3-17
 */
public class UrlConstant {

    /**
     * networks target.
     */
    public static final String LOCATION_TARGET = "location";

    /**
     * MSB register url.
     */
    public static final String REST_MSB_REGISTER = "/openoapi/microservices/v1/services";

    public static final String ESR_GET_VIM_URL = "/openoapi/extsys/v1/vims/%s";

    public static final String ESR_GET_VIMS_URL = "/openoapi/extsys/v1/vims";

    /**
     * send resource info to monitor url
     */
    public static final String SEND_MSG_MONITOR = "/openoapi/umc/v1/resinfo";

    /**
     * networks url.
     */
    public static final String LOCATION_URL = "/v1/locations";

    /**
     * port url.
     */
    public static final String PORT_URL = "/v1/ports";

    /**
     * host url.
     */
    public static final String HOST_URL = "/v1/hosts";

    /**
     * vl url.
     */
    public static final String VL_URL = "/v1/vl";

    /**
     * vnf url.
     */
    public static final String VNF_URL = "/v1/vnf";

    /**
     * vnfinfo url.
     */
    public static final String VNFINFO_URL = "/v1/vnfinfo";

    /**
     * vnfstatus url.
     */
    public static final String VNFSTATUS_URL = "/v1/vnfstatus";

    /**
     * grant resource url.
     */
    public static final String GRANTRES_URL = "/v1/resource/grant";

    /**
     * vm url.
     */
    public static final String VM_URL = "/v1/vm";

    /**
     * sites target.
     */
    public static final String SITES_TARGET = "sites";

    /**
     * sites url.
     */
    public static final String SITES_URL = "/v1/datacenters";

    /**
     * networks target.
     */
    public static final String NETWORKS_TARGET = "networks";

    /**
     * networks url.
     */
    public static final String NETWORKS_URL = "/v1/networks";

    /**
     * updateres.
     */
    public static final String MODRES_URL = "/updateres";

    /**
     * resoperate target.
     */
    public static final String RESOPERATE_TARGET = "resoperate";

    /**
     * APPLICATION_TYPE.
     */
    public static final String APPLICATION_TYPE = "application/json";

    /**
     * ADDRES.
     */
    public static final String ADDRES_URL = "/resmgr/vims";

    /**
     * updatebytenant.
     */
    public static final String UPDATE_BY_TENANT = "updatebytenant";

    /**
     * updatebyvapp.
     */
    public static final String UPDATE_BY_VAPP = "updatebyvapp";

    /**
     * getNetworkURL.
     */
    public static final String GET_NETWORK_URL = "/openoapi/multivim/v1/%s/%s/networks";

    /**
     * getTenantURL.
     */
    public static final String GET_TENANT_URL = "/openoapi/multivim/v1/%s/tenants";

    /**
     * getHostURL.
     */
    public static final String GET_HOST_URL = "/openoapi/multivim/v1/%s/%s/hosts";

    /**
     * getHostDetailURL.
     */
    public static final String GET_HOSTDETAIL_URL = "/openoapi/multivim/v1/%s/%s/hosts/%s";

    /**
     * getNetworkURL.
     */
    public static final String GET_PORT_URL = "/openoapi/multivim/v1/%s/%s/ports";

    /**
     * getLimitsURL.
     */
    public static final String GET_LIMITS_URL = "/openoapi/multivim/v1/%s/%s/limits";

    /**
     * template notify M url.
     */
    public static final String TEMPLATE_NOTIFY_M_URL = "";

    /**
     * tenantsite allot url.
     */
    public static final String TENANTSITE_ALLOT_URL = "/v1/resmanage/tenantsite/allot";

    /**
     * tenantsite allot target.
     */
    public static final String TENANTSITE_ALLOT_TARGET = "tenantsite/allot";

    /**
     * tenant url.
     */
    public static final String TENANT_URL = "/v1/resmanage/tenant";

    /**
     * tenant target.
     */
    public static final String TENANT_TARGET = "tenant";

    /**
     * rollback url.
     */
    public static final String ROLLBACK_URL = "/v1/resmanage/rollback";

    /**
     * rollback target.
     */
    public static final String ROLLBACK_TARGET = "rollback";

    /**
     * vms target.
     */
    public static final String VIM_TARGET = "vim";

    /**
     * vms url.
     */
    public static final String VIM_URL = "/v1/resmanage/vim";

    /**
     * https
     */
    public static final String GET_HTTPS = "https://";

    /**
     * get token
     */
    public static final String GET_IAM_TOKEN = "/v3/auth/tokens";

    /**
     * rest.
     */
    public static final String REST = "/rest";

    /**
     * donsdata url.
     */
    public static final String INSTALL_URL = "install";

    /**
     * donsdata url.
     */
    public static final String UNINSTALL_URL = "uninstall";

    /**
     * respool url.
     */
    public static final String RESOPERATE_URL = "/v1/resoperate";

    /**
     * limits url.
     */
    public static final String LIMITS_URL = "/v1/limits";

    private UrlConstant() {
        // private constructor
    }

}
