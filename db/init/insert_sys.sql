
insert into SYS_ROLE (id, name, roledesc, code, sort ) values (2415, '微信用户', '微信用户', 'WX_ROLE', 2415);

insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (1428, 3, '微信应用', '微信应用', 1, 'WeChat');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (1444, 1428, '微回复', '微回复', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (1451, 1457, '微菜单', '微菜单', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (1457, 1428, '微官网', '微官网', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (1539, 1457, '首页幻灯片', '首页幻灯片', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (1545, 1428, '模板管理', '模板管理', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (1556, 1457, '素材管理', '素材管理', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (1653, 1444, '关注时回复', '关注时回复', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (1659, 1444, '默认回复', '默认回复', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (1665, 1444, '关键字回复', '关键字回复', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (1671, 1444, 'LBS回复', 'LBS回复', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (1699, 1457, '栏目管理', '栏目管理', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (1875, 1457, '微网站发布', '微网站发布', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (2709, 1545, '首页模板风格设定', '首页模板风格设定', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (2715, 1545, '列表页模板风格设定', '列表页模板风格设定', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (2721, 1545, '详细页模板风格设定', '详细页模板风格设定', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (5066, 1428, '微活动', '微活动', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (5072, 5066, '微反馈', '微反馈', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (5079, 5066, '微投票', '微投票', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (5085, 5066, '会员卡', '会员卡', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (4651, 1428, '消息推送', '消息推送', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (4653, 4651, '关注者列表', '关注者列表', 1, '');

insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (2408, 5, '网站用户', 'D', 1, 'website');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (2410, 2408, '城市门户', 'D', 1, 'wechat');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (3864, 2408, '个人自媒体', 'D', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (3873, 2408, '企业微站', 'D', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (3878, 2408, '生活购物', 'D', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (3883, 2408, '新闻媒体', 'D', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (3888, 2408, '影音娱乐', 'D', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (3893, 2408, '政务官微', 'D', 1, '');


insert into SYS_DEPARTMENT (id, name, deptdesc, sort, deptno, code, code2, status, fincode, nodeid ) values (2408, '网站用户', '网站用户', 2408, 'website', 'website', 'website', 0, null, 2408);
insert into SYS_DEPARTMENT (id, name, deptdesc, sort, deptno, code, code2, status, fincode, nodeid ) values (2409, '城市门户', '城市门户', 2409, 'wechat', 'wechat', 'WX', 0, null, 2410);
insert into SYS_DEPARTMENT (id, name, deptdesc, sort, deptno, code, code2, status, fincode, nodeid ) values (3863, '个人自媒体', '个人自媒体', 3863, '', '', '', 0, null, 3864);
insert into SYS_DEPARTMENT (id, name, deptdesc, sort, deptno, code, code2, status, fincode, nodeid ) values (3872, '企业微站', '企业微站', 3872, '', '', '', 0, null, 3873);
insert into SYS_DEPARTMENT (id, name, deptdesc, sort, deptno, code, code2, status, fincode, nodeid ) values (3877, '生活购物', '生活购物', 3877, '', '', '', 0, null, 3878);
insert into SYS_DEPARTMENT (id, name, deptdesc, sort, deptno, code, code2, status, fincode, nodeid ) values (3882, '新闻媒体', '新闻媒体', 3882, '', '', '', 0, null, 3883);
insert into SYS_DEPARTMENT (id, name, deptdesc, sort, deptno, code, code2, status, fincode, nodeid ) values (3887, '影音娱乐', '影音娱乐', 3887, '', '', '', 0, null, 3888);
insert into SYS_DEPARTMENT (id, name, deptdesc, sort, deptno, code, code2, status, fincode, nodeid ) values (3892, '政务官微', '政务官微', 3892, '', '', '', 0, null, 3893);

insert into SYS_DEPT_ROLE (id, grade, code, sort, sysroleid, deptid) values (1000, 0, null, 0, 2415, 2408);
insert into SYS_DEPT_ROLE (id, grade, code, sort, sysroleid, deptid) values (1001, 0, null, 0, 2415, 2409);
insert into SYS_DEPT_ROLE (id, grade, code, sort, sysroleid, deptid) values (1002, 0, null, 0, 2415, 3863);
insert into SYS_DEPT_ROLE (id, grade, code, sort, sysroleid, deptid) values (1003, 0, null, 0, 2415, 3872);
insert into SYS_DEPT_ROLE (id, grade, code, sort, sysroleid, deptid) values (1004, 0, null, 0, 2415, 3877);
insert into SYS_DEPT_ROLE (id, grade, code, sort, sysroleid, deptid) values (1005, 0, null, 0, 2415, 3882);
insert into SYS_DEPT_ROLE (id, grade, code, sort, sysroleid, deptid) values (1006, 0, null, 0, 2415, 3887);
insert into SYS_DEPT_ROLE (id, grade, code, sort, sysroleid, deptid) values (1007, 0, null, 0, 2415, 3892);


insert into SYS_APPLICATION (id, name, code, appdesc, url, sort, showmenu, nodeid, locked) values (1427, '微信应用', 'WeChat', '微信应用', '', 1427, 1, 1428, 0);
insert into SYS_APPLICATION (id, name, code, appdesc, url, sort, showmenu, nodeid, locked) values (1443, '微回复', '', '微回复', '', 1443, 1, 1444, 0);
insert into SYS_APPLICATION (id, name, code, appdesc, url, sort, showmenu, nodeid, locked) values (1456, '微官网', '', '微官网', '', 1456, 1, 1457, 0);
insert into SYS_APPLICATION (id, name, code, appdesc, url, sort, showmenu, nodeid, locked) values (1652, '关注时回复', '', '关注时回复', '/mx/wx/wxContent?type=F', 1652, 1, 1653, 0);
insert into SYS_APPLICATION (id, name, code, appdesc, url, sort, showmenu, nodeid, locked) values (1658, '默认回复', '', '默认回复', '/mx/wx/wxContent?type=D', 1658, 1, 1659, 0);
insert into SYS_APPLICATION (id, name, code, appdesc, url, sort, showmenu, nodeid, locked) values (1664, '关键字回复', '', '关键字回复', '/mx/wx/wxContent?type=K', 1664, 1, 1665, 0);
insert into SYS_APPLICATION (id, name, code, appdesc, url, sort, showmenu, nodeid, locked) values (1670, 'LBS回复', '', 'LBS回复', '/mx/wx/wxContent?type=L', 1670, 1, 1671, 0);
insert into SYS_APPLICATION (id, name, code, appdesc, url, sort, showmenu, nodeid, locked) values (2708, '首页模板风格设定', '', '首页模板风格设定', '/mx/wx/wxTemplate/settings?type=0', 2708, 1, 2709, 0);
insert into SYS_APPLICATION (id, name, code, appdesc, url, sort, showmenu, nodeid, locked) values (2714, '列表页模板风格设定', '', '列表页模板风格设定', '/mx/wx/wxTemplate/settings?type=1', 2714, 1, 2715, 0);
insert into SYS_APPLICATION (id, name, code, appdesc, url, sort, showmenu, nodeid, locked) values (2720, '详细页模板风格设定', '', '详细页模板风格设定', '/mx/wx/wxTemplate/settings?type=2', 2720, 1, 2721, 0);
insert into SYS_APPLICATION (id, name, code, appdesc, url, sort, showmenu, nodeid, locked) values (1698, '栏目管理', '', '栏目管理', '/mx/wx/wxCategory?type=category', 1698, 1, 1699, 0);
insert into SYS_APPLICATION (id, name, code, appdesc, url, sort, showmenu, nodeid, locked) values (1555, '素材管理', '', '素材管理', '/mx/wx/wxFile', 1555, 1, 1556, 0);
insert into SYS_APPLICATION (id, name, code, appdesc, url, sort, showmenu, nodeid, locked) values (5065, '微活动', '', '', '', 5065, 1, 5066, 0);
insert into SYS_APPLICATION (id, name, code, appdesc, url, sort, showmenu, nodeid, locked) values (5084, '会员卡', '', '会员卡', '/mx/wx/wxMember', 5084, 1, 5085, 0);
insert into SYS_APPLICATION (id, name, code, appdesc, url, sort, showmenu, nodeid, locked) values (5071, '微反馈', '', '微反馈', '/mx/wx/wxMessage', 5071, 1, 5072, 0);
insert into SYS_APPLICATION (id, name, code, appdesc, url, sort, showmenu, nodeid, locked) values (5078, '微投票', '', '微投票', '/mx/wx/wxVote', 5078, 1, 5079, 0);
insert into SYS_APPLICATION (id, name, code, appdesc, url, sort, showmenu, nodeid, locked) values (1874, '微网站发布', '', '微网站发布', '/mx/wx/wxContent/treeList', 1874, 1, 1875, 0);
insert into SYS_APPLICATION (id, name, code, appdesc, url, sort, showmenu, nodeid, locked) values (1450, '微菜单', '', '微菜单', '/mx/wx/wxMenu?group=menu', 1450, 1, 1451, 0);
insert into SYS_APPLICATION (id, name, code, appdesc, url, sort, showmenu, nodeid, locked) values (1544, '模板管理', '', '模板管理', '', 1544, 1, 1545, 0);
insert into SYS_APPLICATION (id, name, code, appdesc, url, sort, showmenu, nodeid, locked) values (1538, '首页幻灯片', '', '首页幻灯片', '/mx/wx/wxContent/indexPPT', 1538, 1, 1539, 0);
insert into SYS_APPLICATION (id, name, code, appdesc, url, sort, showmenu, nodeid, locked) values (4651, '消息推送', '', '消息推送', '', 4651, 1, 4651, 0);
insert into SYS_APPLICATION (id, name, code, appdesc, url, sort, showmenu, nodeid, locked) values (4653, '关注者列表', '', '关注者列表', '/mx/wx/wxFollower', 4653, 1, 4653, 0);
  
insert into SYS_ACCESS (roleid, appid) values (1000, 1427);
insert into SYS_ACCESS (roleid, appid) values (1000, 1443);
insert into SYS_ACCESS (roleid, appid) values (1000, 1456);
insert into SYS_ACCESS (roleid, appid) values (1000, 1652);
insert into SYS_ACCESS (roleid, appid) values (1000, 1658);
insert into SYS_ACCESS (roleid, appid) values (1000, 1664);
insert into SYS_ACCESS (roleid, appid) values (1000, 1670);
insert into SYS_ACCESS (roleid, appid) values (1000, 2708);
insert into SYS_ACCESS (roleid, appid) values (1000, 2714);
insert into SYS_ACCESS (roleid, appid) values (1000, 2720);
insert into SYS_ACCESS (roleid, appid) values (1000, 1698);
insert into SYS_ACCESS (roleid, appid) values (1000, 1555);
insert into SYS_ACCESS (roleid, appid) values (1000, 5065); 
insert into SYS_ACCESS (roleid, appid) values (1000, 5084); 
insert into SYS_ACCESS (roleid, appid) values (1000, 5071); 
insert into SYS_ACCESS (roleid, appid) values (1000, 5078); 
insert into SYS_ACCESS (roleid, appid) values (1000, 1874); 
insert into SYS_ACCESS (roleid, appid) values (1000, 1450); 
insert into SYS_ACCESS (roleid, appid) values (1000, 1544); 
insert into SYS_ACCESS (roleid, appid) values (1000, 1538); 
insert into SYS_ACCESS (roleid, appid) values (1000, 4651); 
insert into SYS_ACCESS (roleid, appid) values (1000, 4653); 

insert into SYS_ACCESS (roleid, appid) values (1001, 1427);
insert into SYS_ACCESS (roleid, appid) values (1001, 1443);
insert into SYS_ACCESS (roleid, appid) values (1001, 1456);
insert into SYS_ACCESS (roleid, appid) values (1001, 1652);
insert into SYS_ACCESS (roleid, appid) values (1001, 1658);
insert into SYS_ACCESS (roleid, appid) values (1001, 1664);
insert into SYS_ACCESS (roleid, appid) values (1001, 1670);
insert into SYS_ACCESS (roleid, appid) values (1001, 2708);
insert into SYS_ACCESS (roleid, appid) values (1001, 2714);
insert into SYS_ACCESS (roleid, appid) values (1001, 2720);
insert into SYS_ACCESS (roleid, appid) values (1001, 1698);
insert into SYS_ACCESS (roleid, appid) values (1001, 1555);
insert into SYS_ACCESS (roleid, appid) values (1001, 5065); 
insert into SYS_ACCESS (roleid, appid) values (1001, 5084); 
insert into SYS_ACCESS (roleid, appid) values (1001, 5071); 
insert into SYS_ACCESS (roleid, appid) values (1001, 5078); 
insert into SYS_ACCESS (roleid, appid) values (1001, 1874); 
insert into SYS_ACCESS (roleid, appid) values (1001, 1450); 
insert into SYS_ACCESS (roleid, appid) values (1001, 1544); 
insert into SYS_ACCESS (roleid, appid) values (1001, 1538); 
insert into SYS_ACCESS (roleid, appid) values (1001, 4651); 
insert into SYS_ACCESS (roleid, appid) values (1001, 4653); 

insert into SYS_ACCESS (roleid, appid) values (1002, 1427);
insert into SYS_ACCESS (roleid, appid) values (1002, 1443);
insert into SYS_ACCESS (roleid, appid) values (1002, 1456);
insert into SYS_ACCESS (roleid, appid) values (1002, 1652);
insert into SYS_ACCESS (roleid, appid) values (1002, 1658);
insert into SYS_ACCESS (roleid, appid) values (1002, 1664);
insert into SYS_ACCESS (roleid, appid) values (1002, 1670);
insert into SYS_ACCESS (roleid, appid) values (1002, 2708);
insert into SYS_ACCESS (roleid, appid) values (1002, 2714);
insert into SYS_ACCESS (roleid, appid) values (1002, 2720);
insert into SYS_ACCESS (roleid, appid) values (1002, 1698);
insert into SYS_ACCESS (roleid, appid) values (1002, 1555);
insert into SYS_ACCESS (roleid, appid) values (1002, 5065); 
insert into SYS_ACCESS (roleid, appid) values (1002, 5084); 
insert into SYS_ACCESS (roleid, appid) values (1002, 5071); 
insert into SYS_ACCESS (roleid, appid) values (1002, 5078); 
insert into SYS_ACCESS (roleid, appid) values (1002, 1874); 
insert into SYS_ACCESS (roleid, appid) values (1002, 1450); 
insert into SYS_ACCESS (roleid, appid) values (1002, 1544); 
insert into SYS_ACCESS (roleid, appid) values (1002, 1538); 
insert into SYS_ACCESS (roleid, appid) values (1002, 4651); 
insert into SYS_ACCESS (roleid, appid) values (1002, 4653); 

insert into SYS_ACCESS (roleid, appid) values (1003, 1427);
insert into SYS_ACCESS (roleid, appid) values (1003, 1443);
insert into SYS_ACCESS (roleid, appid) values (1003, 1456);
insert into SYS_ACCESS (roleid, appid) values (1003, 1652);
insert into SYS_ACCESS (roleid, appid) values (1003, 1658);
insert into SYS_ACCESS (roleid, appid) values (1003, 1664);
insert into SYS_ACCESS (roleid, appid) values (1003, 1670);
insert into SYS_ACCESS (roleid, appid) values (1003, 2708);
insert into SYS_ACCESS (roleid, appid) values (1003, 2714);
insert into SYS_ACCESS (roleid, appid) values (1003, 2720);
insert into SYS_ACCESS (roleid, appid) values (1003, 1698);
insert into SYS_ACCESS (roleid, appid) values (1003, 1555);
insert into SYS_ACCESS (roleid, appid) values (1003, 5065); 
insert into SYS_ACCESS (roleid, appid) values (1003, 5084); 
insert into SYS_ACCESS (roleid, appid) values (1003, 5071); 
insert into SYS_ACCESS (roleid, appid) values (1003, 5078); 
insert into SYS_ACCESS (roleid, appid) values (1003, 1874); 
insert into SYS_ACCESS (roleid, appid) values (1003, 1450); 
insert into SYS_ACCESS (roleid, appid) values (1003, 1544); 
insert into SYS_ACCESS (roleid, appid) values (1003, 1538); 
insert into SYS_ACCESS (roleid, appid) values (1003, 4651); 
insert into SYS_ACCESS (roleid, appid) values (1003, 4653); 

insert into SYS_ACCESS (roleid, appid) values (1004, 1427);
insert into SYS_ACCESS (roleid, appid) values (1004, 1443);
insert into SYS_ACCESS (roleid, appid) values (1004, 1456);
insert into SYS_ACCESS (roleid, appid) values (1004, 1652);
insert into SYS_ACCESS (roleid, appid) values (1004, 1658);
insert into SYS_ACCESS (roleid, appid) values (1004, 1664);
insert into SYS_ACCESS (roleid, appid) values (1004, 1670);
insert into SYS_ACCESS (roleid, appid) values (1004, 2708);
insert into SYS_ACCESS (roleid, appid) values (1004, 2714);
insert into SYS_ACCESS (roleid, appid) values (1004, 2720);
insert into SYS_ACCESS (roleid, appid) values (1004, 1698);
insert into SYS_ACCESS (roleid, appid) values (1004, 1555);
insert into SYS_ACCESS (roleid, appid) values (1004, 5065); 
insert into SYS_ACCESS (roleid, appid) values (1004, 5084); 
insert into SYS_ACCESS (roleid, appid) values (1004, 5071); 
insert into SYS_ACCESS (roleid, appid) values (1004, 5078); 
insert into SYS_ACCESS (roleid, appid) values (1004, 1874); 
insert into SYS_ACCESS (roleid, appid) values (1004, 1450); 
insert into SYS_ACCESS (roleid, appid) values (1004, 1544); 
insert into SYS_ACCESS (roleid, appid) values (1004, 1538); 
insert into SYS_ACCESS (roleid, appid) values (1004, 4651); 
insert into SYS_ACCESS (roleid, appid) values (1004, 4653); 

insert into SYS_ACCESS (roleid, appid) values (1005, 1427);
insert into SYS_ACCESS (roleid, appid) values (1005, 1443);
insert into SYS_ACCESS (roleid, appid) values (1005, 1456);
insert into SYS_ACCESS (roleid, appid) values (1005, 1652);
insert into SYS_ACCESS (roleid, appid) values (1005, 1658);
insert into SYS_ACCESS (roleid, appid) values (1005, 1664);
insert into SYS_ACCESS (roleid, appid) values (1005, 1670);
insert into SYS_ACCESS (roleid, appid) values (1005, 2708);
insert into SYS_ACCESS (roleid, appid) values (1005, 2714);
insert into SYS_ACCESS (roleid, appid) values (1005, 2720);
insert into SYS_ACCESS (roleid, appid) values (1005, 1698);
insert into SYS_ACCESS (roleid, appid) values (1005, 1555);
insert into SYS_ACCESS (roleid, appid) values (1005, 5065); 
insert into SYS_ACCESS (roleid, appid) values (1005, 5084); 
insert into SYS_ACCESS (roleid, appid) values (1005, 5071); 
insert into SYS_ACCESS (roleid, appid) values (1005, 5078); 
insert into SYS_ACCESS (roleid, appid) values (1005, 1874); 
insert into SYS_ACCESS (roleid, appid) values (1005, 1450); 
insert into SYS_ACCESS (roleid, appid) values (1005, 1544); 
insert into SYS_ACCESS (roleid, appid) values (1005, 1538); 
insert into SYS_ACCESS (roleid, appid) values (1005, 4651); 
insert into SYS_ACCESS (roleid, appid) values (1005, 4653); 

insert into SYS_ACCESS (roleid, appid) values (1006, 1427);
insert into SYS_ACCESS (roleid, appid) values (1006, 1443);
insert into SYS_ACCESS (roleid, appid) values (1006, 1456);
insert into SYS_ACCESS (roleid, appid) values (1006, 1652);
insert into SYS_ACCESS (roleid, appid) values (1006, 1658);
insert into SYS_ACCESS (roleid, appid) values (1006, 1664);
insert into SYS_ACCESS (roleid, appid) values (1006, 1670);
insert into SYS_ACCESS (roleid, appid) values (1006, 2708);
insert into SYS_ACCESS (roleid, appid) values (1006, 2714);
insert into SYS_ACCESS (roleid, appid) values (1006, 2720);
insert into SYS_ACCESS (roleid, appid) values (1006, 1698);
insert into SYS_ACCESS (roleid, appid) values (1006, 1555);
insert into SYS_ACCESS (roleid, appid) values (1006, 5065); 
insert into SYS_ACCESS (roleid, appid) values (1006, 5084); 
insert into SYS_ACCESS (roleid, appid) values (1006, 5071); 
insert into SYS_ACCESS (roleid, appid) values (1006, 5078); 
insert into SYS_ACCESS (roleid, appid) values (1006, 1874); 
insert into SYS_ACCESS (roleid, appid) values (1006, 1450); 
insert into SYS_ACCESS (roleid, appid) values (1006, 1544); 
insert into SYS_ACCESS (roleid, appid) values (1006, 1538); 
insert into SYS_ACCESS (roleid, appid) values (1006, 4651); 
insert into SYS_ACCESS (roleid, appid) values (1006, 4653); 

insert into SYS_ACCESS (roleid, appid) values (1007, 1427);
insert into SYS_ACCESS (roleid, appid) values (1007, 1443);
insert into SYS_ACCESS (roleid, appid) values (1007, 1456);
insert into SYS_ACCESS (roleid, appid) values (1007, 1652);
insert into SYS_ACCESS (roleid, appid) values (1007, 1658);
insert into SYS_ACCESS (roleid, appid) values (1007, 1664);
insert into SYS_ACCESS (roleid, appid) values (1007, 1670);
insert into SYS_ACCESS (roleid, appid) values (1007, 2708);
insert into SYS_ACCESS (roleid, appid) values (1007, 2714);
insert into SYS_ACCESS (roleid, appid) values (1007, 2720);
insert into SYS_ACCESS (roleid, appid) values (1007, 1698);
insert into SYS_ACCESS (roleid, appid) values (1007, 1555);
insert into SYS_ACCESS (roleid, appid) values (1007, 5065); 
insert into SYS_ACCESS (roleid, appid) values (1007, 5084); 
insert into SYS_ACCESS (roleid, appid) values (1007, 5071); 
insert into SYS_ACCESS (roleid, appid) values (1007, 5078); 
insert into SYS_ACCESS (roleid, appid) values (1007, 1874); 
insert into SYS_ACCESS (roleid, appid) values (1007, 1450); 
insert into SYS_ACCESS (roleid, appid) values (1007, 1544); 
insert into SYS_ACCESS (roleid, appid) values (1007, 1538); 
insert into SYS_ACCESS (roleid, appid) values (1007, 4651); 
insert into SYS_ACCESS (roleid, appid) values (1007, 4653); 