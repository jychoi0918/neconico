package com.neconico.neconico.mapper.users;

import com.neconico.neconico.dto.users.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private List<UserJoinDto> userJoinDtos = new ArrayList<>();

    @BeforeEach
    void insertUserJoinDtos() {
        for(int i=1; i<=10; i++) {
            String gender = "M";

            if(i%2 == 0){
                gender = "F";
            }

            UserJoinDto userJoinDto = new UserJoinDto();
            userJoinDto.setAccountId("user" + i);
            userJoinDto.setAccountPw(passwordEncoder.encode("1234"));
            userJoinDto.setAccountName("user" + i);
            userJoinDto.setGender(gender);
            userJoinDto.setBirthdate("990331");
            userJoinDto.setEmail("user" + i + "@gmail.com");
            userJoinDto.setPhoneNumber("010-1111-1111");
            userJoinDto.setAddress("서울시");
            userJoinDto.setZipNo("0158" + i);
            userJoinDto.setInfoAgreement("check");
            userJoinDto.setCreateDate(LocalDateTime.of(2021, 04, 29, 04, 43, i));
            userJoinDto.setModifiedDate(LocalDateTime.of(2021, 04, 29, 04, 43, i));
            userJoinDto.setAuthority("ROLE_USER");

            userJoinDtos.add(userJoinDto);
        }

        for(UserJoinDto userJoinDto : userJoinDtos) {
            userMapper.insertUser(userJoinDto);
        }
    }

    @Test
    @DisplayName("모든회원 정보를 DB에서 가져온다")
    void get_user_information_in_database() {
        List<UserInfoDto> userInfos = userMapper.selectUserAll();

        assertThat(userInfos).isNotNull();

    }

    @Test
    @DisplayName("회원가입시 회원정보를 DB에 저장")
    void insert_join_user_info_in_database() {
        //given
        UserJoinDto userJoinDto = new UserJoinDto();
        userJoinDto.setAccountId("user11");
        userJoinDto.setAccountPw(passwordEncoder.encode("1234"));
        userJoinDto.setAccountName("user11");
        userJoinDto.setGender("F");
        userJoinDto.setBirthdate("980631");
        userJoinDto.setEmail("user11" + "@gmail.com");
        userJoinDto.setPhoneNumber("010-1111-1111");
        userJoinDto.setAddress("서울시");
        userJoinDto.setZipNo("01583");
        userJoinDto.setInfoAgreement("check");
        userJoinDto.setCreateDate(LocalDateTime.of(2021, 04, 29, 04, 43));
        userJoinDto.setModifiedDate(LocalDateTime.of(2021, 04, 29, 04, 43));
        userJoinDto.setAuthority("ROLE_USER");

        //when
        userMapper.insertUser(userJoinDto);

        List<UserInfoDto> userInfoDtos = userMapper.selectUserAll();

        //then
        assertThat(userInfoDtos.size()).isEqualTo(11);

    }

    @ParameterizedTest(name = "{index} -> 유저아이디가 {0}일때")
    @ValueSource(
            strings = {"user1", "user2", "user3", "user4", "user5",
                    "user6", "user7", "user8", "user9", "user10"})
    @DisplayName("회원의 아이디로 해당 유저정보를 DB에서 가져온다")
    void select_user_by_account_id_in_database(String accountId) {

        UserInfoDto userInfoDto = userMapper.selectUserByAccountId(accountId);

        assertThat(userInfoDto).extracting("accountId").isEqualTo(accountId);

    }

    @ParameterizedTest(name = "{index} -> 유저아이디가 {0}일때")
    @DisplayName("회원 탈퇴시 회원권한을 ROLE_DROP으로 변경")
    @ValueSource(
            strings = {"user1", "user2", "user3", "user4", "user5",
            "user6", "user7", "user8", "user9", "user10"})
    void update_authority_when_user_withdrawal(String accountId) {

        userMapper.updateUserAuthority(accountId);
        UserInfoDto userInfoDto = userMapper.selectUserByAccountId(accountId);

        assertThat(userInfoDto)
                .extracting("authority").isEqualTo("ROLE_DROP");
    }

    @Test
    @DisplayName("회원 정보 변경 시 DB에 반영")
    void update_user_info_in_database() {
        //given
        UserJoinDto userJoinDto = userJoinDtos.get(0);

        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setAccountId(userJoinDto.getAccountId());
        userInfoDto.setPhoneNumber("010-2325-3535");
        userInfoDto.setEmail("user11111@gamil.com");
        userInfoDto.setZipNo("51562");
        userInfoDto.setAddress("부산시");
        userInfoDto.setModifiedDate(LocalDateTime.of(2021, 04, 30, 03, 13, 20));

        //when
        userMapper.updateUserInfo(userInfoDto);

        UserInfoDto findUserInfo = userMapper.selectUserByAccountId(userJoinDto.getAccountId());

        //then
        assertThat(findUserInfo).extracting(
                "phoneNumber",
                "email",
                "zipNo",
                "address",
                "modifiedDate")
                .contains(
                        userInfoDto.getPhoneNumber(),
                        userInfoDto.getEmail(),
                        userInfoDto.getZipNo(),
                        userInfoDto.getAddress(),
                        userInfoDto.getModifiedDate()
                );
    }

    @ParameterizedTest(name = "{index} -> 유저아이디가 {0}일때")
    @DisplayName("회원 로그인시 해당 아이디로 sessionUser 객체 반환")
    @ValueSource(
            strings = {"user1", "user2", "user3", "user4", "user5",
                    "user6", "user7", "user8", "user9", "user10"})
    void the_sessionUser_object_is_returned_with_the_corresponding_id(String accountId) {

        SessionUser sessionUser = userMapper.selectSessionUserInfoByAccountId(accountId);

        assertThat(sessionUser)
                .extracting("accountId", "authority")
                .contains(accountId, "ROLE_USER");

    }

    @ParameterizedTest(name = "{index} -> 유저이름이 {0}이고, 이메일이 {1}일때")
    @DisplayName("회원 아이디 찾기 시 해당 회원이 존재하는지 확인")
    @CsvSource({"'user1', 'user1@gmail.com'", "'user2', 'user2@gmail.com'", "'user3', 'user3@gmail.com'",
                "'user4', 'user4@gmail.com'", "'user5', 'user5@gmail.com'", "'user6', 'user6@gmail.com'",
                "'user7', 'user7@gmail.com'", "'user8', 'user8@gmail.com'", "'user9', 'user9@gmail.com'",
                "'user10', 'user10@gmail.com'"})
    void when_searching_for_a_account_id_check_whether_the_member_exists(String accountName, String email) {
        //given
        UserFindAccountIdDto userFindAccountIdDto = new UserFindAccountIdDto();
        userFindAccountIdDto.setAccountName(accountName);
        userFindAccountIdDto.setEmail(email);

        //when
        UserAccountIdDto userAccountIdDto = userMapper
                .selectUserByNameAndEmail(userFindAccountIdDto);

        //then
        assertThat(userAccountIdDto).isNotNull();
    }

    @ParameterizedTest(name = "{index} -> 유저이름이 {0}이고, 핸드폰 번호가 {1}이고 이메일이 {2}일때")
    @DisplayName("회원 비밀번호 찾기 시 해당 회원이 존재하는지 확인")
    @CsvSource(
            {"'user1', '010-1111-1111', 'user1@gmail.com'", "'user2', '010-1111-1111', 'user2@gmail.com'",
            "'user3', '010-1111-1111', 'user3@gmail.com'", "'user4', '010-1111-1111', 'user4@gmail.com'",
            "'user5', '010-1111-1111', 'user5@gmail.com'", "'user6', '010-1111-1111', 'user6@gmail.com'",
            "'user7', '010-1111-1111', 'user7@gmail.com'", "'user8', '010-1111-1111', 'user8@gmail.com'",
                    "'user9', '010-1111-1111', 'user9@gmail.com'", "'user10', '010-1111-1111', 'user10@gmail.com'"
            }
    )
    void when_searching_for_a_account_pw_check_whether_the_member_exists(String accountId, String phoneNumber, String email) {

        //given
        UserFindAccountPwDto userFindAccountPwDto = new UserFindAccountPwDto();
        userFindAccountPwDto.setAccountId(accountId);
        userFindAccountPwDto.setPhoneNumber(phoneNumber);
        userFindAccountPwDto.setEmail(email);

        //when
        UserAccountIdDto userAccountIdDto = userMapper
                .selectUserByAccountIdAndPhoneNumAndEmail(userFindAccountPwDto);

        //then
        assertThat(userAccountIdDto).isNotNull();
    }
}