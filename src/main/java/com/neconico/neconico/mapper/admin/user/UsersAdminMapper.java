package com.neconico.neconico.mapper.admin.user;

import com.neconico.neconico.dto.admin.user.SearchConditionDto;
import com.neconico.neconico.dto.admin.user.UserListDto;
import com.neconico.neconico.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UsersAdminMapper {

    //전체 회원 수
    long countUserList(@Param("authority") String authority, @Param("searchCondition")SearchConditionDto searchCondition);

    //회원 목록 조회
    List<UserListDto> selectUserList(@Param("cri") Criteria cri, @Param("authority")String authority, @Param("searchCondition")SearchConditionDto searchCondition);

    //회원 그룹별 조회
    Map<String, Long> selectUserListByAge();

    Map<String, Long> selectUserListByGender();

    Map<String, Long> selectUserListByLocation();

    Map<String, Long> selectUserListByRegistered();
}