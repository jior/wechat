/** 清空数据

delete from sys_access;
delete from sys_function;
delete from sys_user_role;
delete from sys_dept_role;
delete from sys_user;
delete from sys_department;
delete from sys_application;
delete from sys_tree;
delete from sys_role;
delete from sys_dictory;
delete from sys_dictory_def;
delete from sys_dbid;
delete from sys_property;
delete from sys_workcalendar;
delete from sys_params;
delete from sys_input_def;

**/



/*初始化角色信息*/
insert into sys_role (id, name, roleDesc, sort, code) values (1, '部长', '', 15, 'R001');
insert into sys_role (id, name, roleDesc, sort, code) values (2, '科长', '', 14, 'R002');
insert into sys_role (id, name, roleDesc, sort, code) values (3, 'CO', '', 12, 'R003');
insert into sys_role (id, name, roleDesc, sort, code) values (4, '系长', '', 13, 'R004');
insert into sys_role (id, name, roleDesc, sort, code) values (5, '预算员', '', 11, 'R005');
insert into sys_role (id, name, roleDesc, sort, code) values (6, '部门管理员', '', 10, 'R006');
insert into sys_role (id, name, roleDesc, sort, code) values (7, '采购联络员', '', 9, 'R007');
insert into sys_role (id, name, roleDesc, sort, code) values (10, '采购担当', '', 6, 'R010');
insert into sys_role (id, name, roleDesc, sort, code) values (11, '申请担当', '使用部门申请起票经办人', 5, 'R011');
insert into sys_role (id, name, roleDesc, sort, code) values (12, '收单担当', '', 4, 'R012');
insert into sys_role (id, name, roleDesc, sort, code) values (15, '系统管理员', '', 1, 'SystemAdministrator');
insert into sys_role (id, name, roleDesc, sort, code) values (16, '分级管理员', '', 2, 'BranchAdmin');
insert into sys_role (id, name, roleDesc, sort, code) values (18, '主任', '', 18, 'R017');


/*初始系统树信息*/
insert into sys_tree (id, parent, name, nodedesc, sort, code) values (1, 0, '/', '根目录', 1, '0');
insert into sys_tree (id, parent, name, nodedesc, sort, code) values (2, 1, '数据结构', '', 20, '01');
insert into sys_tree (id, parent, name, nodedesc, sort, code) values (3, 1, '应用模块', '', 10, '02');
insert into sys_tree (id, parent, name, nodedesc, sort, code) values (4, 2, '基础数据', '', 50, '011');
insert into sys_tree (id, parent, name, nodedesc, sort, code, discriminator) values (5, 2, '部门结构', '部门结构', 40, '012','D');
insert into sys_tree (id, parent, name, nodedesc, sort, code, discriminator) values (6, 5, '技术部', '技术部', 284, 'JS000','D');
insert into sys_tree (id, parent, name, nodedesc, sort, code) values (8, 3, '系统管理', '系统管理', 5, '');
insert into sys_tree (id, parent, name, nodedesc, sort, code) values (10, 3, '安全设置', '安全设置', 30, '');
insert into sys_tree (id, parent, name, nodedesc, sort, code) values (11, 8, '系统目录', '系统目录', 20, '');
insert into sys_tree (id, parent, name, nodedesc, sort, code) values (12, 10, '部门管理', '部门管理', 20, '');
insert into sys_tree (id, parent, name, nodedesc, sort, code) values (14, 10, '角色管理', '角色管理', 15, '');
insert into sys_tree (id, parent, name, nodedesc, sort, code) values (15, 10, '模块管理', '模块管理', 12, '');
insert into sys_tree (id, parent, name, nodedesc, sort, code) values (16, 8, '字典管理', '字典管理', 20, '');
insert into sys_tree (id, parent, name, nodedesc, sort, code) values (20, 3, '个人设置', '个人设置', 30, '');
insert into sys_tree (id, parent, name, nodedesc, sort, code) values (21, 20, '修改密码', '修改密码', 30, '');
insert into sys_tree (id, parent, name, nodedesc, sort, code) values (22, 20, '修改个人信息', '修改个人信息', 30, '');
insert into sys_tree (id, parent, name, nodedesc, sort, code) values (26, 8, '数据重载', '数据重载', 26, '');


