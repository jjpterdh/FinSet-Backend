package com.kb.finance.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Community {
    private long bno;
    private long sno;
    private String name;
    private long likes;
    private int isLiked;
    private String content;
    private String updatedAt;
}
