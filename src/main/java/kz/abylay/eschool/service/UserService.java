package kz.abylay.eschool.service;

import kz.abylay.eschool.models.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    Users addUser(Users user);
    Users findUserByEmail(String email);
}
