package com.umsimplesrodrigo.comunicacao_bot_bling.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/callback")
public class BlingCallbackController {

    @GetMapping("/callback")
    public ResponseEntity<String> getCallback(@RequestParam("code") String code, @RequestParam("state") String state) {
        System.out.println("Code received: " + code);
        System.out.println("State received: " + state);

        return ResponseEntity.ok("Code received with success!");
    }
}
