package com.girlymart.girlymart.repository;

import com.girlymart.girlymart.model.CustomizeRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CustomizeRequestRepository extends JpaRepository<CustomizeRequest, Long> {
    List<CustomizeRequest> findAllByOrderBySubmittedAtDesc();
}