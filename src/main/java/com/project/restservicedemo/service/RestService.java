package com.project.restservicedemo.service;


import com.project.restservicedemo.model.request.JsonData;
import com.project.restservicedemo.repository.ServiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.util.Base64;
import java.util.Optional;

@Service
public class RestService {

    private static final Logger logger = LoggerFactory.getLogger(RestService.class);

    @Autowired
    private ServiceRepository serviceRepository;

    @Value("${app.expiring.milliseconds.add}")
    private long addMilliseconds;

    public JsonData save(JsonData jsonData) {
        try {
            byte[] decodedByte = Base64.getDecoder().decode(jsonData.getBlob());
            Blob b = new SerialBlob(decodedByte);
            jsonData.setBlobData(b);
            long currentMilliseconds = System.currentTimeMillis();
            jsonData.setCreated_at(currentMilliseconds);
            jsonData.setUpdated_at(currentMilliseconds);

            long expiringMilliseconds = currentMilliseconds + addMilliseconds;
            jsonData.setExpiring_at(expiringMilliseconds);
            logger.debug("Saving Task to the Database");
            return serviceRepository.save(jsonData);

        } catch (Exception ex) {
            logger.error("Exception in Saving Task to the Database - Returning NULL -  ex: ", ex);
            return null;
        }
    }

    public Iterable<JsonData> findAll() {
        return serviceRepository.findAll();
    }

    public Optional<JsonData> findById(long id) {
        return serviceRepository.findById(id);
    }

    public int updateTask(long id) {
        return serviceRepository.updateTask(id, "resumed");
    }

}
