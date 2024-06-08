## install docker
1. check the linux core version if 3.10 or above `uname -srm`
2. uninstall old version Docker `yum remove docker docker-client docker-client-latest docker-common docker-latest docker-latest-logrotate docker-logrotate docker-selinux docker-engine-selinux docker-engine docker-ce`
3. install gcc environment `yum -y install gcc` `yum -y install gcc-c++`
4. install yum `yum install -y yum-utils device-mapper-persistent-data lvm2 --skip-broken`
5. set Docker mirror repository `yum-config-manager --add-repo https://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo`
6. replace the mirror repository in docker-ce.repo with ali repository `sed -i 's/download.docker.com/mirrors.aliyun.com\/docker-ce/g' /etc/yum.repos.d/docker-ce.repo`
7. update yum software index for faster install software with yum `yum makecache fast`
8. install Docker `yum -y install docker-ce docker-ce-cli containerd.io`
9. start Docker `systemctl start docker`
10. set Docker start when machine starts `systemctl enable docker`
11. set docker mirror for faster 
```
sudo mkdir -p /etc/docker
sudo tee /etc/docker/deamon.json <<-'EOF'
{
    "registry-mirrors":["https://zz3sblpi.mirror.aliyuncs.com]
}
EOF
sudo systemctl daemon-reload
sudo systemctl restart docker

```


## install docker-compose
1. download docker-compose to destinate place and install `curl -SL https://github.com/docker/compose/releases/download/v2.17.3/docker-compose-linux-x86_64 -o /usr/local/bin/docker-compose`
2. add execution right `sudo chmod +x /usr/local/bin/docker-compose`
3. creat symbolic link for docker-compose `ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose`
4. 