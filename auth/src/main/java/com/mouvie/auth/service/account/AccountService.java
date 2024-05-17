package com.mouvie.auth.service.account;

import com.mouvie.auth.config.appcontext.AppContext;
import com.mouvie.auth.config.customexception.ElementNotFoundException;
import com.mouvie.auth.config.customexception.ForbiddenException;
import com.mouvie.auth.config.customexception.LoginAlreadyExistsException;
import com.mouvie.auth.dto.mapper.account.AccountDtoMapper;
import com.mouvie.auth.dto.model.account.InputAccountDto;
import com.mouvie.auth.dto.model.account.OutputAccountDto;
import com.mouvie.auth.repository.role.RoleRepository;
import com.mouvie.auth.repository.user.UserRepository;
import com.mouvie.auth.repository.user.UserStatusRepository;
import com.mouvie.library.tools.RolesEnum;
import com.mouvie.library.model.Role;
import com.mouvie.library.model.User;
import com.mouvie.library.model.UserStatus;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AccountService implements IAccountService {

    private final AppContext appContext;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserStatusRepository userStatusRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public OutputAccountDto getById(String id) {
        // Check current user is either admin or trying to access his own account data
        User currentUser = appContext.getCurrentUser();
        if (!currentUser.getId().equals(id) && !isEqualToAlias(id) && !isUserAdmin(currentUser)) throw new ForbiddenException("You're not allowed to access this resource");

        // Return the user infos
        User user = isEqualToAlias(id) ? currentUser : getUserById(id);

        return AccountDtoMapper.toOutputAccountDto(user);
    }

    @Override
    public OutputAccountDto update(String id, InputAccountDto inputAccountDto) {
        User user = isEqualToAlias(id) ? appContext.getCurrentUser() : getUserById(id);
        

        if (!isUserAdmin(appContext.getCurrentUser()) && !appContext.getCurrentUser().getRoles().stream().map(Role::getName).toList().equals(inputAccountDto.getRoles())) throw new ForbiddenException("Only admin can update roles");

        // Vérification si le login existe déjà
        if (!user.getUsername().equals(inputAccountDto.getLogin()) && loginAlreadyExists(inputAccountDto.getLogin()) ) {
            throw new LoginAlreadyExistsException("Login already exists");
        }
        
        user = applyUserChanges(inputAccountDto, user);

        return AccountDtoMapper.toOutputAccountDto(user);
    }

    @Override
    public OutputAccountDto create(InputAccountDto inputAccountDto) {
        User userToCreate = new User();
        
        if (loginAlreadyExists(inputAccountDto.getLogin())) {
            throw new LoginAlreadyExistsException("Login already exists");
        }

        userToCreate = applyUserChanges(inputAccountDto, userToCreate);

        return AccountDtoMapper.toOutputAccountDto(userToCreate);
    }

    // Private methods

    private boolean isEqualToAlias(String id) {
        return id.equals("me");
    }

    private boolean isUserAdmin(User user) {
        return user.getRoles().stream().anyMatch(r -> r.getName().equals(RolesEnum.ROLE_ADMIN.name()));
    }

    private User applyUserChanges(InputAccountDto inputAccountDto, User user) {
        user
                .setUsername(inputAccountDto.getLogin())
                .setRoles(getRoleFromInput(inputAccountDto.getRoles()))
                .setStatus(getStatusFromInput(inputAccountDto.getStatus()))
                .setPassword(passwordEncoder.encode(inputAccountDto.getPassword()));

        return userRepository.save(user);
    }

    private User getUserById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new ElementNotFoundException(String.format("Unable to find user [id = %s]", id)));
    }

    private List<Role> getRoleFromInput(List<String> roles) {
        List<Role> roleList = new ArrayList<>();

        for (String role : roles) {
            Role dbRole = roleRepository.findByName(role).orElseThrow(() -> new ElementNotFoundException(String.format("Unable to find role [name = %s]", role)));
            roleList.add(dbRole);
        }

        return roleList;
    }

    private UserStatus getStatusFromInput(String status) {
        if (status == null) return userStatusRepository.findByName("Open").get();
        return userStatusRepository.findByName(status).orElseThrow(() -> new ElementNotFoundException(String.format("Unable to find status [name = %s]", status)));
    }
    
    private boolean loginAlreadyExists (String login) {
    	return userRepository.existsByUsername(login);
    }
}
