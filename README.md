# 说明
### 1、已实现功能 
目前已经实现tmall oauth2.0 认证，设备发现，部分设备控制（格力空调和小米空气净化器，作者家中无其他设备，不好测试）；
另需说明，此项目中的控制部分设置均采取异步调用homeassistant api，即接收tmall的指令后，就返回成功状态，同时异步去对设备做控制操作，
因作者测试发现tmall限制超时时间为2s，同步调用会很大概率超时，tmall会大概率提示“怎么办，智能家居设备暂时不能控制，请一会在试试”，而实际设备却控制成功了。

### 2、正在实现功能
由于没人接入homeassistant的设备不一样，作者不想在代码中写过多的死代码，想采取灵活的方式，目前想的是在页面配置设备的一些参数，保持至数据库中，
在调取homeassistant restapi时去读取这些参数。

### 3、架构
参考了论坛中<https://bbs.hassbian.com/thread-1862-1-1.html>的框图，原谅我盗图
![架构](https://github.com/292427558/aligenieHomeAssistant/blob/sqldatadevelop/pic/frame.jpg)


# 部署

## 部署条件
1、需要自己有公网ip，其他内网穿透方式没测试过。作者是动态公网ip 然后ddns方式
2、证书文件 配置https，作者采取homeassistant的插件duck dns生成的证书文件。
3、树莓派 或者其他可以运行java 或者 docker的环境。
4、homeassistant 开启了rest api访问.
### 1、springboot jar包部署
项目采用springboot方式，作者已经打好包，在docker-image目录下。
### 2、docker 镜像部署
同时提供了docker镜像 提供了供树莓派使用的 arm64版本和x86架构镜像，请大家根据需要下载。
镜像仓库地址
#### docker hub
<https://hub.docker.com/repository/docker/292427558/aligeniehomeassistant>

##### 拉取镜像 
docker hub

```docker pull 292427558/aligeniehomeassistant:latest```

阿里镜像仓库

```docker pull registry.cn-hangzhou.aliyuncs.com/junge/aligeniehomeassistant:latest```


# 待完善部分

### 1、配置设备暂时没有设备位置选项下拉
    待完善，暂时实现为输入域 需和tmall的一致
### 2、目前暂只实现了空调和空气净化器
    由于作者家中只接入了空调和小米的空气净化器,目前为硬编码支持格力空调和小米空气净化器pro3
    其他设备的支持 作者正在完善，有兴趣的可以fork代码自己完善
### 镜像编译 优化 内存优化  容器日志中文乱码  部分请求不需要登录就可以访问 