insert into sys_tree (id, parent, name, nodedesc, sort, code) values (34, 3, '流程管理', '流程管理', 34, '');
insert into sys_tree (id, parent, name, nodedesc, sort, code) values (435, 34, '流程发布', '流程发布', 435, '');
insert into sys_tree (id, parent, name, nodedesc, sort, code) values (437, 34, '流程演示', '流程演示', 437, '');
insert into sys_tree (id, parent, name, nodedesc, sort, code) values (438, 34, '流程监控', '流程监控', 438, '');
insert into sys_tree (id, parent, name, nodedesc, sort, code) values (449, 8, '工作日历', '工作日历', 449, '');
insert into sys_tree (id, parent, name, nodedesc, sort, code) values (450, 8, '系统参数', '系统参数', 450, '');
insert into sys_tree (id, parent, name, nodeDesc, sort, code) values (452, 8, '邮件服务设置', '邮件服务设置', 452, '');

insert into sys_tree (id, parent, name, nodedesc, sort, code) values (494, 8, 'TODO配置', 'TODO配置', 494, '');
insert into sys_tree (id, parent, name, nodedesc, sort, code) values (495, 8, '调度管理', '调度管理', 495, '');
insert into sys_tree (id, parent, name, nodeDesc, sort, code) values (498, 4, '货币类型', '货币类型', 498, 'money');

insert into sys_tree (id, parent, name, nodedesc, sort, code) values (512, 10, '授权管理', '授权管理', 512, '');


insert into sys_tree (id, parent, name, nodedesc, sort, code) values (701, 3, '内容管理', '内容管理', 0, '');
insert into sys_tree (id, parent, name, nodedesc, sort, code) values (702, 701, '新闻管理', '新闻管理', 0, '');
insert into sys_tree (id, parent, name, nodedesc, sort, code) values (703, 701, '公告管理', '公告管理', 0, '');
insert into sys_tree (id, parent, name, nodedesc, sort, code) values (720, 1, '内容管理分类', '', 0, '');
insert into sys_tree (id, parent, name, nodedesc, sort, code) values (721, 720, '新闻', '新闻', 0, 'news');
insert into sys_tree (id, parent, name, nodedesc, sort, code) values (722, 721, '公司新闻', '公司新闻', 0, 'news_01');
insert into sys_tree (id, parent, name, nodedesc, sort, code) values (723, 721, '业界新闻', '业界新闻', 0, 'news_02');
insert into sys_tree (id, parent, name, nodedesc, sort, code) values (731, 720, '公告', '公告', 0, 'bulletin');
insert into sys_tree (id, parent, name, nodedesc, sort, code) values (732, 731, '普通', '', 0, '');
insert into sys_tree (id, parent, name, nodedesc, sort, code) values (733, 731, '重要', '', 0, '');



/*初始化部门信息*/
insert into sys_department (id, name, deptdesc, createtime, sort, deptno, code, code2, status, fincode, nodeid) values (6, '技术部', '技术部', null, 284, 'JS000', 'JS', 'J', 0, 'JS000', 6);
 
/*初始化用户信息*/
insert into sys_user (id, deptId, account, password, code, name, blocked, createTime, lastLoginTime, lastLoginIP, mobile, email, telephone, evection, gender, headship, userType, fax, accountType, dumpFlag, adminFlag) values (1, 6, 'root', 'lueSGJZetyySpUndWjMB', 'root', 'root', 0, null, null, '127.0.0.1', '111', 'root@127.0.0.1', '111', 0, 0, '管理员', 40, null, 0, 0, '1');

