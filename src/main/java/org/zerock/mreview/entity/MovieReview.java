package org.zerock.mreview.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"movie,reviewer"})
public class MovieReview extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewnum;

    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reviewer reviewer;

    private int grade;

    private String text;

    public void changeText(String text){
        this.text = text;
    }

    public void changeGrade(int grade){
        this.grade = grade;
    }
}
