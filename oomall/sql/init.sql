CREATE DATABASE IF NOT EXISTS oomall;
USE oomall;

-- 售后单表
CREATE TABLE IF NOT EXISTS `aftersale` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `shop_id` bigint NOT NULL,
  `type` int NOT NULL COMMENT '0:换货, 1:退货, 2:维修',
  `status` int NOT NULL DEFAULT 0 COMMENT '0:待审核, 1:审核通过, 2:审核不通过',
  `reason` varchar(255) DEFAULT NULL,
  `conclusion` varchar(255) DEFAULT NULL,
  `customer_id` bigint DEFAULT NULL,
  `order_id` bigint DEFAULT NULL,
  `order_item_id` bigint DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `consignee` varchar(50) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `region_id` bigint DEFAULT NULL,
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 服务单表
CREATE TABLE IF NOT EXISTS `service_order` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `shop_id` bigint NOT NULL,
  `aftersale_id` bigint NOT NULL,
  `type` int NOT NULL COMMENT '0:上门, 1:寄件, 2:线下',
  `status` int NOT NULL DEFAULT 0,
  `consignee` varchar(50) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `region_id` bigint DEFAULT NULL,
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 插入测试数据
INSERT INTO `aftersale` (`id`, `shop_id`, `type`, `status`, `reason`, `customer_id`, `order_id`, `consignee`, `mobile`, `address`, `region_id`) 
VALUES (1, 1, 2, 0, '商品损坏', 1001, 2001, '张三', '13800138000', '厦门大学', 361000);
