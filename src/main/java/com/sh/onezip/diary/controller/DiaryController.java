package com.sh.onezip.diary.controller;

import com.sh.onezip.auth.vo.MemberDetails;
import com.sh.onezip.diary.dto.DiaryCreateDto;
import com.sh.onezip.diary.dto.DiaryListDto;
import com.sh.onezip.diary.repository.DiaryRepository;
import com.sh.onezip.diary.service.DiaryService;
import com.sh.onezip.member.entity.Member;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
@Controller
@Slf4j
public class DiaryController {

    @Autowired
    private DiaryService diaryService;

    @GetMapping("/diaryList.do")
    public void diaryList(@PageableDefault(size = 5, page = 0) Pageable pageable, Model model) {
        log.info("diaryService={}",diaryService.getClass());

        log.debug("pageable = {}", pageable);
        Page<DiaryListDto> diaryPage = diaryService.findAll(pageable);
        log.debug("diary = {}", diaryPage.getContent());
        model.addAttribute("diaries", diaryPage.getContent());
        model.addAttribute("totalCount", diaryPage.getTotalElements()); // 전체 게시물수
    }

    @PostMapping("/createDiary.do")
    public String createBoard(
            @Valid DiaryCreateDto diaryCreateDto,
            BindingResult bindingResult,
            @AuthenticationPrincipal MemberDetails memberDetails,
            RedirectAttributes redirectAttributes)
            throws IOException {
        if (bindingResult.hasErrors()) {
            throw new RuntimeException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        Member member = memberDetails.getMember();

        // DiaryService를 사용하여 다이어리를 생성합니다.
//        diaryService.createDiary(diaryCreateDto,member);

        // 리다이렉트후에 사용자피드백
        redirectAttributes.addFlashAttribute("msg", "🎈🎈🎈 게시글을 성공적으로 등록했습니다. 🎈🎈🎈");
        return "redirect:/";
    }
}
