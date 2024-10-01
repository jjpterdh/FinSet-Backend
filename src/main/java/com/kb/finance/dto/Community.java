package com.kb.finance.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Community {
    long bno;
    long uno;
    long sno;
    long likes;
    boolean isLiked;
    String content;
    String updatedAt;
}
