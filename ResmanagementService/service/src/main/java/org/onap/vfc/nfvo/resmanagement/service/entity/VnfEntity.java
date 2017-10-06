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

package org.onap.vfc.nfvo.resmanagement.service.entity;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.onap.vfc.nfvo.resmanagement.common.conf.Config;
import org.onap.vfc.nfvo.resmanagement.common.constant.UrlConstant;
import org.onap.vfc.nfvo.resmanagement.common.util.JsonUtil;
import org.onap.vfc.nfvo.resmanagement.common.util.RestfulUtil;
import org.onap.vfc.nfvo.resmanagement.common.util.request.RequestUtil;
import org.onap.vfc.nfvo.resmanagement.common.util.restclient.RestfulParametes;
import org.onap.vfc.nfvo.resmanagement.common.util.restclient.RestfulResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author l00345485
 * @date 2016-10-28
 */
public class VnfEntity implements Serializable {

    /**  */
    private String id;

    /**  */
    private String vnfInstanceId;

    /**  */
    private String vnfInstanceName;

    /**  */
    private String nsId;

    /**  */
    private String nsName;

    /**  */
    private String vnfmId;

    /**  */
    private String vnfmName;

    /**  */
    private String vnfPackageName;

    /**  */
    private String vnfDescriptorName;

    /**  */
    private String vimId;

    /**  */
    private String vimName;

    /**  */
    private String vimTenant;

    /**  */
    private String jobId;

    /**  */
    private String vnfStatus;

    /**  */
    private String vnfType;

    /**  */
    private Integer maxVm;

    /**  */
    private Integer maxCpu;

    /**  */
    private Integer maxDisk;

    /**  */
    private Integer maxRam;

    /**  */
    private Integer maxShd;

    /**  */
    private Integer maxNet;

    /**  */
    private String name;

    /**  */
    private String vnfResourceVersion;

    private String vnfmResourceVersion;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVnfInstanceId() {
        return vnfInstanceId;
    }

    public void setVnfInstanceId(String vnfInstanceId) {
        this.vnfInstanceId = vnfInstanceId;
    }

    public String getVnfInstanceName() {
        return vnfInstanceName;
    }

    public void setVnfInstanceName(String vnfInstanceName) {
        this.vnfInstanceName = vnfInstanceName;
    }

    public String getNsId() {
        return nsId;
    }

    public void setNsId(String nsId) {
        this.nsId = nsId;
    }

    public String getNsName() {
        return nsName;
    }

    public void setNsName(String nsName) {
        this.nsName = nsName;
    }

    public String getVnfmId() {
        return vnfmId;
    }

    public void setVnfmId(String vnfmId) {
        this.vnfmId = vnfmId;
    }

    public String getVnfmName() {
        return vnfmName;
    }

    public void setVnfmName(String vnfmName) {
        this.vnfmName = vnfmName;
    }

    public String getVnfPackageName() {
        return vnfPackageName;
    }

    public void setVnfPackageName(String vnfPackageName) {
        this.vnfPackageName = vnfPackageName;
    }

    public String getVnfDescriptorName() {
        return vnfDescriptorName;
    }

    public void setVnfDescriptorName(String vnfDescriptorName) {
        this.vnfDescriptorName = vnfDescriptorName;
    }

    public String getVimId() {
        return vimId;
    }

    public void setVimId(String vimId) {
        this.vimId = vimId;
    }

    public String getVimName() {
        return vimName;
    }

    public void setVimName(String vimName) {
        this.vimName = vimName;
    }

    public String getVimTenant() {
        return vimTenant;
    }

