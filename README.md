# jBlog
A simple java based blog.

## Run Server

`mvn jetty:run`

## Run Tests

`mvn test`

## Package WAR file

`mvn package`

This will start a local server on port `:8080`

## List Posts

`curl http://localhost:8080/blog/posts`

## Create Post

`curl -H 'Content-Type: application/json' -d '{"title":"First blog", "content": "This is my first blog"}' http://localhost:8080/blog/posts`

## Get Post

`curl http://localhost:8080/blog/posts/:id`

## Update Post

`curl -XPUT -H 'Content-Type: application/json' -d '{"content": "This is an update."}' http://localhost:8080/blog/posts/1`

## Delete Post

`curl -XDELETE http://localhost:8080/blog/posts/:id`

## Search Posts

`curl http://localhost:8080/blog/posts/search?q=first`
