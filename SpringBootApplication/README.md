# SpringBootApplication


SpringBootApplication is a sample spring boot project.

### Tech

SpringBootApplication uses:

* [Spring Boot]
* [MySql]

### Installation

Install the dependencies and devDependencies and start the server.

```sh
JDK 1.7 or later
Maven 3 or later
spring-boot 1.3.5.RELEASE
MySql
```

#### Database
```sh
    username: admin
    password: admin
    url: jdbc:mysql://localhost/sample
```
Table
```sh
	/*
 Navicat Premium Data Transfer

 Source Server         : 10.10.132.118_3306
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : 10.10.132.118:3306
 Source Schema         : sample

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 04/08/2019 01:55:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `Id` int(255) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Family` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `User_Name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Salary` decimal(10, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
```

#### Run
```sh
mvn spring-boot:run
```

