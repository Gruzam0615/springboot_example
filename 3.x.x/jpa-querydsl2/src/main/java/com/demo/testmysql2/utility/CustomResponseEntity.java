package com.demo.testmysql2.utility;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomResponseEntity {
    
    private int responseCode;
    private Collection<Object> response;

}
