# In‑Memory API

## Overview
Spring Boot API with in‑memory storage, JWT auth, users and posts with like/delete/list.

## Setup
- Java 21+, Maven
- Run: `mvn spring‑boot:run`

## Endpoints
| Path                     | Method | Description                                                                 |
|--------------------------|--------|-----------------------------------------------------------------------------|
| POST /auth/signup        | signup | Registers a new user with username and password.                           |
| POST /auth/login         | login, returns JWT | Authenticates user credentials and returns a JWT token for further requests. |
| POST /posts              | create a new post (auth) | Creates a new post; requires a valid JWT in the `Authorization` header.      |
| DELETE /posts/{id}       | delete own post | Deletes the authenticated user's own post by ID.                             |
| POST /posts/{id}/like    | like a post (auth) | Likes the specified post; requires authentication via JWT.                   |
| GET /posts               | list all posts | Returns a list of all posts (public endpoint).                                |


## JWT
Include header: `Authorization: Bearer <token>`

## Validation & Error Handling
Basic `RuntimeException`; Spring returns 400/500 accordingly.

## Testing via Postman
Import `postman_collection.json`. Contains all requests defined.

🛠 API Testing with Postman
Below are the steps to test each API endpoint using Postman.

1️⃣ Signup – Create a New User
Method: POST
URL: http://localhost:8080/auth/signup

Body (JSON):

json
Copy
Edit
{
  "username": "testuser",
  "password": "testpass"
}
Expected Response:

json
Copy
Edit
{
  "message": "User registered successfully"
}
2️⃣ Login – Get JWT Token
Method: POST
URL: http://localhost:8080/auth/login

Body (JSON):

json
Copy
Edit
{
  "username": "testuser",
  "password": "testpass"
}
Expected Response:

json
Copy
Edit
{
  "token": "eyJhbGciOiJIUzI1NiIs..."
}
📌 Copy the token from the response. You will need it for authenticated requests.

3️⃣ Create a New Post
Method: POST
URL: http://localhost:8080/posts

Headers:

pgsql
Copy
Edit
Authorization: Bearer <your_jwt_token>
Content-Type: application/json
Body (JSON):

json
Copy
Edit
{
  "content": "This is my first post"
}
Expected Response:

json
Copy
Edit
{
  "id": 1,
  "content": "This is my first post",
  "likes": 0
}
4️⃣ Like a Post
Method: POST
URL: http://localhost:8080/posts/{id}/like
(Replace {id} with the actual post ID, e.g., 1)

Headers:

makefile
Copy
Edit
Authorization: Bearer <your_jwt_token>
Expected Response:

json
Copy
Edit
{
  "message": "Post liked successfully"
}
5️⃣ Delete a Post (Only Owner Can Delete)
Method: DELETE
URL: http://localhost:8080/posts/{id}

Headers:

makefile
Copy
Edit
Authorization: Bearer <your_jwt_token>
Expected Response:

json
Copy
Edit
{
  "message": "Post deleted successfully"
}
6️⃣ List All Posts
Method: GET
URL: http://localhost:8080/posts

Expected Response:

json
Copy
Edit
[
  {
    "id": 1,
    "content": "This is my first post",
    "likes": 2
  }
]


