package com.umanizales.lists_prog2.controller;

import com.umanizales.lists_prog2.exception.ListaSeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.umanizales.lists_prog2.controller.dto.*;

import java.util.ArrayList;
import java.util.List;


@ControllerAdvice
public class ErrorHandlerController {
    /**
     * Metodo el cual se invoca para poner las execpciones en el servicio de la lista SE
     * @param ex
     * @return
     */
    @ExceptionHandler(ListaSeException.class)
    protected ResponseEntity<?> handle(ListaSeException ex){
        /**
         * Se invoca la metodo par realizar exepcciones
         */
        String message = ex.getMessage();
        /**
         * Se crea lista para invocar el constructor y utilizarlo en el servicio cuando falla
         */
        ResponseDTO response = new ResponseDTO( message,null , null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * Metodo para validar las excepciones  de las clases cuando se pide la informacion
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<?> handle(MethodArgumentNotValidException ex){
        /**
         * se crea arreglo para verificar la informacion y luego compararla
       */
        List<ErrorDTO> listErrors = new ArrayList<>();
        /** Se hacen la coparacion de los datos y se assingna el erro y el campo del nombte
         *
         */
        ex.getBindingResult().getFieldErrors().forEach((error) -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            /** si se encuentra el error se añaden a la sit y sueltan el mensaje
             *
             */
            listErrors.add(new ErrorDTO(HttpStatus.BAD_REQUEST.value(), fieldName +" "+ errorMessage));
        });
        String message = "Algunos campos son inválidos o faltantes, por favor corrija los errores y vuelva a intentarlo";
        ResponseDTO response = new ResponseDTO( message,null , listErrors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


}
