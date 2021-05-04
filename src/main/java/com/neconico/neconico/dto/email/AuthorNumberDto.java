package com.neconico.neconico.dto.email;

import lombok.*;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;
@Getter @Setter
@NoArgsConstructor
@Alias("authorNumberDto")
public class AuthorNumberDto {

    private java.lang.Long emailId;
    private String authorNumber;
    private LocalDateTime createdDate;

}
