package com.spring.project01.service;


import com.spring.project01.dto.MemberDTO;
import com.spring.project01.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public int save(MemberDTO memberDTO) {
        return memberRepository.save(memberDTO);
    }

    public boolean login(MemberDTO memberDTO) {
        MemberDTO loginMember = memberRepository.login(memberDTO);
        if(loginMember != null)
            return true;
        else
            return false;
    }

    public List<MemberDTO> findAll() {
        return memberRepository.findAll();
    }

    public MemberDTO findById(Long id) {
        return memberRepository.findById(id);
    }

    public void delete(Long id) {
        memberRepository.delete(id);
    }

    public MemberDTO findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public boolean update(MemberDTO memberDTO) {
        int result = memberRepository.update(memberDTO);
        if(result > 0) {
            return true;
        } else {
            return false;
        }
    }

    public String emailCheck(String email) {
        MemberDTO memberDTO = memberRepository.findByEmail(email);
        if(memberDTO == null)
            return "ok";
        else
            return "no";
    }
}
