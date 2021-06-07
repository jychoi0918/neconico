package com.neconico.neconico.config.security.service;


import com.neconico.neconico.config.security.oauth.OAuthAttributes;
import com.neconico.neconico.dto.store.StoreInfoDto;
import com.neconico.neconico.dto.users.SessionUser;
import com.neconico.neconico.dto.users.UserInfoDto;
import com.neconico.neconico.dto.users.UserJoinDto;
import com.neconico.neconico.mapper.store.StoreInfoMapper;
import com.neconico.neconico.mapper.users.UserMapper;
import com.neconico.neconico.service.email.certgenerator.GenerateCertCharacter;
import com.neconico.neconico.Maker.StoreInfoMaker;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Collections;

@Service("customOAuth2UserService")
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserMapper userMapper;
    private final StoreInfoMapper storeInfoMapper;
    private final GenerateCertCharacter generateCertCharacter;
    private final HttpSession httpSession;
    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        String userNameAttributeName = userRequest
                .getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        SessionUser sessionUser = saveOrUpdate(attributes);

        httpSession.setAttribute("sessionUser", sessionUser);

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(sessionUser.getAuthority())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private SessionUser saveOrUpdate(OAuthAttributes attributes) {
        changeNullToGenerateParam(attributes);

        UserInfoDto userInfoDto = userMapper.selectUserByEmail(attributes.getEmail());
        
        if(userInfoDto == null) {
            UserJoinDto userJoinDto = attributes.createUserJoinDto();
            userJoinDto.setAccountPw(passwordEncoder.encode(userJoinDto.getAccountPw()));
            userMapper.insertUser(userJoinDto);

            //스토어저장
            StoreInfoDto storeInfoDto = StoreInfoMaker.createStoreInfoDtoByUserId(userJoinDto.getUserId());
            storeInfoDto.setStoreImgUrl(attributes.getPicture());
            storeInfoMapper.insertStoreInfo(storeInfoDto);

            return createSessionUser(userJoinDto);
        }

        if(checkUpdate(userInfoDto, attributes)) {
            userMapper.updateUserInfo(userInfoDto);
        }

        return createSessionUser(userInfoDto);
    }

    private void changeNullToGenerateParam(OAuthAttributes attributes) {
        generateCertCharacter.setNumberLength(5);
        String generateNumber = generateCertCharacter.executeGenerate();

        if(attributes.getName() == null) {
            attributes.changeName(generateNumber);
        }

        if(attributes.getEmail() == null) {
            attributes.changeEmail(attributes.getUniqueId() + "@");
        }

        if(attributes.getGender() == null) {
            attributes.changeGender("U");
        }

        if(attributes.getPhoneNumber() == null){
            attributes.changePhoneNumber("OAUTH" + generateNumber);
        }

        if(attributes.getPicture() == null) {
            attributes.changePicture("");
        }
    }
    

    private SessionUser createSessionUser(UserJoinDto userJoinDto) {
        return SessionUser.builder()
                .userId(userJoinDto.getUserId())
                .accountId(userJoinDto.getAccountId())
                .accountName(userJoinDto.getAccountName())
                .email(userJoinDto.getEmail())
                .authority(userJoinDto.getAuthority())
                .build();
    }

    private SessionUser createSessionUser(UserInfoDto userInfoDto) {
        return SessionUser.builder()
                .userId(userInfoDto.getUserId())
                .accountId(userInfoDto.getAccountId())
                .accountName(userInfoDto.getAccountName())
                .email(userInfoDto.getEmail())
                .authority(userInfoDto.getAuthority())
                .build();
    }

    private boolean checkUpdate(UserInfoDto userInfoDto, OAuthAttributes attributes){
        if(!userInfoDto.getAccountName().equals(attributes.getName())) {
            userInfoDto.setAccountName(attributes.getName());
            userInfoDto.setModifiedDate(LocalDateTime.now());
            return true;
        }
        return false;
    }



}
