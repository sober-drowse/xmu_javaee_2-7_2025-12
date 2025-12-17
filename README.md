# OOMall 微服务项目开发指南

本项目是一个基于 Spring Boot 3.2.0 和 Spring Cloud OpenFeign 的微服务示例项目，包含“售后服务 (Aftersale)”和“服务单服务 (Service)”两个模块。

## 1. 环境准备

在开始之前，请确保你的开发环境满足以下要求：

*   **JDK**: OpenJDK 17
*   **Maven**: 3.8+
*   **IDE**: IntelliJ IDEA 
*   **Database**: MySQL 8.0+
*   **Git**: 版本控制工具

## 2. 获取代码

从 GitHub 克隆项目到本地：

```bash
git clone https://github.com/sober-drowse/xmu_javaee_2-7_2025-12.git
cd xmu_javaee_2-7_2025-12
```

## 3. 数据库配置

项目包含两个微服务，它们共用一个数据库（在实际生产中通常分离，但本项目为简化演示共用）。

### 方式 A：使用本地数据库 (推荐)

1.  **创建数据库**：
    在你的本地 MySQL 中创建一个名为 `oomall` 的数据库。
    ```sql
    CREATE DATABASE oomall DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
    ```

2.  **初始化数据**：
    执行项目提供的初始化脚本 `oomall/sql/init.sql`。该脚本会创建必要的表 (`aftersale`, `service_order`) 并插入测试数据。

### 方式 B：使用远程数据库

如果你有现成的远程 MySQL 服务器（例如 IP 为 `119.3.210.49`），请确保该服务器上已创建 `oomall` 库并执行了初始化脚本。

## 4. 项目配置 (Environment Variables)

本项目使用 Spring Boot 的配置文件 (`application.yml`)，支持通过环境变量动态配置数据库连接。

默认配置如下：
*   Host: `localhost`
*   Port: `3306`
*   Username: `root`
*   Password: `password`

**如果你的数据库配置与默认值不同，请在 IDE 中配置以下环境变量：**

| 变量名 | 描述 | 示例值 |
| :--- | :--- | :--- |
| `MYSQL_HOST` (只有这个需要改，其他不需要改)| 数据库 IP 地址 | `119.3.210.49` |
| `MYSQL_PORT` | 数据库端口 | `3306` |
| `MYSQL_USERNAME` | 数据库用户名 | `root` |
| `MYSQL_PASSWORD` | 数据库密码 | `123456` |

## 5. 启动项目 (IntelliJ IDEA)

本项目包含两个启动类，需要同时启动。

1.  **打开项目**：使用 IDEA 打开 `oomall` 目录（包含 `pom.xml` 的根目录）。
2.  **加载 Maven**：等待 IDEA 自动加载 Maven 依赖。
3.  **配置运行环境**：
    *   找到 `oomall/aftersale/src/main/java/.../AftersaleApplication.java`。
    *   找到 `oomall/service/src/main/java/.../ServiceApplication.java`。
    *   **关键步骤**：如果使用非默认数据库配置，请在“Run/Debug Configurations”中，为这两个应用添加上述环境变量（Environment variables），即在 “步骤4：项目配置” 中修改MYSQL_HOST环境变量为部署mysql服务器的IP地址。
4.  **启动服务**：
    *   运行 `AftersaleApplication` (端口 8081)
    *   运行 `ServiceApplication` (端口 8082)

确保控制台日志中没有报错，且看到 `Started ...Application in ... seconds` 字样。

## 6. 功能验证

项目提供了一个 HTTP 请求文件，用于快速验证微服务链路。

1.  在 IDEA 中打开文件：`oomall/requests.http`。
2.  找到 **"1. 审核售后单 (管理员同意维修)"** 的请求。
3.  点击请求旁边的绿色运行按钮 (Run)。

**预期结果**：
*   **HTTP 响应**：状态码 `200 OK`，返回 `{"errno": 0, "errmsg": "成功"}`。
*   **Aftersale 日志**：显示 `Audit aftersale...` 和 `Creating service order...`。
*   **Service 日志**：显示 `Creating service order...` 和 SQL 插入语句。
*   **数据库**：`aftersale` 表中对应记录状态变为 `1`，`service_order` 表中新增一条记录。

## 7. 常见问题

*   **连接超时 (Communications link failure)**：
    *   检查 `MYSQL_HOST` 是否正确。
    *   如果是远程数据库，检查防火墙是否开放 3306 端口。
    *   项目已配置 HikariCP 心跳保活，防止空闲连接断开。
*   **OpenFeign 调用失败 (Connection refused)**：
    *   确保 `ServiceApplication` (8082) 已成功启动。

---
Happy Coding!
javaee第四次实验
