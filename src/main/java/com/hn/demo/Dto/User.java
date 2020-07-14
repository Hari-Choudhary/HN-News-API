package com.hn.demo.Dto;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class User implements Serializable {

    private String hnHandle;
    private String comment;

    public static List<User> convert(List<Item> items) {
        return items.stream().map(item -> User.builder()
                .hnHandle(item.getBy())
                .comment(item.getText())
                .build())
                .collect(Collectors.toList());

    }
}
