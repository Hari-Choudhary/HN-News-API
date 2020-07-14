package com.hn.demo.service;

import com.hn.demo.Dto.Item;

import java.util.List;

public interface HackerNewsService {

    List<Item> getTopStories();

    List<Item> getTopComments(Long storyId);
}

