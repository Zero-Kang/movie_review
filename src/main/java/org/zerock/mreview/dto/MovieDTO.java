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
public class MovieDTO {

    private Long mno;

    private String title;

    private String file1;

    private String file2;

    private String file3;

    private LocalDateTime regDate, modDate;

    //영화의 평균 평점
    private double avg;

    //리뷰 수
    private int reviewCnt;

}
