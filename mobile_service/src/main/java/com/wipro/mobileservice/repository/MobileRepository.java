package com.wipro.mobileservice.repository;

import com.wipro.mobileservice.entity.Mobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MobileRepository extends JpaRepository<Mobile, Integer> {
}
