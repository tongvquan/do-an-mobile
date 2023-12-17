package org.example.doanmobile.service;


import org.example.doanmobile.entity.UserEntity;
import org.example.doanmobile.model.RegisterDto;
import org.example.doanmobile.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<UserEntity> findByUserName(String userName){
        UserEntity userEntity = userRepository.findByUserName(userName);
        if(userEntity == null) return Optional.empty();

        var user = new UserEntity();
        user.setId(userEntity.getId());
        user.setUserName(userEntity.getUserName());
        user.setPassword(userEntity.getPassword());
        user.setRole(userEntity.getRole());
        user.setExtraInfo(userEntity.getExtraInfo());
        return Optional.of(user);
    }

    public Boolean register(RegisterDto dto){
        if(userRepository.findByUserName(dto.getUserName()) != null){
            return false;
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(dto.getUserName());
        userEntity.setPassword(passwordEncoder.encode(dto.getPassword()));
        userEntity.setRole("USER");
        userRepository.save(userEntity);
        return true;
    }
}
