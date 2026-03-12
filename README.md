
# Student Management Microservices

Hệ thống quản lý sinh viên được xây dựng dựa trên kiến trúc Microservices, bao gồm các service chính: **Auth-service**, **API-Gateway**, **Student-service**, và **Notification-service**.

## 🛠️ Công nghệ sử dụng

* **Java 17**
* **Spring Boot 3.x**
* **Spring Cloud Gateway**
* **MySQL Database**
* **Spring Security & JWT**

## 🏗️ Kiến trúc các thành phần

* **API-Gateway:** Cổng truy cập duy nhất, điều hướng request và xác thực JWT.
* **Auth-service:** Quản lý tài khoản, đăng nhập và cấp phát token.
* **Student-service:** Quản lý thông tin sinh viên.
* **Notification-service:** Xử lý các thông báo trong hệ thống.

## 🚀 Cách khởi chạy

### 1. Yêu cầu hệ thống

* JDK 17
* Maven 3.8+
* MySQL Server

### 2. Thiết lập Database

Chạy lệnh sau trong MySQL Workbench để tạo database:

```sql
CREATE DATABASE auth_db;
-- Các database khác tương tự...

```

### 3. Khởi chạy project

```bash
# Clone project
git clone <URL_REPO_CUA_BAN>

# Build project
mvn clean install

# Chạy từng service theo thứ tự:
# 1. Auth-service
# 2. API-Gateway
# 3. Các service còn lại

```

## 📝 Quy trình Git

Dự án áp dụng Git Flow:

* `feature/*`: Nhánh tính năng mới.
* `develop`: Nhánh đang phát triển.
* `main`: Nhánh ổn định (Production).

---
