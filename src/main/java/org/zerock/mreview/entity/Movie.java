package org.zerock.mreview.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Movie extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;

    private String title;

    private String file1;

    private String file2;

    private String file3;

    public void changeTitle(String title){
        this.title = title;
    }

    public void changeFile1(String file1) {
        this.file1 = file1;
    }

    public void changeFile2(String file2) {
        this.file2 = file2;
    }

    public void changeFile3(String file3) {
        this.file3 = file3;
    }
}







