package com.neohoon.enverstest.app.controller;

import com.neohoon.enverstest.app.domain.Member;
import com.neohoon.enverstest.app.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberRepository memberRepository;

    @GetMapping
    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    @PostMapping
    public void saveMember(@RequestBody String name) {
        memberRepository.save(new Member(name));
    }


}
