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
 * @date 2016-10-29
 */
public class VnfStatusEntity implements Serializable {

    /**  */
    private String vnfInstanceId;

    /**  */
    private String jobId;

    /**  */
    private String nsId;

    /**  */
    private String vnfmId;

    /**  */
    private String responseDescriptor;

    /**  */
    private String status;

    /**  */
    private String progress;

    /**  */
    private String statusDescription;

    /**  */
    private String errorCode;

    /**  */
    private String responseId;

    /**  */
    private String responseHistoryList;

    /**  */
    private String addVm;

    /**  */
    private String delVm;

    private static final long serialVersionUID = 1L;

    public String getVnfInstanceId() {
        return vnfInstanceId;
    }

    public void setVnfInstanceId(String vnfInstanceId) {
        this.vnfInstanceId = vnfInstanceId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
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

    public String getResponseDescriptor() {
        return responseDescriptor;
    }

    public void setResponseDescriptor(String responseDescriptor) {
        this.responseDescriptor = responseDescriptor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getResponseId() {
        return responseId;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    public String getResponseHistoryList() {
        return responseHistoryList;
    }

    public void setResponseHistoryList(String responseHistoryList) {
        this.responseHistoryList = responseHistoryList;
    }

    public String getAddVm() {
        return addVm;
    }

    public void setAddVm(String addVm) {
        this.addVm = addVm;
    }

    public String getDelVm() {
        return delVm;
    }

    public void setDelVm(String delVm) {
        this.delVm = delVm;
    }

    public static VnfStatusEntity toEntity(JSONObject jsonObject) {
        VnfStatusEntity vnfStatusEntity = new VnfStatusEntity();
        vnfStatusEntity.setVnfInstanceId(JsonUtil.getJsonFieldStr(jsonObject, "vnfInstanceId"));
        vnfStatusEntity.setJobId(JsonUtil.getJsonFieldStr(jsonObject, "jobId"));
        vnfStatusEntity.setNsId(JsonUtil.getJsonFieldStr(jsonObject, "nsId"));
        vnfStatusEntity.setVnfmId(JsonUtil.getJsonFieldStr(jsonObject, "vnfmId"));
        vnfStatusEntity.setResponseDescriptor(JsonUtil.getJsonFieldStr(jsonObject, "responseDescriptor"));
        vnfStatusEntity.setStatus(JsonUtil.getJsonFieldStr(jsonObject, "status"));
        vnfStatusEntity.setProgress(JsonUtil.getJsonFieldStr(jsonObject, "progress"));
        vnfStatusEntity.setStatusDescription(JsonUtil.getJsonFieldStr(jsonObject, "statusDescription"));
        vnfStatusEntity.setErrorCode(JsonUtil.getJsonFieldStr(jsonObject, "errorCode"));
        vnfStatusEntity.setResponseId(JsonUtil.getJsonFieldStr(jsonObject, "responseId"));
        vnfStatusEntity.setResponseHistoryList(JsonUtil.getJsonFieldStr(jsonObject, "responseHistoryList"));
        vnfStatusEntity.setAddVm(JsonUtil.getJsonFieldStr(jsonObject, "addVm"));
        vnfStatusEntity.setDelVm(JsonUtil.getJsonFieldStr(jsonObject, "delVm"));
        return vnfStatusEntity;
    }

    @Override
    public String toString() {
        JSONObject vnfStatusResJson = new JSONObject();
        vnfStatusResJson.put("vnfInstanceId", StringUtils.trimToEmpty(this.getVnfInstanceId()));
        vnfStatusResJson.put("jobId", StringUtils.trimToEmpty(this.getJobId()));
        vnfStatusResJson.put("nsId", StringUtils.trimToEmpty(this.getNsId()));
        vnfStatusResJson.put("vnfmId", StringUtils.trimToEmpty(this.getVnfmId()));
        vnfStatusResJson.put("responseDescriptor", StringUtils.trimToEmpty(this.getResponseDescriptor()));
        vnfStatusResJson.put("status", StringUtils.trimToEmpty(this.getStatus()));
        vnfStatusResJson.put("progress", StringUtils.trimToEmpty(this.getProgress()));
        vnfStatusResJson.put("statusDescription", StringUtils.trimToEmpty(this.getStatusDescription()));
        vnfStatusResJson.put("errorCode", StringUtils.trimToEmpty(this.getErrorCode()));
        vnfStatusResJson.put("responseId", StringUtils.trimToEmpty(this.getResponseId()));
        vnfStatusResJson.put("responseHistoryList", StringUtils.trimToEmpty(this.getResponseHistoryList()));
        vnfStatusResJson.put("addVm", StringUtils.trimToEmpty(this.getAddVm()));
        vnfStatusResJson.put("delVm", StringUtils.trimToEmpty(this.getDelVm()));
        return vnfStatusResJson.toString();
    }
}
