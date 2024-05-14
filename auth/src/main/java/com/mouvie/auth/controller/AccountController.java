package com.mouvie.auth.controller;

import com.mouvie.auth.dto.account.InputAccountDto;
import com.mouvie.auth.dto.account.OutputAccountDto;
import com.mouvie.auth.service.account.AccountService;
import com.mouvie.auth.service.account.IAccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {

    private final IAccountService accountService;

    @GetMapping("/{id}")
    public ResponseEntity<OutputAccountDto> getAccountById(@PathVariable String id){
        return ResponseEntity.ok(accountService.getById(id));
    }

    @PostMapping
    public ResponseEntity<OutputAccountDto> createAccount(@Valid @RequestBody InputAccountDto inputAccountDto){
        return new ResponseEntity<>(accountService.create(inputAccountDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OutputAccountDto> updateAccount(@PathVariable String id, @Valid @RequestBody InputAccountDto inputAccountDto){
        return new ResponseEntity<>(accountService.update(id, inputAccountDto), HttpStatus.CREATED);
    }
}
