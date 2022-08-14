package com.springex.jpa.group;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="GroupEntity")
public class GroupEntity {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long groupIdx;

    @Column private String groupName;
    

}
