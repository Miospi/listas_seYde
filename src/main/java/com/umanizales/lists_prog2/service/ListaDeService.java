package com.umanizales.lists_prog2.service;

import com.umanizales.lists_prog2.controller.dto.ResponseDTO;
import com.umanizales.lists_prog2.exception.ListaDeException;
import com.umanizales.lists_prog2.exception.ListaSeException;
import com.umanizales.lists_prog2.model.Boy;
import com.umanizales.lists_prog2.model.Location;
import com.umanizales.lists_prog2.model.listade.ListaDE;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListaDeService {
    private ListaDE listBoys;
    private List<Location> locations;

    public ListaDeService() {
        listBoys = new ListaDE();
    }

    private void inicializatedLocation() {
        locations = new ArrayList<>();
        locations.add(new Location("1002635010", "Manizales"));
        locations.add(new Location("1002635011", "Chinchina"));
        locations.add(new Location("1002635012", "Pereira"));
    }

    public boolean validateLocation(Location location) {
        for (Location loc : locations) {
            if (loc.getCode().equals(location.getCode()) && loc.getDescription().equals(location.getDescription())) {
                return true;
            }
        }
        return false;
    }

    public ResponseEntity<ResponseDTO> addDE(Boy boy) throws ListaDeException {
        if (!validateLocation(boy.getLocation())) {
            throw new ListaDeException("La ubicación ingresada no es válida");
        }

        listBoys.add(boy);
        return new ResponseEntity<>(
                new ResponseDTO("Niño adicionado", true, null),
                HttpStatus.OK);

        public ResponseEntity<ResponseDTO> addToStart(Boy boy) throws ListaDeException{
            // se llama el metodo agregar al inicio y se le envia la data del niño
            listBoys.addTostart(boy);
            // hubo coexion con el sistema
            return new ResponseEntity<>(new ResponseDTO("Niño Adicionado", true, null), HttpStatus.OK);
        }

    }
}