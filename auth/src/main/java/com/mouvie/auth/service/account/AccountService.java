package com.mouvie.auth.service.account;

import com.mouvie.auth.dto.account.InputAccountDto;
import com.mouvie.auth.dto.account.OutputAccountDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountService implements IAccountService {


    @Override
    public OutputAccountDto getById(String id) {
        return null;
    }

    @Override
    public OutputAccountDto update(String id, InputAccountDto inputAccountDto) {
        return null;
    }

    @Override
    public OutputAccountDto create(InputAccountDto inputAccountDto) {
        return null;
    }
}
