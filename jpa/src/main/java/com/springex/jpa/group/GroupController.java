package com.springex.jpa.group;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springex.jpa.util.random.RandomGenEngKeyword;

@Controller
@RequestMapping(value="/group")
public class GroupController {

    @Autowired private GroupService groupService;
    
    @GetMapping("/randomAddGroup")
    public String randomAddGroup() {
        Set<String> tempGroupList = new HashSet<>();
        while(tempGroupList.size() < 10) {
            String temp = new RandomGenEngKeyword().randomGenKeyword(10);
            tempGroupList.add(temp);
        }

        tempGroupList.forEach(item -> {
            GroupEntity g = new GroupEntity();
            g.setGroupName(item);
            groupService.save(g);
        });
        
        
        return "/index";
    }

}
