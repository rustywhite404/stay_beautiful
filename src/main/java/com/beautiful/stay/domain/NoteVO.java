package com.beautiful.stay.domain;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NoteVO {
    private int bno;
    private String nickname;
    private String subject;
    private String contents;
    private String writer;
    private String category;
    private String tag;
    private Timestamp date;
    private int readcount;
}
