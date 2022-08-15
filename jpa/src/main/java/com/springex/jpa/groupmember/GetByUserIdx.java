package com.springex.jpa.groupmember;

import java.time.LocalDateTime;

public interface GetByUserIdx {

    Long getGroupMemberIdx();
    Long getGroupIdx();
    Long getUserIdx();
    String getUserName();
    LocalDateTime getJoinDate();

}
