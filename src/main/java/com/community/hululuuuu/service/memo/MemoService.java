package com.community.hululuuuu.service.memo;

import com.community.hululuuuu.domain.memo.Memo;
import com.community.hululuuuu.domain.memo.MemoRepository;
import com.community.hululuuuu.myComponent.PageableDefault;
import com.community.hululuuuu.web.dto.MemoRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemoService {

    private final MemoRepository memoRepository;

    public Long createMemo(MemoRequestDto requestDto) {
        return memoRepository.save(requestDto.toEntity()).getMemoId();
    }

    public Page<Memo> findMemoList(Pageable pageable) {
        pageable = PageableDefault.setPageable(pageable);
        return memoRepository.findAll(pageable);
    }

    public void deleteMemo(Long id) {
        memoRepository.deleteById(id);
    }

}
