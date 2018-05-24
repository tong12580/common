# common-lib
[Apache License2.0](http://www.apache.org/licenses/)

## 关键字

`Spring` `Json` `image` `Files` `Nio` `time`

## 版本

| 时间        | 编辑人     | 版本号| 发布|
|:---------- |:---------:|---:|---:|
| 2017/09/27  | joker      | 1.0 | 是
| 2018/03/01  | joker      | 1.1 | 是
| 2018/05/24  | joker      | 1.2 | 是

----

## maven 中央仓库下载地址

Apache Maven

```
		<dependency>
			<groupId>io.github.tong12580</groupId>
			<artifactId>common-lib</artifactId>
			<version>1.2</version>
		</dependency>

```

Gradle/Grails

```
compile 'io.github.tong12580:common-lib:1.2'

```

## 简介

1.这个一个 基于Spring的一个工具类,您可以在您的Spring工程上使用它，它能够为您节省大量的代码。

### common-lib 可以做什么?

common-lib 是一个基于 Spring 的工具类

#### 1.0，1.1版本 提供如下的工具类服务

1. json解析,提供Gson和jackson来解析Json数据格式的封装
2. 缓存的配置处理 提供一个基于WeakHashMap和一个基于Guava的本地缓存封装
3. 提供路径寻找配置
4. 提供Cookies的解析处理添加配置
5. 提供基于java8的带时区的(可通过对"GTM+?"来对需要格式化的时区进行修正)
6. 带泛型的IResult结果集的统一处理
7. 身份证信息的简单校验
8. 统一错误提示信息
9. 图片的压缩剪裁
10. csv数据处理
11. Base64 MD5 处理
12. 基于io和Nio的文件处理
13. 基于httpClient4.5的http和https服务提供
14. 基于SnowflakeIdWorker和短8位UUID的唯一性id提供
15. 数字,字母,邮箱,手机号,文件后缀,号码隐藏,表情符号过滤替换方法提供
16. IP,系统可用内存获取
17. Map和Bean的互相转换
18. 基本排序教程
19. 16进制10进制的位数转换 与字符编码解码方式

#### 1.2版本 提供

20. 线程池快捷创建使用方式
21. Netty使用和指导用例
22. JWT的封装与快捷使用方式
23. Excel的快捷读写方式

#### 后续将提供的工具类服务（为1.3版本将会提供到的服务）

1. redis
2. 谷歌Wifi和基站数据解析服务
3. ...