/*初始化应用信息*/
insert into sys_application (id, name, appdesc, url, sort, showmenu, nodeid) values (3, '应用模块', '应用模块', '', 3, 1, 3);
insert into sys_application (id, name, appdesc, url, sort, showmenu, nodeid) values (8, '系统管理', '系统管理', '', 5, 1, 8);
insert into sys_application (id, name, appdesc, url, sort, showmenu, nodeid) values (9, '基础数据', '基础数据', '', 30, 1, 4);
insert into sys_application (id, name, appdesc, url, sort, showmenu, nodeid) values (10, '安全设置', '', '', 14, 2, 10);
insert into sys_application (id, name, appdesc, url, sort, showmenu, nodeid) values (11, '系统目录', '', '/sys/tree.do?method=showMain', 10, 2, 11);
insert into sys_application (id, name, appdesc, url, sort, showmenu, nodeid) values (12, '部门管理', '', '/sys/department.do?method=showFrame', 20, 2, 12);
insert into sys_application (id, name, appdesc, url, sort, showmenu, nodeid) values (14, '角色管理', '', '/sys/role.do?method=showList', 15, 2, 14);
insert into sys_application (id, name, appdesc, url, sort, showmenu, nodeid) values (15, '模块管理', '', '/sys/application.do?method=showFrame', 12, 2, 15);
insert into sys_application (id, name, appdesc, url, sort, showmenu, nodeid) values (16, '字典管理', '', '/sys/dictory.do?method=showFrame', 15, 2, 16);
insert into sys_application (id, name, appdesc, url, sort, showmenu, nodeid) values (20, '个人设置', '', '', 15, 2, 20);
insert into sys_application (id, name, appdesc, url, sort, showmenu, nodeid) values (21, '修改密码', '', '/identity/user.do?method=prepareModifyPwd', 15, 2, 21);
insert into sys_application (id, name, appdesc, url, sort, showmenu, nodeid) values (22, '修改个人信息', '', '/identity/user.do?method=prepareModifyInfo', 15, 2, 22);
insert into sys_application (id, name, appdesc, url, sort, showmenu, nodeid) values (26, '数据重载', '', '/sys/dictory.do?method=loadDictory', 10, 2, 26);
insert into sys_application (id, name, appdesc, url, sort, showmenu, nodeid) values (34, '流程管理', '工作流管理', '', 0, 1, 34);
insert into sys_application (id, name, appdesc, url, sort, showmenu, nodeid) values (36, '流程监控', '流程监控', '/mx/jbpm/tree', 36, 1, 438);
insert into sys_application (id, name, appdesc, url, sort, showmenu, nodeid) values (435, '流程发布', '', '/mx/jbpm/deploy', 435, 1, 435);
insert into sys_application (id, name, appdesc, url, sort, showmenu, nodeid) values (437, '流程演示', '', '/workflow/test/index.jsp', 437, 1, 437);
insert into sys_application (id, name, appdesc, url, sort, showmenu, nodeid) values (449, '工作日历', '工作日历', '/sys/workCalendar.do?method=showList', 449, 1, 449);
insert into sys_application (id, name, appdesc, url, sort, showmenu, nodeid) values (450, '系统参数', '系统参数', '/mx/sys/property/edit?category=SYS', 450, 1, 450);
insert into sys_application (id, name, appdesc, url, sort, showmenu, nodeid) values (452, '邮件服务设置', '邮件服务设置', '/mx/sys/mailConfig', 452, 1, 452);
insert into sys_application (id, name, appdesc, url, sort, showmenu, nodeid) values (512, '授权管理', '', '/sys/sysUserRole.do?method=showUsers', 512, 2, 512);
insert into sys_application (id, name, appdesc, url, sort, showmenu, nodeid) values (494, 'TODO配置', '', '/sys/todo.do?method=showList', 494, 1, 494);
insert into sys_application (id, name, appdesc, url, sort, showmenu, nodeid) values (495, '调度管理', '', '/sys/scheduler.do?method=showList', 495, 1, 495);

