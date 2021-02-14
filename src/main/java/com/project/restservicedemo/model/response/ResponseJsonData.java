package com.project.restservicedemo.model.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.restservicedemo.model.request.Security;
import lombok.Data;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@ToString
public class ResponseJsonData {
    private com.project.restservicedemo.model.response.Data data;
    private Security security;

}
