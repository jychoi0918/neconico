package com.neconico.neconico.service.item;

import com.neconico.neconico.dto.category.CategorySubInfoDto;
import com.neconico.neconico.dto.file.FileResultInfoDto;
import com.neconico.neconico.dto.item.ItemInfoDto;
import com.neconico.neconico.dto.users.UserJoinDto;
import com.neconico.neconico.service.category.CategoryService;
import com.neconico.neconico.service.users.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
@Transactional
class ItemServiceTest {
    @Autowired
    UserService userService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryService categoryService;

    private Long userId;

    private List<Long> itemIds = new ArrayList<>();

    @BeforeEach
    void insertUserAndItems() {
        UserJoinDto userJoinDto = new UserJoinDto();
        userJoinDto.setAccountId("user1");
        userJoinDto.setAccountPw("1234");
        userJoinDto.setAccountName("user");
        userJoinDto.setGender("M");
        userJoinDto.setBirthdate("980631");
        userJoinDto.setEmail("user" + "@gmail.com");
        userJoinDto.setPhoneNumber("010-1111-1111");
        userJoinDto.setAddress("서울시");
        userJoinDto.setZipNo("01583");
        userJoinDto.setInfoAgreement("check");
        userJoinDto.setCreateDate(LocalDateTime.of(2021, 04, 29, 04, 43));
        userJoinDto.setModifiedDate(LocalDateTime.of(2021, 04, 29, 04, 43));
        userJoinDto.setAuthority("ROLE_USER");
        userService.joinUser(userJoinDto);

        this.userId = userJoinDto.getUserId();

        List<CategorySubInfoDto> categorySubInfoDtoList = categoryService.findCategorySubAll();

        for (int i = 1; i <= 10; i++) {
            CategorySubInfoDto categorySubInfoDto = categorySubInfoDtoList.get(i);

            FileResultInfoDto fileResultInfoDto = new FileResultInfoDto(
                    "https://",
                    "2fegd22f-2ffdimgs.png");

            ItemInfoDto itemInfoDto = new ItemInfoDto();
            itemInfoDto.setUserId(this.userId);
            itemInfoDto.setTitle("아이템 제목" + i);
            itemInfoDto.setContent("아이템 내용" + i);
            itemInfoDto.setPrice(i + "0,000");
            itemInfoDto.setArea("서울특벌시 강북구");
            itemInfoDto.setItemStatus("신품");
            itemInfoDto.setHits(0);
            itemInfoDto.setCreatedDate(LocalDateTime.now());
            itemInfoDto.setModifiedDate(LocalDateTime.now());
            itemInfoDto.setSaleStatus("판매 중");
            itemInfoDto.setShippingPrice("포함");


            itemService.insertItem(fileResultInfoDto, categorySubInfoDto.getCategorySubId(), itemInfoDto);

            //DB에 저장되며 auto_increment로 생성된 itemId를 인스턴스 변수에 리스트로 저장한다.
            this.itemIds.add(itemInfoDto.getItemId());
        }
    }


    @RepeatedTest(value = 10, name = "{displayName} {currentRepetition} / {totalRepetitions}")
    @DisplayName("10개의 item을 DB에 저장한 후 생성된 itemId들로 item정보를 가져온다.")
    void item_information_is_retrieved_by_the_created_itemId() throws Exception {
        //given
        Long itemId = getItemId();

        //when
        ItemInfoDto findItemInfo = itemService.findItemByItemId(itemId);

        //then
        assertThat(findItemInfo.getItemId()).isEqualTo(itemId);
    }

    @RepeatedTest(value = 10, name = "{displayName} {currentRepetition} / {totalRepetitions}")
    @DisplayName("등록한 상품에 대해 변경할 시 변경사항 DB에 반영")
    void changes_are_reflected_in_the_DB() throws Exception {
        //given
        Long itemId = getItemId();


        ItemInfoDto itemInfoDto = itemService.findItemByItemId(itemId); // 기존 DB에 저장된 아이템정보

        FileResultInfoDto fileResultInfoDto = new FileResultInfoDto(
                "https//fdd",
                "2f23f-f3fd-fdn이미지.png"); // 변경된 파일

        //when
        itemInfoDto.setTitle("바뀐제목");
        itemInfoDto.setContent("바뀐 내용");

        itemService.changeItemInfo(fileResultInfoDto, itemInfoDto);
        ItemInfoDto changeItemInfo = itemService.findItemByItemId(itemId);

        //then
        assertAll(
                () -> assertThat(changeItemInfo.getTitle()).isEqualTo("바뀐제목"),
                () -> assertThat(changeItemInfo.getContent()).isEqualTo("바뀐 내용"),
                () -> assertThat(fileResultInfoDto.getFileNames())
                        .isEqualTo(changeItemInfo.getImgFileNames()),
                () -> assertThat(fileResultInfoDto.getFileUrls())
                        .isEqualTo(changeItemInfo.getItemImgUrls())
        );
    }

    @RepeatedTest(value = 10, name = "{displayName} {currentRepetition} / {totalRepetitions}")
    @DisplayName("item 삭제 요청시 DB에서 해당 아이템삭제")
    void delete_the_item_from_DB_when_requesting_to_delete_an_item() throws Exception {
        //given
        Long itemId = getItemId();

        //when
        itemService.removeItem(itemId);
        ItemInfoDto findItemInfoDto = itemService.findItemByItemId(itemId);

        //then
        assertThat(findItemInfoDto).isNull();
    }

    private Long getItemId() {
        Random random = new Random();
        int randomNumber = random.nextInt(10);
        return this.itemIds.get(randomNumber);
    }
}