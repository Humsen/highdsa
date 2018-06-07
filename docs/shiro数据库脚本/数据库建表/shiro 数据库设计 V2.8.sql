CREATE TABLE `sys_user` (
`user_id` bigint(19) NOT NULL COMMENT '开发模块分配分布式ID，长度18，此处设置为Long最大的字符长度',
`user_name` varchar(15) NOT NULL COMMENT '登录账号',
`user_email` varchar(30) NULL COMMENT '邮箱',
`user_phone` varchar(15) NULL COMMENT '手机号',
`user_password` varchar(32) NOT NULL COMMENT 'MD5加盐加密密码',
`user_pwd_salt` varchar(32) NOT NULL COMMENT '密码加密盐值',
`user_state` varchar(3) NOT NULL COMMENT '用户状态：100正常, 101锁定, 102注销',
PRIMARY KEY (`user_name`) 
);

CREATE TABLE `sys_user_info` (
`user_id` bigint(19) NOT NULL COMMENT '开发模块分配分布式ID，长度18，此处设置为Long最大的字符长度',
`user_nick_name` varchar(20) NULL COMMENT '昵称',
`user_register_time` timestamp(3) NOT NULL COMMENT '注册日期',
`user_head_url` varchar(255) NULL COMMENT '头像链接',
`user_sex` tinyint(1) NULL COMMENT '性别',
`user_birthday` varchar(8) NULL COMMENT '生日',
`user_desc` varchar(255) NULL COMMENT '描述，类似签名',
`user_last_login_time` timestamp(3) NOT NULL ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '最后一次登录',
PRIMARY KEY (`user_id`) 
);

CREATE TABLE `sys_user_role` (
`user_id` bigint(19) NOT NULL COMMENT '开发模块分配分布式ID，长度18，此处设置为Long最大的字符长度',
`role_id` bigint(19) NOT NULL COMMENT '开发模块分配分布式ID，长度18，此处设置为Long最大的字符长度',
PRIMARY KEY (`user_id`, `role_id`) 
);

CREATE TABLE `sys_role` (
`role_id` bigint(19) NOT NULL COMMENT '分布式ID，长度18，此处设置为Long最大的字符长度',
`role_name` varchar(16) NOT NULL COMMENT '角色名称，如管理员，总监',
`role_code` varchar(15) NOT NULL,
`role_desc` varchar(255) NULL COMMENT '角色描述',
`role_create_time` timestamp(3) NOT NULL COMMENT '角色创建时间',
`role_last_modify_time` timestamp(3) NOT NULL ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '角色最后修改时间',
`role_valid` tinyint(1) NOT NULL COMMENT '是否可用，0为假，1为真',
PRIMARY KEY (`role_id`) 
);

CREATE TABLE `sys_role_permission` (
`role_id` bigint(19) NOT NULL COMMENT '开发模块分配分布式ID',
`permission_id` bigint(19) NOT NULL COMMENT '分布式ID，长度18，此处设置为Long最大的字符长度',
PRIMARY KEY (`role_id`, `permission_id`) 
);

CREATE TABLE `sys_permission` (
`permission_id` bigint(19) NOT NULL COMMENT '分布式ID，长度18，此处设置为Long最大的字符长度',
`permission_name` varchar(36) NOT NULL COMMENT '权限名称，如 sys:user:create，对权限的概括说明',
`permission_code` varchar(36) NOT NULL COMMENT '权限代码，用于设置shiro权限',
`permission_desc` varchar(255) NULL COMMENT '权限描述',
`permission_navi` tinyint(1) NOT NULL COMMENT '是否为导航节点，0为假，1为真',
`permission_url` varchar(100) NULL COMMENT '获取相应导航页面的链接',
`permission_create_time` timestamp(3) NOT NULL COMMENT '权限创建时间',
`permission_last_modify_time` timestamp(3) NOT NULL ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '权限最后修改时间',
`permission_valid` tinyint(1) NOT NULL COMMENT '是否可用，0为假，1为真',
PRIMARY KEY (`permission_id`) 
);

CREATE TABLE `cust_user_info` (
`user_id` bigint(32) NOT NULL COMMENT '客户用户分布式id',
`user_nick_name` varchar(20) NULL COMMENT '客户用户昵称',
`user_register_time` timestamp(3) NOT NULL COMMENT '顾客用户注册时间',
`user_head_url` varchar(255) NULL COMMENT '头像地址',
`user_sex` tinyint(1) NULL COMMENT '性别',
`user_address` varchar(255) NULL COMMENT '地址',
`user_age` varchar(255) NULL COMMENT '年龄',
`user_birthday` varchar(8) NULL COMMENT '生日',
`user_desc` varchar(255) NULL COMMENT '描述',
`user_last_login_time` timestamp(3) NOT NULL ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '最后一次登录时间',
PRIMARY KEY (`user_id`) 
);

