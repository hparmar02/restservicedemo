package com.project.restservicedemo.model.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.restservicedemo.model.request.Security;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@lombok.Data
@ToString
public class Data {
    private long created_at;
    private long expiring_at;
    private String type;
    private long updated_at;
    private String task_token;
}
