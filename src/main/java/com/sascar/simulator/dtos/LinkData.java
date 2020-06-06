package com.sascar.simulator.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LinkData {
    private String url;
    private String title;

    public LinkData(final String url, final String title) {
        this.url = url;
        this.title = title;
    }
}