insert into sys_application (id, name, appdesc, url, sort, showmenu, nodeid, code) values (71, '内容管理', '', '', 1, 1, 701, null);
insert into sys_application (id, name, appdesc, url, sort, showmenu, nodeid, code) values (72, '新闻管理', '', '/mx/cms/info?serviceKey=news', 2, 1, 702, null);
insert into sys_application (id, name, appdesc, url, sort, showmenu, nodeid, code) values (73, '公告管理', '', '/mx/cms/info?serviceKey=bulletin', 3, 1, 703, null);

/*插入部门角色*/ 
insert into sys_dept_role (id, grade, code, sort, sysroleid, deptid) values (1, 0, null, 0, 15, 6);


/*插入用户角色*/
insert into sys_user_role (id, userid, roleid, authorized, authorizefrom, availdatestart, availdateend, processdescription) values (2, 1, 1, 1, 1, null, null, null);
 
   
 
/*插入访问权限*/
insert into sys_access (roleid, appid) values (1, 3);
insert into sys_access (roleid, appid) values (1, 8);
insert into sys_access (roleid, appid) values (1, 9);
insert into sys_access (roleid, appid) values (1, 10);
insert into sys_access (roleid, appid) values (1, 11);
insert into sys_access (roleid, appid) values (1, 12);
insert into sys_access (roleid, appid) values (1, 14);
insert into sys_access (roleid, appid) values (1, 15);
insert into sys_access (roleid, appid) values (1, 16);
insert into sys_access (roleid, appid) values (1, 20);
insert into sys_access (roleid, appid) values (1, 21);
insert into sys_access (roleid, appid) values (1, 22);
insert into sys_access (roleid, appid) values (1, 26);

insert into sys_access (roleid, appid) values (1, 36);
insert into sys_access (roleid, appid) values (1, 34);
insert into sys_access (roleid, appid) values (1, 435);
insert into sys_access (roleid, appid) values (1, 437);

insert into sys_access (roleid, appid) values (1, 449);
insert into sys_access (roleid, appid) values (1, 512); 
insert into sys_access (roleid, appid) values (1, 494); 
insert into sys_access (roleid, appid) values (1, 495); 

/*插入系统功能*/

insert into sys_function (id, name, funcdesc, funcmethod, sort, appid, code) values (1, '模块列表', '模块列表', 'com.glaf.base.modules.sys.springmvc.SysApplicationController.showList', 1, 15, 'app:list');
insert into sys_function (id, name, funcdesc, funcmethod, sort, appid, code) values (2, '增加模块', '增加模块', 'com.glaf.base.modules.sys.springmvc.SysApplicationController.prepareAdd', 2, 15, 'app:create');
insert into sys_function (id, name, funcdesc, funcmethod, sort, appid, code) values (3, '修改模块', '修改模块', 'com.glaf.base.modules.sys.springmvc.SysApplicationController.prepareModify', 3, 15, 'app:update');
insert into sys_function (id, name, funcdesc, funcmethod, sort, appid, code) values (4, '删除模块', '删除模块', 'com.glaf.base.modules.sys.springmvc.SysApplicationController.batchDelete', 4, 15, 'app:delete');


insert into sys_dictory (id, typeId, code, name, sort, dictDesc, blocked, ext1, ext2) values (1, 498, 'CNY', '人民币', 1, null, 0, '', '');
insert into sys_dictory (id, typeId, code, name, sort, dictDesc, blocked, ext1, ext2) values (2, 498, 'USD', '美元', 2, null, 0, '', '');
insert into sys_dictory (id, typeId, code, name, sort, dictDesc, blocked, ext1, ext2) values (3, 498, 'JPY', '日元', 3, null, 0, '', '');
insert into sys_dictory (id, typeId, code, name, sort, dictDesc, blocked, ext1, ext2) values (4, 498, 'HKD', '港币', 4, null, 0, '', '');
insert into sys_dictory (id, typeId, code, name, sort, dictDesc, blocked, ext1, ext2) values (5, 498, 'EUR', '欧元', 5, null, 0, '', '');
 

