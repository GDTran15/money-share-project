package com.share_money.backend.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@AllArgsConstructor
@RequestMapping("share-request")
public class ShareRequestController {

    @PostMapping
    public ResponseEntity<String> createNewShareRequest(){
        return ResponseEntity.ok("success");
    }
}
