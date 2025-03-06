package com.wipro.mobileservice.controller;

import com.wipro.mobileservice.entity.Mobile;
import com.wipro.mobileservice.service.MobileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mobiles")
public class MobileController {

    private static final Logger logger = LoggerFactory.getLogger(MobileController.class);

    @Autowired
    private MobileService mobileService;

    @PostMapping("/save")
    public ResponseEntity<Mobile> addMobile(@RequestBody Mobile mobile) {
        logger.info("Mobile Controller: Adding mobile started");
        Mobile savedMobile = mobileService.saveMobile(mobile);
        logger.info("Mobile Controller: Adding mobile ended with status code {}", HttpStatus.CREATED);
        return new ResponseEntity<>(savedMobile, HttpStatus.CREATED);
    }

    @GetMapping("/{mobileId}")
    public ResponseEntity<Mobile> fetchMobileDetails(@PathVariable int mobileId) {
        logger.info("Mobile Controller: Fetching mobile with ID {} started", mobileId);
        Mobile mobile = mobileService.fetchMobileById(mobileId);
        logger.info("Mobile Controller: Fetching mobile with ID {} ended with status code {}", mobileId, HttpStatus.OK);
        return new ResponseEntity<>(mobile, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Mobile>> fetchAllMobiles() {
        logger.info("Mobile Controller: Fetching all mobiles started");
        List<Mobile> mobiles = mobileService.getAllMobiles();
        logger.info("Mobile Controller: Fetching all mobiles ended with status code {}", HttpStatus.OK);
        return new ResponseEntity<>(mobiles, HttpStatus.OK);
    }
}
