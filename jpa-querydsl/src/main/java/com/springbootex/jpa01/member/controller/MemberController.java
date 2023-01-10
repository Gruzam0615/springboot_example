package com.springbootex.jpa01.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springbootex.jpa01.member.entity.Member;
import com.springbootex.jpa01.member.entity.MemberRegistParam;
import com.springbootex.jpa01.member.service.MemberService;
import com.springbootex.jpa01.team.entity.Team;
import com.springbootex.jpa01.team.service.TeamService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping(value = "/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private TeamService teamService;
    
    @GetMapping(value = "/list")
    public String getListPage() {
        return "member/list";
    }

    @GetMapping(value = "/regist")
    public String getRegistPage() {
        return "member/regist";
    }
    
    @PostMapping(value = "/regist")
    @ResponseBody
    public String registAction(MemberRegistParam param) {
        log.info("param: " + param.toString());
        Team team = new Team(param.getTeamName());
        Member member = new Member(param.getMemberName(), 20, team);

        teamService.save(team);
        memberService.save(member);
        return "done...";
    }

}
