package com.umanizales.lists_prog2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoysByGender {
    /**
     * atributo creado para saber el genero
     */
    private Gender1 gender;
    /**
     * atributo creado para que me cuente los niños o niñas por el genero
     */
    private int count;
}
