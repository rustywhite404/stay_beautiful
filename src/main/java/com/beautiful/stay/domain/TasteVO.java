package com.beautiful.stay.domain;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TasteVO {
    private int bno;
    private String subject;
    private String thumbnail;
    private String contents;
    private String writer;
    private String date;
    private String category;
    private String s_contents;
    private String tag;
}
