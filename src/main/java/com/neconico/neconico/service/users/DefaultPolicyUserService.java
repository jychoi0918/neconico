package com.neconico.neconico.service.users;

import com.neconico.neconico.dto.users.*;
import com.neconico.neconico.mapper.users.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DefaultPolicyUserService implements UserService{

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Long joinUser(UserJoinDto userJoinDto) {
        userJoinDto.setAccountPw(
                passwordEncoder.encode(userJoinDto.getAccountPw())
        );
        log.info("사용자 비밀번호 암호화");

        userMapper.insertUser(userJoinDto);
        return userJoinDto.getUserId();
    }

    @Override
    public UserInfoDto findUserByAccountId(String accountId) {
        return userMapper.selectUserByAccountId(accountId);
    }

    @Override
    public List<UserInfoDto> findUsers() {
        return userMapper.selectUserAll();
    }

    @Override
    public UserReturnAccountIdDto findAccountIdByNameAndEmail(UserFindAccountIdDto userFindAccountIdDto) {
        return userMapper.selectUserByNameAndEmail(userFindAccountIdDto);
    }

    @Override
    public UserReturnAccountIdDto findAccountPwByAccountIdAndPhoneNumAndEmail(UserFindAccountPwDto userFindAccountPwDto) {
        return userMapper.selectUserByAccountIdAndPhoneNumAndEmail(userFindAccountPwDto);
    }

    @Override
    @Transactional
    public void changeDropUserAuthority(String accountId) {
        userMapper.updateUserAuthority(accountId);
        log.info("사용자 권한 변경 해당아이디:{} {} -> {}", accountId, "ROLE_USER", "ROLE_DROP");
    }

    @Override
    @Transactional
    public void changeUserInfo(UserInfoDto userInfoDto) {
        userMapper.updateUserInfo(userInfoDto);
        log.info("사용자 정보 변경");
    }
}
