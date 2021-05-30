package com.neconico.neconico.service.store;

import com.neconico.neconico.dto.file.FileResultInfoDto;
import com.neconico.neconico.dto.store.StoreInfoDto;
import com.neconico.neconico.dto.store.card.StoreInfoCardDto;
import com.neconico.neconico.dto.users.SessionUser;
import com.neconico.neconico.file.policy.FilePolicy;
import com.neconico.neconico.file.process.S3FileProcess;
import com.neconico.neconico.mapper.store.StoreInfoMapper;
import com.neconico.neconico.mapper.store.StoreItemListMapper;
import com.neconico.neconico.mapper.users.UserMapper;
import com.neconico.neconico.service.file.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreInfoService {

    private final StoreInfoMapper storeInfoMapper;
    private final StoreItemListMapper storeItemListMapper;
    private final UserMapper userMapper;
    private final FileService fileService;

    public StoreInfoCardDto findStoreInfo(SessionUser user) {

        StoreInfoCardDto storeInfo = new StoreInfoCardDto();

        StoreInfoDto storeDto = storeInfoMapper.selectStoreInfoByUser(user.getUserId());
        storeInfo.setStoreInfo(storeDto.getStoreInfo());
        storeInfo.setStoreName(storeDto.getStoreName());
        storeInfo.setStoreImgUrl(storeDto.getStoreImgUrl());

        storeInfo.setCreated(
                calculateCreatedDate(user.getAccountId())
        );

        storeInfo.setSoldCount(storeItemListMapper.countStoreSoldItem(user.getUserId()));

        return storeInfo;
    }

    @Transactional
    public void createStoreInfo(StoreInfoDto storeInfoDto) {
        storeInfoMapper.insertStoreInfo(storeInfoDto);
    }

    @Transactional
    public void updateStoreInfo(StoreInfoDto storeInfoDto) {
        if (storeInfoDto.getStoreName() != null) {
            if (storeInfoMapper.selectStoreInfoByName(storeInfoDto.getStoreName())) {
                throw new IllegalArgumentException("Exist same storename");
            }
        }
        storeInfoMapper.updateStoreInfo(storeInfoDto);
    }

    public String calculateCreatedDate(String accountId) {
        LocalDate created = userMapper.selectUserByAccountId(accountId).getCreatedDate().toLocalDate();
        LocalDate now = LocalDate.now();

        Long range = ChronoUnit.DAYS.between(created, now);

        return range + "일 전";
    }

    public SessionUser getSessionUserInfoByAccountId(String accountId) {
        return userMapper.selectSessionUserInfoByAccountId(accountId);
    }

    @Transactional
    public void updateStoreImg(MultipartFile multipartFiles, Long userId) throws IOException {
        StoreInfoDto initInfo = storeInfoMapper.selectStoreInfoByUser(userId);
        fileService.setFileProcess(new S3FileProcess(FilePolicy.STORE));
        FileResultInfoDto result = fileService.uploadFiles(multipartFiles);
        updateStoreInfo(new StoreInfoDto(userId, null, result.getFileUrls(), null, result.getFileNames()));
        if(!(initInfo.getStoreImgUrl().equals(""))) {
            fileService.deleteFiles(initInfo.getStoreImgName());
        }
    }
}
