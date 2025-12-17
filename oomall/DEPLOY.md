# 部署指南 (Updated)

## 1. 数据库配置 (Server C: 119.3.210.49)

由于您指定了 MySQL 部署在 `119.3.210.49`，请确保该服务器上的 MySQL：
1.  **已启动** 并且端口 `3306` 对外开放（或对集群内网开放）。
2.  **已创建数据库和表**：请在 `119.3.210.49` 上执行 `sql/init.sql` 脚本。
3.  **允许远程连接**：用户 `root` (或您使用的用户) 允许从其他服务器 IP 连接。

## 2. 部署应用服务 (Server A & Server B)

### 方案一：使用 Docker Swarm (推荐，符合实验要求)

假设您已经初始化了 Swarm 集群，并且 `119.3.210.49` 是集群中的一个节点 (Server C)。

**docker-stack.yml** 配置示例：

```yaml
version: '3.8'
services:
  # 如果 MySQL 也是 Swarm 服务的一部分，运行在 Server C
  mysql:
    image: mysql:8.0
    environment:
      MYSQL_ROOT_PASSWORD: password
      MYSQL_DATABASE: oomall
    volumes:
      - db-data:/var/lib/mysql
    ports:
      - "3306:3306"
    deploy:
      placement:
        constraints: [node.ip == 119.3.210.49] # 强制部署到 Server C
    networks:
      - oomall-net

  aftersale-service:
    image: your-username/oomall-aftersale:v1
    environment:
      # 如果 mysql 在同一个 overlay 网络中，直接用服务名 'mysql'
      MYSQL_HOST: mysql
      MYSQL_PORT: 3306
      MYSQL_PASSWORD: password
      SERVICE_SERVICE_URL: http://service-service:8082
    ports:
      - "8081:8081"
    deploy:
      replicas: 1
      placement:
        constraints: [node.role == manager] # 假设 Server A 是 Manager
    networks:
      - oomall-net

  service-service:
    image: your-username/oomall-service:v1
    environment:
      MYSQL_HOST: mysql
      MYSQL_PORT: 3306
      MYSQL_PASSWORD: password
    ports:
      - "8082:8082"
    deploy:
      replicas: 1
      placement:
        constraints: [node.role == worker] # 假设 Server B 是 Worker
    networks:
      - oomall-net

networks:
  oomall-net:
    driver: overlay

volumes:
  db-data:
```

### 方案二：MySQL 独立部署 (非 Swarm 服务)

如果 MySQL 只是独立运行在 `119.3.210.49` 上，而不是 Swarm 集群的服务，您需要让应用直接连接 IP。

**docker-compose.yml (在 Server A 和 Server B 上分别运行)**

**Server A (售后模块):**
```yaml
version: '3.8'
services:
  aftersale-service:
    image: your-username/oomall-aftersale:v1
    ports:
      - "8081:8081"
    environment:
      MYSQL_HOST: 119.3.210.49  # 指向 Server C IP
      MYSQL_PORT: 3306
      MYSQL_PASSWORD: password
      # 如果 Server B 的 IP 是 192.168.x.x
      SERVICE_SERVICE_URL: http://<Server-B-IP>:8082 
```

**Server B (服务模块):**
```yaml
version: '3.8'
services:
  service-service:
    image: your-username/oomall-service:v1
    ports:
      - "8082:8082"
    environment:
      MYSQL_HOST: 119.3.210.49 # 指向 Server C IP
      MYSQL_PORT: 3306
      MYSQL_PASSWORD: password
```

## 3. 本地开发连接远程数据库

如果您想在本地 IDEA 或 Docker 中运行应用，但连接到 `119.3.210.49` 的数据库进行调试：

**修改 docker-compose.yml:**
```yaml
  aftersale-service:
    # ...
    environment:
      MYSQL_HOST: 119.3.210.49
      # ...
```

**或者在 IDEA 环境变量中设置:**
`MYSQL_HOST=119.3.210.49`
