# explore

## WSL Debian 安装 Docker

[参考链接](https://docs.docker.com/engine/install/debian/)

``` Shell
# Add Docker's official GPG key:
sudo apt-get update
sudo apt-get install ca-certificates curl
sudo install -m 0755 -d /etc/apt/keyrings
sudo curl -fsSL https://download.docker.com/linux/debian/gpg -o /etc/apt/keyrings/docker.asc
sudo chmod a+r /etc/apt/keyrings/docker.asc

# Add the repository to Apt sources:
echo \
  "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.asc] https://download.docker.com/linux/debian \
  $(. /etc/os-release && echo "$VERSION_CODENAME") stable" | \
  sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
sudo apt-get update

sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin
```

但安装好后，我运行 docker 命令会报错，直接将 /etc/init.d/docker:62 注释可以解决问题。

``` plain
/etc/init.d/docker:62: ulimit: limit setting error (Invalid argument)
```

配置 docker 可以不通过 sudo 执行命令

``` Shell
sudo groupadd docker
sudo gpasswd -a ${current_user} docker
sudo chmod a+rw /var/run/docker.sock
sudo service docker restart 
```

## 通过 Docker 运行 MySQL

``` Shell
docker pull mysql:5.7

docker run -itd --name mysql-dev -p 3306:3306 -e MYSQL_ROOT_PASSWORD=xxxxx mysql:5.7
```

通过 [SQLyog](https://www.download.io/sqlyog-community-edition-download-windows.html) 连接 MySQL，但 SQLyog 不支持 MySQL 8.0 以上版本的 caching_sha2 加密方式（无法连接数据库），所以 MySQL 版本我选择了 5.7。

## Spring Boot 整合 MyBatis

参考连接：

- ChatGPT
- <https://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/zh/index.html>
