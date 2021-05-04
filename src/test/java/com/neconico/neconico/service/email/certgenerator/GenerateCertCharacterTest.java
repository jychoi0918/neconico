package com.neconico.neconico.service.email.certgenerator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;


class GenerateCertCharacterTest {


    private GenerateCertCharacter generateCertCharacter;

    GenerateCertCharacterTest() {
        this.generateCertCharacter = new GenerateCertCharacter();
    }

    @ParameterizedTest(name = "{index} -> 인증번호 요청길이가 {0}일때")
    @DisplayName("여러길이가 주어졌을때 해당 길이에 맞는 인증번호 난수 생성")
    @ValueSource(ints = {3, 4, 5, 6, 7, 8, 9, 10, 11, 12})
    void when_multiple_lengths_are_given_author_number_generated(int numberLength) {
        generateCertCharacter.setNumberLength(numberLength);
        String authorNumber = generateCertCharacter.executeGenerate();

        assertThat(authorNumber.length()).isEqualTo(numberLength);
    }
}