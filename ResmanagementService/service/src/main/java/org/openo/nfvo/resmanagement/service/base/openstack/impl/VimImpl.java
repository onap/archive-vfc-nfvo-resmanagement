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

package org.openo.nfvo.resmanagement.service.base.openstack.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.nfvo.resmanagement.service.base.openstack.inf.Vim;
import org.openo.nfvo.resmanagement.service.business.inf.VimBusiness;
import org.openo.nfvo.resmanagement.service.entity.VimEntity;

import net.sf.json.JSONObject;

/**
 * Vim info interface.<br/>
 * <p>
 * </p>
 *
 * @author
 * @version NFVO 0.5 Aug 24, 2016
 */
public class VimImpl implements Vim {

    private VimBusiness vimBusiness;

    @Override
    public int add(String id) throws ServiceException {
        return vimBusiness.addVim(id);
    }

    @Override
    public int add(JSONObject jsonObject) throws ServiceException {
        return add(jsonObject.getString("id"));
    }

    @Override
    public int update(JSONObject jsonObject) throws ServiceException {
        return 0;
    }

    @Override
    public int delete(String id) throws ServiceException {
        return vimBusiness.deleteVim(id);
    }

    @Override
    public VimEntity getVim(String id) throws ServiceException {
        return vimBusiness.getVim(id);
    }

    @Override
    public List<VimEntity> getList() throws ServiceException {
        return vimBusiness.getVims();
    }

    @Override
    public List<JSONObject> getVimInfo(Map<String, Object> condition) throws ServiceException {
        // get vim from vimadapter
        return new ArrayList<JSONObject>();
    }

    public void setVimBusiness(VimBusiness vimBusiness) {
        this.vimBusiness = vimBusiness;
    }

}
