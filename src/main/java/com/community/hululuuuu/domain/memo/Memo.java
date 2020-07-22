package com.community.hululuuuu.domain.memo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
@Getter
@Table
public class Memo {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memoId;

    @Column
    private String memoContent;

    @Column
    private String memoLink;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime memoCreatedate;

    @Builder
    public Memo(String memoContent, String memoLink, LocalDateTime memoCreatedate) {
        this.memoContent = memoContent;
        this.memoLink = memoLink;
        this.memoCreatedate = memoCreatedate;
    }

}