    public void setVimTenant(String vimTenant) {
        this.vimTenant = vimTenant;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getVnfStatus() {
        return vnfStatus;
    }

    public void setVnfStatus(String vnfStatus) {
        this.vnfStatus = vnfStatus;
    }

    public String getVnfType() {
        return vnfType;
    }

    public void setVnfType(String vnfType) {
        this.vnfType = vnfType;
    }

    public Integer getMaxVm() {
        return maxVm;
    }

    public void setMaxVm(Integer maxVm) {
        this.maxVm = maxVm;
    }

    public Integer getMaxCpu() {
        return maxCpu;
    }

    public void setMaxCpu(Integer maxCpu) {
        this.maxCpu = maxCpu;
    }

    public Integer getMaxDisk() {
        return maxDisk;
    }

    public void setMaxDisk(Integer maxDisk) {
        this.maxDisk = maxDisk;
    }

    public Integer getMaxRam() {
        return maxRam;
    }

    public void setMaxRam(Integer maxRam) {
        this.maxRam = maxRam;
    }

    public Integer getMaxShd() {
        return maxShd;
    }

    public void setMaxShd(Integer maxShd) {
        this.maxShd = maxShd;
    }

    public Integer getMaxNet() {
        return maxNet;
    }

    public void setMaxNet(Integer maxNet) {
        this.maxNet = maxNet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * <br>
     *
     * @param jsonObject
     * @return
     * @since VFC 1.0
     */
    public static VnfEntity toEntity(JSONObject jsonObject) {
        VnfEntity vnfEntity = new VnfEntity();
        vnfEntity.setId(JsonUtil.getJsonFieldStr(jsonObject, "id"));
        vnfEntity.setName(JsonUtil.getJsonFieldStr(jsonObject, "name"));
        vnfEntity.setVnfInstanceId(JsonUtil.getJsonFieldStr(jsonObject, "vnfInstanceId"));
        vnfEntity.setVnfInstanceName(JsonUtil.getJsonFieldStr(jsonObject, "vnfInstanceName"));
        vnfEntity.setNsId(JsonUtil.getJsonFieldStr(jsonObject, "nsId"));
        vnfEntity.setNsName(JsonUtil.getJsonFieldStr(jsonObject, "nsName"));
        vnfEntity.setVnfmId(JsonUtil.getJsonFieldStr(jsonObject, "vnfmId"));
        vnfEntity.setVnfmName(JsonUtil.getJsonFieldStr(jsonObject, "vnfmName"));
        vnfEntity.setVnfPackageName(JsonUtil.getJsonFieldStr(jsonObject, "vnfPackageName"));
        vnfEntity.setVnfDescriptorName(JsonUtil.getJsonFieldStr(jsonObject, "vnfDescriptorName"));
        vnfEntity.setVimId(JsonUtil.getJsonFieldStr(jsonObject, "vimId"));
        vnfEntity.setVimName(JsonUtil.getJsonFieldStr(jsonObject, "vimName"));
        vnfEntity.setVimTenant(JsonUtil.getJsonFieldStr(jsonObject, "vimTenant"));
        vnfEntity.setJobId(JsonUtil.getJsonFieldStr(jsonObject, "jobId"));
        vnfEntity.setVnfStatus(JsonUtil.getJsonFieldStr(jsonObject, "vnfStatus"));
        vnfEntity.setVnfType(JsonUtil.getJsonFieldStr(jsonObject, "vnfType"));
        vnfEntity.setMaxVm(JsonUtil.getJsonFieldInt(jsonObject, "maxVm"));
        vnfEntity.setMaxCpu(JsonUtil.getJsonFieldInt(jsonObject, "maxCpu"));
        vnfEntity.setMaxDisk(JsonUtil.getJsonFieldInt(jsonObject, "maxDisk"));
        vnfEntity.setMaxRam(JsonUtil.getJsonFieldInt(jsonObject, "maxRam"));
        vnfEntity.setMaxShd(JsonUtil.getJsonFieldInt(jsonObject, "maxShd"));
        vnfEntity.setMaxNet(JsonUtil.getJsonFieldInt(jsonObject, "maxNet"));
        vnfEntity.setVnfResourceVersion(JsonUtil.getJsonFieldStr(jsonObject, "resource-version"));
        return vnfEntity;
    }

    public static VnfEntity toEntityFromAai(JSONObject jsonObject) {
        VnfEntity vnfEntity = new VnfEntity();
        vnfEntity.setId(JsonUtil.getJsonFieldStr(jsonObject, "vnf-id"));
        vnfEntity.setVnfInstanceId(JsonUtil.getJsonFieldStr(jsonObject, "vnf-id"));
        vnfEntity.setVnfInstanceName(JsonUtil.getJsonFieldStr(jsonObject, "vnf-name"));
        vnfEntity.setName(JsonUtil.getJsonFieldStr(jsonObject, "vnf-name2"));
        vnfEntity.setNsId(JsonUtil.getJsonFieldStr(jsonObject, "service-id"));
        vnfEntity.setVnfStatus(JsonUtil.getJsonFieldStr(jsonObject, "orchestration-status"));
        vnfEntity.setVnfType(JsonUtil.getJsonFieldStr(jsonObject, "vnf-type"));
        vnfEntity.setVnfPackageName(JsonUtil.getJsonFieldStr(jsonObject, "vnf-package-name"));
        vnfEntity.setVnfDescriptorName(JsonUtil.getJsonFieldStr(jsonObject, "vnf-discriptor-name"));
        vnfEntity.setJobId(JsonUtil.getJsonFieldStr(jsonObject, "job-id"));
        vnfEntity.setMaxCpu(JsonUtil.getJsonFieldInt(jsonObject, "vcpu"));
        vnfEntity.setMaxDisk(JsonUtil.getJsonFieldInt(jsonObject, "vdisk"));
        vnfEntity.setMaxRam(JsonUtil.getJsonFieldInt(jsonObject, "vmemory"));
        vnfEntity.setMaxNet(JsonUtil.getJsonFieldInt(jsonObject, "vnet"));
        vnfEntity.setMaxShd(JsonUtil.getJsonFieldInt(jsonObject, "nshd"));
        vnfEntity.setMaxVm(JsonUtil.getJsonFieldInt(jsonObject, "nvm"));
        vnfEntity.setVnfResourceVersion(JsonUtil.getJsonFieldStr(jsonObject, "resource-version"));
        return vnfEntity;
    }

    public static void updateEntityWithVnfmInfo(VnfEntity vnfEntity, JSONObject jsonObject) {
         vnfEntity.setVnfmId(JsonUtil.getJsonFieldStr(jsonObject, "vnfm-id"));
        vnfEntity.setVnfmName(JsonUtil.getJsonFieldStr(jsonObject, "vnfm-name"));
        vnfEntity.setVnfmResourceVersion(JsonUtil.getJsonFieldStr(jsonObject, "resource-version"));
        vnfEntity.setVimId(JsonUtil.getJsonFieldStr(jsonObject, "vim-id"));

        // vim id stores the info as vim-id: <cloud-region-owner>_<cloud-region-id>
        // and esr-system-info contains vim info.
        String str = vnfEntity.getVimId();

        if (!(str == null) && str.contains("_")){
        String[] result = str.split("_");
        RestfulParametes restfulParametes = new RestfulParametes();
        restfulParametes.setHeaderMap(RequestUtil.getAAIHeaderMap());
        RestfulResponse restfulResponse = RestfulUtil
                .getRestfulResponse(Config.getHost() +":" + Config.getPort() + UrlConstant.CLOUD_REGION_URL
        + result[0] + "/" + result[1] + "/esr-system-info-list/esr-system-info", restfulParametes, "get");

        if(restfulResponse.isSuccess()) {
            vnfEntity.setVimId(JsonUtil.getJsonFieldStr(JSONObject.fromObject(restfulResponse.getResponseContent()), "esr-system-info-id"));
            vnfEntity.setVimName(JsonUtil.getJsonFieldStr(JSONObject.fromObject(restfulResponse.getResponseContent()), "system-name"));
            vnfEntity.setVimTenant(JsonUtil.getJsonFieldStr(JSONObject.fromObject(restfulResponse.getResponseContent()), "default-tenant"));
        }
        }
        else{

            //do nothing
        }
    }

    public static void updateEntityWithNsInfo(VnfEntity vnfEntity, JSONObject jsonObject) {
        vnfEntity.setNsId(JsonUtil.getJsonFieldStr(jsonObject, "service-instance-id"));
        vnfEntity.setNsName(JsonUtil.getJsonFieldStr(jsonObject, "service-instance-name"));
    }

    public String toStringForAai() {
        JSONObject vnfResJson = new JSONObject();
        vnfResJson.put("vnf-id", StringUtils.trimToEmpty(this.getId()));
        vnfResJson.put("vnf-name", StringUtils.trimToEmpty(this.getVnfInstanceName()));
        vnfResJson.put("vnf-name2", StringUtils.trimToEmpty(this.getName()));
        vnfResJson.put("service-id", StringUtils.trimToEmpty(this.getNsId()));
        vnfResJson.put("vnf-type", StringUtils.trimToEmpty(this.getVnfType()));
        vnfResJson.put("vcpu", this.getMaxCpu());
        vnfResJson.put("vdisk", this.getMaxDisk());
        vnfResJson.put("vmemory", this.getMaxRam());
        vnfResJson.put("nshd", this.getMaxShd());
        vnfResJson.put("nvm", this.getMaxVm());
        vnfResJson.put("nnet", this.getMaxNet());
        vnfResJson.put("vnf-package-name", StringUtils.trimToEmpty(this.getVnfPackageName()));
        vnfResJson.put("vnf-discriptor-name", StringUtils.trimToEmpty(this.getVnfDescriptorName()));
        vnfResJson.put("job-id", StringUtils.trimToEmpty(this.getJobId()));
        vnfResJson.put("orchestration-status", StringUtils.trimToEmpty(this.getVnfStatus()));

        JSONObject relationshipDataEntry = new JSONObject();
        relationshipDataEntry.put("relationship-key", "esr-vnfm.vnfm-id");
        relationshipDataEntry.put("relationship-value", this.getVnfmId());

        JSONArray relationshipData1 = new JSONArray();
        JSONObject customer = new JSONObject();
        customer.put("relationship-key", "customer.global-customer-id");
        customer.put("relationship-value", Config.getGlobalCustomerId());
        JSONObject service = new JSONObject();
        service.put("relationship-key", "service-subscription.service-type");
        service.put("relationship-value", Config.getServiceType());
        JSONObject serviceInstance = new JSONObject();
        serviceInstance.put("relationship-key", "service-instance.service-instance-id");
        serviceInstance.put("relationship-value", this.getNsId());
        relationshipData1.add(customer);
        relationshipData1.add(service);
        relationshipData1.add(serviceInstance);


        JSONArray relationshipData = new JSONArray();
        relationshipData.add(relationshipDataEntry);
        JSONArray relationship = new JSONArray();
        JSONObject relationshipEntry = new JSONObject();
        relationshipEntry.put("related-to", "esr-vnfm");
        relationshipEntry.put("relationship-data", relationshipData);
        JSONObject relationshipEntry1 = new JSONObject();
        relationshipEntry1.put("related-to", "service-instance");
        relationshipEntry1.put("relationship-data", relationshipData1);
        relationship.add(relationshipEntry);
        relationship.add(relationshipEntry1);

        JSONObject relation = new JSONObject();
        relation.put("relationship", relationship);

        vnfResJson.put("relationship-list", relation);
        return vnfResJson.toString();
    }

    public String toEsrVnfmStringForAai() {
        JSONObject esrVnfm = new JSONObject();
        esrVnfm.put("vnfm-id", StringUtils.trimToEmpty(this.getVnfmId()));
        esrVnfm.put("vim-id", StringUtils.trimToEmpty(this.getVimId()));
        return esrVnfm.toString();
    }

    @Override
    public String toString() {
        JSONObject vnfResJson = new JSONObject();
        vnfResJson.put("id", StringUtils.trimToEmpty(this.getId()));
        vnfResJson.put("name", StringUtils.trimToEmpty(this.getName()));
        vnfResJson.put("vnfInstanceId", StringUtils.trimToEmpty(this.getName()));
        vnfResJson.put("vnfInstanceName", StringUtils.trimToEmpty(this.getName()));
        vnfResJson.put("nsId", StringUtils.trimToEmpty(this.getName()));
        vnfResJson.put("nsName", StringUtils.trimToEmpty(this.getName()));
        vnfResJson.put("vnfmId", StringUtils.trimToEmpty(this.getName()));
        vnfResJson.put("vnfmName", StringUtils.trimToEmpty(this.getName()));
        vnfResJson.put("vnfPackageName", StringUtils.trimToEmpty(this.getId()));
        vnfResJson.put("vnfDescriptorName", StringUtils.trimToEmpty(this.getName()));
        vnfResJson.put("vimId", StringUtils.trimToEmpty(this.getName()));
        vnfResJson.put("vimName", StringUtils.trimToEmpty(this.getName()));
        vnfResJson.put("vimTenant", StringUtils.trimToEmpty(this.getName()));
        vnfResJson.put("jobId", StringUtils.trimToEmpty(this.getName()));
        vnfResJson.put("vnfStatus", StringUtils.trimToEmpty(this.getName()));
        vnfResJson.put("vnfType", StringUtils.trimToEmpty(this.getName()));
        vnfResJson.put("maxVm", StringUtils.trimToEmpty(this.getName()));
        vnfResJson.put("maxCpu", StringUtils.trimToEmpty(this.getName()));
        vnfResJson.put("maxDisk", StringUtils.trimToEmpty(this.getName()));
        vnfResJson.put("maxRam", StringUtils.trimToEmpty(this.getName()));
        vnfResJson.put("maxShd", StringUtils.trimToEmpty(this.getName()));
        vnfResJson.put("maxNet", StringUtils.trimToEmpty(this.getName()));
        return vnfResJson.toString();
    }

    public String getVnfmResourceVersion() {
        return vnfmResourceVersion;
    }

    public void setVnfmResourceVersion(String vnfmResourceVersion) {
        this.vnfmResourceVersion = vnfmResourceVersion;
    }

    public String getVnfResourceVersion() {
        return vnfResourceVersion;
    }

    public void setVnfResourceVersion(String vnfResourceVersion) {
        this.vnfResourceVersion = vnfResourceVersion;
    }
}
