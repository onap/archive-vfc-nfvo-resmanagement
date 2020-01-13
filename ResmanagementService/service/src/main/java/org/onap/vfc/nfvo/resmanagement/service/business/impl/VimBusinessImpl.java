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

package org.onap.vfc.nfvo.resmanagement.service.business.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.onap.vfc.nfvo.resmanagement.common.ResourceUtil;
import org.onap.vfc.nfvo.resmanagement.common.util.StringUtil;
import org.onap.vfc.nfvo.resmanagement.service.business.inf.VimBusiness;
import org.onap.vfc.nfvo.resmanagement.service.dao.inf.VimDao;
import org.onap.vfc.nfvo.resmanagement.service.entity.VimEntity;
import org.onap.vfc.nfvo.resmanagement.common.util.restclient.ServiceException;

/**
 * Vim info interface.<br/>
 * <p>
 * </p>
 *
 * @author
 * @version VFC 1.0 Aug 24, 2016
 */
public class VimBusinessImpl implements VimBusiness {

    private static final Logger LOGGER = LogManager.getLogger(VimBusinessImpl.class);

    private VimDao vimDao;

    @Override
    public VimEntity getVim(String id) {
        if(StringUtils.isEmpty(id)) {
            LOGGER.error("function=getVim; msg=get error, because id is empty.");
            return null;
        }
        return vimDao.getVim(id);
    }

    @Override
    public List<VimEntity> getVims() {
        return vimDao.getVims();
    }

    @Override
    public int deleteVim(String id) throws ServiceException {
        if(StringUtils.isEmpty(id)) {
            LOGGER.error("function=deleteVim; msg=deleteVim error, because id is empty.");
            throw new ServiceException(
                    ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.base.vim.del.id.check"));
        }
        return vimDao.deleteVim(id);
    }

    @Override
    public int addVim(String id) throws ServiceException {
        if(!StringUtil.isValidString(id)) {
            LOGGER.error("function=addVim; msg=add error, because id is null.");
            throw new ServiceException(
                    ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.base.vim.add.id.null"));
        }
        if(!checkId(id)) {
            LOGGER.error("function=addVim; msg=add error, because id is already exist.");
            throw new ServiceException(
                    ResourceUtil.getMessage("org.openo.nfvo.resmanage.service.base.vim.add.id.check"));
        }

        VimEntity vimEntity = new VimEntity();
        vimEntity.setId(id);
        return vimDao.addVim(vimEntity);
    }

    private boolean checkId(String id) {
        VimEntity vim = vimDao.getVim(id);
        if(null == vim) {
            return true;
        }
        return false;
    }

    public void setVimDao(VimDao vimDao) {
        this.vimDao = vimDao;
    }

}
