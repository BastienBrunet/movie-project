package com.mouvie.auth.dto.mapper.account;

import com.mouvie.auth.dto.model.account.OutputAccountDto;
import com.mouvie.library.model.Role;
import com.mouvie.library.model.User;

public class AccountDtoMapper {

    public static OutputAccountDto toOutputAccountDto(User user){
        return new OutputAccountDto()
                .setUuid(user.getId())
                .setLogin(user.getUsername())
                .setCreatedAt(user.getCreatedOn())
                .setUpdatedAt(user.getLastUpdatedOn())
                .setRoles(user.getRoles().stream().map(Role::getName).toList());
    }
}
