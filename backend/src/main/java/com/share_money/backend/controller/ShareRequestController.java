package com.share_money.backend.controller;

import com.share_money.backend.dto.share_request.GetOweShareRequest;
import com.share_money.backend.model.User;
import com.share_money.backend.service.ShareRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("share-request")
public class ShareRequestController {

    private final ShareRequestService shareRequestService;

    @GetMapping
    public ResponseEntity<List<GetOweShareRequest>> getShareRequest(@AuthenticationPrincipal User user) {
        shareRequestService.getOweShareRequest(user);
        return ResponseEntity.ok(shareRequestService.getOweShareRequest(user));
    }
}
