package com.neconico.neconico.service.users;

import com.neconico.neconico.dto.users.*;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    Long joinUser(UserJoinDto userJoinDto);

    UserInfoDto findUserByAccountId(String accountId);

    UserAccountIdDto findAccountIdByNameAndEmail(UserFindAccountIdDto userFindAccountIdDto);

    UserAccountIdDto findAccountPwByAccountIdAndPhoneNumAndEmail(UserFindAccountPwDto userFindAccountPwDto);

    void changeDropUserAuthority(String accountId);

    void changeUserAuthorityToAdmin(String accountId);

    void changeUserInfo(UserInfoDto userInfoDto);

    void changeUserAccountPw(String accountId, String changePw);

    UserInfoDto findUserByEmail(String email);
}