insert into sys_dictory_def (id, nodeid, name, columnname, title, type, length, sort, required, target) values (4,  0, 'ext1',  'EXT1', '',  'String', 200, 3, 0, 'sys_dictory');
insert into sys_dictory_def (id, nodeid, name, columnname, title, type, length, sort, required, target) values (5,  0, 'ext2',  'EXT2', '',  'String', 200, 4, 0, 'sys_dictory');
insert into sys_dictory_def (id, nodeid, name, columnname, title, type, length, sort, required, target) values (6,  0, 'ext3',  'EXT3', '',  'String', 200, 5, 0, 'sys_dictory');
insert into sys_dictory_def (id, nodeid, name, columnname, title, type, length, sort, required, target) values (7,  0, 'ext4',  'EXT4', '',  'String', 200, 5, 0, 'sys_dictory');
insert into sys_dictory_def (id, nodeid, name, columnname, title, type, length, sort, required, target) values (8,  0, 'ext5',  'EXT5', '',  'Date', 20, 7, 0, 'sys_dictory');
insert into sys_dictory_def (id, nodeid, name, columnname, title, type, length, sort, required, target) values (9,  0, 'ext6',  'EXT6', '',  'Date', 20, 8, 0, 'sys_dictory');
insert into sys_dictory_def (id, nodeid, name, columnname, title, type, length, sort, required, target) values (10, 0, 'ext7',  'EXT7', '',  'Date', 20, 9, 0, 'sys_dictory');
insert into sys_dictory_def (id, nodeid, name, columnname, title, type, length, sort, required, target) values (11, 0, 'ext8',  'EXT8', '',  'Date', 20, 10, 0, 'sys_dictory');
insert into sys_dictory_def (id, nodeid, name, columnname, title, type, length, sort, required, target) values (12, 0, 'ext9',  'EXT9', '',  'Date', 20, 11, 0, 'sys_dictory');
insert into sys_dictory_def (id, nodeid, name, columnname, title, type, length, sort, required, target) values (13, 0, 'ext10', 'EXT10', '', 'Date', 20, 12, 0, 'sys_dictory');
insert into sys_dictory_def (id, nodeid, name, columnname, title, type, length, sort, required, target) values (14, 0, 'ext11', 'EXT11', '', 'Long', 20, 13, 0, 'sys_dictory');
insert into sys_dictory_def (id, nodeid, name, columnname, title, type, length, sort, required, target) values (15, 0, 'ext12', 'EXT12', '', 'Long', 20, 14, 0, 'sys_dictory');
insert into sys_dictory_def (id, nodeid, name, columnname, title, type, length, sort, required, target) values (16, 0, 'ext13', 'EXT13', '', 'Long', 20, 15, 0, 'sys_dictory');
insert into sys_dictory_def (id, nodeid, name, columnname, title, type, length, sort, required, target) values (17, 0, 'ext14', 'EXT14', '', 'Long', 20, 16, 0, 'sys_dictory');
insert into sys_dictory_def (id, nodeid, name, columnname, title, type, length, sort, required, target) values (18, 0, 'ext15', 'EXT15', '', 'Long', 20, 17, 0, 'sys_dictory');
insert into sys_dictory_def (id, nodeid, name, columnname, title, type, length, sort, required, target) values (19, 0, 'ext16', 'EXT16', '', 'Double', 20, 18, 0, 'sys_dictory');
insert into sys_dictory_def (id, nodeid, name, columnname, title, type, length, sort, required, target) values (20, 0, 'ext17', 'EXT17', '', 'Double', 20, 19, 0, 'sys_dictory');
insert into sys_dictory_def (id, nodeid, name, columnname, title, type, length, sort, required, target) values (21, 0, 'ext18', 'EXT18', '', 'Double', 20, 20, 0, 'sys_dictory');
insert into sys_dictory_def (id, nodeid, name, columnname, title, type, length, sort, required, target) values (22, 0, 'ext19', 'EXT19', '', 'Double', 20, 21, 0, 'sys_dictory');
insert into sys_dictory_def (id, nodeid, name, columnname, title, type, length, sort, required, target) values (23, 0, 'ext20', 'EXT20', '', 'Double', 20, 22, 0, 'sys_dictory');

