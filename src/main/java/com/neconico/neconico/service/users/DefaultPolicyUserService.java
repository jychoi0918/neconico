package com.neconico.neconico.service.users;

import com.neconico.neconico.dto.users.*;
import com.neconico.neconico.mapper.users.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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

        userJoinDto.setCreateDate(LocalDateTime.now());
        userJoinDto.setModifiedDate(LocalDateTime.now());
        userJoinDto.setAuthority("ROLE_USER");

        userMapper.insertUser(userJoinDto);
        return userJoinDto.getUserId();
    }

    @Override
    public UserInfoDto findUserByAccountId(String accountId) {
        return userMapper.selectUserByAccountId(accountId);
    }

    @Override
    public UserAccountIdDto findAccountIdByNameAndEmail(UserFindAccountIdDto userFindAccountIdDto) {
        return userMapper.selectUserByNameAndEmail(userFindAccountIdDto);
    }

    @Override
    public UserAccountIdDto findAccountPwByAccountIdAndPhoneNumAndEmail(UserFindAccountPwDto userFindAccountPwDto) {
        return userMapper.selectUserByAccountIdAndPhoneNumAndEmail(userFindAccountPwDto);
    }

    @Override
    @Transactional
    public void changeDropUserAuthority(String accountId) {
        userMapper.updateUserAuthorityToDrop(accountId);
    }

    @Override
    @Transactional
    public void changeUserAuthorityToAdmin(String accountId) {
        userMapper.updateUserAuthorityToAdmin(accountId);
    }

    @Override
    @Transactional
    public void changeUserInfo(UserInfoDto userInfoDto) {
        setBlankToOauth(userInfoDto);
        //변경시간 등록
        userInfoDto.setModifiedDate(LocalDateTime.now());
        userMapper.updateUserInfo(userInfoDto);
    }

    @Override
    @Transactional
    public void changeUserAccountPw(String accountId, String changePw) {
        userMapper.updateAccountPw(accountId, passwordEncoder.encode(changePw));
    }

    @Override
    public UserInfoDto findUserByEmail(String email) {
        return userMapper.selectUserByEmail(email);
    }

    private void setBlankToOauth(UserInfoDto userInfoDto) {
        String defaultOauthInfo = "OAUTH";

        if(userInfoDto.getZipNo().equals("")) {
            userInfoDto.setZipNo(defaultOauthInfo);
        }

        if(userInfoDto.getAddress().equals("")) {
            userInfoDto.setAddress(defaultOauthInfo);
        }

        if(userInfoDto.getPhoneNumber().equals("")) {
            userInfoDto.setPhoneNumber(defaultOauthInfo);
        }
    }
}
