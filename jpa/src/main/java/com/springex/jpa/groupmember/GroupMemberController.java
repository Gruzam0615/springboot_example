package com.springex.jpa.groupmember;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value="/groupMember")
public class GroupMemberController {

    @Autowired private GroupMemberService groupMemberService;

    @GetMapping("/test01")
    @ResponseBody
    public String test01() {
        return "/groupMember/test01";
    }
    
    /**
     * OneToOne 관계 예제
     * @param userIdx
     * @return
     */
    @GetMapping("/getGroupMember")
    @ResponseBody
    public String getGroupMember(
        @RequestParam Long userIdx
    ) {
        log.info("$ input userIdx: {}", userIdx);
        GetByUserIdx result = groupMemberService.getByUserIdx(userIdx);
        if(result == null) {
            return "result is Null";
        }
        else {
            log.info("result: {}", result.toString());
            return result.getUserName();
        }
    }

}
