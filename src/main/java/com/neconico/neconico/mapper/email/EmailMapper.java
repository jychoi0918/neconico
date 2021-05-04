package com.neconico.neconico.mapper.email;

import com.neconico.neconico.dto.email.AuthorNumberDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmailMapper {

    List<AuthorNumberDto> selectAll();

    Long selectByAuthorNumber(String authorNumber);

    void insertAuthorNumber(AuthorNumberDto authorNumberDto);

    void deleteAuthorNumber(Long emailId);
}
