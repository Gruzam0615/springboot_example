package com.demo.testmysql2.handler.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomException extends Exception {
    HttpErrorCode httpErrorCode;
}
