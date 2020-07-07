package com.community.hululuuuu.memo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
@Getter
@Table
public class Memo implements Serializable {

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
