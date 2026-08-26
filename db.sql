CREATE DATABASE `myapp`
USE `myapp`;
-- myapp.restaurants definition

CREATE TABLE `restaurants` (
  `delivery_fee` int NOT NULL,
  `is_open` bit(1) NOT NULL,
  `minimum_order_amount` int NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `restaurant_id` bigint NOT NULL AUTO_INCREMENT,
  `updated_at` datetime(6) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`restaurant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- myapp.carts definition

CREATE TABLE `carts` (
  `cart_id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `restaurant_id` bigint NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `session_id` varchar(36) NOT NULL,
  PRIMARY KEY (`cart_id`),
  KEY `FK1wwmot9772p901aismub7pd5c` (`restaurant_id`),
  CONSTRAINT `FK1wwmot9772p901aismub7pd5c` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurants` (`restaurant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- myapp.menus definition

CREATE TABLE `menus` (
  `is_available` bit(1) NOT NULL,
  `price` int NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `menu_id` bigint NOT NULL AUTO_INCREMENT,
  `restaurant_id` bigint NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `image_url` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`menu_id`),
  KEY `FK49thmnytvo47ttv4ggtwo9rrj` (`restaurant_id`),
  CONSTRAINT `FK49thmnytvo47ttv4ggtwo9rrj` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurants` (`restaurant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- myapp.orders definition

CREATE TABLE `orders` (
  `required_payers` int NOT NULL,
  `total_amount` int NOT NULL,
  `order_id` bigint NOT NULL AUTO_INCREMENT,
  `ordered_at` datetime(6) NOT NULL,
  `restaurant_id` bigint NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `session_id` varchar(36) NOT NULL,
  `payment_type` enum('single','split') NOT NULL,
  `status` enum('pending','paid','cooking','delivering','completed','cancelled') NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `FK2m9qulf12xm537bku3jnrrbup` (`restaurant_id`),
  CONSTRAINT `FK2m9qulf12xm537bku3jnrrbup` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurants` (`restaurant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- myapp.payments definition

CREATE TABLE `payments` (
  `paid_amount` int NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `order_id` bigint NOT NULL,
  `payment_id` bigint NOT NULL AUTO_INCREMENT,
  `updated_at` datetime(6) NOT NULL,
  `session_id` varchar(36) NOT NULL,
  `payment_method` enum('card','transfer','cash') NOT NULL,
  `status` enum('pending','paid','failed') NOT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `FK81gagumt0r8y3rmudcgpbk42l` (`order_id`),
  CONSTRAINT `FK81gagumt0r8y3rmudcgpbk42l` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- myapp.restaurant_images definition

CREATE TABLE `restaurant_images` (
  `sort_order` int NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `image_id` bigint NOT NULL AUTO_INCREMENT,
  `restaurant_id` bigint NOT NULL,
  `image_url` text NOT NULL,
  `type` enum('thumbnail','detail') NOT NULL,
  PRIMARY KEY (`image_id`),
  KEY `FK714rhrkn3odt4ucjohgipd9h4` (`restaurant_id`),
  CONSTRAINT `FK714rhrkn3odt4ucjohgipd9h4` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurants` (`restaurant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- myapp.cart_items definition

CREATE TABLE `cart_items` (
  `quantity` int NOT NULL,
  `cart_id` bigint NOT NULL,
  `cart_item_id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `menu_id` bigint NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  PRIMARY KEY (`cart_item_id`),
  KEY `FKpcttvuq4mxppo8sxggjtn5i2c` (`cart_id`),
  KEY `FKcgxdkukntyc1bqo7gkml96657` (`menu_id`),
  CONSTRAINT `FKcgxdkukntyc1bqo7gkml96657` FOREIGN KEY (`menu_id`) REFERENCES `menus` (`menu_id`),
  CONSTRAINT `FKpcttvuq4mxppo8sxggjtn5i2c` FOREIGN KEY (`cart_id`) REFERENCES `carts` (`cart_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- myapp.order_items definition

CREATE TABLE `order_items` (
  `menu_price` int NOT NULL,
  `quantity` int NOT NULL,
  `menu_id` bigint NOT NULL,
  `order_id` bigint NOT NULL,
  `order_item_id` bigint NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(100) NOT NULL,
  PRIMARY KEY (`order_item_id`),
  KEY `FKl768w9ey6elx9j3a7u2m2i47c` (`menu_id`),
  KEY `FKbioxgbv59vetrxe0ejfubep1w` (`order_id`),
  CONSTRAINT `FKbioxgbv59vetrxe0ejfubep1w` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
  CONSTRAINT `FKl768w9ey6elx9j3a7u2m2i47c` FOREIGN KEY (`menu_id`) REFERENCES `menus` (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

