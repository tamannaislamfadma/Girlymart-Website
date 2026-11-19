package com.girlymart.girlymart.repository;

import com.girlymart.girlymart.model.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BannerRepository extends JpaRepository<Banner, Long> {
    List<Banner> findByTypeOrderBySortOrderAsc(String type);
}