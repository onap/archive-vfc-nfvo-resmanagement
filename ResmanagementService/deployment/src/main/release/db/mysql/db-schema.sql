#*******************************************************************************
# Copyright 2016 Huawei Technologies Co., Ltd.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#*******************************************************************************

/******************drop old database and user***************************/
use mysql;
drop database IF  EXISTS resmanagementdb;
delete from user where User='resmanagement';
FLUSH PRIVILEGES;

/******************create new database and user***************************/
create database resmanagementdb CHARACTER SET utf8;

GRANT ALL PRIVILEGES ON resmanagementdb.* TO 'resmanagement'@'%' IDENTIFIED BY 'resmanagement' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON mysql.* TO 'resmanagement'@'%' IDENTIFIED BY 'resmanagement' WITH GRANT OPTION;

GRANT ALL PRIVILEGES ON resmanagementdb.* TO 'resmanagement'@'localhost' IDENTIFIED BY 'resmanagement' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON mysql.* TO 'resmanagement'@'localhost' IDENTIFIED BY 'resmanagement' WITH GRANT OPTION;
FLUSH PRIVILEGES;

use resmanagementdb;
set Names 'utf8';

/******************drop old table and create new***************************/

DROP TABLE IF EXISTS host;
CREATE TABLE host (
    ID                        VARCHAR(128)       NOT NULL,
    NAME                    VARCHAR(256)       NULL,
    CPU                      VARCHAR(256)       NULL,
    MEMORY                  VARCHAR(256)       NULL,
    DISK                      VARCHAR(256)       NULL,
    VIM_ID                     VARCHAR(256)       NULL,
    VIM_NAME                  VARCHAR(256)       NULL,
    CONSTRAINT host PRIMARY KEY(ID)
);

DROP TABLE IF EXISTS location;
CREATE TABLE location (
    ID                        VARCHAR(128)       NOT NULL,
    COUNTRY                 VARCHAR(256)       NULL,
    LOCATION                  VARCHAR(256)       NULL,
    LATITUDE                VARCHAR(256)       NULL,
    LONGITUDE               VARCHAR(256)       NULL,
    DESCRIPTION             VARCHAR(256)       NULL,
    CONSTRAINT location PRIMARY KEY(ID)
);

DROP TABLE IF EXISTS network;
CREATE TABLE network (
    ID                        VARCHAR(128)       NOT NULL,
    NAME                    VARCHAR(256)       NULL,
    TENANT_ID                  VARCHAR(256)       NULL,
    VIM_ID                  VARCHAR(256)       NULL,
    VIM_NAME                   VARCHAR(256)       NULL,
    STATUS                     VARCHAR(256)       NULL,
    PHYSICAL_NETWORK        VARCHAR(256)       NULL,
    NETWORK_TYPE            VARCHAR(256)       NULL,
    SEGMENTATION_ID         VARCHAR(256)       NULL,
    CONSTRAINT network PRIMARY KEY(ID)
);

DROP TABLE IF EXISTS port;
CREATE TABLE port (
    ID                        VARCHAR(128)       NOT NULL,
    NAME                    VARCHAR(256)       NULL,
    NWTWORK_ID              VARCHAR(256)       NULL,
    STATUS                  VARCHAR(256)       NULL,
    TENANT_ID               VARCHAR(256)       NULL,
    VIM_ID                     VARCHAR(256)       NULL,
    VIM_NAME                  VARCHAR(256)       NULL,
    CONSTRAINT port PRIMARY KEY(ID)
);

DROP TABLE IF EXISTS site;
CREATE TABLE site (
    ID                        VARCHAR(128)       NOT NULL,
    NAME                    VARCHAR(256)       NULL,
    LOCATION                  VARCHAR(256)       NULL,
    COUNTRY                 VARCHAR(256)       NULL,
    VIM_ID                   VARCHAR(256)       NULL,
    VIM_NAME                 VARCHAR(256)       NULL,
    STATUS                  VARCHAR(256)       NULL,
    TOTAL_CPU                  VARCHAR(256)       NULL,
    TOTAL_MEMORY            VARCHAR(256)       NULL,
    TOTAL_DISK              VARCHAR(256)       NULL,
    USED_CPU                VARCHAR(256)       NULL,
    USED_MEMORY             VARCHAR(256)       NULL,
    USED_DISK                 VARCHAR(256)       NULL,
    CONSTRAINT site PRIMARY KEY(ID)
);

