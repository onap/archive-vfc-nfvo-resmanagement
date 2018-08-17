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

package org.onap.vfc.nfvo.resmanagement.service.dao.impl.aai;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.onap.vfc.nfvo.resmanagement.common.conf.Config;
import org.onap.vfc.nfvo.resmanagement.common.constant.UrlConstant;
import org.onap.vfc.nfvo.resmanagement.common.util.RestfulUtil;
import org.onap.vfc.nfvo.resmanagement.common.util.request.RequestUtil;
import org.onap.vfc.nfvo.resmanagement.common.util.restclient.RestfulParametes;
import org.onap.vfc.nfvo.resmanagement.common.util.restclient.RestfulResponse;
import org.onap.vfc.nfvo.resmanagement.service.dao.inf.VnfDao;
import org.onap.vfc.nfvo.resmanagement.service.entity.VnfEntity;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class VnfAaiDaoImpl implements VnfDao {

    private static int VNF_AAI_DAO_SUCCESS = 1;

    private static int VNF_AAI_DAO_FAIL = -1;

    @Override
    public List<VnfEntity> getVnfs(Map<String, Object> condition) {
        ArrayList<VnfEntity> vnfList = new ArrayList<>();

        if (condition.containsKey("id")) {
            VnfEntity vnf = getVnf((String)condition.get("id"));
            if(vnf != null) {
                vnfList.add(vnf);
            }
        } else {
            RestfulParametes restfulParametes = new RestfulParametes();
            restfulParametes.setHeaderMap(RequestUtil.getAAIHeaderMap());

            RestfulResponse response = RestfulUtil.getRestfulResponse(
                    Config.getHost() +":" + Config.getPort() + UrlConstant.GENERIC_VNFS_URL, restfulParametes, "get");

            if(response.isSuccess()) {
                JSONObject jsonObject = JSONObject.fromObject(response.getResponseContent());
                JSONArray jsonArray = jsonObject.getJSONArray("generic-vnf");

                for(int i = 0; i < jsonArray.size(); i++) {
                    JSONObject genericVnf = jsonArray.getJSONObject(i);
                    VnfEntity entityFromAai = VnfEntity.toEntityFromAai(genericVnf);
                    vnfList.add(updateVnfInfo(entityFromAai, genericVnf));
                }
            }
        }
        return vnfList;
    }

    private VnfEntity updateVnfInfo(VnfEntity vnfEntity, JSONObject jsonObject) {
        updateVnfmInfo(vnfEntity, jsonObject);
        updateNsInfo(vnfEntity, jsonObject);
        return vnfEntity;
    }

    private VnfEntity updateVnfmInfo(VnfEntity vnfEntity, JSONObject jsonObject) {
        if(!jsonObject.has("relationship-list")) {
            return vnfEntity;
        }

        JSONArray relList = (JSONArray)((JSONObject)jsonObject.get("relationship-list")).get("relationship");
        for(int i = 0; i < relList.size(); i++) {
            JSONObject obj = relList.getJSONObject(i);
            if("esr-vnfm".equals(obj.getString("related-to"))) {
                String relatedLink = obj.getString("related-link");

                RestfulParametes restfulParametes = new RestfulParametes();
                restfulParametes.setHeaderMap(RequestUtil.getAAIHeaderMap());
                RestfulResponse restfulResponse = RestfulUtil
                        .getRestfulResponse(Config.getHost() +":" + Config.getPort() + relatedLink, restfulParametes, "get");

                if(restfulResponse.isSuccess()) {
                    VnfEntity.updateEntityWithVnfmInfo(vnfEntity,
                            JSONObject.fromObject(restfulResponse.getResponseContent()));
                    break;
                }
            }
        }
        return vnfEntity;
    }

    private VnfEntity updateNsInfo(VnfEntity vnfEntity, JSONObject jsonObject) {
        if(!jsonObject.has("relationship-list")) {
            return vnfEntity;
        }

        JSONArray relList = (JSONArray)((JSONObject)jsonObject.get("relationship-list")).get("relationship");
        for(int i = 0; i < relList.size(); i++) {
            JSONObject obj = relList.getJSONObject(i);
            if("service-instance".equals(obj.getString("related-to"))) {
                String relatedLink = obj.getString("related-link");

                RestfulParametes restfulParametes = new RestfulParametes();
                restfulParametes.setHeaderMap(RequestUtil.getAAIHeaderMap());
                RestfulResponse restfulResponse = RestfulUtil
                        .getRestfulResponse(Config.getHost() +":" + Config.getPort() + relatedLink, restfulParametes, "get");

                if(restfulResponse.isSuccess()) {
                    VnfEntity.updateEntityWithNsInfo(vnfEntity,
                            JSONObject.fromObject(restfulResponse.getResponseContent()));
                    break;
                }
            }
        }
        return vnfEntity;
    }

    @Override
    public VnfEntity getVnf(String id) {
        VnfEntity vnfEntity = null;
        RestfulParametes restfulParametes = new RestfulParametes();
        restfulParametes.setHeaderMap(RequestUtil.getAAIHeaderMap());

        RestfulResponse response = RestfulUtil.getRestfulResponse(
                Config.getHost() +":" + Config.getPort() + UrlConstant.GENERIC_VNF_URL + id, restfulParametes, "get");

        if(response.isSuccess()) {
            JSONObject jsonObject = JSONObject.fromObject(response.getResponseContent());
            vnfEntity = VnfEntity.toEntityFromAai(jsonObject);
            updateVnfInfo(vnfEntity, jsonObject);
        }
        return vnfEntity;
    }

    private int checkVnfmEntity(VnfEntity vnfEntity) {
        RestfulParametes restfulParametes = new RestfulParametes();

        restfulParametes.setHeaderMap(RequestUtil.getAAIHeaderMap());
        RestfulResponse response = RestfulUtil.getRestfulResponse(
                Config.getHost() +":" + Config.getPort() + UrlConstant.ESR_VNFM_URL + vnfEntity.getVnfmId(),
                restfulParametes, "get");
        return response == null || !response.isSuccess() ? VNF_AAI_DAO_FAIL : VNF_AAI_DAO_SUCCESS;
    }

    private int checkServiceInstanceEntity(VnfEntity vnfEntity) {
        RestfulParametes restfulParametes = new RestfulParametes();

        restfulParametes.setHeaderMap(RequestUtil.getAAIHeaderMap());
        RestfulResponse response = RestfulUtil.getRestfulResponse(
                Config.getHost() +":" + Config.getPort() + UrlConstant.CUSTOMER_URL + Config.getGlobalCustomerId() + UrlConstant.SERVICE_SUBSCRIPTION_URL
                + Config.getServiceType() + UrlConstant.SERVICE_INSTANCE_URL + vnfEntity.getNsId(), restfulParametes, "get");
        return response == null || !response.isSuccess() ? VNF_AAI_DAO_FAIL : VNF_AAI_DAO_SUCCESS;
    }


    private int addVnfToAAI(VnfEntity vnfEntity) {
        RestfulParametes restfulParametes = new RestfulParametes();

        restfulParametes.setHeaderMap(RequestUtil.getAAIHeaderMap());
        restfulParametes.setRawData(vnfEntity.toStringForAai());

        RestfulResponse response = RestfulUtil.getRestfulResponse(
                Config.getHost() +":" + Config.getPort() + UrlConstant.GENERIC_VNF_URL + vnfEntity.getId(),
                restfulParametes, "put");
        return response == null || !response.isSuccess() ? VNF_AAI_DAO_FAIL : VNF_AAI_DAO_SUCCESS;
    }

    @Override
    public int addVnf(VnfEntity vnfEntity) {

        if(checkVnfmEntity(vnfEntity) == VNF_AAI_DAO_SUCCESS) {
            if(checkServiceInstanceEntity(vnfEntity) == VNF_AAI_DAO_SUCCESS){
                if(addVnfToAAI(vnfEntity) == VNF_AAI_DAO_SUCCESS) {
                    return VNF_AAI_DAO_SUCCESS;
                }

            }
        }

        return VNF_AAI_DAO_FAIL;
    }

    private int deteletVnfm(VnfEntity vnfEntity) {
        if(!StringUtils.isEmpty(vnfEntity.getVnfmResourceVersion())) {
            RestfulParametes restfulParametes = new RestfulParametes();
            restfulParametes.setHeaderMap(RequestUtil.getAAIHeaderMap());
            restfulParametes.put("resource-version", vnfEntity.getVnfmResourceVersion());

            RestfulResponse response = RestfulUtil
                    .getRestfulResponse(Config.getHost() +":" + Config.getPort() + UrlConstant.ESR_VNFM_URL
                            + vnfEntity.getVnfmId(), restfulParametes, "delete");
            return response == null || !response.isSuccess() ? VNF_AAI_DAO_FAIL : VNF_AAI_DAO_SUCCESS;
        }
        return VNF_AAI_DAO_FAIL;
    }

    @Override
    public int deleteVnfById(String id) {
        VnfEntity vnfEntity = getVnf(id);

        if(vnfEntity != null) {
            RestfulParametes restfulParametes = new RestfulParametes();
            restfulParametes.setHeaderMap(RequestUtil.getAAIHeaderMap());
            restfulParametes.put("resource-version", vnfEntity.getVnfResourceVersion());
            RestfulResponse response = RestfulUtil
                    .getRestfulResponse(Config.getHost() +":" + Config.getPort() + UrlConstant.GENERIC_VNF_URL
                            + vnfEntity.getVnfInstanceId(), restfulParametes, "delete");

            if(response.isSuccess()) {
                deteletVnfm(vnfEntity);
                return VNF_AAI_DAO_SUCCESS;
            }
        }
        return VNF_AAI_DAO_FAIL;
    }
}
