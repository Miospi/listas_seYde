//paquete.
package com.umanizales.lists_prog2.controller.dto;
//importe
import lombok.AllArgsConstructor;
import lombok.Data;
//datos
@Data
@AllArgsConstructor
//clase pública
public class ErrorDTO {
    //Sí
    private int code;
    ////Non.
    private String message;
}
