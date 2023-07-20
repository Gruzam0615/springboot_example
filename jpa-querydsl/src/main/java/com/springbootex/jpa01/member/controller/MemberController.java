package com.springbootex.jpa01.member.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springbootex.jpa01.member.entity.Member;
import com.springbootex.jpa01.member.entity.MemberRegistParam;
import com.springbootex.jpa01.member.entity.QMember;
import com.springbootex.jpa01.member.service.MemberService;

import com.springbootex.jpa01.team.entity.Team;
import com.springbootex.jpa01.team.entity.QTeam;
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

    @GetMapping(value = "/testSelect01")
    @ResponseBody
    public String testSelect01() {
        Member result = memberService.findByMemberId(Long.valueOf("1"));
        // log.info("size: {}, {}", result.size(), result.toString());
        log.info(result.toString());
        return result.toString();
    }

    @PersistenceContext
    EntityManager em;

    @GetMapping(value = "/testSelect02")
    @ResponseBody
    public String testSelect02() {
        TypedQuery<Member> result = em.createQuery("select m from Member m", Member.class);
        return result.getResultList().get(0).toString();
    }

    @GetMapping(value = "/queryDslSelect01")
    @ResponseBody
    public String queryDslSelect01() {
        String result = "";

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QMember m = new QMember("m");

        Member findMember = queryFactory
                                .select(m)
                                .from(m)
                                .where(m.memberId.eq(1l))
                                .fetchOne();

         result = findMember.toString();

        return result;
    }

    @PostMapping(value = "/queryDslInsert01")
    @ResponseBody
    public String queryDslInsert01(MemberRegistParam param) {
        log.info("param: " + param.toString());

        QMember m = new QMember("m");
        QTeam t = new QTeam("t");


//        queryFactory.insert(m)
//                .columns(m.memberName, m.memberAge)
//                .values(param.getMemberName(), 21)
//                .execute();

        return "Done";
    }

}
