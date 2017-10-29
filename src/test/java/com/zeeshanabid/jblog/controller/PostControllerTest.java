package com.zeeshanabid.jblog.controller;

import com.zeeshanabid.jblog.ApplicationConfig;
import com.zeeshanabid.jblog.model.Post;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.List;

public class PostControllerTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ApplicationConfig();
    }

    @Test
    public void testCreatePost() {
        Post p = new Post();
        p.setTitle("First post");
        p.setContent("This is my first post");

        Entity<Post> postEntity     = Entity.entity(p, MediaType.APPLICATION_JSON);
        Response     response       = target("posts").request(MediaType.APPLICATION_JSON).post(postEntity);
        Post         responseEntity = response.readEntity(Post.class);

        Assert.assertEquals("Post created successfully", response.getStatus(),        Status.OK.getStatusCode());
        Assert.assertEquals("Id should be set",          responseEntity.getId(),      1);
        Assert.assertEquals("Title should match",        responseEntity.getTitle(),   p.getTitle());
        Assert.assertEquals("Content should match",      responseEntity.getContent(), p.getContent());
    }

    @Test
    public void testGetPosts() {
        Post p = new Post();
        p.setTitle("First post");
        p.setContent("This is my first post");

        Response   response = target("posts").request(MediaType.APPLICATION_JSON).get(Response.class);
        List<Post> posts    = response.readEntity(new GenericType<List<Post>>() {});
        Assert.assertEquals("Get posts successfully", response.getStatus(), Status.OK.getStatusCode());
        Assert.assertEquals("Posts must be 0",        posts.size(),         0);

        Entity<Post> postEntity = Entity.entity(p, MediaType.APPLICATION_JSON);
        target("posts").request(MediaType.APPLICATION_JSON).post(postEntity);

        response = target("posts").request(MediaType.APPLICATION_JSON).get(Response.class);
        posts    = response.readEntity(new GenericType<List<Post>>() {});
        Assert.assertEquals("Get posts successfully", response.getStatus(), Status.OK.getStatusCode());
        Assert.assertEquals("Posts must be 1",        posts.size(),         1);
    }

    @Test
    public void testGetPost() {
        Response response = target("posts/1").request(MediaType.APPLICATION_JSON).get();
        Assert.assertEquals("Get invalid post", response.getStatus(), Status.NOT_FOUND.getStatusCode());

        Post p = new Post();
        p.setTitle("First post");
        p.setContent("This is my first post");
        Entity<Post> postEntity = Entity.entity(p, MediaType.APPLICATION_JSON);
        target("posts").request(MediaType.APPLICATION_JSON).post(postEntity);

        response = target("posts/1").request(MediaType.APPLICATION_JSON).get();
        Assert.assertEquals("Get post successfully", response.getStatus(), Status.OK.getStatusCode());
    }

    @Test
    public void testDeletePost() {
        Response response = target("posts/1").request(MediaType.APPLICATION_JSON).delete();
        Assert.assertEquals("Delete invalid post", response.getStatus(), Status.NOT_FOUND.getStatusCode());

        Post p = new Post();
        p.setTitle("First post");
        p.setContent("This is my first post");
        Entity<Post> postEntity = Entity.entity(p, MediaType.APPLICATION_JSON);
        target("posts").request(MediaType.APPLICATION_JSON).post(postEntity);

        response = target("posts/1").request(MediaType.APPLICATION_JSON).delete();
        Assert.assertEquals("Delete post successfully", response.getStatus(), Status.OK.getStatusCode());
    }

    @Test
    public void testUpdatePost() {
        Post p = new Post();
        p.setTitle("First post");
        p.setContent("This is my first post");
        Entity<Post> postEntity = Entity.entity(p, MediaType.APPLICATION_JSON);

        Response response = target("posts/1").request(MediaType.APPLICATION_JSON).put(postEntity);
        Assert.assertEquals("Update invalid post", response.getStatus(), Status.NOT_FOUND.getStatusCode());

        target("posts").request(MediaType.APPLICATION_JSON).post(postEntity);

        p.setContent("This should update the content");
        response = target("posts/1").request(MediaType.APPLICATION_JSON).put(postEntity);
        Post responseEntity = response.readEntity(Post.class);
        Assert.assertEquals("Update post successfully", response.getStatus(), Status.OK.getStatusCode());
        Assert.assertEquals("Content should be updated", responseEntity.getContent(), p.getContent());
    }
}
