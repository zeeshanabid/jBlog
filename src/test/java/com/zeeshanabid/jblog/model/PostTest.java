package com.zeeshanabid.jblog.model;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PostTest {
    @Test
    public void testId() throws Exception {
        Post p = new Post();
        int id = 1;
        p.setId(id);
        Assert.assertEquals("Id of the post must be 1", p.getId(), id);
    }

    @Test
    public void testTitle() throws Exception {
        Post   p     = new Post();
        String title = "First Blog";
        p.setTitle(title);
        Assert.assertEquals("Title of the post must be 'First Blog'", p.getTitle(), title);
    }

    @Test
    public void testContent() throws Exception {
        Post   p       = new Post();
        String content = "This is my first blog post.";
        p.setContent(content);
        Assert.assertEquals("Content of the post must match", p.getContent(), content);
    }

    @Test
    public void testTime() throws Exception {
        Post              p         = new Post();
        String            time      = "2017-10-28 20:00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        p.setTime(LocalDateTime.parse(time, formatter));
        Assert.assertEquals("Time of the post must match", p.getTime().format(formatter), time);
    }
}
