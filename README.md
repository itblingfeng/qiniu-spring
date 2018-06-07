<h3>a simple qiniucloud and spring Integration</h3>

<h4>How to use?</h4>

1. ```git clone https://github.com/itblingfeng/qiniu-spring.git```
   ```cd qiniu-spring ```
   ```mvn clean install```
   
   add a jar package to your project 

2. a example: in application.xml

```xml
<beans>
......
<bean id = "uploadService" class = "cn.blingfeng.qiniu.UploadService">
	<property name = "accessKey" value = "${you_accessKey}"/>
	<property name = "secretKey" value = "${you_secretKey}"/>
	<property name = "bucket" value = "${you_bucket}"/>
	<property name = "engineRoom" value = "${you_engineRoom}"/>
</bean>
<bean id = "uploader" class = "cn.blingfeng.qiniu.Uploader">
	<property name = "uploadService" ref = "uploadService"/> 
</bean>
</beans>
```
then you can use @Autowired inject an instance into the Uploader Class。
