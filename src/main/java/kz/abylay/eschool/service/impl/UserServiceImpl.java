package kz.abylay.eschool.service.impl;

import kz.abylay.eschool.models.Roles;
import kz.abylay.eschool.models.Users;
import kz.abylay.eschool.repositories.UsersRepository;
import kz.abylay.eschool.repositories.mapper.RoleMapper;
import kz.abylay.eschool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UsersRepository repository;
    private final RoleMapper roleMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UsersRepository repository, RoleMapper mapper, BCryptPasswordEncoder passwordEncoder) {
        this.repository = repository;
        roleMapper = mapper;
        this.bCryptPasswordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        roleMapper.insertRole(new Roles(null, "USER"));
        Users users = new Users(null, "admin@gmail.com", bCryptPasswordEncoder.encode("admin"), "Admin Adminov", List.of(roleMapper.findRole("USER")));
        repository.save(users);
    }

    @PreDestroy
    public void destroy() {
        if (roleMapper.findRole("USER") != null) {
            roleMapper.deleteRole("USER");
        }
        Users users = repository.findByEmail("admin@gmail.com");
        if (users != null){
            repository.delete(users);
        }
    }

    @Override
    public Users addUser(Users user) {
        if (findUserByEmail(user.getEmail()) == null) {
            Roles roles = roleMapper.findRole("USER");
            if (roles != null) {
                user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
                user.setRoles(List.of(roles));
                return repository.save(user);
            }
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users myUser = repository.findByEmail(username);
        if (myUser != null) {
            User user_security = new User(myUser.getEmail(), myUser.getPassword(), myUser.getRoles());
            return user_security;
        }
        throw new UsernameNotFoundException("User not found!");
    }

    @Override
    public Users findUserByEmail(String email) {
        return repository.findByEmail(email);
    }
}
