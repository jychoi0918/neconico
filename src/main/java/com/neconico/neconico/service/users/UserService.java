package com.neconico.neconico.service.users;

import com.neconico.neconico.dto.users.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    Long joinUser(UserJoinDto userJoinDto);

    UserInfoDto findUserByAccountId(String accountId);

    List<UserInfoDto> findUsers();

    UserReturnAccountIdDto findAccountIdByNameAndEmail(UserFindAccountIdDto userFindAccountIdDto);

    UserReturnAccountIdDto findAccountPwByAccountIdAndPhoneNumAndEmail(UserFindAccountPwDto userFindAccountPwDto);

    void changeDropUserAuthority(String accountId);

    void changeUserInfo(UserInfoDto userInfoDto);
}
