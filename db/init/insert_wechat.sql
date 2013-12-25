insert into WX_MODULE (ID_, CODE_, CONTENT_, IDFIELD_, JSON_, LINK_, LINKTYPE_, LISTLINK_, LOCKED_, MODULEID_, MODULENAME_, SQL_, SUBJECTFIELD_, TITLE_) values (1, 'vote', '投票', 'id', null, '/website/wx/vote/vote/${id}', 'html', null, 0, 'vote', '投票', 'select ID_ as id, TITLE_ as title from WX_VOTE where ACCOUNTID_=#{accountId} and STATUS_ = 1', 'title', '投票');


insert into WX_TEMPLATE (ID_, CREATEBY_, CREATEDATE_, DEFAULTFLAG_, SKINIMAGE_, TYPE_, PATH_, LASTUPDATEBY_, LASTUPDATEDATE_, LOCKED_, CATEGORYID_, CONTENT_, NAME_, DESC_, TEMPLATETYPE_,  ACCOUNTID_) values (1, 'system', null, null, '/templates/images/index.gif', '0', '/WEB-INF/conf/templates/wx/default/index.ftl', null, null, null, 0, null, '首页0', null, 'html', 0);
insert into WX_TEMPLATE (ID_, CREATEBY_, CREATEDATE_, DEFAULTFLAG_, SKINIMAGE_, TYPE_, PATH_, LASTUPDATEBY_, LASTUPDATEDATE_, LOCKED_, CATEGORYID_, CONTENT_, NAME_, DESC_, TEMPLATETYPE_,  ACCOUNTID_) values (2, 'system', null, null, '/templates/images/list.gif', '1', '/WEB-INF/conf/templates/wx/default/list.ftl', null, null, null, 0, null, '列表页0', null, 'html', 0);
insert into WX_TEMPLATE (ID_, CREATEBY_, CREATEDATE_, DEFAULTFLAG_, SKINIMAGE_, TYPE_, PATH_, LASTUPDATEBY_, LASTUPDATEDATE_, LOCKED_, CATEGORYID_, CONTENT_, NAME_, DESC_, TEMPLATETYPE_,  ACCOUNTID_) values (3, 'system', null, null, '/templates/images/detail.gif', '2', '/WEB-INF/conf/templates/wx/default/detail.ftl', null, null, null, 0, null, '详细页0', null, 'html', 0);

insert into WX_USER_TEMPLATE (ID_, CATEGORYID_, CREATEBY_, TEMPLATEID_, TYPE_,  ACCOUNTID_) values (1, 0, 'system', 1, '0', 0);
insert into WX_USER_TEMPLATE (ID_, CATEGORYID_, CREATEBY_, TEMPLATEID_, TYPE_,  ACCOUNTID_) values (2, 0, 'system', 2, '1', 0);
insert into WX_USER_TEMPLATE (ID_, CATEGORYID_, CREATEBY_, TEMPLATEID_, TYPE_,  ACCOUNTID_) values (3, 0, 'system', 3, '2', 0);
