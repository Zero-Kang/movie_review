package org.zerock.mreview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieReviewDTO {

    //review num
    private Long reviewnum;

    //Movie mno
    private Long mno;

    //Reviewer id
    private String rid;
    //Reviewer nickname
    private String nickname;

    private int grade;

    private String text;

    private LocalDateTime regDate, modDate;


}
