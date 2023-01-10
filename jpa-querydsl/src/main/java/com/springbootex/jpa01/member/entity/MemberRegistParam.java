package com.springbootex.jpa01.member.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberRegistParam {
    
    String memberName;
    String teamName;

}
