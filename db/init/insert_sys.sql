
insert into SYS_ROLE (id, name, roledesc, code, sort ) values (2415, '΢���û�', '΢���û�', 'WX_ROLE', 2415);

insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (1428, 3, '΢��Ӧ��', '΢��Ӧ��', 1, 'WeChat');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (1444, 1428, '΢�ظ�', '΢�ظ�', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (1451, 1457, '΢�˵�', '΢�˵�', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (1457, 1428, '΢����', '΢����', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (1539, 1457, '��ҳ�õ�Ƭ', '��ҳ�õ�Ƭ', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (1545, 1428, 'ģ�����', 'ģ�����', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (1556, 1457, '�زĹ���', '�زĹ���', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (1653, 1444, '��עʱ�ظ�', '��עʱ�ظ�', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (1659, 1444, 'Ĭ�ϻظ�', 'Ĭ�ϻظ�', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (1665, 1444, '�ؼ��ֻظ�', '�ؼ��ֻظ�', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (1671, 1444, 'LBS�ظ�', 'LBS�ظ�', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (1699, 1457, '��Ŀ����', '��Ŀ����', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (1875, 1457, '΢��վ����', '΢��վ����', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (2709, 1545, '��ҳģ�����趨', '��ҳģ�����趨', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (2715, 1545, '�б�ҳģ�����趨', '�б�ҳģ�����趨', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (2721, 1545, '��ϸҳģ�����趨', '��ϸҳģ�����趨', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (5066, 1428, '΢�', '΢�', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (5072, 5066, '΢����', '΢����', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (5079, 5066, '΢ͶƱ', '΢ͶƱ', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (5085, 5066, '��Ա��', '��Ա��', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (4651, 1428, '��Ϣ����', '��Ϣ����', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (4653, 4651, '��ע���б�', '��ע���б�', 1, '');

insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (2408, 5, '��վ�û�', 'D', 1, 'website');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (2410, 2408, '�����Ż�', 'D', 1, 'wechat');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (3864, 2408, '������ý��', 'D', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (3873, 2408, '��ҵ΢վ', 'D', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (3878, 2408, '�����', 'D', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (3883, 2408, '����ý��', 'D', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (3888, 2408, 'Ӱ������', 'D', 1, '');
insert into SYS_TREE (id, parent, name, nodedesc, sort, code) values (3893, 2408, '�����΢', 'D', 1, '');


insert into SYS_DEPARTMENT (id, name, deptdesc, sort, deptno, code, code2, status, fincode, nodeid ) values (2408, '��վ�û�', '��վ�û�', 2408, 'website', 'website', 'website', 0, null, 2408);
insert into SYS_DEPARTMENT (id, name, deptdesc, sort, deptno, code, code2, status, fincode, nodeid ) values (2409, '�����Ż�', '�����Ż�', 2409, 'wechat', 'wechat', 'WX', 0, null, 2410);
insert into SYS_DEPARTMENT (id, name, deptdesc, sort, deptno, code, code2, status, fincode, nodeid ) values (3863, '������ý��', '������ý��', 3863, '', '', '', 0, null, 3864);
insert into SYS_DEPARTMENT (id, name, deptdesc, sort, deptno, code, code2, status, fincode, nodeid ) values (3872, '��ҵ΢վ', '��ҵ΢վ', 3872, '', '', '', 0, null, 3873);
insert into SYS_DEPARTMENT (id, name, deptdesc, sort, deptno, code, code2, status, fincode, nodeid ) values (3877, '�����', '�����', 3877, '', '', '', 0, null, 3878);
insert into SYS_DEPARTMENT (id, name, deptdesc, sort, deptno, code, code2, status, fincode, nodeid ) values (3882, '����ý��', '����ý��', 3882, '', '', '', 0, null, 3883);
insert into SYS_DEPARTMENT (id, name, deptdesc, sort, deptno, code, code2, status, fincode, nodeid ) values (3887, 'Ӱ������', 'Ӱ������', 3887, '', '', '', 0, null, 3888);
insert into SYS_DEPARTMENT (id, name, deptdesc, sort, deptno, code, code2, status, fincode, nodeid ) values (3892, '�����΢', '�����΢', 3892, '', '', '', 0, null, 3893);


insert into SYS_APPLICATION (id, name, code, appdesc, url, sort, showmenu, nodeid, locked) values (1427, '΢��Ӧ��', 'WeChat', '΢��Ӧ��', '', 1427, 1, 1428, 0);
insert into SYS_APPLICATION (id, name, code, appdesc, url, sort, showmenu, nodeid, locked) values (1443, '΢�ظ�', '', '΢�ظ�', '', 1443, 1, 1444, 0);
insert into SYS_APPLICATION (id, name, code, appdesc, url, sort, showmenu, nodeid, locked) values (1456, '΢����', '', '΢����', '', 1456, 1, 1457, 0);
insert into SYS_APPLICATION (id, name, code, appdesc, url, sort, showmenu, nodeid, locked) values (1652, '��עʱ�ظ�', '', '��עʱ�ظ�', '/mx/wx/wxContent?type=F', 1652, 1, 1653, 0);
insert into SYS_APPLICATION (id, name, code, appdesc, url, sort, showmenu, nodeid, locked) values (1658, 'Ĭ�ϻظ�', '', 'Ĭ�ϻظ�', '/mx/wx/wxContent?type=D', 1658, 1, 1659, 0);
insert into SYS_APPLICATION (id, name, code, appdesc, url, sort, showmenu, nodeid, locked) values (1664, '�ؼ��ֻظ�', '', '�ؼ��ֻظ�', '/mx/wx/wxContent?type=K', 1664, 1, 1665, 0);
insert into SYS_APPLICATION (id, name, code, appdesc, url, sort, showmenu, nodeid, locked) values (1670, 'LBS�ظ�', '', 'LBS�ظ�', '/mx/wx/wxContent?type=L', 1670, 1, 1671, 0);
insert into SYS_APPLICATION (id, name, code, appdesc, url, sort, showmenu, nodeid, locked) values (2708, '��ҳģ�����趨', '', '��ҳģ�����趨', '/mx/wx/wxTemplate/settings?type=0', 2708, 1, 2709, 0);
insert into SYS_APPLICATION (id, name, code, appdesc, url, sort, showmenu, nodeid, locked) values (2714, '�б�ҳģ�����趨', '', '�б�ҳģ�����趨', '/mx/wx/wxTemplate/settings?type=1', 2714, 1, 2715, 0);
insert into SYS_APPLICATION (id, name, code, appdesc, url, sort, showmenu, nodeid, locked) values (2720, '��ϸҳģ�����趨', '', '��ϸҳģ�����趨', '/mx/wx/wxTemplate/settings?type=2', 2720, 1, 2721, 0);
insert into SYS_APPLICATION (id, name, code, appdesc, url, sort, showmenu, nodeid, locked) values (1698, '��Ŀ����', '', '��Ŀ����', '/mx/wx/wxCategory?type=category', 1698, 1, 1699, 0);
insert into SYS_APPLICATION (id, name, code, appdesc, url, sort, showmenu, nodeid, locked) values (1555, '�زĹ���', '', '�زĹ���', '/mx/wx/wxFile', 1555, 1, 1556, 0);
insert into SYS_APPLICATION (id, name, code, appdesc, url, sort, showmenu, nodeid, locked) values (5065, '΢�', '', '', '', 5065, 1, 5066, 0);
insert into SYS_APPLICATION (id, name, code, appdesc, url, sort, showmenu, nodeid, locked) values (5084, '��Ա��', '', '��Ա��', '/mx/wx/wxMember', 5084, 1, 5085, 0);
insert into SYS_APPLICATION (id, name, code, appdesc, url, sort, showmenu, nodeid, locked) values (5071, '΢����', '', '΢����', '/mx/wx/wxMessage', 5071, 1, 5072, 0);
insert into SYS_APPLICATION (id, name, code, appdesc, url, sort, showmenu, nodeid, locked) values (5078, '΢ͶƱ', '', '΢ͶƱ', '/mx/wx/wxVote', 5078, 1, 5079, 0);
insert into SYS_APPLICATION (id, name, code, appdesc, url, sort, showmenu, nodeid, locked) values (1874, '΢��վ����', '', '΢��վ����', '/mx/wx/wxContent/treeList', 1874, 1, 1875, 0);
insert into SYS_APPLICATION (id, name, code, appdesc, url, sort, showmenu, nodeid, locked) values (1450, '΢�˵�', '', '΢�˵�', '/mx/wx/wxMenu?group=menu', 1450, 1, 1451, 0);
insert into SYS_APPLICATION (id, name, code, appdesc, url, sort, showmenu, nodeid, locked) values (1544, 'ģ�����', '', 'ģ�����', '', 1544, 1, 1545, 0);
insert into SYS_APPLICATION (id, name, code, appdesc, url, sort, showmenu, nodeid, locked) values (1538, '��ҳ�õ�Ƭ', '', '��ҳ�õ�Ƭ', '/mx/wx/wxContent/indexPPT', 1538, 1, 1539, 0);
insert into SYS_APPLICATION (id, name, code, appdesc, url, sort, showmenu, nodeid, locked) values (4651, '��Ϣ����', '', '��Ϣ����', '', 4651, 1, 4651, 0);
insert into SYS_APPLICATION (id, name, code, appdesc, url, sort, showmenu, nodeid, locked) values (4653, '��ע���б�', '', '��ע���б�', '/mx/wx/wxFollower', 4653, 1, 4653, 0);
  
 