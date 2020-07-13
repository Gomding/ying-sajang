package com.community.hululuuuu.restController;

import com.community.hululuuuu.memo.MemoCommand;
import com.community.hululuuuu.memo.MemoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/memo")
public class MemoRestController {

    private MemoService memoService;

    public MemoRestController(MemoService memoService) {
        this.memoService = memoService;
    }

    @PostMapping("/memoList")
    public ResponseEntity<?> createMemo(@RequestBody MemoCommand memoCommand) {
        memoService.createMemo(memoCommand);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    @DeleteMapping("/memoList/{id}")
    public ResponseEntity<?> deleteMemo(@PathVariable("id")Long id) {
        memoService.deleteMemo(id);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

}
