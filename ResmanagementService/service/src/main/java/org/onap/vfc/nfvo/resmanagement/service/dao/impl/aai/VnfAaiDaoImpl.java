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

import org.onap.vfc.nfvo.resmanagement.common.util.RestfulUtil;
import org.onap.vfc.nfvo.resmanagement.common.util.request.RequestUtil;
import org.onap.vfc.nfvo.resmanagement.common.util.restclient.RestfulParametes;
import org.onap.vfc.nfvo.resmanagement.common.util.restclient.RestfulResponse;
import org.onap.vfc.nfvo.resmanagement.service.dao.inf.VnfDao;
import org.onap.vfc.nfvo.resmanagement.service.entity.VnfEntity;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class VnfAaiDaoImpl extends NsAaiDaoImpl implements VnfDao {

    private static int VNF_AAI_DAO_SUCCESS = 1;

    private static int VNF_AAI_DAO_FAIL = -1;

    @Override
    public List<VnfEntity> getVnfs(Map<String, Object> condition) {
        ArrayList<VnfEntity> vnfList = new ArrayList<>();

        if(condition.containsKey("id")) {
            vnfList.add(getVnf((String)condition.get("id")));
        } else {
            RestfulParametes restfulParametes = new RestfulParametes();
            restfulParametes.setHeaderMap(RequestUtil.getAAIHeaderMap());

            RestfulResponse response = RestfulUtil.getRestfulResponse(
                    "https://192.168.17.24:8443/aai/v11/network/generic-vnfs", restfulParametes, "get");

            JSONObject jsonObject = JSONObject.fromObject(response.getResponseContent());
            JSONArray jsonArray = jsonObject.getJSONArray("generic-vnf");

            jsonArray.forEach(genericVnf -> vnfList.add(VnfEntity.toEntityFromAai((JSONObject)genericVnf)));
        }
        return vnfList;
    }

    @Override
    public VnfEntity getVnf(String id) {
        RestfulParametes restfulParametes = new RestfulParametes();
        restfulParametes.setHeaderMap(RequestUtil.getAAIHeaderMap());

        RestfulResponse response = RestfulUtil.getRestfulResponse(
                "https://192.168.17.24:8443/aai/v11/network/generic-vnfs/generic-vnf/" + id, restfulParametes, "get");

        JSONObject jsonObject = JSONObject.fromObject(response.getResponseContent());
        VnfEntity vnfEntity = VnfEntity.toEntityFromAai(jsonObject);
        return vnfEntity;
    }

    @Override
    public int addVnf(VnfEntity vnfEntity) {
        RestfulParametes restfulParametes = new RestfulParametes();
        restfulParametes.setHeaderMap(RequestUtil.getAAIHeaderMap());
        restfulParametes.setRawData(vnfEntity.toStringForAai());

        RestfulResponse response = RestfulUtil.getRestfulResponse(
                "https://192.168.17.24:8443/aai/v11/network/generic-vnfs/generic-vnf/" + vnfEntity.getVnfInstanceId(),
                restfulParametes, "put");

        return response == null || response.getStatus() == -1 ? VNF_AAI_DAO_FAIL : VNF_AAI_DAO_SUCCESS;
    }

    @Override
    public int deleteVnfById(String id) {
        VnfEntity vnfEntity = getVnf(id);

        if(vnfEntity != null) {
            RestfulParametes restfulParametes = new RestfulParametes();
            restfulParametes.setHeaderMap(RequestUtil.getAAIHeaderMap());
            restfulParametes.put("resource-version", vnfEntity.getResourceVersion());

            RestfulResponse response = RestfulUtil
                    .getRestfulResponse("https://192.168.17.24:8443/aai/v11/network/generic-vnfs/generic-vnf/"
                            + vnfEntity.getVnfInstanceId(), restfulParametes, "delete");
        }
        return 1;
    }
}
