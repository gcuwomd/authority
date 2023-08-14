package com.example.authority.pojo;

import lombok.Data;

import java.util.List;
@Data
public class PageBean {
    private long total;
    private List row;

    public PageBean(long total, List row) {
        this.total = total;
        this.row = row;
    }
}
