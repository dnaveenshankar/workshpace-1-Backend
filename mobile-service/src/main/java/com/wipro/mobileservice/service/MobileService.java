package com.wipro.mobileservice.service;

import java.util.List;

import com.wipro.mobileservice.entity.Mobile;

public interface MobileService {
    Mobile saveMobile(Mobile mobile);
    Mobile fetchMobileById(int mobileId);
    List<Mobile> getAllMobiles();
}
