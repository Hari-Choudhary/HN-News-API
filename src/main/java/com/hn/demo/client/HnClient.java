package com.hn.demo.client;

import com.hn.demo.Dto.Item;
import com.hn.demo.Dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "HnFeignClient", url = "${hn.service.base.url}")
public interface HnClient {

    @GetMapping(value = "/item/{itemId}.json", produces = "application/json")
    Item getItem(@PathVariable("itemId") Long itemId);

    @GetMapping(value = "/newstories.json", produces = "application/json")
    List<Long> getNewStories();

    @GetMapping(value = "/user/{userId}.json", produces = "application/json")
    List<User> getUser(@PathVariable("userId") Long userId);
}
