/*
 * Copyright 2017 Huawei Technologies Co., Ltd.
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

package org.onap.vfc.nfvo.resmanagement.service.group.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.onap.vfc.nfvo.resmanagement.common.util.restclient.ServiceException;
import org.onap.vfc.nfvo.resmanagement.service.dao.inf.NsDao;
import org.onap.vfc.nfvo.resmanagement.service.entity.NsEntity;
import org.onap.vfc.nfvo.resmanagement.service.group.inf.NsService;

import net.sf.json.JSONObject;

/**
 * <br>
 * <p>
 * </p>
 *
 * @author
 * @version VFC 1.0 Sep 4, 2017
 */
public class NsServiceImpl implements NsService {

    private static final Logger LOGGER = LogManager.getLogger(NsServiceImpl.class);

    private NsDao nsDao;

    @Override
    public JSONObject addNs(JSONObject object) {
        NsEntity nsEntity = NsEntity.toEntity(object);
        JSONObject restJson = new JSONObject();

        if(!checkId(nsEntity.getId())) {
            LOGGER.error("function=addNs; msg=add error, because id is already exist.");
            restJson.put("message", "Ns id is already exist.");
            return restJson;
        }

        if(StringUtils.isEmpty(nsEntity.getId())) {
            nsEntity.setId(UUID.randomUUID().toString());
        }
        int result = nsDao.addNs(nsEntity);

        if(result > 0) {
            restJson.put("ns", nsEntity);
        } else {
            LOGGER.error("function=addNs; msg=add ns into DB error.");
            restJson.put("message", "Add ns into DB error.");
        }
        return restJson;
    }

    private boolean checkId(String id) {
        NsEntity nsEntity = nsDao.getNs(id);
        if(nsEntity.getId() == null) {
            return true;
        }
        return false;
    }

    public void setNsDao(NsDao nsDao) {
        this.nsDao = nsDao;
    }

    @Override
    public List<NsEntity> getList(Map<String, Object> map) throws ServiceException {
        return nsDao.getAllNs(map);
    }

    @Override
    public int delete(String id) throws ServiceException {
        return nsDao.deleteNsById(id);
    }

}
