package com.neconico.neconico.mapper.item;

import com.neconico.neconico.dto.category.CategorySubInfoDto;
import com.neconico.neconico.dto.item.ItemInfoDto;
import com.neconico.neconico.dto.item.ItemInquireInfoDto;
import com.neconico.neconico.dto.item.SearchInfoDto;
import com.neconico.neconico.dto.item.card.ItemCardDto;
import com.neconico.neconico.dto.store.StoreInfoDto;
import com.neconico.neconico.dto.users.UserJoinDto;
import com.neconico.neconico.mapper.category.CategoryMapper;
import com.neconico.neconico.mapper.store.StoreInfoMapper;
import com.neconico.neconico.mapper.users.UserMapper;
import com.neconico.neconico.paging.Criteria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
@Transactional
class ItemMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private StoreInfoMapper storeInfoMapper;

    private Long userId;

    private List<Long> itemIds = new ArrayList<>();

    @BeforeEach
    void insertUserAndItems() {
        UserJoinDto userJoinDto = new UserJoinDto();
        userJoinDto.setAccountId("user1");
        userJoinDto.setAccountPw(passwordEncoder.encode("1234"));
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
        userMapper.insertUser(userJoinDto);

        this.userId = userJoinDto.getUserId();

        // store 정보 저장
        StoreInfoDto store = new StoreInfoDto(
                userJoinDto.getUserId(),
                userJoinDto.getAccountName(),
                "",
                "user1의 store",
                "");
        storeInfoMapper.insertStoreInfo(store);

        //item 저장을 위한 중분류 카테고리
        List<CategorySubInfoDto> categorySubInfoDtoList = categoryMapper.selectCategorySubs();

        for (int i = 1; i <= 10; i++) {
            CategorySubInfoDto categorySubInfoDto = categorySubInfoDtoList.get(i);

            ItemInfoDto itemInfoDto = new ItemInfoDto();
            itemInfoDto.setUserId(this.userId);
            itemInfoDto.setCategorySubId(categorySubInfoDto.getCategorySubId());
            itemInfoDto.setTitle("아이템 제목" + i);
            itemInfoDto.setContent("아이템 내용" + i);
            itemInfoDto.setPrice(i + "0,000");
            itemInfoDto.setItemImgUrls("https://");
            if(i%2 ==0 ) {
                itemInfoDto.setArea("서울특벌시 강북구");
            }else{
                itemInfoDto.setArea("경기도 수원시");
            }
            itemInfoDto.setItemStatus("신품");
            itemInfoDto.setHits(0);
            itemInfoDto.setCreatedDate(LocalDateTime.now());
            itemInfoDto.setModifiedDate(LocalDateTime.now());
            itemInfoDto.setSaleStatus("판매 중");
            itemInfoDto.setShippingPrice("포함");
            itemInfoDto.setImgFileNames("2fegd22f-2ffdimgs.png");

            itemMapper.insertItems(itemInfoDto);
            //DB에 저장되며 auto_increment로 생성된 itemId를 인스턴스 변수에 리스트로 저장한다.
            this.itemIds.add(itemInfoDto.getItemId());
        }
    }

    @RepeatedTest(value = 10, name = "{displayName} {currentRepetition} / {totalRepetitions}")
    @DisplayName("10개의 item을 DB에 저장한 후 생성된 itemId들로 item정보를 가져온다.")
    void select_item_by_item_id() {
        //given
        Long itemId = getItemId();

        //when
        ItemInquireInfoDto itemInquireInfoDto = itemMapper.selectItemByItemId(itemId);

        //then
        assertAll(
                () -> assertThat(itemInquireInfoDto).isNotNull(),
                () -> assertThat(itemInquireInfoDto.getStoreInquireInfoDto()).isNotNull(),
                () -> assertThat(itemInquireInfoDto.getItemQuestionInquireDtoList()).isEmpty()
        );

    }

    @RepeatedTest(value = 10, name = "{displayName} {currentRepetition} / {totalRepetitions}")
    @DisplayName("등록한 상품에 대해 변경할 시 변경사항 DB에 반영")
    void changes_are_reflected_in_the_DB() {
        //given
        Random random = new Random();
        int randomNumber = random.nextInt(10);
        Long itemId = itemIds.get(randomNumber);

        //중분류 변경시 필요한 중분류 Id
        List<CategorySubInfoDto> categorySubInfoDtoList = categoryMapper.selectCategorySubs();
        CategorySubInfoDto categorySubInfoDto = categorySubInfoDtoList.get(randomNumber);
        Long categorySubId = categorySubInfoDto.getCategorySubId();

        ItemInfoDto itemInfoDto = itemMapper.selectItemByItemIdForUpdate(itemId);
        String content = itemInfoDto.getContent();
        String itemImgUrls = itemInfoDto.getItemImgUrls();
        String title = itemInfoDto.getTitle();
        String price = itemInfoDto.getPrice();

        //when
        itemInfoDto.setCategorySubId(categorySubId);
        itemInfoDto.setItemImgUrls("https//ffd.com");
        itemInfoDto.setContent("내용 바뀌었음 zzzzzzz");
        itemInfoDto.setTitle("제목 바뀌었음");
        itemInfoDto.setPrice("42,000");
        itemInfoDto.setModifiedDate(LocalDateTime.now());

        itemMapper.updateItemInfo(itemInfoDto);

        ItemInquireInfoDto changeItemInfoDto = itemMapper.selectItemByItemId(itemId);

        //then
        assertAll(
                () -> assertThat(content).isNotEqualTo(changeItemInfoDto.getContent()),
                () -> assertThat(itemImgUrls).isNotEqualTo(changeItemInfoDto.getItemImgUrls()),
                () -> assertThat(title).isNotEqualTo(changeItemInfoDto.getTitle()),
                () -> assertThat(price).isNotEqualTo(changeItemInfoDto.getPrice())
        );
    }

    @RepeatedTest(value = 10, name = "{displayName} {currentRepetition} / {totalRepetitions}")
    @DisplayName("item 삭제요청시 DB에서 해당 item 삭제")
    void delete_the_item_from_DB_when_requesting_to_delete_an_item() {
        //given
        Long itemId = getItemId();

        Criteria criteria = getCriteria();

        SearchInfoDto searchInfoDto = createSerSearchInfoDto("");

        //when
        itemMapper.deleteItem(itemId);

        List<ItemCardDto> itemCardDtoList = itemMapper.selectItemBySearch(criteria, searchInfoDto);

        //then
        assertThat(itemCardDtoList.size()).isLessThan(itemIds.size());
    }

    @Test
    @DisplayName("상품거래 지역명 검색 시 해당 상품들 조회")
    void search_for_relevant_items_when_searching_for_a_item_transaction_area_name() throws Exception {
        //given
        Criteria criteria = getCriteria();

        SearchInfoDto searchInfoDto = createSerSearchInfoDto("수원");

        //when
        List<ItemCardDto> itemInfoDtoList = itemMapper.selectItemBySearch(criteria, searchInfoDto);

        //then
        assertThat(itemInfoDtoList).hasSize(5);
    }

    @Test
    @DisplayName("상품거래 제목명 검색 시 해당 상품들 조회")
    void search_for_relevant_items_when_searching_for_a_item_transaction_title() throws Exception {
        //given
        Criteria criteria = getCriteria();

        SearchInfoDto searchInfoDto = createSerSearchInfoDto("제목");

        //when
        List<ItemCardDto> itemInfoDtoList = itemMapper.selectItemBySearch(criteria, searchInfoDto);

        //then
        assertThat(itemInfoDtoList)
                .hasSize(10)
                .anyMatch(i -> i.getTitle().contains("제목"));
    }

    @Test
    @DisplayName("DB에 저장된 item의 총 수를 계산한다.")
    void count_the_total_number_of_items_stored_in_the_DB() throws Exception {
        Long totalItemCount = itemMapper.selectTotalItemCount();

        assertThat(totalItemCount).isEqualTo(itemIds.size());
    }

    private Long getItemId() {
        Random random = new Random();
        int randomNumber = random.nextInt(10);
        return this.itemIds.get(randomNumber);
    }

    private Criteria getCriteria() {
        Criteria criteria = new Criteria();
        criteria.setContentPerPage(10);
        criteria.setRequestOrder("DESC");
        criteria.setCurrentPage(1);
        criteria.setSortingColumn("created_date");

        return criteria;
    }

    private SearchInfoDto createSerSearchInfoDto(String text) {
        SearchInfoDto searchInfoDto = new SearchInfoDto();
        searchInfoDto.setSearchText(text);
        return searchInfoDto;
    }
}