/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.petresgate.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 *
 * @author lara
 */

@ResponseStatus(HttpStatus.CONFLICT)
public class AnimalInformationConflictException extends RuntimeException{
     private static final long serialVersionUID = 1L;
     
     public AnimalInformationConflictException(String message){
         super(message);
     }
}
