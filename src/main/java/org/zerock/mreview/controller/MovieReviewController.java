package org.zerock.mreview.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.mreview.dto.MovieReviewDTO;
import org.zerock.mreview.service.MovieReviewService;

import java.util.List;

@RestController
@RequestMapping("/mreviews")
@Log4j2
@RequiredArgsConstructor
public class MovieReviewController {

    private final MovieReviewService movieReviewService;

    @GetMapping("/{mno}/all")
    public ResponseEntity<List<MovieReviewDTO>> getList(@PathVariable("mno") Long mno){
        log.info("--------------list---------------");
        log.info("MNO: " + mno);

        List<MovieReviewDTO> reviewDTOList = movieReviewService.getListOfMovie(mno);

        return new ResponseEntity<>(reviewDTOList, HttpStatus.OK);
    }

    @PostMapping("/{mno}")
    public ResponseEntity<Long> addReview(@RequestBody MovieReviewDTO movieReviewDTO){
        log.info("--------------add MovieReview---------------");
        log.info("movieReviewDTO: " + movieReviewDTO);

        Long reviewnum = movieReviewService.register(movieReviewDTO);

        return new ResponseEntity<>( reviewnum, HttpStatus.OK);
    }

    @PutMapping("/{mno}/{reviewnum}")
    public ResponseEntity<Long> modifyReview(@PathVariable Long reviewnum,
                                             @RequestBody MovieReviewDTO movieReviewDTO){
        log.info("---------------modify MovieReview--------------" + reviewnum);
        log.info("movieReviewDTO: " + movieReviewDTO);

        movieReviewService.modify(movieReviewDTO);

        return new ResponseEntity<>( reviewnum, HttpStatus.OK);
    }

    @DeleteMapping("/{mno}/{reviewnum}")
    public ResponseEntity<Long> removeReview( @PathVariable Long reviewnum){
        log.info("---------------modify removeReview--------------");
        log.info("reviewnum: " + reviewnum);

        movieReviewService.remove(reviewnum);

        return new ResponseEntity<>( reviewnum, HttpStatus.OK);
    }

}



















