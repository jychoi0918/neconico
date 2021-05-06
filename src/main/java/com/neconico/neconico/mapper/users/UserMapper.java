package com.neconico.neconico.mapper.users;

import com.neconico.neconico.dto.users.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    List<UserInfoDto> selectUserAll();

    UserInfoDto selectUserByAccountId(String accountId);

    SessionUser selectSessionUserInfoByAccountId(String accountId);

    UserAccountIdDto selectUserByNameAndEmail(UserFindAccountIdDto userFindAccountIdDto);

    UserAccountIdDto selectUserByAccountIdAndPhoneNumAndEmail(UserFindAccountPwDto userFindAccountPwDto);

    void insertUser(UserJoinDto userJoinDto);

    void updateUserAuthority(String accountId);

    void updateUserInfo(UserInfoDto userInfoDto);

    void updateAccountPw(@Param("accountId") String accountId, @Param("accountPw") String accountPw);
}

