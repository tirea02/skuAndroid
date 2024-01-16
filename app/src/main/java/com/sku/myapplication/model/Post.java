package com.sku.myapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private int id;
    private String title;
    private String content;
    private String date;
    private int viewCount;
    private String imgFile;
    private int userAccountId; // Integer ID referencing userAccount
    private String userId; // String UserID from userAccount table

}
