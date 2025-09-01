controller : buat rest end point atau http request (POSTMAN)
services : buat bussines logic
repository : buat db opperation
entity : buat db table mapping
dto : data transfer object (antara BE sama API)
config : applicaton cofiguration buat db sama security
flow : controller - services - repository - db
component/urutan = controller - services - repository - ntt - dto - config

# step by step = 
1. project foundation (https://start.spring.io/)
2. setting di application.properties (buat nama db buat port & gpa configuration)
3. data layer: bikin di dalam folder entity ada user.java (penamaan file dalam folder harus dari huruf besar) di dalam repository bikin UserRepositor.java
4. bussines layer di dalam dto : auth response.java - loginrequest.java - registerrequest.java
5. di dalam controller bikin AuthController.java
6. di dalam config bikin SecurityConfig.java
7. testing application with postman

# API Testing

Proyek ini adalah contoh API sederhana dengan beberapa endpoint yang dapat diuji menggunakan **Postman**.

## 🚀 Cara Menjalankan

1. Jalankan aplikasi Spring Boot:
   ```bash
   mvnd spring-boot:run
   
kenapa mvnd? karna aku download mavem mvnd bukan mvn. kalau mau disamain nanti aku kasih zipnya ya

Aplikasi akan berjalan di http://localhost:8081.

# Step by step POSTMAN 
1. Buka Postman
2. New
3. Add new request Register
Method: POST
URL: http://localhost:8081/api/auth/register
Headers: Content-Type: application/json

Body raw (JSON):
{
"username": "testuser",
"email": "test@example.com",
"password": "123456"
}
Response: 200 OK

4. Add new request Login 

Method: POST
URL: http://localhost:8081/api/auth/login
Headers: Content-Type: application/json

Body raw (JSON):

{
"username": "testuser",
"password": "123456"
}
Response: 200 OK

5. Add new request Check user

Method: GET
URL: http://localhost:8081/api/auth/users
SEND !

Response: 200 OK
{
"message": "Users retrieved successfully!",
"success": true,
"data": []
}

6. Add new request Check user by id

Method: GET
URL: http://localhost:8081/api/auth/users/{id}
SEND !

Response: 200 OK
{
"message": "User found!",
"success": true,
"data": {}
}

7. Add new request Update user

Method: PUT
URL: http://localhost:8081/api/auth/users/{id}

Body raw (JSON):
tinggal rubah data yang mau di rubah
{
"username": "haifazahra", 
"email": "haifazaahra@gmail.com",
"password": "1234567"
}

Response: 200 OK
{
"message": "User updated successfully!",
"success": true,
"data": {}
}
