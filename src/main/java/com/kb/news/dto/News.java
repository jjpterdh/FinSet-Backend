package com.kb.news.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class News {
    private long nno;
    private long sno;
    private String title;
    private String content;
    private String link;
    private String image;
}
