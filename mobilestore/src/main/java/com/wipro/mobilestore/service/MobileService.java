package com.wipro.mobilestore.service;

import java.util.List;
import com.wipro.mobilestore.entity.Mobile;

public interface MobileService {
    Mobile saveMobile(Mobile mobile);
    Mobile fetchMobileById(int mobileId);
    List<Mobile> getAllMobiles();
    Mobile updateMobile(Mobile mobile);
    void deleteMobile(int mobileId); 
}
