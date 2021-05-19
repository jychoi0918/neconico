package com.neconico.neconico.config.security.oauth;


import com.neconico.neconico.dto.users.UserJoinDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String phoneNumber;
    private String gender;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes,
                           String nameAttributeKey, String name, String email,
                           String phoneNumber, String gender, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.picture = picture;

    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        if(registrationId.equals("naver")) {
            return ofNaver("id", attributes);
        }
        return ofGoogle(userNameAttributeName, attributes);
    }



    public UserJoinDto createUserJoinDto() {

        UserJoinDto userJoinDto = new UserJoinDto();
        userJoinDto.setAccountId(extractAccountId(email));
        userJoinDto.setAccountPw(email + UUID.randomUUID());
        userJoinDto.setAccountName(name);
        userJoinDto.setGender(gender);
        userJoinDto.setBirthdate("OAUTH");
        userJoinDto.setEmail(email);
        userJoinDto.setPhoneNumber(phoneNumber);
        userJoinDto.setZipNo("OAUTH");
        userJoinDto.setAddress("OAUTH");
        userJoinDto.setInfoAgreement("check");
        userJoinDto.setCreateDate(LocalDateTime.now());
        userJoinDto.setModifiedDate(LocalDateTime.now());
        userJoinDto.setAuthority("ROLE_USER");

        return userJoinDto;
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .picture("")
                .gender((String) response.get("gender"))
                .attributes(response)
                .phoneNumber((String) response.get("mobile"))
                .nameAttributeKey(userNameAttributeName)
                .build();
    }
    public static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {

        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .gender("M")
                .attributes(attributes)
                .phoneNumber("구글로그인")
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private String extractAccountId(String email) {
        int index = email.indexOf("@");
        return email.substring(0, index);
    }
}
