package com.neconico.neconico.file.policy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FilePolicyTest {

    @Test
    @DisplayName("STORE 정책에 따른 파일개수")
    void number_of_files_according_to_store_policy() {
        //given
        FilePolicy store = FilePolicy.STORE;

        //when
        int fileCount = store.getFileCount();

        //then
        assertThat(fileCount).isEqualTo(1);
    }

    @Test
    @DisplayName("STORE 정책에 따른 디렉토리 이름")
    void directory_name_according_to_store_policy() {
        //given
        FilePolicy store = FilePolicy.STORE;

        //when
        String dirName = store.getDirName();

        //then
        assertThat(dirName).isEqualTo("store");

    }

    @Test
    @DisplayName("ADVERTISEMENT 정책에 따른 파일개수")
    void number_of_files_according_to_advertisement_policy() {
        //given
        FilePolicy advertisement = FilePolicy.ADVERTISEMENT;

        //when
        int fileCount = advertisement.getFileCount();

        //then
        assertThat(fileCount).isEqualTo(1);

    }

    @Test
    @DisplayName("ADVERTISEMENT 정책에 따른 디렉토리 이름")
    void  directory_name_according_to_advertisement_policy() {
        //given
        FilePolicy advertisement = FilePolicy.ADVERTISEMENT;

        //when
        String dirName = advertisement.getDirName();

        //then
        assertThat(dirName).isEqualTo("adver");

    }

    @Test
    @DisplayName("ITEM 정책에 따른 파일개수")
    void number_of_files_according_to_item_policy() {
        //given
        FilePolicy item = FilePolicy.ITEM;

        //when
        int fileCount = item.getFileCount();

        //then
        assertThat(fileCount).isEqualTo(3);

    }

    @Test
    @DisplayName("ITEM 정책에 따른 디렉토리 이름")
    void directory_name_according_to_item_policy() {
        //given
        FilePolicy item = FilePolicy.ITEM;

        //when
        String dirName = item.getDirName();

        //then
        assertThat(dirName).isEqualTo("item");

    }
}