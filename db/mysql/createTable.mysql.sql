
 create table WX_VOTE_RESULT ( 
    ID_ bigint  not null ,
    ACTORID_ varchar  (50) ,
    IP_ varchar  (200) ,
    RESULT_ varchar  (500) ,
    VOTEDATE_ datetime ,
    VOTEID_ bigint ,
    primary key (ID_) 
);


 create table WX_VOTE_ITEM ( 
    ID_ bigint  not null ,
    NAME_ varchar  (200) ,
    VALUE_ varchar  (20) ,
    VOTEID_ bigint ,
    ICON_ varchar  (150) ,
    SORT_ integer ,
    URL_ varchar  (500) ,
    primary key (ID_) 
);


 create table WX_VOTE ( 
    ID_ bigint  not null ,
    CONTENT_ varchar  (2048) ,
    CREATEBY_ varchar  (50) ,
    CREATEDATE_ datetime ,
    DESC_ varchar  (500) ,
    ENDDATE_ datetime ,
    ICON_ varchar  (250) ,
    LIMITFLAG_ integer ,
    LIMITTIMEINTERVAL_ integer ,
    MULTIFLAG_ integer ,
    SIGNFLAG_ integer ,
    STARTDATE_ datetime ,
    STATUS_ integer ,
    TITLE_ varchar  (200) ,
    KEYWORDS_ varchar  (100) ,
    RESULTFLAG_ integer ,
    SHOWICONFLAG_ integer ,
    ACCOUNTID_ bigint ,
    RELATIONIDS_ varchar  (100) ,
    SORT_ integer ,
    primary key (ID_) 
);


 create table WX_USER_TEMPLATE ( 
    ID_ bigint  not null ,
    CATEGORYID_ bigint ,
    CREATEBY_ varchar  (50) ,
    CREATEDATE_ datetime ,
    TEMPLATEID_ bigint ,
    TYPE_ varchar  (50) ,
    APPID_ bigint ,
    ACCOUNTID_ bigint ,
    primary key (ID_) 
);


 create table WX_USER ( 
    ID bigint  not null ,
    ACCOUNTTYPE integer ,
    ACTORID varchar  (50) ,
    APPID bigint ,
    AREA varchar  (200) ,
    CITY varchar  (200) ,
    CREATEDATE datetime ,
    DEPTID bigint ,
    ENDDATE datetime ,
    LOCKED integer ,
    MAIL varchar  (100) ,
    MOBILE varchar  (20) ,
    NAME varchar  (50) ,
    PROVINCE varchar  (50) ,
    REMARK varchar  (250) ,
    TELEPHONE varchar  (50) ,
    TOKEN varchar  (100) ,
    TYPE varchar  (20) ,
    USERTYPE integer ,
    WXHEADIMAGE varchar  (250) ,
    WXSOURCEID varchar  (50) ,
    WXID varchar  (50) ,
    WXNAME varchar  (50) ,
    YXHEADIMAGE varchar  (250) ,
    YXSOURCEID varchar  (50) ,
    YXID varchar  (50) ,
    YXNAME varchar  (50) ,
    LBSPOSITION integer ,
    WXAPPID varchar  (100) ,
    WXAPPSECRET varchar  (100) ,
    YXAPPID varchar  (100) ,
    YXAPPSECRET varchar  (100) ,
    primary key (ID) 
);


 create table WX_TEMPLATE ( 
    ID_ bigint  not null ,
    CREATEBY_ varchar  (50) ,
    CREATEDATE_ datetime ,
    DEFAULTFLAG_ integer ,
    SKINID_ varchar  (50) ,
    SKINIMAGE_ varchar  (500) ,
    TYPE_ varchar  (50) ,
    PATH_ varchar  (500) ,
    UUID_ varchar  (50) ,
    LASTUPDATEBY_ varchar  (50) ,
    LASTUPDATEDATE_ datetime ,
    LOCKED_ integer ,
    CATEGORYID_ bigint ,
    CONTENT_ longtext   ,
    NAME_ varchar  (100) ,
    DESC_ varchar  (500) ,
    TEMPLATETYPE_ varchar  (50) ,
    APPID_ bigint ,
    ACCOUNTID_ bigint ,
    primary key (ID_) 
);


 create table WX_MODULE ( 
    ID_ bigint  not null ,
    CODE_ varchar  (50) ,
    CONTENT_ varchar  (255) ,
    IDFIELD_ varchar  (50) ,
    JSON_ varchar  (500) ,
    LINK_ varchar  (250) ,
    LINKTYPE_ varchar  (50) ,
    LISTLINK_ varchar  (250) ,
    LOCKED_ integer ,
    MODULEID_ varchar  (50) ,
    MODULENAME_ varchar  (200) ,
    SQL_ varchar  (2000) ,
    SUBJECTFIELD_ varchar  (50) ,
    TITLE_ varchar  (200) ,
    SORT_ integer ,
    primary key (ID_) 
);


 create table WX_MESSAGE ( 
    ID_ bigint  not null ,
    CONTENT_ varchar  (2048) ,
    CREATEBY_ varchar  (50) ,
    CREATEDATE_ datetime ,
    MOBILE_ varchar  (50) ,
    NAME_ varchar  (50) ,
    TITLE_ varchar  (200) ,
    UUID_ varchar  (50) ,
    LASTUPDATEBY_ varchar  (50) ,
    LASTUPDATEDATE_ datetime ,
    APPID_ bigint ,
    ACCOUNTID_ bigint ,
    primary key (ID_) 
);


 create table WX_MENU ( 
    ID_ bigint  not null ,
    CREATEBY_ varchar  (50) ,
    CREATEDATE_ datetime ,
    KEY_ varchar  (200) ,
    NAME_ varchar  (200) ,
    PARENT_ bigint ,
    SORT_ integer ,
    TYPE_ varchar  (50) ,
    URL_ varchar  (500) ,
    UUID_ varchar  (50) ,
    LASTUPDATEBY_ varchar  (50) ,
    LASTUPDATEDATE_ datetime ,
    LOCKED_ integer ,
    DESC_ varchar  (500) ,
    GROUP_ varchar  (50) ,
    ICON_ varchar  (150) ,
    ICONCLS_ varchar  (50) ,
    TREEID_ varchar  (500) ,
    PICURL_ varchar  (500) ,
    APPID_ bigint ,
    ACCOUNTID_ bigint ,
    primary key (ID_) 
);


 create table WX_LOG ( 
    ID_ bigint  not null ,
    ACCOUNTID_ bigint ,
    ACTORID_ varchar  (50) ,
    CONTENT_ varchar  (500) ,
    CREATETIME_ datetime ,
    FLAG_ integer ,
    IP_ varchar  (100) ,
    OPENID_ varchar  (200) ,
    OPERATE_ varchar  (50) ,
    primary key (ID_) 
);


 create table WX_KEYWORDS ( 
    ID_ bigint  not null ,
    CATEGORYID_ bigint ,
    CONTENTID_ bigint ,
    CREATEBY_ varchar  (50) ,
    CREATEDATE_ datetime ,
    KEYWORDS_ varchar  (250) ,
    KEYWORDSMATCHTYPE_ varchar  (250) ,
    APPID_ bigint ,
    ACCOUNTID_ bigint ,
    primary key (ID_) 
);


 create table WX_FOLLOWER ( 
    ID_ bigint  not null ,
    ACCOUNTID_ bigint ,
    ACTORID_ varchar  (50) ,
    CITY_ varchar  (100) ,
    COUNTRY_ varchar  (100) ,
    CREATEDATE_ datetime ,
    HEADIMGURL_ varchar  (500) ,
    LANGUAGE_ varchar  (50) ,
    LOCKED_ integer ,
    MAIL_ varchar  (100) ,
    MOBILE_ varchar  (20) ,
    NICKNAME_ varchar  (200) ,
    OPENID_ varchar  (200) ,
    PROVINCE_ varchar  (100) ,
    REMARK varchar  (250) ,
    SEX_ varchar  (1) ,
    SUBSCRIBETIME_ bigint ,
    TELEPHONE_ varchar  (50) ,
    SOURCEID_ varchar  (200) ,
    UNSUBSCRIBETIME_ bigint ,
    primary key (ID_) 
);


 create table WX_FILE ( 
    ID_ bigint  not null ,
    CATEGORYID_ bigint ,
    CREATEBY_ varchar  (50) ,
    CREATEDATE_ datetime ,
    FILENAME_ varchar  (200) ,
    PATH_ varchar  (500) ,
    TITLE_ varchar  (200) ,
    UUID_ varchar  (50) ,
    LASTUPDATEBY_ varchar  (50) ,
    LASTUPDATEDATE_ datetime ,
    DESC_ varchar  (500) ,
    LOCKED_ integer ,
    ORIGINALFILENAME_ varchar  (200) ,
    SIZE_ bigint ,
    TYPE_ varchar  (50) ,
    CONTENT_ longtext   ,
    APPID_ bigint ,
    ACCOUNTID_ bigint ,
    primary key (ID_) 
);


 create table WX_CONTENT ( 
    ID_ bigint  not null ,
    BIGICON_ varchar  (150) ,
    CATEGORYID_ bigint ,
    CONTENT_ longtext   ,
    CREATEBY_ varchar  (50) ,
    CREATEDATE_ datetime ,
    ICON_ varchar  (150) ,
    KEYWORDS_ varchar  (250) ,
    KEYWORDSCOUNT_ integer ,
    PRIORTY_ integer ,
    SMALLICON_ varchar  (150) ,
    STATUS_ integer ,
    SUMMARY_ varchar  (250) ,
    TITLE_ varchar  (200) ,
    TYPE_ varchar  (20) ,
    URL_ varchar  (500) ,
    UUID_ varchar  (50) ,
    LASTUPDATEBY_ varchar  (50) ,
    LASTUPDATEDATE_ datetime ,
    SORT_ integer ,
    AUTHOR_ varchar  (20) ,
    KEYWORDSMATCHTYPE_ varchar  (255) ,
    RELATIONIDS_ varchar  (100) ,
    RECOMMENDATIONIDS_ varchar  (100) ,
    DETAILSHOWCOVER_ varchar  (10) ,
    PICURL_ varchar  (500) ,
    LABEL_ varchar  (200) ,
    LOCATIONX_ double ,
    LOCATIONY_ double ,
    SCALE_ varchar  (50) ,
    LATITUDE_ double ,
    LONGITUDE_ double ,
    MSGTYPE_ varchar  (20) ,
    APPID_ bigint ,
    ACCOUNTID_ bigint ,
    primary key (ID_) 
);


 create table WX_CATEGORY ( 
    ID_ bigint  not null ,
    CODE_ varchar  (50) ,
    CREATEBY_ varchar  (50) ,
    CREATEDATE_ datetime ,
    DESC_ varchar  (500) ,
    EVENTTYPE_ varchar  (50) ,
    ICON_ varchar  (150) ,
    ICONCLS_ varchar  (50) ,
    INDEXICON_ varchar  (150) ,
    INDEXSHOW_ integer ,
    LOCKED_ integer ,
    NAME_ varchar  (100) ,
    PARENT_ bigint ,
    SORT_ integer ,
    TREEID_ varchar  (500) ,
    URL_ varchar  (500) ,
    UUID_ varchar  (50) ,
    LASTUPDATEBY_ varchar  (50) ,
    LASTUPDATEDATE_ datetime ,
    TYPE_ varchar  (50) ,
    COVERICON_ varchar  (150) ,
    APPID_ bigint ,
    ACCOUNTID_ bigint ,
    primary key (ID_) 
);

