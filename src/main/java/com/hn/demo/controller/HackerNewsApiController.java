package com.hn.demo.controller;

import com.hn.demo.Dto.Item;
import com.hn.demo.Dto.User;
import com.hn.demo.service.HackerNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hacker-news/v0/api")
public class HackerNewsApiController {

    @Autowired
    private HackerNewsService hackerNewsService;

    @GetMapping("/top-stories")
    public List<Item> getTopStories() {
        return hackerNewsService.getTopStories();
    }

    @GetMapping("/comments/{storyId}")
    public List<User> getTopComments(@PathVariable("storyId") Long storyId) {
        return User.convert(hackerNewsService.getTopComments(storyId));
    }
}
