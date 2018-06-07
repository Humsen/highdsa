-- 禁用外键约束
SET FOREIGN_KEY_CHECKS=0;

-- 清空数据
truncate table cust_user;
truncate table cust_user_info;
truncate table cust_user_role;
truncate table cust_role;
truncate table cust_role_permission;
truncate table cust_permission;

-- 启用外键约束
SET FOREIGN_KEY_CHECKS=1;

-- 用户表初始化数据,所有初始密码均为 highdsa
INSERT INTO `cust_user`(`user_id`, `user_name`, `user_email`, `user_phone`, `user_password`, `user_pwd_salt`, `user_state`) VALUES (1000, 'highdsa', '1123767053@qq.com', '186264222426', 'e8d34888284cf9792da8427b182788bc', '1e7f73802232f9a8619561e0634ae75b', '100');
INSERT INTO `cust_user`(`user_id`, `user_name`, `user_email`, `user_phone`, `user_password`, `user_pwd_salt`, `user_state`) VALUES (1001, 'husen', '2796407738@qq.com', '186264222425', 'c218d06585d77c11e2fe742e93460bfe', '19ba72fbf09cf77c10d4f1c25631d6b6', '100');
INSERT INTO `cust_user`(`user_id`, `user_name`, `user_email`, `user_phone`, `user_password`, `user_pwd_salt`, `user_state`) VALUES (1002, 'user', '3449394002@qq.com', '186264222424', '6f835c34c4f693d1b2fbfa9f584e109a', '5404ccdb41bc7c17b0adb73ef78ae2a3', '100');

-- 用户信息表初始化数据 
INSERT INTO `cust_user_info`(`user_id`, `user_nick_name`, `user_register_time`, `user_head_url`, `user_sex`, `user_birthday`, `user_desc`, `user_last_login_time`) VALUES (1000, '天哥', '2018-04-13 15:37:20.885', 'https://image.husen.com/sys/v1/user/1000', 1, '19950810', '客户1000', '2018-04-13 15:37:20.885');
INSERT INTO `cust_user_info`(`user_id`, `user_nick_name`, `user_register_time`, `user_head_url`, `user_sex`, `user_birthday`, `user_desc`, `user_last_login_time`) VALUES (1001, '明胜', '2018-04-13 15:37:20.926', 'https://image.husen.com/sys/v1/user/1001', 1, '19950810', '客户1001', '2018-04-13 15:37:20.926');
INSERT INTO `cust_user_info`(`user_id`, `user_nick_name`, `user_register_time`, `user_head_url`, `user_sex`, `user_birthday`, `user_desc`, `user_last_login_time`) VALUES (1002, '龙傲天', '2018-04-13 15:37:20.964', 'https://image.husen.com/sys/v1/user/1002', 0, '19950810', '客户1002', '2018-04-13 15:37:20.964');

-- 用户-角色表初始化数据
INSERT INTO `cust_user_role`(`user_id`, `role_id`) VALUES (1000, 1000);
INSERT INTO `cust_user_role`(`user_id`, `role_id`) VALUES (1000, 1003);
INSERT INTO `cust_user_role`(`user_id`, `role_id`) VALUES (1000, 1004);
INSERT INTO `cust_user_role`(`user_id`, `role_id`) VALUES (1000, 1005);
INSERT INTO `cust_user_role`(`user_id`, `role_id`) VALUES (1001, 1001);
INSERT INTO `cust_user_role`(`user_id`, `role_id`) VALUES (1002, 1002);


-- 角色表初始化数据
INSERT INTO `cust_role`(`role_id`, `role_name`, `role_code`, `role_desc`, `role_create_time`, `role_last_modify_time`, `role_valid`) VALUES (1000, '超级管理员', 'super_admin', '系统管理配置', TIMESTAMP(3), TIMESTAMP(3), 1);
INSERT INTO `cust_role`(`role_id`, `role_name`, `role_code`, `role_desc`, `role_create_time`, `role_last_modify_time`, `role_valid`) VALUES (1001, '用户权限管理员', 'shiro_admin', '用户权限管理', TIMESTAMP(3), TIMESTAMP(3), 1);
INSERT INTO `cust_role`(`role_id`, `role_name`, `role_code`, `role_desc`, `role_create_time`, `role_last_modify_time`, `role_valid`) VALUES (1002, '普通用户', 'common_user', '系统配置查看', TIMESTAMP(3), TIMESTAMP(3), 1);
INSERT INTO `cust_role`(`role_id`, `role_name`, `role_code`, `role_desc`, `role_create_time`, `role_last_modify_time`, `role_valid`) VALUES (1003, '模块管理员', 'module_admin', '模块管理', TIMESTAMP(3), TIMESTAMP(3), 1);
INSERT INTO `cust_role`(`role_id`, `role_name`, `role_code`, `role_desc`, `role_create_time`, `role_last_modify_time`, `role_valid`) VALUES (1004, '评论管理员', 'comment_admin', '评论管理', TIMESTAMP(3), TIMESTAMP(3), 1);
INSERT INTO `cust_role`(`role_id`, `role_name`, `role_code`, `role_desc`, `role_create_time`, `role_last_modify_time`, `role_valid`) VALUES (1005, '在线管理员', 'online_admin', '在线管理', TIMESTAMP(3), TIMESTAMP(3), 1);

