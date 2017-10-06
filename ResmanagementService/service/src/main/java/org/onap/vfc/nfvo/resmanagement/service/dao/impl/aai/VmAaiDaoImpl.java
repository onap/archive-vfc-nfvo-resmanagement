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

package org.onap.vfc.nfvo.resmanagement.service.dao.impl.aai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.onap.vfc.nfvo.resmanagement.common.conf.Config;
import org.onap.vfc.nfvo.resmanagement.common.constant.ParamConstant;
import org.onap.vfc.nfvo.resmanagement.common.util.RestfulUtil;
import org.onap.vfc.nfvo.resmanagement.common.util.request.RequestUtil;
import org.onap.vfc.nfvo.resmanagement.common.util.restclient.RestfulParametes;
import org.onap.vfc.nfvo.resmanagement.common.util.restclient.RestfulResponse;
import org.onap.vfc.nfvo.resmanagement.service.dao.inf.VmDao;
import org.onap.vfc.nfvo.resmanagement.service.entity.VmEntity;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class VmAaiDaoImpl implements VmDao {

    private static int VNF_AAI_DAO_SUCCESS = 1;

    private static int VNF_AAI_DAO_FAIL = -1;

    @Override
    public VmEntity getVm(String id) {
        VmEntity vmEntity = null;
        RestfulParametes restfulParametes = new RestfulParametes();
        restfulParametes.setHeaderMap(RequestUtil.getAAIHeaderMap());

        RestfulResponse response = RestfulUtil.getRestfulResponse(
                "https://192.168.17.24:8443/aai/v11/cloud-infrastructure/cloud-regions/cloud-region/"
                        + Config.getCloudOwner() + "/" + Config.getCloudRegionId() + "/tenants/tenant/"
                        + Config.getTenantId() + "/vservers/vserver/" + id,
                restfulParametes, "get");

        if(response.isSuccess()) {
            JSONObject jsonObject = JSONObject.fromObject(response.getResponseContent());
            vmEntity = updateVnfInstnaceId(VmEntity.toEntityFromAai(jsonObject), jsonObject);
        }

        return vmEntity;
    }

    private int checkGenericVnfExist(VmEntity vmEntity) {
        if(!StringUtils.isEmpty(vmEntity.getVnfInstanceId())) {
            RestfulParametes restfulParametes = new RestfulParametes();
            restfulParametes.setHeaderMap(RequestUtil.getAAIHeaderMap());

            RestfulResponse response = RestfulUtil
                    .getRestfulResponse("https://192.168.17.24:8443/aai/v11/network/generic-vnfs/generic-vnf/"
                            + vmEntity.getVnfInstanceId(), restfulParametes, "get");
            if(!response.isSuccess()) {
                return VNF_AAI_DAO_FAIL;
            }
        }
        return VNF_AAI_DAO_SUCCESS;
    }

    @Override
    public int addVm(VmEntity vmEntity) {
        if(checkGenericVnfExist(vmEntity) == VNF_AAI_DAO_SUCCESS) {
            RestfulParametes restfulParametes = new RestfulParametes();
            restfulParametes.setHeaderMap(RequestUtil.getAAIHeaderMap());
            restfulParametes.setRawData(vmEntity.toStringForAai());
            RestfulResponse response = RestfulUtil.getRestfulResponse(
                    "https://192.168.17.24:8443/aai/v11/cloud-infrastructure/cloud-regions/cloud-region/"
                            + Config.getCloudOwner() + "/" + Config.getCloudRegionId() + "/tenants/tenant/"
                            + Config.getTenantId() + "/vservers/vserver/" + vmEntity.getVmId(),
                    restfulParametes, "put");

            if(response.isSuccess()) {
                return VNF_AAI_DAO_SUCCESS;
            }
        }
        return VNF_AAI_DAO_FAIL;
    }

    @Override
    public List<VmEntity> getVms(Map<String, Object> condition) {
        List<VmEntity> vmEntities = new ArrayList<>();
        if(condition.containsKey(ParamConstant.PARAM_ID)) {
            VmEntity vmEntity = getVm((String)condition.get(ParamConstant.PARAM_ID));
            if(vmEntity != null) {
                vmEntities.add(vmEntity);
            }
        } else {
            RestfulParametes restfulParametes = new RestfulParametes();
            restfulParametes.setHeaderMap(RequestUtil.getAAIHeaderMap());

            RestfulResponse response =
                    RestfulUtil
                            .getRestfulResponse(
                                    "https://192.168.17.24:8443/aai/v11/cloud-infrastructure/cloud-regions/cloud-region/"
                                            + Config.getCloudOwner() + "/" + Config.getCloudRegionId()
                                            + "/tenants/tenant/" + Config.getTenantId() + "/vservers",
                                    restfulParametes, "get");

            JSONObject jsonObject = JSONObject.fromObject(response.getResponseContent());
            JSONArray jsonArray = jsonObject.getJSONArray("vserver");

            // update vnfInstanceId
            for(int i = 0; i < jsonArray.size(); i++) {
                JSONObject vserverObj = jsonArray.getJSONObject(i);
                vmEntities.add(updateVnfInstnaceId(VmEntity.toEntityFromAai(vserverObj), vserverObj));
            }
        }
        return vmEntities;
    }

    private VmEntity updateVnfInstnaceId(VmEntity vmEntity, JSONObject jsonObj) {
        if(jsonObj.has("relationship-list")) {
            JSONArray relList = (JSONArray)((JSONObject)jsonObj.get("relationship-list")).get("relationship");
            for(int i = 0; i < relList.size(); i++) {
                JSONObject obj = relList.getJSONObject(i);
                if("generic-vnf".equals(obj.getString("related-to"))) {
                    vmEntity.setVnfInstanceId(StringUtils.substringAfterLast(obj.getString("related-link"), "/"));
                }
            }
        }
        return vmEntity;
    }

    @Override
    public int deleteVmById(String id) {
        VmEntity vmEntity = getVm(id);

        if(vmEntity != null) {
            RestfulParametes restfulParametes = new RestfulParametes();
            restfulParametes.setHeaderMap(RequestUtil.getAAIHeaderMap());
            restfulParametes.put("resource-version", vmEntity.getResourceVersion());

            RestfulResponse response =
                    RestfulUtil
                            .getRestfulResponse(
                                    "https://192.168.17.24:8443/aai/v11/cloud-infrastructure/cloud-regions/cloud-region/"
                                            + Config.getCloudOwner() + "/" + Config.getCloudRegionId()
                                            + "/tenants/tenant/" + Config.getTenantId() + "/vservers/vserver/" + id,
                                    restfulParametes, "delete");

            if(response.isSuccess()) {
                return VNF_AAI_DAO_SUCCESS;
            }
        }
        return VNF_AAI_DAO_FAIL;
    }

    @Override
    public int updateVm(VmEntity vmEntity) {

        VmEntity vm = this.getVm(vmEntity.getVmId());
        vmEntity.setResourceVersion(vm.getResourceVersion());

        RestfulParametes restfulParametes = new RestfulParametes();
        restfulParametes.setHeaderMap(RequestUtil.getAAIHeaderMap());
        restfulParametes.setRawData(vmEntity.toStringForAai());

        RestfulResponse response =
                RestfulUtil.getRestfulResponse(
                        "https://192.168.17.24:8443/aai/v11/cloud-infrastructure/cloud-regions/cloud-region/"
                                + Config.getCloudOwner() + "/" + Config.getCloudRegionId() + "/tenants/tenant/"
                                + Config.getTenantId() + "/vservers/vserver/" + vmEntity.getVmId(),
                        restfulParametes, "put");
        return response.isSuccess() ? VNF_AAI_DAO_SUCCESS : VNF_AAI_DAO_FAIL;
    }

    @Override
    public int deleteVmByVnfId(String vnfInstanceId) {
        List<VmEntity> vms = this.getVms(new HashMap<>());
        Optional<VmEntity> vmOpt = vms.stream().filter(vm -> vnfInstanceId.equals(vm.getVnfInstanceId())).findFirst();
        if(vmOpt.isPresent()) {
            return this.deleteVmById(vmOpt.get().getVmId());
        }
        return VNF_AAI_DAO_FAIL;
    }

}
