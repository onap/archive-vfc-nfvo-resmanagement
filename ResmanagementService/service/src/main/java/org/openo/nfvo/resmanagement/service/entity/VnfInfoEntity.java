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
public class VnfInfoEntity implements Serializable {

    /**  */
    private String vnfInstanceId;

    /**  */
    private String nsId;

    /**  */
    private String vnfmId;

    private static final long serialVersionUID = 1L;

    public String getVnfInstanceId() {
        return vnfInstanceId;
    }

    public void setVnfInstanceId(String vnfInstanceId) {
        this.vnfInstanceId = vnfInstanceId;
    }

    public String getNsId() {
        return nsId;
    }

    public void setNsId(String nsId) {
        this.nsId = nsId;
    }

    public String getVnfmId() {
        return vnfmId;
    }

    public void setVnfmId(String vnfmId) {
        this.vnfmId = vnfmId;
    }

    public static VnfInfoEntity toEntity(JSONObject jsonObject) {
        VnfInfoEntity vnfInfoEntity = new VnfInfoEntity();
        vnfInfoEntity.setVnfInstanceId(JsonUtil.getJsonFieldStr(jsonObject, "vnfInstanceId"));
        vnfInfoEntity.setNsId(JsonUtil.getJsonFieldStr(jsonObject, "nsId"));
        vnfInfoEntity.setVnfmId(JsonUtil.getJsonFieldStr(jsonObject, "vnfmId"));
        return vnfInfoEntity;
    }

    @Override
    public String toString() {
        JSONObject vnfInfoResJson = new JSONObject();
        vnfInfoResJson.put("vnfInstanceId", StringUtils.trimToEmpty(this.getVnfInstanceId()));
        vnfInfoResJson.put("nsId", StringUtils.trimToEmpty(this.getNsId()));
        vnfInfoResJson.put("vnfmId", StringUtils.trimToEmpty(this.getVnfmId()));
        return vnfInfoResJson.toString();
    }
}