-- 角色-权限表初始化数据
INSERT INTO `cust_role_permission`(`role_id`, `permission_id`) VALUES (1000, 1000);
INSERT INTO `cust_role_permission`(`role_id`, `permission_id`) VALUES (1000, 1007);
INSERT INTO `cust_role_permission`(`role_id`, `permission_id`) VALUES (1000, 1013);
INSERT INTO `cust_role_permission`(`role_id`, `permission_id`) VALUES (1000, 1017);
INSERT INTO `cust_role_permission`(`role_id`, `permission_id`) VALUES (1001, 1001);
INSERT INTO `cust_role_permission`(`role_id`, `permission_id`) VALUES (1001, 1003);
INSERT INTO `cust_role_permission`(`role_id`, `permission_id`) VALUES (1001, 1004);
INSERT INTO `cust_role_permission`(`role_id`, `permission_id`) VALUES (1001, 1005);
INSERT INTO `cust_role_permission`(`role_id`, `permission_id`) VALUES (1001, 1006);
INSERT INTO `cust_role_permission`(`role_id`, `permission_id`) VALUES (1001, 1007);
INSERT INTO `cust_role_permission`(`role_id`, `permission_id`) VALUES (1001, 1008);
INSERT INTO `cust_role_permission`(`role_id`, `permission_id`) VALUES (1001, 1010);
INSERT INTO `cust_role_permission`(`role_id`, `permission_id`) VALUES (1001, 1011);
INSERT INTO `cust_role_permission`(`role_id`, `permission_id`) VALUES (1001, 1012);
INSERT INTO `cust_role_permission`(`role_id`, `permission_id`) VALUES (1001, 1013);
INSERT INTO `cust_role_permission`(`role_id`, `permission_id`) VALUES (1001, 1014);
INSERT INTO `cust_role_permission`(`role_id`, `permission_id`) VALUES (1001, 1016);
INSERT INTO `cust_role_permission`(`role_id`, `permission_id`) VALUES (1001, 1017);
INSERT INTO `cust_role_permission`(`role_id`, `permission_id`) VALUES (1002, 1007);
INSERT INTO `cust_role_permission`(`role_id`, `permission_id`) VALUES (1002, 1013);
INSERT INTO `cust_role_permission`(`role_id`, `permission_id`) VALUES (1002, 1017);

