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

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.openo.nfvo.resmanagement.common.util.JsonUtil;

import net.sf.json.JSONObject;

/**
 * @author l00345485
 * @date 2016-10-28
 */
public class VmEntity implements Serializable {

    /**  */
    private String vmId;

    /**  */
    private String vmName;

    /**  */
    private String vmStatus;

    /**  */
    private String vnfInstanceId;

    private static final long serialVersionUID = 1L;

    public String getVmId() {
        return vmId;
    }

    public void setVmId(String vmId) {
        this.vmId = vmId;
    }

    public String getVmName() {
        return vmName;
    }

    public void setVmName(String vmName) {
        this.vmName = vmName;
    }

    public String getVmStatus() {
        return vmStatus;
    }

    public void setVmStatus(String vmStatus) {
        this.vmStatus = vmStatus;
    }

    public String getVnfInstanceId() {
        return vnfInstanceId;
    }

    public void setVnfInstanceId(String vnfInstanceId) {
        this.vnfInstanceId = vnfInstanceId;
    }

    public static VmEntity toEntity(JSONObject jsonObject) {
        VmEntity vmEntity = new VmEntity();
        vmEntity.setVmId(JsonUtil.getJsonFieldStr(jsonObject, "vmId"));
        vmEntity.setVmName(JsonUtil.getJsonFieldStr(jsonObject, "vmName"));
        vmEntity.setVmStatus(JsonUtil.getJsonFieldStr(jsonObject, "vmStatus"));
        vmEntity.setVnfInstanceId(JsonUtil.getJsonFieldStr(jsonObject, "vnfInstanceId"));
        return vmEntity;
    }

    @Override
    public String toString() {
        JSONObject vmResJson = new JSONObject();
        vmResJson.put("vmId", StringUtils.trimToEmpty(this.getVmId()));
        vmResJson.put("vmName", StringUtils.trimToEmpty(this.getVmName()));
        vmResJson.put("vmStatus", StringUtils.trimToEmpty(this.getVmStatus()));
        vmResJson.put("vnfInstanceId", StringUtils.trimToEmpty(this.getVnfInstanceId()));
        return vmResJson.toString();
    }
}
