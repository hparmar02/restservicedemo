package com.project.restservicedemo.repository;

import com.project.restservicedemo.model.request.JsonData;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface ServiceRepository extends CrudRepository<JsonData, Long> {

    @Modifying
    @Transactional
    @Query("update JsonData j set j.status = :status WHERE j.id = :id")
    int updateTask(@Param("id") long id, @Param("status") String status);
}

