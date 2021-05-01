package com.neconico.neconico.service.users;

import com.neconico.neconico.dto.users.UserFindAccountIdDto;
import com.neconico.neconico.dto.users.UserInfoDto;
import com.neconico.neconico.dto.users.UserJoinDto;
import com.neconico.neconico.dto.users.UserReturnAccountIdDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    Long joinUser(UserJoinDto userJoinDto);

    UserInfoDto findUserByAccountId(String accountId);

    List<UserInfoDto> findAll();

    UserReturnAccountIdDto findAccountIdByNameAndEmail(UserFindAccountIdDto userFindAccountIdDto);

    void changeDropUserAuthority(String accountId);

    void changeUserInfo(UserInfoDto userInfoDto);
}
