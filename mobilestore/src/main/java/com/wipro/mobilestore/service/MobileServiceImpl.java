package com.wipro.mobilestore.service;

import java.util.List;	
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wipro.mobilestore.entity.Mobile;
import com.wipro.mobilestore.exception.ResourceNotFoundException;
import com.wipro.mobilestore.repository.MobileRepository;
import java.util.Optional;

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
    
    @Override
    public Mobile updateMobile(Mobile mobile) {

        Optional<Mobile> optionalMobile = mobileRepository.findById(mobile.getMobileId());

        if (optionalMobile.isEmpty()) {
            throw new ResourceNotFoundException("Mobile not existing with id: " + mobile.getMobileId());
        }
        mobileRepository.save(mobile);
        return mobile;
    }
    @Override
    public void deleteMobile(int mobileId) {

        Optional<Mobile> optionalMobile = mobileRepository.findById(mobileId);
        if (optionalMobile.isEmpty()) {
            throw new ResourceNotFoundException("Mobile not existing with id: " + mobileId);
        }
        Mobile mobile = optionalMobile.get();
        mobileRepository.delete(mobile);
    }
}
