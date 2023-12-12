package com.demo.testmysql2.utility.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomException extends Exception {
    HttpErrorCode httpErrorCode;
}
