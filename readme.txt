######################################################################
####请先执行build.bat编译再执行copy.bat复制class到运行环境。     #####
######################################################################

根据服务器信息自行调整JVM参数：
jvm_args    : -Xms1024m -Xmx1024m -XX:NewSize=64m -XX:MaxNewSize=64m -XX:PermSize=256m -XX:MaxPermSize=512m -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:CMSInitiatingOccupancyFraction=7 -XX:+UseCMSInitiatingOccupancyOnly


1、安装postgresql9.3数据库
   创建wechat2014空白数据库，并建立账号为root的系统管理员用户,密码为root（不一致请自行修改WebContent\WEB-INF\conf\jdbc.properties）。
   将db\postgresql\wechat2014.postgresql.backup数据文件通过pgAdmin III恢复。
   演示数据库用户名为root,密码为root。


2、修改jdbc.properties配置文件
   修改WebContent\WEB-INF\conf\jdbc.properties,如果提供的数据库名称（url）、用户名（username）、密码（password）不一致时请修改成您自己的配置信息，默认配置如下：
jdbc.type=postgresql
jdbc.driver=org.postgresql.Driver
jdbc.url=jdbc:postgresql://127.0.0.1:5432/wechat2014
jdbc.user=root
jdbc.password=root
 
3、 启动server
如果把需要修改虚拟目录，请修改server/conf/server.xml中配置信息
<Context path="/glaf" docBase="../../WebContent" reloadable="false"/>

启动server服务器
访问如下地址：
http://127.0.0.1:8080/glaf
系统管理员账号：用户名为root，密码111111
微信公众号用户：用户名为joy，密码111111
系统初始用户密码为111111  