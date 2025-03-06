package com.wipro.mobilestore.repository;

import com.wipro.mobilestore.entity.Mobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MobileRepository extends JpaRepository<Mobile, Integer> {
}
