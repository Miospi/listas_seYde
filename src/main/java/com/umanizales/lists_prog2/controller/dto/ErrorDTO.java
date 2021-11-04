
package com.umanizales.lists_prog2.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Clase de constructor la cual crea un menssage de errores par a la listaSE
 */
@Data
@AllArgsConstructor

public class ErrorDTO {
    private int code;
    private String message;
}
