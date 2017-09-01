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

package org.onap.vfc.nfvo.resmanagement.service.mapper;

import java.util.List;
import java.util.Map;

import org.onap.vfc.nfvo.resmanagement.service.entity.NsEntity;

public interface NsEntityMapper {

    int deleteByPrimaryKey(String id);

    int insert(NsEntity record);

    int insertSelective(NsEntity record);

    NsEntity selectByPrimaryKey(String id);

    List<NsEntity> getAllNs(Map<String, Object> condition);

    int updateByPrimaryKeySelective(NsEntity record);

    int updateByPrimaryKey(NsEntity record);
}
