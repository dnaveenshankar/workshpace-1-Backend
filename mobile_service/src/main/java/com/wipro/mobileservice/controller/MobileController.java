package com.wipro.mobileservice.controller;

import com.wipro.mobileservice.entity.Mobile;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.wipro.mobileservice.service.MobileService;
import java.util.List;

@RestController
@RequestMapping("/mobiles")
public class MobileController {

    @Autowired
    private MobileService mobileService;

    @PostMapping("/save")
    public ResponseEntity<Mobile> addMobile(@RequestBody Mobile mobile) {
        mobileService.saveMobile(mobile);
        return new ResponseEntity<>(mobile, HttpStatus.CREATED);
    }

    @GetMapping("/{mobileId}")
    public ResponseEntity<Mobile> fetchMobileDetails(@PathVariable int mobileId) {
        Mobile mobile = mobileService.fetchMobileById(mobileId);
        return new ResponseEntity<>(mobile, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Mobile>> fetchAllMobiles() {
        List<Mobile> mobiles = mobileService.getAllMobiles();
        return new ResponseEntity<>(mobiles, HttpStatus.OK);
    }
}
