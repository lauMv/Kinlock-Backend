package com.app.kinlock.exceptions;

public class MandatoryFieldException extends RuntimeException{
    public MandatoryFieldException (String Field1, String Field2){
        super(String.format("Los campos: %s, %s son obligatorios", Field1, Field2));
    }
}