CREATE TABLE `cust_user` (
`user_id` bigint(32) NOT NULL COMMENT '顾客用户分布式Id',
`user_name` varchar(15) NOT NULL COMMENT '顾客用户账号',
`user_email` varchar(36) NULL COMMENT '顾客邮箱',
`user_phone` varchar(15) NULL COMMENT '客户电话',
`user_password` varchar(32) NOT NULL COMMENT '客户密码',
`user_pwd_salt` varchar(32) NOT NULL COMMENT '客户密码加盐',
`user_state` varchar(3) NOT NULL COMMENT '客户当前状态',
PRIMARY KEY (`user_name`) 
);

CREATE TABLE `cust_user_role` (
`user_id` bigint(32) NOT NULL COMMENT '客户分布式Id',
`role_id` bigint(32) NOT NULL COMMENT '角色分布式id',
PRIMARY KEY (`user_id`, `role_id`) 
);

CREATE TABLE `cust_role` (
`role_id` bigint(32) NOT NULL COMMENT '角色分布式id',
`role_name` varchar(16) NOT NULL COMMENT '角色名称',
`role_code` varchar(15) NOT NULL,
`role_desc` varchar(255) NULL COMMENT '角色描述',
`role_create_time` timestamp(3) NOT NULL,
`role_last_modify_time` timestamp(3) NOT NULL ON UPDATE CURRENT_TIMESTAMP(3),
`role_valid` tinyint(1) NOT NULL COMMENT '角色是否有效',
PRIMARY KEY (`role_id`) 
);

CREATE TABLE `cust_role_permission` (
`role_id` bigint(32) NOT NULL COMMENT '角色分布式id',
`permission_id` bigint(32) NOT NULL COMMENT '权限分布式id',
PRIMARY KEY (`role_id`, `permission_id`) 
);

CREATE TABLE `cust_permission` (
`permission_id` bigint(32) NOT NULL COMMENT '权限分布式id',
`permission_name` varchar(36) NOT NULL COMMENT '权限名称',
`permission_code` varchar(36) NOT NULL,
`permission_desc` varchar(255) NULL COMMENT '权限描述',
`permission_navi` tinyint(1) NOT NULL,
`permission_url` varchar(1000) NULL,
`permission_create_time` timestamp(3) NOT NULL,
`permission_last_modify_time` timestamp(3) NOT NULL ON UPDATE CURRENT_TIMESTAMP(3),
`permission_valid` tinyint(1) NOT NULL COMMENT '权限是否有效',
PRIMARY KEY (`permission_id`) 
);

CREATE TABLE `sys_sessions` (
`session_id` varchar(200) NOT NULL COMMENT '会话id',
`session_value` text NOT NULL COMMENT '会话序列化后的值',
`session_create_time` timestamp(3) NOT NULL,
`session_last_modify_time` timestamp(3) NOT NULL ON UPDATE CURRENT_TIMESTAMP(3),
`session_valid` tinyint(1) NOT NULL,
PRIMARY KEY (`session_id`) 
);

CREATE TABLE `cust_sessions` (
`session_id` varchar(200) NOT NULL,
`session_value` text NOT NULL,
`session_create_time` timestamp(3) NOT NULL,
`session_last_modify_time` timestamp(3) NOT NULL ON UPDATE CURRENT_TIMESTAMP(3),
`session_valid` tinyint(1) NOT NULL,
PRIMARY KEY (`session_id`) 
);


ALTER TABLE `sys_user` ADD CONSTRAINT `sys_user_role` FOREIGN KEY (`user_id`) REFERENCES `sys_user_role` (`user_id`);
ALTER TABLE `sys_user_role` ADD CONSTRAINT `sys_role` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`);
ALTER TABLE `sys_role` ADD CONSTRAINT `sys_role_permission` FOREIGN KEY (`role_id`) REFERENCES `sys_role_permission` (`role_id`);
ALTER TABLE `sys_role_permission` ADD CONSTRAINT `sys_permission` FOREIGN KEY (`permission_id`) REFERENCES `sys_permission` (`permission_id`);
ALTER TABLE `cust_user` ADD CONSTRAINT `cust_user_role` FOREIGN KEY (`user_id`) REFERENCES `cust_user_role` (`user_id`);
ALTER TABLE `cust_user_role` ADD CONSTRAINT `cust_role` FOREIGN KEY (`role_id`) REFERENCES `cust_role` (`role_id`);
ALTER TABLE `cust_role` ADD CONSTRAINT `cust_role_permission` FOREIGN KEY (`role_id`) REFERENCES `cust_role_permission` (`role_id`);
ALTER TABLE `cust_role_permission` ADD CONSTRAINT `cust_permission` FOREIGN KEY (`permission_id`) REFERENCES `cust_permission` (`permission_id`);

