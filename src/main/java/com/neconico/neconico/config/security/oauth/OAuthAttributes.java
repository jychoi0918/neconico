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
    private String registrationId;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String phoneNumber;
    private String gender;
    private String picture;
    private String uniqueId; //NAVER 이용 시 해당 유저 고유식별 ID

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey,
                           String registrationId, String name, String email, String phoneNumber,
                           String gender, String picture, String uniqueId) {
        this.attributes = attributes;
        this.registrationId = registrationId;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.picture = picture;
        this.uniqueId = uniqueId;

    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        if(registrationId.equals("naver")) {
            return ofNaver(registrationId, "id", attributes);
        }
        return ofGoogle(registrationId, userNameAttributeName, attributes);
    }



    public UserJoinDto createUserJoinDto() {
        String accountId = "";

        if(registrationId.equals("naver")) {
            accountId = extractAccountId(email) + "N";
        }else {
            accountId = extractAccountId(email) + "G";
        }

        UserJoinDto userJoinDto = new UserJoinDto();
        userJoinDto.setAccountId(accountId);
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

    private static OAuthAttributes ofNaver(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .picture((String) response.get("profile_image"))
                .gender((String) response.get("gender"))
                .attributes(response)
                .phoneNumber((String) response.get("mobile"))
                .uniqueId((String) response.get("id"))
                .registrationId(registrationId)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }
    public static OAuthAttributes ofGoogle(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {

        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .gender("U")
                .attributes(attributes)
                .phoneNumber(null)
                .uniqueId(null)
                .registrationId(registrationId)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private String extractAccountId(String email) {
        int index = email.indexOf("@");
        return email.substring(0, index);
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changeEmail(String email) {
        this.email = email;
    }

    public void changePhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void changeGender(String gender) {
        this.gender = gender;
    }

    public void changePicture(String picture) {
        this.picture = picture;
    }
}
