package com.side.mylist.service;

import com.side.mylist.dto.JoinDTO;
import com.side.mylist.entity.UserEntity;
import com.side.mylist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public void joinProcess(JoinDTO joinDTO) {

        // DB에 이미 동일한 username을 가진 회원이 존재하는지? (검증)
        boolean isUser = userRepository.existsByUsername(joinDTO.getUsername());

        if (isUser) {
            return;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setUsername(joinDTO.getUsername());
        userEntity.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));
        userEntity.setRole("ROLE_USER");

        userRepository.save(userEntity);

    }

}
