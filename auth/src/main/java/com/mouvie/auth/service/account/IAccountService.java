package com.mouvie.auth.service.account;

import com.mouvie.auth.dto.model.account.InputAccountDto;
import com.mouvie.auth.dto.model.account.OutputAccountDto;

public interface IAccountService {

    OutputAccountDto getById(String id);

    OutputAccountDto update(String id, InputAccountDto inputAccountDto);

    OutputAccountDto create(InputAccountDto inputAccountDto);
}