insert into sys_dbid(name_, title_, value_, version_) values ('next.dbid', '系统内置主键', '1001', 1);

insert into sys_property (id_, description_, locked_, name_, title_, type_, value_, category_, initvalue_) values ('1', '系统名称', 0, 'res_system_name', '系统名称', null, 'GLAF基础应用框架', 'SYS', null);
insert into sys_property (id_, description_, locked_, name_, title_, type_, value_, category_, initvalue_) values ('2', '系统版本', 0, 'res_version', '系统版本', null, 'V3.0', 'SYS', null);
insert into sys_property (id_, description_, locked_, name_, title_, type_, value_, category_, initvalue_) values ('3', '版权信息', 0, 'res_copyright', '版权信息', null, 'GLAF 版权所有', 'SYS', null);
insert into sys_property (id_, description_, locked_, name_, title_, type_, value_, category_, initvalue_) values ('4', null, 0, 'res_mail_from', '邮件发送者', null, 'jior2008@gmail.com', 'SYS', null);

insert into sys_property (id_, description_, locked_, name_, title_, type_, value_, category_, initvalue_) values ('101', '当天的6位年月格式（YYYYMM），如201312', 0, 'curr_yyyymm', '当前年月', null, '', 'RPT', '${curr_yyyymm}');
insert into sys_property (id_, description_, locked_, name_, title_, type_, value_, category_, initvalue_) values ('102', '当天的8位日期格式（YYYYMMDD），如20130630', 0, 'curr_yyyymmdd', '当前日期', null, '', 'RPT', '${curr_yyyymmdd}');
insert into sys_property (id_, description_, locked_, name_, title_, type_, value_, category_, initvalue_) values ('103', '数据文件路径', 0, 'dataDir', '数据文件路径', null, '', 'RPT', null);
insert into sys_property (id_, description_, locked_, name_, title_, type_, value_, category_, initvalue_) values ('104', '报表输入年月，${curr_yyyymm}表示当前月份', 0, 'input_yyyymm', '报表输入年月', null, '', 'RPT', '');
insert into sys_property (id_, description_, locked_, name_, title_, type_, value_, category_, initvalue_) values ('105', '报表输入的日期，${curr_yyyymmdd}-1表示当前日期的前一天', 0, 'input_yyyymmdd', '报表输入日期', null, '', 'RPT', '');
insert into sys_property (id_, description_, locked_, name_, title_, type_, value_, category_, initvalue_) values ('106', '报表生成根目录', 0, 'report_save_path', '报表生成根目录', null, '', 'RPT', null);


insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (1, 6, 1, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (2, 13, 1, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (3, 20, 1, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (4, 27, 1, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (5, 26, 1, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (6, 19, 1, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (7, 12, 1, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (8, 5, 1, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (9, 2, 2, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (10, 9, 2, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (11, 16, 2, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (12, 23, 2, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (13, 3, 2, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (14, 10, 2, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (15, 17, 2, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (16, 24, 2, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (17, 3, 3, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (18, 10, 3, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (19, 17, 3, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (20, 24, 3, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (21, 31, 3, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (22, 30, 3, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (23, 23, 3, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (24, 16, 3, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (25, 9, 3, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (26, 2, 3, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (27, 7, 4, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (28, 14, 4, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (29, 21, 4, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (30, 28, 4, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (31, 27, 4, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (32, 20, 4, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (33, 13, 4, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (34, 6, 4, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (35, 5, 5, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (36, 12, 5, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (37, 19, 5, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (38, 26, 5, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (39, 25, 5, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (40, 18, 5, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (41, 11, 5, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (42, 4, 5, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (43, 2, 6, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (44, 9, 6, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (45, 16, 6, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (46, 23, 6, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (47, 30, 6, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (48, 29, 6, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (49, 22, 6, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (50, 15, 6, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (51, 8, 6, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (52, 1, 6, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (53, 7, 7, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (54, 14, 7, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (55, 21, 7, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (56, 27, 7, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (57, 20, 7, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (58, 13, 7, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (59, 6, 7, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (60, 28, 7, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (61, 4, 8, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (62, 11, 8, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (63, 18, 8, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (64, 25, 8, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (65, 31, 8, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (66, 24, 8, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (67, 17, 8, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (68, 10, 8, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (69, 3, 8, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (70, 1, 9, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (71, 8, 9, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (72, 15, 9, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (73, 22, 9, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (74, 29, 9, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (75, 28, 9, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (76, 21, 9, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (77, 14, 9, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (78, 7, 9, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (79, 7, 12, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (80, 14, 12, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (81, 21, 12, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (82, 28, 12, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (83, 1, 12, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (84, 8, 12, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (85, 15, 12, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (86, 22, 12, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (87, 29, 12, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (89, 2, 11, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (90, 9, 11, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (91, 16, 11, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (92, 23, 11, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (93, 30, 11, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (94, 24, 11, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (95, 17, 11, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (96, 10, 11, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (97, 3, 11, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (98, 5, 10, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (99, 12, 10, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (100, 19, 10, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (101, 1, 10, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (102, 2, 10, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (103, 3, 10, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (104, 4, 10, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (105, 6, 10, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (106, 13, 10, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (107, 20, 10, 2013);
insert into sys_workcalendar (id, freeday, freemonth, freeyear) values (108, 27, 10, 2013);


insert into sys_params (id, business_key, date_val, double_val, int_val, java_type, key_name, long_val, service_key, string_val, text_val, title, type_cd) values ('news__news_processName', 'news', null, null, null, 'string', 'processName', null, 'news', 'PublicInfoProcess', 'PublicInfoProcess', '流程名称', 'news');
insert into sys_params (id, business_key, date_val, double_val, int_val, java_type, key_name, long_val, service_key, string_val, text_val, title, type_cd) values ('bulletin__bulletin_processName', 'bulletin', null, null, null, 'string', 'processName', null, 'bulletin', 'PublicInfoProcess', 'PublicInfoProcess', '流程名称', 'bulletin');

insert into sys_params (id, business_key, date_val, double_val, int_val, java_type, key_name, long_val, service_key, string_val, text_val, title, type_cd) values ('sys_table', 'sys_table', null, null, null, 'json', 'sys_table', null, 'sys_table', '[{"tablename":"sys_role","title":"角色"},{"tablename":"sys_tree","title":"系统树"},{"tablename":"sys_department","title":"部门"},{"tablename":"sys_user","title":"用户"},{"tablename":"sys_application","title":"模块"},{"tablename":"sys_dept_role","title":"部门角色"},{"tablename":"sys_user_role","title":"用户角色"},{"tablename":"sys_access","title":"访问权限"},{"tablename":"sys_function","title":"系统功能"},{"tablename":"sys_dictory","title":"数据字典"},{"tablename":"sys_dictory_def","title":"字典定义"},{"tablename":"sys_dbid","title":"系统主键"},{"tablename":"sys_property","title":"系统属性"},{"tablename":"sys_workcalendar","title":"工作日历"},{"tablename":"sys_params","title":"参数定义"},{"tablename":"sys_input_def","title":"系统输入定义"}]', '[{"tablename":"sys_role","title":"角色"},{"tablename":"sys_tree","title":"系统树"},{"tablename":"sys_department","title":"部门"},{"tablename":"sys_user","title":"用户"},{"tablename":"sys_application","title":"模块"},{"tablename":"sys_dept_role","title":"部门角色"},{"tablename":"sys_user_role","title":"用户角色"},{"tablename":"sys_access","title":"访问权限"},{"tablename":"sys_function","title":"系统功能"},{"tablename":"sys_dictory","title":"数据字典"},{"tablename":"sys_dictory_def","title":"字典定义"},{"tablename":"sys_dbid","title":"系统主键"},{"tablename":"sys_property","title":"系统属性"},{"tablename":"sys_workcalendar","title":"工作日历"},{"tablename":"sys_params","title":"参数定义"},{"tablename":"sys_input_def","title":"系统输入定义"}]', '系统表', 'sys_table');


insert into sys_input_def (id, init_value, input_type, java_type, key_name, required, service_key, text_field, title, type_cd, type_title, url, valid_type, value_field) values ('7', '/rs/jbpm/definition/json', 'combobox', 'String', 'processName', null, 'news', 'text', '流程名称', 'news', '新闻发布', '/rs/jbpm/definition/json', null, 'name');
insert into sys_input_def (id, init_value, input_type, java_type, key_name, required, service_key, text_field, title, type_cd, type_title, url, valid_type, value_field) values ('8', '/rs/jbpm/definition/json', 'combobox', 'String', 'processName', null, 'bulletin', 'text', '流程名称', 'bulletin', '公告发布', '/rs/jbpm/definition/json', null, 'name');


/*UI管理初始化数据*/
insert into ui_panel (id_,actorid_,close_,collapsible_,columnindex_,height_,link_,locked_,name_,resize_,title_,type_,width_) values ( '1010','root',0,0,0,250,'/user/todo.do?method=userTasks',0,'todo',0,'待办事项','L',0);
insert into ui_panel (id_,actorid_,close_,collapsible_,columnindex_,height_,link_,locked_,name_,resize_,title_,type_,width_) values ( '1016','root',0,0,0,250,'/mx/public/info/indexList?serviceKey=news',0,'news',0,'新闻','L',0);
insert into ui_panel (id_,actorid_,close_,collapsible_,columnindex_,height_,link_,locked_,name_,resize_,title_,type_,width_) values ( '1018','root',0,0,0,250,'/mx/public/info/indexList?serviceKey=bulletin',0,'bulletin',0,'公告','L',0);
insert into ui_panel (id_,actorid_,close_,collapsible_,columnindex_,content_,height_,locked_,name_,resize_,title_,type_,width_) values ( '1020','root',0,0,0,'<p>&nbsp;</p><p>&nbsp;&nbsp;&nbsp;&nbsp;GLAF 1.0准备发布！</p>',250,0,'notice',0,'通知','T',0);

insert into ui_panelinstance (id_,name_,panel_,userpanel_) values ( '1024','2','1020','1023');
insert into ui_panelinstance (id_,name_,panel_,userpanel_) values ( '1025','1','1010','1023');
insert into ui_panelinstance (id_,name_,panel_,userpanel_) values ( '1026','4','1018','1023');
insert into ui_panelinstance (id_,name_,panel_,userpanel_) values ( '1027','3','1016','1023');

insert into ui_userpanel (id_,actorid_,layoutname_,refreshseconds_) values ( '1023','root','P2',0);

insert into ui_userportal (id_,actorid_,columnindex_,panelid_,position_) values ( '1028','root',0,'1010',1);
insert into ui_userportal (id_,actorid_,columnindex_,panelid_,position_) values ( '1029','root',0,'1016',3);
insert into ui_userportal (id_,actorid_,columnindex_,panelid_,position_) values ( '1030','root',1,'1018',4);
insert into ui_userportal (id_,actorid_,columnindex_,panelid_,position_) values ( '1031','root',1,'1020',2);

update sys_application set locked = 0 where locked is null;

update sys_tree set locked = 0 where locked is null;

