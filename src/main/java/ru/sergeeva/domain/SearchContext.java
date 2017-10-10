package ru.sergeeva.domain;

import lombok.Data;

/**
 * contentType: "city"
 limit : 10
 query : "Магн"
 withParent : "1"

 searchContext
 */
@Data
public class SearchContext {
    private String contentType;
    private int limit;
    private String query;
    private String withParent;
}
