package com.wipro.mobileservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wipro.mobileservice.entity.Mobile;
import com.wipro.mobileservice.exception.ResourceNotFoundException;
import com.wipro.mobileservice.repository.MobileRepository;

@Service
public class MobileServiceImpl implements MobileService {

    @Autowired
    private MobileRepository mobileRepository;

    @Override
    public Mobile saveMobile(Mobile mobile) {
        return mobileRepository.save(mobile);
    }

    @Override
    public Mobile fetchMobileById(int mobileId) {
        return mobileRepository.findById(mobileId)
            .orElseThrow(() -> new ResourceNotFoundException("Mobile not found with id " + mobileId));
    }

    @Override
    public List<Mobile> getAllMobiles() {
        return mobileRepository.findAll();
    }
}
