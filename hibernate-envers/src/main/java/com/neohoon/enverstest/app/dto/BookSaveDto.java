package com.neohoon.enverstest.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookSaveDto {
    private String bookName;
    private long memberId;
}
