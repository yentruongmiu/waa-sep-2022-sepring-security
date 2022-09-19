package edu.miu.productReview.service.impl;

import edu.miu.productReview.domain.Role;
import edu.miu.productReview.domain.User;
import edu.miu.productReview.dto.RoleDto;
import edu.miu.productReview.dto.UserDto;
import edu.miu.productReview.repo.RoleRepo;
import edu.miu.productReview.repo.UserRepo;
import edu.miu.productReview.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final ModelMapper mapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto save(UserDto user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        User result = userRepo.save(mapper.map(user, User.class));
        return mapper.map(result, UserDto.class);
    }

    @Override
    public List<UserDto> findAll() {
        List<User> users = new ArrayList<>();
        userRepo.findAll().forEach(users::add);
        return users.stream().map(u -> mapper.map(u, UserDto.class)).toList();
    }

    @Override
    public UserDto findById(int id) {
        return userRepo.findById(id).map(user -> mapper.map(user, UserDto.class)).orElse(new UserDto());
    }

    @Override
    public UserDto update(int id, UserDto user) {
        User result = userRepo.findById(id).get();
        if(result != null) {
            user.setId(result.getId());
            userRepo.save(mapper.map(user, User.class));
            return user;
        } else throw new IllegalArgumentException();
    }

    @Override
    public void delete(int id) {
        userRepo.deleteById(id);
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       return userRepo.findByEmail(username);
    }

    @Override
    public UserDto addRole(int id, RoleDto role) {
        User user = userRepo.findById(id).get();
        if(user != null) {
            if(role.getId() != 0) {
                Role _role = roleRepo.findById(role.getId()).get();
                if(_role != null)
                    user.addRole(_role);
            } else {
                var result = roleRepo.save(mapper.map(role, Role.class));
                user.addRole(result);
            }
            return mapper.map(userRepo.save(user), UserDto.class);
        } else throw new IllegalArgumentException();
    }
}