DROP TABLE IF EXISTS vim;
CREATE TABLE vim (
    ID                        VARCHAR(128)       NOT NULL,
    CONSTRAINT vim PRIMARY KEY(ID)
);

DROP TABLE IF EXISTS vl;
CREATE TABLE vl (
  ID VARCHAR(128) NOT NULL,
  NAME VARCHAR(256) NULL,
  BACKEND_ID VARCHAR(256) NULL,
  IS_PUBLIC VARCHAR(256) NULL,
  DC_NAME VARCHAR(256) NULL,
  VIM_ID VARCHAR(256) NULL,
  VIM_NAME VARCHAR(256) NULL,
  PHYSICIAL_NET VARCHAR(256) NULL,
  NS_ID VARCHAR(256) NULL,
  NS_NAME VARCHAR(256) NULL,
  DESCRIPTION VARCHAR(256) NULL,
  NETWORK_TYPE VARCHAR(256) NULL,
  SEGMENTATION VARCHAR(256) NULL,
  MTU VARCHAR(256) NULL,
  VLAN_TRANSPARENT VARCHAR(256) NULL,
  ROUTER_EXTERNAL VARCHAR(256) NULL,
  RESOURCE_PROVIDER_TYPE VARCHAR(256) NULL,
  RESOURCE_PROVIDER_ID VARCHAR(256) NULL,
  CONSTRAINT vl PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS vnf;
CREATE TABLE vnf (
  ID VARCHAR(128) NOT NULL,
  VNF_INSTANCE_ID VARCHAR(256) NULL,
  VNF_INSTANCE_NAME VARCHAR(256) NULL,
  NS_ID VARCHAR(256) NULL,
  NS_NAME VARCHAR(256) NULL,
  VNFM_ID VARCHAR(256) NULL,
  VNFM_NAME VARCHAR(256) NULL,
  VNF_PACKAGE_NAME VARCHAR(256) NULL,
  VNF_DESCRIPTOR_NAME VARCHAR(256) NULL,
  VIM_ID VARCHAR(256) NULL,
  VIM_NAME VARCHAR(256) NULL,
  VIM_TENANT VARCHAR(256) NULL,
  JOB_ID VARCHAR(256) NULL,
  VNF_STATUS VARCHAR(256) NULL,
  VNF_TYPE VARCHAR(256) NULL,
  MAX_VM INT NULL,
  MAX_CPU INT NULL,
  MAX_DISK INT NULL,
  MAX_RAM INT NULL,
  MAX_SHD INT NULL,
  MAX_NET INT NULL,
  NAME VARCHAR(256) NULL,
  CONSTRAINT vnf PRIMARY KEY (ID)
);
  
DROP TABLE IF EXISTS vm;
CREATE TABLE vm (
  VM_ID VARCHAR(128) NOT NULL,
  VM_NAME VARCHAR(256) NULL,
  VM_STATUS VARCHAR(256) NULL,
  VNF_INSTANCE_ID VARCHAR(256) NULL,
  CONSTRAINT vm PRIMARY KEY (VM_ID)
);
  
DROP TABLE IF EXISTS vnfinfo;
CREATE TABLE vnfinfo (
  VNF_INSTANCE_ID VARCHAR(128) NOT NULL,
  NS_ID VARCHAR(256) NULL,
  VNFM_ID VARCHAR(256) NULL,
  CONSTRAINT vnfinfo PRIMARY KEY (VNF_INSTANCE_ID)
);
  
DROP TABLE IF EXISTS vnfstatus;
CREATE TABLE vnfstatus (
  VNF_INSTANCE_ID VARCHAR(128) NOT NULL,
  JOB_ID VARCHAR(256) NULL,
  NS_ID VARCHAR(256) NULL,
  VNFM_ID VARCHAR(256) NULL,
  RESPONSE_DESCRIPTOR VARCHAR(1024) NULL,
  STATUS VARCHAR(256) NULL,
  PROGRESS VARCHAR(256) NULL,
  STATUS_DESCRIPTION VARCHAR(256) NULL,
  ERROR_CODE VARCHAR(256) NULL,
  RESPONSE_ID VARCHAR(256) NULL,
  RESPONSE_HISTORY_LIST VARCHAR(1024) NULL,
  ADD_VM VARCHAR(1024) NULL,
  DEL_VM VARCHAR(1024) NULL,
  CONSTRAINT vnfstatus PRIMARY KEY (VNF_INSTANCE_ID)
);

