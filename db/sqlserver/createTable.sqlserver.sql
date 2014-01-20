
 create table WX_VOTE_RESULT ( 
    ID_ bigint  not null ,
    ACTORID_ nvarchar  (50) ,
    IP_ nvarchar  (200) ,
    RESULT_ nvarchar  (500) ,
    VOTEDATE_ datetime ,
    VOTEID_ bigint ,
    primary key (ID_) 
);


 create table WX_VOTE_ITEM ( 
    ID_ bigint  not null ,
    NAME_ nvarchar  (200) ,
    VALUE_ nvarchar  (20) ,
    VOTEID_ bigint ,
    ICON_ nvarchar  (150) ,
    SORT_ integer ,
    URL_ nvarchar  (500) ,
    primary key (ID_) 
);


 create table WX_VOTE ( 
    ID_ bigint  not null ,
    CONTENT_ nvarchar  (2048) ,
    CREATEBY_ nvarchar  (50) ,
    CREATEDATE_ datetime ,
    DESC_ nvarchar  (500) ,
    ENDDATE_ datetime ,
    ICON_ nvarchar  (250) ,
    LIMITFLAG_ integer ,
    LIMITTIMEINTERVAL_ integer ,
    MULTIFLAG_ integer ,
    SIGNFLAG_ integer ,
    STARTDATE_ datetime ,
    STATUS_ integer ,
    TITLE_ nvarchar  (200) ,
    KEYWORDS_ nvarchar  (100) ,
    RESULTFLAG_ integer ,
    SHOWICONFLAG_ integer ,
    ACCOUNTID_ bigint ,
    RELATIONIDS_ nvarchar  (100) ,
    SORT_ integer ,
    primary key (ID_) 
);


 create table WX_USER_TEMPLATE ( 
    ID_ bigint  not null ,
    CATEGORYID_ bigint ,
    CREATEBY_ nvarchar  (50) ,
    CREATEDATE_ datetime ,
    TEMPLATEID_ bigint ,
    TYPE_ nvarchar  (50) ,
    APPID_ bigint ,
    ACCOUNTID_ bigint ,
    primary key (ID_) 
);


 create table WX_USER ( 
    ID bigint  not null ,
    ACCOUNTTYPE integer ,
    ACTORID nvarchar  (50) ,
    APPID bigint ,
    AREA nvarchar  (200) ,
    CITY nvarchar  (200) ,
    CREATEDATE datetime ,
    DEPTID bigint ,
    ENDDATE datetime ,
    LOCKED integer ,
    MAIL nvarchar  (100) ,
    MOBILE nvarchar  (20) ,
    NAME nvarchar  (50) ,
    PROVINCE nvarchar  (50) ,
    REMARK nvarchar  (250) ,
    TELEPHONE nvarchar  (50) ,
    TOKEN nvarchar  (100) ,
    TYPE nvarchar  (20) ,
    USERTYPE integer ,
    WXHEADIMAGE nvarchar  (250) ,
    WXSOURCEID nvarchar  (50) ,
    WXID nvarchar  (50) ,
    WXNAME nvarchar  (50) ,
    YXHEADIMAGE nvarchar  (250) ,
    YXSOURCEID nvarchar  (50) ,
    YXID nvarchar  (50) ,
    YXNAME nvarchar  (50) ,
    LBSPOSITION integer ,
    WXAPPID nvarchar  (100) ,
    WXAPPSECRET nvarchar  (100) ,
    YXAPPID nvarchar  (100) ,
    YXAPPSECRET nvarchar  (100) ,
    primary key (ID) 
);


 create table WX_TEMPLATE ( 
    ID_ bigint  not null ,
    CREATEBY_ nvarchar  (50) ,
    CREATEDATE_ datetime ,
    DEFAULTFLAG_ integer ,
    SKINID_ nvarchar  (50) ,
    SKINIMAGE_ nvarchar  (500) ,
    TYPE_ nvarchar  (50) ,
    PATH_ nvarchar  (500) ,
    UUID_ nvarchar  (50) ,
    LASTUPDATEBY_ nvarchar  (50) ,
    LASTUPDATEDATE_ datetime ,
    LOCKED_ integer ,
    CATEGORYID_ bigint ,
    CONTENT_ text   ,
    NAME_ nvarchar  (100) ,
    DESC_ nvarchar  (500) ,
    TEMPLATETYPE_ nvarchar  (50) ,
    APPID_ bigint ,
    ACCOUNTID_ bigint ,
    primary key (ID_) 
);


 create table WX_MODULE ( 
    ID_ bigint  not null ,
    CODE_ nvarchar  (50) ,
    CONTENT_ nvarchar  (255) ,
    IDFIELD_ nvarchar  (50) ,
    JSON_ nvarchar  (500) ,
    LINK_ nvarchar  (250) ,
    LINKTYPE_ nvarchar  (50) ,
    LISTLINK_ nvarchar  (250) ,
    LOCKED_ integer ,
    MODULEID_ nvarchar  (50) ,
    MODULENAME_ nvarchar  (200) ,
    SQL_ nvarchar  (2000) ,
    SUBJECTFIELD_ nvarchar  (50) ,
    TITLE_ nvarchar  (200) ,
    SORT_ integer ,
    primary key (ID_) 
);


 create table WX_MESSAGE ( 
    ID_ bigint  not null ,
    CONTENT_ nvarchar  (2048) ,
    CREATEBY_ nvarchar  (50) ,
    CREATEDATE_ datetime ,
    MOBILE_ nvarchar  (50) ,
    NAME_ nvarchar  (50) ,
    TITLE_ nvarchar  (200) ,
    UUID_ nvarchar  (50) ,
    LASTUPDATEBY_ nvarchar  (50) ,
    LASTUPDATEDATE_ datetime ,
    APPID_ bigint ,
    ACCOUNTID_ bigint ,
    primary key (ID_) 
);


 create table WX_MENU ( 
    ID_ bigint  not null ,
    CREATEBY_ nvarchar  (50) ,
    CREATEDATE_ datetime ,
    KEY_ nvarchar  (200) ,
    NAME_ nvarchar  (200) ,
    PARENT_ bigint ,
    SORT_ integer ,
    TYPE_ nvarchar  (50) ,
    URL_ nvarchar  (500) ,
    UUID_ nvarchar  (50) ,
    LASTUPDATEBY_ nvarchar  (50) ,
    LASTUPDATEDATE_ datetime ,
    LOCKED_ integer ,
    DESC_ nvarchar  (500) ,
    GROUP_ nvarchar  (50) ,
    ICON_ nvarchar  (150) ,
    ICONCLS_ nvarchar  (50) ,
    TREEID_ nvarchar  (500) ,
    PICURL_ nvarchar  (500) ,
    APPID_ bigint ,
    ACCOUNTID_ bigint ,
    primary key (ID_) 
);


 create table WX_LOG ( 
    ID_ bigint  not null ,
    ACCOUNTID_ bigint ,
    ACTORID_ nvarchar  (50) ,
    CONTENT_ nvarchar  (500) ,
    CREATETIME_ datetime ,
    FLAG_ integer ,
    IP_ nvarchar  (100) ,
    OPENID_ nvarchar  (200) ,
    OPERATE_ nvarchar  (50) ,
    primary key (ID_) 
);


 create table WX_KEYWORDS ( 
    ID_ bigint  not null ,
    CATEGORYID_ bigint ,
    CONTENTID_ bigint ,
    CREATEBY_ nvarchar  (50) ,
    CREATEDATE_ datetime ,
    KEYWORDS_ nvarchar  (250) ,
    KEYWORDSMATCHTYPE_ nvarchar  (250) ,
    APPID_ bigint ,
    ACCOUNTID_ bigint ,
    primary key (ID_) 
);


 create table WX_FOLLOWER ( 
    ID_ bigint  not null ,
    ACCOUNTID_ bigint ,
    ACTORID_ nvarchar  (50) ,
    CITY_ nvarchar  (100) ,
    COUNTRY_ nvarchar  (100) ,
    CREATEDATE_ datetime ,
    HEADIMGURL_ nvarchar  (500) ,
    LANGUAGE_ nvarchar  (50) ,
    LOCKED_ integer ,
    MAIL_ nvarchar  (100) ,
    MOBILE_ nvarchar  (20) ,
    NICKNAME_ nvarchar  (200) ,
    OPENID_ nvarchar  (200) ,
    PROVINCE_ nvarchar  (100) ,
    REMARK nvarchar  (250) ,
    SEX_ nvarchar  (1) ,
    SUBSCRIBETIME_ bigint ,
    TELEPHONE_ nvarchar  (50) ,
    SOURCEID_ nvarchar  (200) ,
    UNSUBSCRIBETIME_ bigint ,
    primary key (ID_) 
);


 create table WX_FILE ( 
    ID_ bigint  not null ,
    CATEGORYID_ bigint ,
    CREATEBY_ nvarchar  (50) ,
    CREATEDATE_ datetime ,
    FILENAME_ nvarchar  (200) ,
    PATH_ nvarchar  (500) ,
    TITLE_ nvarchar  (200) ,
    UUID_ nvarchar  (50) ,
    LASTUPDATEBY_ nvarchar  (50) ,
    LASTUPDATEDATE_ datetime ,
    DESC_ nvarchar  (500) ,
    LOCKED_ integer ,
    ORIGINALFILENAME_ nvarchar  (200) ,
    SIZE_ bigint ,
    TYPE_ nvarchar  (50) ,
    CONTENT_ text   ,
    APPID_ bigint ,
    ACCOUNTID_ bigint ,
    primary key (ID_) 
);


 create table WX_CONTENT ( 
    ID_ bigint  not null ,
    BIGICON_ nvarchar  (150) ,
    CATEGORYID_ bigint ,
    CONTENT_ text   ,
    CREATEBY_ nvarchar  (50) ,
    CREATEDATE_ datetime ,
    ICON_ nvarchar  (150) ,
    KEYWORDS_ nvarchar  (250) ,
    KEYWORDSCOUNT_ integer ,
    PRIORTY_ integer ,
    SMALLICON_ nvarchar  (150) ,
    STATUS_ integer ,
    SUMMARY_ nvarchar  (250) ,
    TITLE_ nvarchar  (200) ,
    TYPE_ nvarchar  (20) ,
    URL_ nvarchar  (500) ,
    UUID_ nvarchar  (50) ,
    LASTUPDATEBY_ nvarchar  (50) ,
    LASTUPDATEDATE_ datetime ,
    SORT_ integer ,
    AUTHOR_ nvarchar  (20) ,
    KEYWORDSMATCHTYPE_ nvarchar  (255) ,
    RELATIONIDS_ nvarchar  (100) ,
    RECOMMENDATIONIDS_ nvarchar  (100) ,
    DETAILSHOWCOVER_ nvarchar  (10) ,
    PICURL_ nvarchar  (500) ,
    LABEL_ nvarchar  (200) ,
    LOCATIONX_ double precision ,
    LOCATIONY_ double precision ,
    SCALE_ nvarchar  (50) ,
    LATITUDE_ double precision ,
    LONGITUDE_ double precision ,
    MSGTYPE_ nvarchar  (20) ,
    APPID_ bigint ,
    ACCOUNTID_ bigint ,
    primary key (ID_) 
);


 create table WX_CATEGORY ( 
    ID_ bigint  not null ,
    CODE_ nvarchar  (50) ,
    CREATEBY_ nvarchar  (50) ,
    CREATEDATE_ datetime ,
    DESC_ nvarchar  (500) ,
    EVENTTYPE_ nvarchar  (50) ,
    ICON_ nvarchar  (150) ,
    ICONCLS_ nvarchar  (50) ,
    INDEXICON_ nvarchar  (150) ,
    INDEXSHOW_ integer ,
    LOCKED_ integer ,
    NAME_ nvarchar  (100) ,
    PARENT_ bigint ,
    SORT_ integer ,
    TREEID_ nvarchar  (500) ,
    URL_ nvarchar  (500) ,
    UUID_ nvarchar  (50) ,
    LASTUPDATEBY_ nvarchar  (50) ,
    LASTUPDATEDATE_ datetime ,
    TYPE_ nvarchar  (50) ,
    COVERICON_ nvarchar  (150) ,
    APPID_ bigint ,
    ACCOUNTID_ bigint ,
    primary key (ID_) 
);

