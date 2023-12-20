package com.springbootex.jpa01.team.controller;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springbootex.jpa01.member.entity.MemberRegistParam;
import com.springbootex.jpa01.team.entity.QTeam;
import com.springbootex.jpa01.team.entity.Team;
import com.springbootex.jpa01.team.service.TeamService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private TeamService teamService;

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
        Team input = new Team(param.getTeamName());
        Team t = teamService.save(input);

        // JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        // QTeam t = new QTeam("t");

        // queryFactory.insert(t)
        //         .columns(t.teamName)
        //         .values("team01")
        //         .execute();

        return "teamRegistAction";
    }

}