/*  权限表初始化数据 */
INSERT INTO `cust_permission`(`permission_id`, `permission_name`, `permission_code`, `permission_desc`, `permission_navi`, `permission_url`, `permission_create_time`, `permission_last_modify_time`, `permission_valid`) VALUES (1000, 'sys:*', '*', '所有系统权限', 0, NULL, TIMESTAMP(3), TIMESTAMP(3), 0);
-- 用户
INSERT INTO `cust_permission`(`permission_id`, `permission_name`, `permission_code`, `permission_desc`, `permission_navi`, `permission_url`, `permission_create_time`, `permission_last_modify_time`, `permission_valid`) VALUES (1001, 'sys:user:create', 'user:add', '创建用户', 0, NULL, TIMESTAMP(3), TIMESTAMP(3), 1);
INSERT INTO `cust_permission`(`permission_id`, `permission_name`, `permission_code`, `permission_desc`, `permission_navi`, `permission_url`, `permission_create_time`, `permission_last_modify_time`, `permission_valid`) VALUES (1002, 'sys:user:delete', 'user:delete', '删除用户', 0, NULL, TIMESTAMP(3), TIMESTAMP(3), 1);
INSERT INTO `cust_permission`(`permission_id`, `permission_name`, `permission_code`, `permission_desc`, `permission_navi`, `permission_url`, `permission_create_time`, `permission_last_modify_time`, `permission_valid`) VALUES (1003, 'sys:user:update', 'user:update', '修改用户', 0, NULL, TIMESTAMP(3), TIMESTAMP(3), 1);
INSERT INTO `cust_permission`(`permission_id`, `permission_name`, `permission_code`, `permission_desc`, `permission_navi`, `permission_url`, `permission_create_time`, `permission_last_modify_time`, `permission_valid`) VALUES (1004, 'sys:user:view', 'user:view', '查看用户', 0, NULL, TIMESTAMP(3), TIMESTAMP(3), 1);
INSERT INTO `cust_permission`(`permission_id`, `permission_name`, `permission_code`, `permission_desc`, `permission_navi`, `permission_url`, `permission_create_time`, `permission_last_modify_time`, `permission_valid`) VALUES (1005, 'sys:user:showroles', 'user:showroles', '查看用户角色', 0, NULL, TIMESTAMP(3), TIMESTAMP(3), 1);
INSERT INTO `cust_permission`(`permission_id`, `permission_name`, `permission_code`, `permission_desc`, `permission_navi`, `permission_url`, `permission_create_time`, `permission_last_modify_time`, `permission_valid`) VALUES (1006, 'sys:user:corelationrole', 'user:corelationrole', '添加用户角色关联', 0, NULL, TIMESTAMP(3), TIMESTAMP(3), 1);
INSERT INTO `cust_permission`(`permission_id`, `permission_name`, `permission_code`, `permission_desc`, `permission_navi`, `permission_url`, `permission_create_time`, `permission_last_modify_time`, `permission_valid`) VALUES (1007, 'sys:user:list', 'user:list', '用户列表', 1, '/user/list', TIMESTAMP(3), TIMESTAMP(3), 1);
-- 角色
INSERT INTO `cust_permission`(`permission_id`, `permission_name`, `permission_code`, `permission_desc`, `permission_navi`, `permission_url`, `permission_create_time`, `permission_last_modify_time`, `permission_valid`) VALUES (1008, 'sys:role:add', 'role:add', '添加新角色', 0, NULL, TIMESTAMP(3), TIMESTAMP(3), 1);
INSERT INTO `cust_permission`(`permission_id`, `permission_name`, `permission_code`, `permission_desc`, `permission_navi`, `permission_url`, `permission_create_time`, `permission_last_modify_time`, `permission_valid`) VALUES (1009, 'sys:role:delete', 'role:delete', '删除角色', 0, NULL, TIMESTAMP(3), TIMESTAMP(3), 1);
INSERT INTO `cust_permission`(`permission_id`, `permission_name`, `permission_code`, `permission_desc`, `permission_navi`, `permission_url`, `permission_create_time`, `permission_last_modify_time`, `permission_valid`) VALUES (1010, 'sys:role:findinfo', 'role:findinfo', '查看角色信息', 0, NULL, TIMESTAMP(3), TIMESTAMP(3), 1);
INSERT INTO `cust_permission`(`permission_id`, `permission_name`, `permission_code`, `permission_desc`, `permission_navi`, `permission_url`, `permission_create_time`, `permission_last_modify_time`, `permission_valid`) VALUES (1011, 'sys:role:corelationperm', 'role:corelationperm', '添加角色权限关联', 0, NULL, TIMESTAMP(3), TIMESTAMP(3), 1);
INSERT INTO `cust_permission`(`permission_id`, `permission_name`, `permission_code`, `permission_desc`, `permission_navi`, `permission_url`, `permission_create_time`, `permission_last_modify_time`, `permission_valid`) VALUES (1012, 'sys:role:showperms', 'role:showperms', '查看角色权限', 0, NULL, TIMESTAMP(3), TIMESTAMP(3), 1);
INSERT INTO `cust_permission`(`permission_id`, `permission_name`, `permission_code`, `permission_desc`, `permission_navi`, `permission_url`, `permission_create_time`, `permission_last_modify_time`, `permission_valid`) VALUES (1013, 'sys:role:list', 'role:list', '角色列表', 1, '/role/list', TIMESTAMP(3), TIMESTAMP(3), 1);
-- 权限
INSERT INTO `cust_permission`(`permission_id`, `permission_name`, `permission_code`, `permission_desc`, `permission_navi`, `permission_url`, `permission_create_time`, `permission_last_modify_time`, `permission_valid`) VALUES (1014, 'sys:perm:add', 'perm:add', '添加权限', 0, NULL, TIMESTAMP(3), TIMESTAMP(3), 1);
INSERT INTO `cust_permission`(`permission_id`, `permission_name`, `permission_code`, `permission_desc`, `permission_navi`, `permission_url`, `permission_create_time`, `permission_last_modify_time`, `permission_valid`) VALUES (1015, 'sys:perm:delete', 'perm:delete', '删除权限', 0, NULL, TIMESTAMP(3), TIMESTAMP(3), 1);
INSERT INTO `cust_permission`(`permission_id`, `permission_name`, `permission_code`, `permission_desc`, `permission_navi`, `permission_url`, `permission_create_time`, `permission_last_modify_time`, `permission_valid`) VALUES (1016, 'sys:perm:update', 'perm:update', '更新权限信息', 0, NULL, TIMESTAMP(3), TIMESTAMP(3), 1);
INSERT INTO `cust_permission`(`permission_id`, `permission_name`, `permission_code`, `permission_desc`, `permission_navi`, `permission_url`, `permission_create_time`, `permission_last_modify_time`, `permission_valid`) VALUES (1017, 'sys:perm:list', 'perm:list', '权限列表', 1, '/perm/list', TIMESTAMP(3), TIMESTAMP(3), 1);

-- 完成 --