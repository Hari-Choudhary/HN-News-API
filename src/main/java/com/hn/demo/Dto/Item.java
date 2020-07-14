package com.hn.demo.Dto;

import lombok.Getter;

import java.util.List;

@Getter
public class Item {

    private String id;
    private String title;
    private Integer score;
    private String url;
    private String by;
    private Long time;
    private String text;
    private List<Long> kids;
}
