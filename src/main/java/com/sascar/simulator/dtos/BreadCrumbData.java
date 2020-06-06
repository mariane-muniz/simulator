package com.sascar.simulator.dtos;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BreadCrumbData {
    private List<LinkData> links;

    {
        this.links = new ArrayList<>();
    }

    public BreadCrumbData add(final LinkData link) {
        this.links.add(link);
        return this;
    }
}