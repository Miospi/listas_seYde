package com.umanizales.lists_prog2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Clase que da la localizacion de los niños en la lista simplemente enlazada
 * @author Miguel Ospina Quintero
 * @autor Carlos Loaiza
 */
@Data
@AllArgsConstructor
public class BoysByLocation {
    private Location location;
    private int count;
}
