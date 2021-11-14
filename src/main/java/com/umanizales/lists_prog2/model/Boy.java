package com.umanizales.lists_prog2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

/**
 * Clase para almacenar la información referente a un niño
 * Maneja campos obligatorios para (identificación, edad, ...
 * @autor Carlos Loaiza
 * @author
 * @version 1.0 - 30-oct-2021
 *
 */

@Data
@AllArgsConstructor
public class Boy {
    @NotNull
    @NotEmpty
    @Size(min=2)
    private String identification;
    @NotNull
    @NotEmpty
    @Size(min=2, max = 50)
    private String name;
    @Positive
    private byte age;
    @NotNull
    private Gender gender;
    @Valid
    @NotNull
    private Location location;
    @NotNull
    private byte grade;
    /**
     * atributo booleano de si es orphan o no
     */
    @NotNull
    private boolean orphan;
    /**
     * creamos un atributo string que sea el rh
     */
    @NotNull
    private String rh;
}
