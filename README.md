# In‑Memory API

## Overview
Spring Boot API with in‑memory storage, JWT auth, users and posts with like/delete/list.

## Setup
- Java 21+, Maven
- Run: `mvn spring‑boot:run`

## Endpoints
| Path                     | Method | Description              |
|--------------------------|--------|---------------------------|
| POST /auth/signup        | signup |
| POST /auth/login         | login, returns JWT         |
| POST /posts              | create a new post (auth) |
| DELETE /posts/{id}       | delete own post           |
| POST /posts/{id}/like    | like a post (auth)        |
| GET /posts               | list all posts             |

## JWT
Include header: `Authorization: Bearer <token>`

## Logging
Uses SLF4J; logs key events: signup, login, post create/delete/like.

## Validation & Error Handling
Basic `RuntimeException`; Spring returns 400/500 accordingly.

## Testing via Postman
Import `postman_collection.json`. Contains all requests defined.

