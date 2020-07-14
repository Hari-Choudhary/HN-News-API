package com.hn.demo.service.impl;

import com.hn.demo.Dto.Item;
import com.hn.demo.client.HnClient;
import com.hn.demo.service.HackerNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HackerNewsServiceImpl implements HackerNewsService {

    @Autowired
    private HnClient hnClient;

    @Override
    public List<Item> getTopStories() {
        List<Long> newStoryIds = hnClient.getNewStories();
        List<Item> stories = new ArrayList<>();
        fetchItems(newStoryIds, stories);
        return topTenStoriesSortedByScore(stories);
    }

    @Override
    public List<Item> getTopComments(Long storyId) {
        Item story = hnClient.getItem(storyId);
        List<Item> childComments = new ArrayList<>();
        fetchItems(story.getKids(), childComments);
        return topTenCommentsSortedByChild(childComments);
    }

    private List<Item> topTenCommentsSortedByChild(List<Item> childComments) {
        return childComments.stream()
                .sorted((item1, item2) -> ((Integer)item2.getKids().size()).compareTo(item1.getKids().size()))
                .collect(Collectors.toList());
    }

    private List<Item> topTenStoriesSortedByScore(List<Item> stories) {
        return stories.stream()
                .filter(item -> item.getTime() >= Instant.now().getEpochSecond() - 10 * 60)
                .sorted((item1, item2) -> item2.getScore().compareTo(item1.getScore()))
                .limit(10)
                .collect(Collectors.toList());
    }

    private void fetchItems(List<Long> itemIds, List<Item> itemList) {
        itemIds.parallelStream()
                .forEach(id -> Optional.ofNullable(hnClient.getItem(id))
                        .ifPresent(itemList::add));
    }
}
