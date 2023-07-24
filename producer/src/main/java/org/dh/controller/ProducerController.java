package org.dh.controller;

import lombok.RequiredArgsConstructor;
import org.dh.service.SenderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class ProducerController {

    private final SenderService senderService;

    @GetMapping("/send1")
    public ResponseEntity<String> sendMsg1(@RequestParam String key, @RequestParam String msg) {
        senderService.sendMsgPartition(key, msg);
        return ResponseEntity.ok(msg);
    }

    @GetMapping("/send2")
    public ResponseEntity<String> sendMsg2(@RequestParam String key, @RequestParam String msg) {
        senderService.sendMsgNoPartition(key, msg);
        return ResponseEntity.ok(msg);
    }
}
