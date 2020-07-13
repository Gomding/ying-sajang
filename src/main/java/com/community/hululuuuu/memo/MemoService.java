package com.community.hululuuuu.memo;

import com.community.hululuuuu.repository.MemoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MemoService {

    private MemoRepository memoRepository;

    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    public void createMemo(MemoCommand memoCommand) {
        memoRepository.save(
                Memo.builder()
                .memoContent(memoCommand.getContent())
                .memoLink(memoCommand.getLink())
                .memoCreatedate(LocalDateTime.now())
                .build()
        );
    }

    public Page<Memo> findMemoList(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, pageable.getPageSize());
        return memoRepository.findAll(pageable);
    }

    public void deleteMemo(Long id) {
        memoRepository.deleteById(id);
    }

}
