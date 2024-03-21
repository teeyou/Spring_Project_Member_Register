package com.spring.project01.controller;

import com.spring.project01.dto.MemberDTO;
import com.spring.project01.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

//@Controller
//@RequiredArgsConstructor
//public class MemberController {
//    private final MemberService memberService;
//
//    @GetMapping("/member/save")
//    public String saveForm() {
//        return "save";
//    }
//}

@Controller
@RequestMapping("/member")  //이걸 써주면 매핑되는 경로 축약 가능
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/save")
    public String saveForm() {
        return "save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute MemberDTO memberDTO) {
        //DTO의 필드와 폼태그의 name이 일치해야함
        //컨트롤러 -> 서비스 -> 리포지토리
        int saveResult = memberService.save(memberDTO);
        if(saveResult > 0) {
            return "login";
        }
        else {
            return "save";
        }
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }


    @PostMapping("/login")
    public String login(@ModelAttribute MemberDTO memberDTO,
                        HttpSession session) {
        boolean loginResult = memberService.login(memberDTO);
        if(loginResult) {
            //로그인을 하게 되면 로그인 정보가 계속 따라다녀야 되기 때문에 세션세팅하고 main.jsp 이동
            session.setAttribute("loginEmail",memberDTO.getMemberEmail());
            return "main";
        } else {
            return "login"; //로그인 실패하면 그대로 login.jsp
        }
    }

    @GetMapping("/")
    public String findAll(Model model) {
        List<MemberDTO> memberDTOList = memberService.findAll();

        System.out.println("memberDTOList");
        for(MemberDTO member : memberDTOList)
            System.out.println(member);
        System.out.println("---------------------");
        model.addAttribute("memberList", memberDTOList);
        return "list";
    }

    // member?id=1처럼 쿼리스트링 처리. list.jsp에서 조회할 때 수행
    //@RequestParam을 이용해서 쿼리스트링 파라미터 받아옴
    @GetMapping
    public String findById(@RequestParam("id") Long id, Model model) {
        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member", memberDTO);
        return "detail";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        memberService.delete(id);
        return "redirect:/member/";
    }

    @GetMapping("/update")
    public String updateForm(HttpSession session, Model model) {
        String email = (String) session.getAttribute("loginEmail");
        MemberDTO memberDTO = memberService.findByEmail(email);
        model.addAttribute("member", memberDTO);
        return "update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute MemberDTO memberDTO) {
        boolean result = memberService.update(memberDTO);
        if(result) {
            return "redirect:/member?id=" + memberDTO.getId();
        } else {
            return "index";
        }
    }

    // ajax응답을 처리할 때 @ResponseBody를 써야함
    @PostMapping("/email-check")
    public @ResponseBody String emailCheck(@RequestParam("memberEmail") String email) {
        System.out.println("memberEmail : " + email);
        String checkResult = memberService.emailCheck(email);
        return checkResult;
    }
}
