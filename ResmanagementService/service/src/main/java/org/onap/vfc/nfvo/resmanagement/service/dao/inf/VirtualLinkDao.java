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

package org.onap.vfc.nfvo.resmanagement.service.dao.inf;

import java.util.List;
import java.util.Map;

import org.onap.vfc.nfvo.resmanagement.service.entity.VirtualLinkEntity;

/**
 * <br>
 * <p>
 * </p>
 * 
 * @author
 * @version VFC 1.0 Oct 28, 2016
 */
public interface VirtualLinkDao {

    /**
     * <br>
     * 
     * @param id
     * @return
     * @since VFC 1.0
     */
    VirtualLinkEntity getVl(String id);

    /**
     * <br>
     * 
     * @param condition
     * @return
     * @since VFC 1.0
     */
    List<VirtualLinkEntity> getVls(Map<String, Object> condition);

    /**
     * <br>
     * 
     * @param virtualLinkEntity
     * @return
     * @since VFC 1.0
     */
    int addVl(VirtualLinkEntity virtualLinkEntity);

    /**
     * <br>
     * 
     * @param id
     * @return
     * @since VFC 1.0
     */
    int deleteVlById(String id);
}
