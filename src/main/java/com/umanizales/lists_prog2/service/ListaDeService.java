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
        locations.add(new Location("1002635013", "Villa Maria"));
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
    }


    public ResponseEntity<ResponseDTO> listBoysorderLocation() throws ListaDeException {
        ListaDE listaTemp = new ListaDE();
        for (Location loc : locations) {
            ListaDE listaLoc = listBoys.listaDEByLocation(loc);
            if (listaLoc.getHead() != null) {
                listaTemp.addNode(listBoys.getHead());
            }
        }
        return new ResponseEntity<>(
                new ResponseDTO("Listado", listaTemp, null),
                HttpStatus.OK);
    }
    public ResponseEntity<ResponseDTO> getOrphansByGradeByLocation() throws ListaDeException {
        List<GradesByLocation> gradeByLocationDTOS = new ArrayList<>();
        for (Location loc : locations) {
            gradeByLocationDTOS.add(listBoys.getGenderByLocation()loc));
        }

        return new ResponseEntity<>(new ResponseDTO("Satisfactorio", gradeByLocationDTOS, null), HttpStatus.OK);
    }


}


