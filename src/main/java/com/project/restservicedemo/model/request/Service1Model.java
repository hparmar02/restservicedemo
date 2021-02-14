package com.project.restservicedemo.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@ToString
public class Service1Model {
    private String channel;
    private String conversationId;
    private String country;
    private JsonData json_data;
    private Security security;
}
