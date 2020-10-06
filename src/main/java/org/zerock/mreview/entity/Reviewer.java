package org.zerock.mreview.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Reviewer  extends BaseEntity{

    @Id
    private String rid;

    private String pw;

    private String nickname;

}
