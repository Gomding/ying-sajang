package com.community.hululuuuu.web;

import com.community.hululuuuu.service.memo.MemoService;
import com.community.hululuuuu.web.dto.MemoRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@RestController
@RequestMapping("/memo")
public class MemoRestController {

    private final MemoService memoService;

    @GetMapping("/memoList")
    public ModelAndView getMemoList(@PageableDefault(size = 5) Pageable pageable) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("memoList", memoService.findMemoList(pageable));
        mav.setViewName("memo/memoList");
        return mav;
    }

    @PostMapping("/memoList")
    public ResponseEntity<?> createMemo(@RequestBody MemoRequestDto requestDto) {
        memoService.createMemo(requestDto);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    @DeleteMapping("/memoList/{id}")
    public ResponseEntity<?> deleteMemo(@PathVariable("id")Long id) {
        memoService.deleteMemo(id);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

}
