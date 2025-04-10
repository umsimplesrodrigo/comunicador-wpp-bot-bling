package com.umsimplesrodrigo.comunicacao_bot_bling.controller;

import com.umsimplesrodrigo.comunicacao_bot_bling.DTO.BlingTokenResponse;
import com.umsimplesrodrigo.comunicacao_bot_bling.service.BlingAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BlingCallbackController {
    private final BlingAuthService authService;

    @Autowired
    public BlingCallbackController(BlingAuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/callback")
    public ResponseEntity<String> getCallback(@RequestParam("code") String code, @RequestParam("state") String state) {
        System.out.println("Code received: " + code);
        System.out.println("State received: " + state);
        //return ResponseEntity.ok("Code received with success!");

        BlingTokenResponse response = authService.exchangeCodeForToken(code);

        System.out.println("Token received: " + response.getAccessToken());

        return ResponseEntity.ok("Token received with success!");
    }
}
