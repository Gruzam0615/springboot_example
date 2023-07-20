package com.springbootex.jpa01.member.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.springbootex.jpa01.team.entity.Team;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"memberId", "memberName", "team"})
public class Member {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    private String memberName;
    private int memberAge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teamId")
    private Team team;

    public Member(String memberName) {
        this(memberName, 0);
    }
    
    public Member(String memberName, int memberAge) {
        this(memberName, memberAge,null);
    }

    public Member(Long memberId, String memberName) {
        this.memberId = memberId;
        this.memberName = memberName;
    }

    public Member(String memberName, int memberAge, Team team) {
        this.memberName = memberName;
        this.memberAge = memberAge;
        if(team != null) {
            changeTeam(team);
        }
    }

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }

}
