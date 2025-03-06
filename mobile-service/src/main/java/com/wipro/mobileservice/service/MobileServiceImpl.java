package com.wipro.mobileservice.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.wipro.mobileservice.controller.MobileController;
import com.wipro.mobileservice.entity.Mobile;
import com.wipro.mobileservice.exception.ResourceNotFoundException;
import com.wipro.mobileservice.repository.MobileRepository;

@Service
public class MobileServiceImpl implements MobileService {

    private static final Logger logger = LoggerFactory.getLogger(MobileServiceImpl.class);

    @Autowired
    private MobileRepository mobileRepository;

    @Override
    public Mobile saveMobile(Mobile mobile) {
        logger.info("MobileService: Saving mobile started");
        Mobile savedMobile = mobileRepository.save(mobile);
        logger.info("MobileService: Saving mobile ended with status code {}", HttpStatus.CREATED);
        return savedMobile;
    }

    @Override
    public Mobile fetchMobileById(int mobileId) {
        logger.info("MobileService: Fetch mobile by ID {} started", mobileId);
        Optional<Mobile> mobileOptional = mobileRepository.findById(mobileId);

        if (mobileOptional.isPresent()) {
            Mobile mobile = mobileOptional.get();
            logger.info("Mobile found with id: {}", mobileId);
            logger.info("MobileService: Fetch mobile by ID {} ended with status code {}", mobileId, HttpStatus.OK);
            return mobile;
        } else {
            logger.warn("Mobile not found with id: {}", mobileId);
            logger.info("MobileService: Fetch mobile by ID {} ended with status code {}", mobileId, HttpStatus.NOT_FOUND);
            throw new ResourceNotFoundException("Mobile not found with id " + mobileId);
        }
    }

    @Override
    public List<Mobile> getAllMobiles() {
        logger.info("MobileService: Fetch all mobiles started");
        List<Mobile> mobiles = mobileRepository.findAll();
        logger.info("MobileService: Fetch all mobiles ended with status code {}", HttpStatus.OK);
        return mobiles;
    }
}