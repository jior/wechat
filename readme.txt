######################################################################
####����ִ��build.bat������ִ��copy.bat����class�����л�����     #####
######################################################################

���ݷ�������Ϣ���е���JVM������
jvm_args    : -Xms1024m -Xmx1024m -XX:NewSize=64m -XX:MaxNewSize=64m -XX:PermSize=256m -XX:MaxPermSize=512m -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:CMSInitiatingOccupancyFraction=7 -XX:+UseCMSInitiatingOccupancyOnly


1����װpostgresql9.3���ݿ�
   ����wechat2014�հ����ݿ⣬�������˺�Ϊroot��ϵͳ����Ա�û�,����Ϊroot����һ���������޸�WebContent\WEB-INF\conf\jdbc.properties����
   ��db\postgresql\wechat2014.postgresql.backup�����ļ�ͨ��pgAdmin III�ָ���
   ��ʾ���ݿ��û���Ϊroot,����Ϊroot��


2���޸�jdbc.properties�����ļ�
   �޸�WebContent\WEB-INF\conf\jdbc.properties,����ṩ�����ݿ����ƣ�url�����û�����username�������루password����һ��ʱ���޸ĳ����Լ���������Ϣ��Ĭ���������£�
jdbc.type=postgresql
jdbc.driver=org.postgresql.Driver
jdbc.url=jdbc:postgresql://127.0.0.1:5432/wechat2014
jdbc.user=root
jdbc.password=root
 
3�� ����server
�������Ҫ�޸�����Ŀ¼�����޸�server/conf/server.xml��������Ϣ
<Context path="/glaf" docBase="../../WebContent" reloadable="false"/>

����server������
�������µ�ַ��
http://127.0.0.1:8080/glaf
ϵͳ����Ա�˺ţ��û���Ϊroot������111111
΢�Ź��ں��û����û���Ϊjoy������111111
ϵͳ��ʼ�û�����Ϊ111111  