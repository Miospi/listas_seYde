package com.umanizales.lists_prog2.controller;


import com.umanizales.lists_prog2.controller.dto.ResponseDTO;
import com.umanizales.lists_prog2.exception.ListaDeException;
import com.umanizales.lists_prog2.exception.ListaSeException;
import com.umanizales.lists_prog2.model.Boy;
import com.umanizales.lists_prog2.service.ListSeService;
import com.umanizales.lists_prog2.service.ListaDeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 *
 */
@RestController
@RequestMapping(path= "boys")
public class BoysController {
    @Autowired
    private ListSeService listSeService;

    /**
     * Metodo para que muestra la informacion de de los ninos anadidos
     * @param boy
     * @return
     * @throws ListaSeException
     */
    @PostMapping
    public ResponseEntity<ResponseDTO> addBoy(@RequestBody @Valid Boy boy) throws ListaSeException
    {
        return listSeService.addBoy(boy);
    }

    /**
     *metodo que invoca la informacion de lista de servicios en el msimo servicio
     * @return
     * @throws ListaSeException
     */
    @GetMapping
    public ResponseEntity<ResponseDTO> listBoys() throws ListaSeException
    {return listSeService.listBoys();}

    /**
     *
     * @return
     * @throws ListaSeException
     */
    @GetMapping(path = "invert")
    public ResponseEntity<ResponseDTO> invertList() throws ListaSeException
    {
        return listSeService.invertList();
    }

    /**
     *
      * @param boy
     * @return
     * @throws ListaSeException
     */
    @PostMapping(path="addtostart")
    public ResponseEntity<ResponseDTO> addBoyToStart(@RequestBody Boy boy) throws ListaSeException
    {
        return listSeService.addBoy(boy);
    }

    /**
     *
     * @param position
     * @param boy
     * @return
     * @throws ListaSeException
     */
    @PostMapping(path="addtoposition/{position}")
    public ResponseEntity<ResponseDTO> addBoyByPosition(@PathVariable int position, @RequestBody Boy boy) throws ListaSeException
    {
        return listSeService.addBoyByPosition(boy,position);
    }

    /**
     * Metodo que recive la informacion de los ninos agregados para mostralos en el servico
     * @param boys
     * @return
     * @throws ListaSeException
     */
    @PostMapping(path = "addboys")
    public ResponseEntity<ResponseDTO> addBoys(@RequestBody List<Boy> boys) throws ListaSeException
    {
        for(Boy boy:boys)
        {
            listSeService.addBoy(boy);
        }
        return new ResponseEntity<ResponseDTO>(new ResponseDTO("Satisfactorio,",
                listSeService.listBoys(),null), HttpStatus.OK);
    }

    /**
     *
     * @return
     */
    @GetMapping(path="getcount")
    public ResponseEntity<ResponseDTO> getCount()
    {
        return listSeService.getCount();
    }

    /**
     *
     * @return
     */
    @GetMapping(path="count")
    public ResponseEntity<ResponseDTO> count()
    {
        return listSeService.Count();
    }

    /**
     *
     * @return
     * @throws ListaSeException
     */
    @GetMapping(path = "free")
    public ResponseEntity<ResponseDTO> listBoysFree() throws ListaSeException{
            return listSeService.listBoysFree();
    }

    /**
     *
     * @return
     * @throws ListaSeException
     */
    @GetMapping(path="changextremes")
    public ResponseEntity<ResponseDTO> changeXtremes() throws ListaSeException{
        return listSeService.changeXtremes();
    }

    /**
     *Metodo que se muestra en el servicio  para eliminar a ;os infantes de la lista
     * @param identification
     * @return
     * @throws ListaSeException
     */
    @GetMapping(path = "delete/{identification}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable String identification) throws ListaSeException
    {
        return listSeService.delete(identification);
    }

    /**
     *Metodo que muetarv en e; servicio
     * @return
     * @throws ListaSeException
     */
    @GetMapping(path = "variant")
    public ResponseEntity<ResponseDTO> variantList() throws ListaSeException
    {
        return listSeService.variantList();
    }

    /**
     * Metod que invoca la informacion de los infantes y la muestrea em el servicio
     * @return
     */
    @GetMapping(path = "boysbylocation")
    public ResponseEntity<ResponseDTO> boysByLocation()
    {
        return listSeService.getBoysByLocation();
    }
/**
 * ///////////////////////////////////////////////////////////////////////////////////////
 */
@Autowired
ListaDeService listaDeService;
@PostMapping(path = "addDe")
    public ResponseEntity<ResponseDTO> addDE (@RequestBody @Valid Boy boy) throws ListaDeException {
    return  listaDeService.addDE(boy);
}
   @PostMapping(path = "addToStartDE")
    public ResponseEntity<ResponseDTO> addToStartDE (@RequestBody @Valid Boy boy) throws ListaDeException {
       return listSe.addBoytoStart(boy);
   }
    @GetMapping(path = "/invertde")
    public ResponseEntity<ResponseDTO> invertListDe() throws ListaSeException {
        return listDeService.invertListDe();
    }






}

