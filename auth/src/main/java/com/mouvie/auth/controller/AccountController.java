package com.mouvie.auth.controller;

import com.mouvie.auth.dto.model.account.InputAccountDto;
import com.mouvie.auth.dto.model.account.OutputAccountDto;
import com.mouvie.auth.service.account.IAccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {

    private final IAccountService accountService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<OutputAccountDto> getAccountById(@PathVariable String id){
        return ResponseEntity.ok(accountService.getById(id));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<OutputAccountDto> createAccount(@Valid @RequestBody InputAccountDto inputAccountDto){
        return new ResponseEntity<>(accountService.create(inputAccountDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/{id}")
    public ResponseEntity<OutputAccountDto> updateAccount(@PathVariable String id, @Valid @RequestBody InputAccountDto inputAccountDto){
        return new ResponseEntity<>(accountService.update(id, inputAccountDto), HttpStatus.CREATED);
    }
}
