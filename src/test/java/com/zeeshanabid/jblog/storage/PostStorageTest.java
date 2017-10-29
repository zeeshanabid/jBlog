package com.zeeshanabid.jblog.storage;

import com.zeeshanabid.jblog.exceptions.PostNotFound;
import com.zeeshanabid.jblog.model.Post;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

public class PostStorageTest {
    @Test
    public void testCreatePost() throws Exception {
        PostStorage posts = new PostStorage();
        Post p     = new Post();
        p.setTitle("First Post");

        posts.save(p);
        Assert.assertEquals("Posts count must be 1", posts.count(), 1);
        Assert.assertEquals("A new id must be assigned to post", p.getId(),1);
    }

    @Test
    public void testCreateMultiplePost() throws Exception {
        PostStorage posts = new PostStorage();
        Post        p1     = new Post();
        p1.setTitle("First Post");
        posts.save(p1);

        Post        p2     = new Post();
        p1.setTitle("Second Post");
        posts.save(p2);
        Assert.assertEquals("Posts count must be 2", posts.count(), 2);
    }

    @Test
    public void testUpdatePost() throws Exception {
        PostStorage posts = new PostStorage();
        Post        p     = new Post();

        p.setTitle("First Post");
        posts.save(p);
        p.setContent("This is my first post");
        posts.update(p);
        Assert.assertEquals("Posts count must be 1", posts.count(), 1);

        Post g = posts.get(1);
        Assert.assertEquals("Post title must be saved", g.getContent(), p.getContent());
    }

    @Test(expected = PostNotFound.class)
    public void testUpdateInvalidPost() throws Exception {
        PostStorage posts = new PostStorage();
        Post        p     = new Post();

        p.setId(1);
        p.setTitle("First Post");
        posts.update(p);
        Assert.assertEquals("Posts count must be 0", posts.count(), 0);
    }

    @Test
    public void testDeletePost() throws Exception {
        PostStorage posts = new PostStorage();
        Post        p     = new Post();

        p.setTitle("First Post");
        posts.save(p);
        Assert.assertEquals("Posts count must be 1", posts.count(), 1);

        posts.delete(p.getId());
        Assert.assertEquals("Posts count must be 0", posts.count(), 0);
    }

    @Test(expected = PostNotFound.class)
    public void testDeleteInvalidPost() throws Exception {
        PostStorage posts = new PostStorage();

        posts.delete(1);
        Assert.assertEquals("Posts count must be 0", posts.count(), 0);
    }

    @Test
    public void testGetPost() throws Exception {
        PostStorage posts   = new PostStorage();
        Post        p       = new Post();
        String      title   = "First Post";
        String      content = "My first post.";
        p.setTitle(title);
        p.setContent(content);

        posts.save(p);

        Post g = posts.get(p.getId());
        Assert.assertEquals("Id must match with saved post", g.getId(), p.getId());
        Assert.assertEquals("Title must match with saved post", g.getTitle(), title);
        Assert.assertEquals("Content must match with saved post", g.getContent(), content);
    }

    @Test(expected = PostNotFound.class)
    public void testGetInvalidPost() throws Exception {
        PostStorage posts = new PostStorage();

        posts.get(1);
        Assert.assertEquals("Posts count must be 0", posts.count(), 0);
    }

    @Test
    public void testGetAll() throws Exception {
        PostStorage posts    = new PostStorage();
        Post        p1       = new Post();
        String      title1   = "First Post";
        String      content1 = "My first post.";
        p1.setTitle(title1);
        p1.setContent(content1);
        posts.save(p1);

        Post        p2       = new Post();
        String      title2   = "Second Post";
        String      content2 = "My Second post.";
        p2.setTitle(title2);
        p2.setContent(content2);
        posts.save(p2);

        Collection<Post> allPosts = posts.getAll();
        Assert.assertEquals("Count must be 2", allPosts.size(), 2);

        Assert.assertTrue("First post must be in the list", allPosts.contains(p1));
        Assert.assertTrue("Second post must be in the list", allPosts.contains(p2));
    }

    @Test
    public void testSearch() throws Exception {
        PostStorage posts    = new PostStorage();
        Post        p1       = new Post();
        String      title1   = "First Post";
        String      content1 = "My first post.";
        p1.setTitle(title1);
        p1.setContent(content1);
        posts.save(p1);

        Post        p2       = new Post();
        String      title2   = "Second Post";
        String      content2 = "My Second post.";
        p2.setTitle(title2);
        p2.setContent(content2);
        posts.save(p2);

        Collection<Post> searchResult = posts.search("first");
        Assert.assertTrue("Search result should contain first post", searchResult.contains(p1));
        Assert.assertFalse("Search result should not contain second post", searchResult.contains(p2));
    }
}
