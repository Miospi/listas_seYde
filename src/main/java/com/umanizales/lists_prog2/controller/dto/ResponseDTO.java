package com.umanizales.lists_prog2.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Constructor de las respuestas de para el servicio de la listaSE
 */
@Data
@AllArgsConstructor
public class ResponseDTO {
    private String message;
    private Object data;
    private List<ErrorDTO> errors;
}
