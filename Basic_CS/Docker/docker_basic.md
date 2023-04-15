# Docker
## Docker的安装(CentOS)
- yum update
- yum install -y yum-utils device-mapper-persistent-data lvm2 安装需要的软件包， yum-util 提供yum-config-manager功能，另外两个是devicemapper驱动依赖的
- yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo 设置yum源
- yum install -y docker-ce 安装docker，出现输入的界面都按 y
- docker -v 查看docker版本，验证是否验证成功
## <a href="https://cr.console.aliyun.com/cn-hangzhou/instances/mirrors">安装阿里云镜像 </a>
- sudo mkdir -p /etc/docker
- sudo tee /etc/docker/daemon.json <<-'EOF' {
  "registry-mirrors":[加速器地址]
  }
  EOF
- sudo systemctl daemon-reload
- sudo systemctl restarter docker
<br>

## docker的命令
- systemctl start docker
- systemctl stop docker
- systemctl restarter docker
- systemctl status docker
- systemctl enable docker 设置开机时启动docker服务
### 镜像相关命令
- docker images 显示所有镜像
- docker images -p 像是所有镜像标号
- docker search imageName e.g. docker search redis 查找镜像
- docker pull imageName e.g docker pull redis 下载
- docker pull imageName:version e.g. docker pull redis:5.0
- docker rmi imageNumber 删除镜像
- docker image -q 查找多有镜像的标号
- docker rmi `docker images -q` 删除本地所有镜像
### 容器相关命令
- docker ps
- docker ps -a
- docker run params
  - -i:保持容器运行。通常与 -t 同时使用。加入it这两个参数后，容器创建后自动进入容器中，退出容器后，容 器自动关闭。
  - -t:为容器重新分配一个伪输入终端，通常与 -i 同时使用。
  - -d:以守护(后台)模式运行容器。创建一个容器在后台运行，需要使用docker exec 进入容器。退出后，容 器不会关闭。
  - it 创建的容器一般称为交互式容器，-id 创建的容器一般称为守护式容器
  - --name:为创建的容器命名。
  - e.g.  docker run -it --name=c1 centos:7 /bin/bash #创建交互式容器
  - e.g. docker run -id --name=c2 centos:7 #创建守护式容器
- docker exec -it c2 /bin/bash 进入容器
- docker stop 容器名称
- docker start 容器名称
- docker rm 容器名称
- docker inspect 容器名称
<br>

## docker容器的数据卷
- docker容器删除后，容器中产生的数据消失；docker容器和外部机器不可以直接交换文件，外部机器链接宿主机，宿主机连接容器；
- 数据卷的概念：
  - 数据卷是宿主机中的一个目录或者文件
  - 当容器目录和数据卷目录绑定后，对方修改会立即同步
  - 一个数据卷可以被多个容器同时挂载
  - 一个容器也可以被挂载多个数据卷
- 数据卷的作用：
  - 容器数据持久化
  - 外部机器和容器间接通信
  - 容器之间数据交换
- 配置数据卷
  - `docker run -it --name=c3 -v /root/data:/root/data_container centos /bin/bash` 在宿主机的/root/data 和 c3容器的/root/data_container配置了数据卷,两文件夹内数据同步
  - 在删除c3容器后，可以恢复 `docker run -it --name=c3 -v /root/data:/root/data_container centos /bin/bash` 
  - 在一个容器内挂载多个数据卷 
  ```
    docker run -it --name=c4 \
    -v ~/data2:/root/data2 \
    -v ~/data3:/root/data3 \
    centos
  ``` 
  - 两个容器挂载同一个数据卷
   ```
    docker run -it --name=c3 -v /root/data:/root/data_container centos:7 /bin/bash  
    docker run -it --name=c4 -v /root/data:/root/data_container centos:7 /bin/bash  
   ```
- 配置数据卷容器
  - `docker run -it --name=c5 -v /volume centos /bin/bash` 设置数据卷
  - `docker run -it --name=c6 --volumes-from c5 centos /bin/bash` 
  - `docker run -it --name=c7 --volumes-from c5 centos /bin/bash` 
<br>

## docker上的应用部署
- mysql
  - `docker pull mysql:5.6`
  - `mkdir ~/mysql`
  - `mkdir ~/mysql`
  - `docker run -id -p 3307:3306 --name=c_mysql -v $PWD/conf:/etc/mysql/conf.d -v $PWD/logs:/logs -v $PWD/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=1234 mysql:5.6`
- tomcat
  - `docker pull tomcat`
  - `mkdir ~/tomcat`
  - `cd ~/tomcat`
  - `docker run -id --name=c_tomcat -p 8080:8080 -v $PWD:/usr/local/tomcat/webapps  tomcat`
- nginx
  - `docker pull nginx`
  - `mkdir ~/nginx` `cd ~/nginx` `mkdir conf` `cd conf` `vim nginx.conf`
  ```
    user  nginx;
    worker_processes  1;

    error_log /var/log/nginx/error.log warn;
    pid /var/run/nginx.pid;

    events {
        worker_connections  1024;
    }

    http {
        include /etc/nginx/mime.types;
        default_type application/octet-stream;

        log_format main '$remote_addr - $remote_user [$time_local] "$request" '
                        '$status $body_bytes_sent "$http_referer" '
                        '"$http_user_agent" "$http_x_forwarded_for"';

        access_log /var/log/nginx/access.log main;

        sendfile on;
        #tcp_nopush     on;

        keepalive_timeout  65;

        #gzip  on;

        include /etc/nginx/conf.d/*.conf;
    }
    ```
    - `docker run -id --name=c_nginx -p 80:80 -v $PWD/conf/nginx.conf:/etc/nginx.conf -v $PWD/logs:/var/log/nginx -v $PWD/html:/usr/share/nginx/html nginx`
- redis
  - `docker pull redis:5.0`
  - `docker run -id --name=c_redis -p 6379:6379 redis:5.0`
  - `./redis-cli -h serverIPAdress -p 6379`本地连接docker中的redis
<br>

## Dockerfile
### docker镜像原理
- docker里面，centos的镜像只有200MB，而一个centos操作系统os有几个G。原因是，linux文件系统由bootfs和rootfs组成，centos镜像会使用宿主机的bootfs。
- docker里面，tomcat镜像有600MB，而tomcat只有几十MB。原因是，docker镜像是由特殊的文件堆叠而成，最底端是bootfs，并使用宿主机的bootfs，第二层是rootfs，再往上叠加jdk，再叠加tomcat，所以tomcat的镜像比较大。
### 镜像制作
- `docker commit imageId imageName:version` 制作镜像
- `docker save -o compressedFileName imageName:version` 压缩镜像
- `docker load -i compressedFileName` 加载镜像
### dockerfile
- 概念
  - dockerfile是一个文本文件，包含了一条条的指令，每一条指令构建一层，基于基础镜像，最终构成一个新的镜像
  - 