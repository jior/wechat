
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
  
 