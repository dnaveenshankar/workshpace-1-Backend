package com.wipro.mobilestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.wipro.mobilestore.entity.Mobile;
import com.wipro.mobilestore.service.MobileService;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
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
    
    @PutMapping("/")
    public ResponseEntity<Mobile> editMobile(@RequestBody Mobile mobile) {

        mobileService.updateMobile(mobile);
        ResponseEntity<Mobile> responseEntity = new ResponseEntity<>(mobile, HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("/{mobileId}")
    public ResponseEntity<Void> deleteMobile(@PathVariable int mobileId) {

        mobileService.deleteMobile(mobileId);
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        return responseEntity;
    }
}
