package com.zeeshanabid.jblog.controller;

import com.zeeshanabid.jblog.exceptions.MaxIdReachedException;
import com.zeeshanabid.jblog.exceptions.PostNotFound;
import com.zeeshanabid.jblog.model.Post;
import com.zeeshanabid.jblog.storage.PostStorage;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Post resource (exposed at "posts" path)
 */
@Path("posts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Singleton
public class PostController {
    private PostStorage posts = new PostStorage();
    /**
     * Method handling HTTP GET requests. This will return a list of all
     * posts to the client as "application/json" media type.
     *
     * @return List of all posts
     */
    @GET
    public Collection<Post> getPosts() {
        return posts.getAll();
    }

    /**
     * Get a single post. Raise PostNotFound if there is no such post.
     * @param id id of the post
     * @return A single post
     * @throws PostNotFound
     */
    @GET
    @Path("/{post_id}")
    public Post getPost(@PathParam("post_id") int id) throws PostNotFound {
        Post post = posts.get(id);
        return post;
    }

    /**
     * Delets a post. Raise PostNotFound if there is no such post.
     * @param id id of the post
     * @return status of the operation
     * @throws PostNotFound
     */
    @DELETE
    @Path("/{post_id}")
    public Map<String, String> deletePost(@PathParam("post_id") int id) throws PostNotFound {
        posts.delete(id);
        Map<String, String> m = new HashMap<>();
        m.put("status", "deleted");
        return m;
    }

    /**
     * Creates a post. Raise MaxIdReachedException if the max id has been reached.
     * @param p post object in JSON form
     * @return created post object in JSON form
     * @throws MaxIdReachedException
     */
    @POST
    public Post createPost(Post p) throws MaxIdReachedException {
        posts.save(p);
        return p;
    }

    /**
     * Update a post. Raise PostNotFound if no such post exists.
     * @param p post object in JSON form.
     * @param id id of the post.
     * @return updated post object.
     * @throws PostNotFound
     */
    @PUT
    @Path("/{post_id}")
    public Post updatePost(Post p, @PathParam("post_id") int id) throws PostNotFound {
        p.setId(id);
        posts.update(p);
        return p;
    }
}
