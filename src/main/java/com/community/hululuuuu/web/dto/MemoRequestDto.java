package com.community.hululuuuu.web.dto;

import com.community.hululuuuu.domain.memo.Memo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class MemoRequestDto {

    private String content;

    private String link;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdate;

    @Builder
    public void requestDto(String content, String link, LocalDateTime createdate) {
        this.content = content;
        this.link = link;
        this.createdate = createdate;
    }

    public Memo toEntity() {
        return Memo.builder()
                .memoContent(content)
                .memoLink(link)
                .memoCreatedate(createdate)
                .build();
    }

}
