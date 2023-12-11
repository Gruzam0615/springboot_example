package com.springbootex.jpa01.team.controller;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springbootex.jpa01.member.entity.Member;
import com.springbootex.jpa01.member.entity.MemberRegistParam;
import com.springbootex.jpa01.member.entity.QMember;
import com.springbootex.jpa01.team.entity.QTeam;
import com.springbootex.jpa01.team.entity.Team;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Slf4j
@Controller
@RequestMapping(value = "/team")
public class TeamController {

    @PersistenceContext
    EntityManager em;

    @GetMapping(value = "/list")
    public String teamList() {
        return "team/list";
    }

    @GetMapping(value = "/regist")
    public String teamRegistPage() {
        return "team/regist";
    }

    @PostMapping(value = "/regist")
    @ResponseBody
    public String teamRegistAction(MemberRegistParam param) {
        log.info("param: " + param.toString());

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QTeam t = new QTeam("t");

        queryFactory.insert(t)
                .columns(t.teamName)
                .values("team01")
                .execute();

        return "teamRegistAction";
    }

}
