package com.chatop.api.controller;

import com.chatop.api.dto.MessageDTO;
import com.chatop.api.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createMessage(@RequestBody MessageDTO messageDTO) {
        messageService.saveMessage(messageDTO);
        return new ResponseEntity<>(Collections.singletonMap("message", "Message sent successfully !"), HttpStatus.CREATED);
    }
}