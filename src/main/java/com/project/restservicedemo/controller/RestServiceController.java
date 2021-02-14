package com.project.restservicedemo.controller;

import com.project.restservicedemo.model.request.JsonData;
import com.project.restservicedemo.model.request.Service1Model;
import com.project.restservicedemo.model.response.Data;
import com.project.restservicedemo.model.response.ResponseJsonData;
import com.project.restservicedemo.model.response.ResponseObject;
import com.project.restservicedemo.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "v1/restservicedemo", consumes = MediaType.APPLICATION_JSON_VALUE)
public class RestServiceController {

    @Autowired
    private RestService restService;

    @PostMapping(value = "saveTask")
    public ResponseEntity<?> saveTask(@RequestBody Service1Model serviceRequest) {
        JsonData returnValue = restService.save(serviceRequest.getJson_data());
        if (returnValue == null) {
            return new ResponseEntity<>(
                    "Error in saving Task", HttpStatus.BAD_REQUEST);
        }
        ResponseJsonData responseJsonData = new ResponseJsonData();
        Data responseData = new Data();
        responseData.setCreated_at(returnValue.getCreated_at());
        responseData.setExpiring_at(returnValue.getExpiring_at());
        responseData.setType(returnValue.getType());
        responseData.setUpdated_at(returnValue.getUpdated_at());
        responseData.setTask_token(returnValue.getId().toString());
        responseJsonData.setSecurity(serviceRequest.getSecurity());
        responseJsonData.setData(responseData);

        return ResponseEntity.ok(responseJsonData);

    }

    @PostMapping(value = "updateTask")
    public ResponseEntity<?> updateTask(@RequestBody ResponseJsonData serviceRequest) {
        long taskToken = Long.parseLong(serviceRequest.getData().getTask_token());
        int returnValue = restService.updateTask(taskToken);

        ResponseObject responseObject = new ResponseObject();
        if (returnValue == 1) {
            responseObject.setMsg("Success");
            responseObject.setStatus("task has been resumed");
            return ResponseEntity.ok(responseObject);
        }
        responseObject.setMsg("Failure");
        responseObject.setStatus("No update to task status");
        return ResponseEntity.badRequest().body(responseObject);
    }
}
