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

