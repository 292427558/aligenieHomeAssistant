-- drop table if exists oauth_client_token;
create table IF NOT EXISTS oauth_client_token (
    token_id VARCHAR(255),
    token LONG VARBINARY,
    authentication_id VARCHAR(255) PRIMARY KEY,
    user_name VARCHAR(255),
    client_id VARCHAR(255)
);

-- drop table if exists oauth_access_token;
create table IF NOT EXISTS oauth_access_token (
    token_id VARCHAR(255),
    token LONG VARBINARY,
    authentication_id VARCHAR(255) PRIMARY KEY,
    user_name VARCHAR(255),
    client_id VARCHAR(255),
    authentication LONG VARBINARY,
    refresh_token VARCHAR(255)
);

-- drop table if exists oauth_refresh_token;
create table IF NOT EXISTS oauth_refresh_token (
    token_id VARCHAR(255),
    token LONG VARBINARY,
    authentication LONG VARBINARY
);

-- drop table if exists oauth_code;
create table IF NOT EXISTS oauth_code (
    code VARCHAR(255), authentication LONG VARBINARY
);

-- drop table if exists oauth_approvals;
create table IF NOT EXISTS oauth_approvals (
    userId VARCHAR(255),
    clientId VARCHAR(255),
    scope VARCHAR(255),
    status VARCHAR(10),
    expiresAt TIMESTAMP,
    lastModifiedAt datetime
);


-- DROP TABLE IF EXISTS `hass_device`;
CREATE TABLE IF NOT EXISTS  `hass_device`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `brand` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `device_name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `device_type` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `entity_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `model` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `device_zone` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_9qiqbmg7epocmyb2nfjuxlyp`(`entity_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for hass_device_type
-- ----------------------------
-- DROP TABLE IF EXISTS `hass_device_type`;
CREATE TABLE IF NOT EXISTS  `hass_device_type`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `english_name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for hass_device_type_operation
-- ----------------------------
-- DROP TABLE IF EXISTS `hass_device_type_operation`;
CREATE TABLE IF NOT EXISTS  `hass_device_type_operation`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `service` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `device_type_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `operation_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `request_type` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `response_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_g9yyhmj7piqvmw19nkof82kxl`(`service`) USING BTREE,
  INDEX `FKbke6jviy8tgdlhoijhl90agty`(`device_type_id`) USING BTREE,
  INDEX `FK92fxmmyah1u25pwi1cyg7buwt`(`operation_id`) USING BTREE,
  CONSTRAINT `FK92fxmmyah1u25pwi1cyg7buwt` FOREIGN KEY (`operation_id`) REFERENCES `hass_operation` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKbke6jviy8tgdlhoijhl90agty` FOREIGN KEY (`device_type_id`) REFERENCES `hass_device_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for hass_operation
-- ----------------------------
-- DROP TABLE IF EXISTS `hass_operation`;
CREATE TABLE IF NOT EXISTS  `hass_operation`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `friendly_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_crgrlc03trfcfl7bumioiua4p`(`friendly_name`) USING BTREE,
  UNIQUE INDEX `UK_6mnv7yvwuhw1yoaug2blq8gua`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for hass_service_parameter
-- ----------------------------
-- DROP TABLE IF EXISTS `hass_service_parameter`;
CREATE TABLE IF NOT EXISTS  `hass_service_parameter`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_conversion` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `parameter` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `device_type_operation_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKs3m5r4obmoadjbocnipjax4b9`(`device_type_operation_id`) USING BTREE,
  CONSTRAINT `FKs3m5r4obmoadjbocnipjax4b9` FOREIGN KEY (`device_type_operation_id`) REFERENCES `hass_device_type_operation` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for hass_service_parameterr_conversion
-- ----------------------------
-- DROP TABLE IF EXISTS `hass_service_parameterr_conversion`;
CREATE TABLE IF NOT EXISTS  `hass_service_parameterr_conversion`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ali_parameter_attr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ali_parameter_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `hass_parameter` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `service_parameter_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKtf7ak35a4xsopew6jvhqpc5rj`(`service_parameter_id`) USING BTREE,
  CONSTRAINT `FKtf7ak35a4xsopew6jvhqpc5rj` FOREIGN KEY (`service_parameter_id`) REFERENCES `hass_service_parameter` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
