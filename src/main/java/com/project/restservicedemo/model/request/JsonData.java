package com.project.restservicedemo.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Blob;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@ToString

@Entity
@Table(name = "jsondata")
public class JsonData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @Transient
    private String blob;

    @JsonIgnore
    @Column(name = "blobData")
    private Blob blobData;

    @Column(name = "type")
    private String type;

    @JsonIgnore
    @Column(name = "status")
    private String status;

    @JsonIgnore
    @Column(name = "created_at")
    private long created_at;

    @JsonIgnore
    @Column(name = "expiring_at")
    private long expiring_at;

    @JsonIgnore
    @Column(name = "updated_at")
    private long updated_at;


}
