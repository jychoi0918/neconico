package com.neconico.neconico.service.admin.user;

import com.neconico.neconico.dto.admin.user.UserListDto;
import com.neconico.neconico.paging.Criteria;

import java.util.List;
import java.util.Map;

public interface UsersAdminService {

    //전체 회원 수
    public long countUserList(String authority);

    //회원 목록 조회
    List<UserListDto> selectUserList(Criteria cri);

    List<UserListDto> selectAdminList(Criteria cri);

    //회원 그룹별 조회
    Map<String, Long> selectUserListByAge();

    Map<String, Long> selectUserListByGender();

    Map<String, Long> selectUserListByLocation();

    Map<String, Long> selectUserListByRegistered();



}