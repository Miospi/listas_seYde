package com.umanizales.lists_prog2.model.listade;

import com.umanizales.lists_prog2.exception.ListaDeException;
import com.umanizales.lists_prog2.model.Gender1;
import com.umanizales.lists_prog2.model.Location;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.umanizales.lists_prog2.model.Boy;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ListaDE {
    private Node head;
    private int count;

    /**
     * pase al otro mientras este ammarrado a otro
     * amarra el infante nuevo
     * Ayudante.siguiente.anterior = ayudante
     *
     * @param boy
     */
    public void add(Boy boy) throws ListaDeException {
        Boy boyExist = searchidentification(boy.getIdentification());
        if (boyExist != null) {
            throw new ListaDeException("La identificacion ya existe");
        }
        if (this.head == null) {
            head = new Node(boy);
        } else {
            Node temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
                break;
            }
            //Se queda parado en el ultimo
            temp.setNext(new Node(boy));
            temp.getNext().setPrevious(temp);
        }
        count++;
    }

    public void addTostart(Boy boy) {
        if (this.head != null) {
            Node temp = this.head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            Node newBoy = new Node(boy);
            temp.setNext(newBoy);
            newBoy.setPrevious(temp);
        } else {
            //NO hay datos
            this.head = new Node(boy);
        }
    }

    /**
     * Parámetros entradas  (Posición y los datos (Infante)
     * <
     * <p>
     * Recorrer la lista de principio a fin uno por uno contando la posición en la que estoy parado
     * <p>
     * Cuando llegue a la posicion a insertar -1
     * <
     * Creamos Nodo con la información del infante
     * Hacer que el  nodoinsertar en su siguiente tome el siguiente del ayudante
     * El ayudante que en su siguiente va el infante a insertar
     * El siguiente del niño insertar que su anterior es el que estoy insertando
     * el nodoinsertar que su anterior es el ayudante
     * rompo el ciclo
     */
    public void addXPosition(int position, Boy boy) throws ListaDeException {

        if (this.head != null) {

            if (position == 1) {
                addTostart(boy);
            } else {
                int cont = 1;
                Node temp = this.head;
                while (temp != null) {
                    if ((position - 1) == cont) {
                        Node nodeinsert = new Node(boy);
                        nodeinsert.setNext(temp.getNext());
                        temp.setNext(nodeinsert);
                        if (nodeinsert.getNext() != null)
                            nodeinsert.getNext().setPrevious(nodeinsert);
                        nodeinsert.setPrevious(temp);
                        break;
                    }
                    temp = temp.getNext();
                    count++;
                }
            }
        } else {
            throw new ListaDeException(("La lista está vacía"));
        }
    }

    /**
     * Metodo para contadopr
     *
     * @return
     */
    public int count() {
        if (this.head == null) {
            return 0;
        } else {
            //llamar a mi ayudante
            Node temp = this.head;
            short cont = 1;
            while (temp.getNext() != null) {
                temp = temp.getNext();
                cont++;
            }
            return count;
        }
    }

    /**
     * Metodo que invierte
     * Crear una lista temporal la cabeza de la lista temporal está vacía
     * <p>
     * Llamo un ayudante
     * Recorro la lista de principio a fin
     * De cada nodo , se extrae la información y se envía a la otra lista al inicio
     * * Igualo la cabeza de mi lista principal a la cabeza de la lista temporal
     */
    public void invert() {
        if (head != null) {

            ListaDE listaTemporal = new ListaDE();

            Node temp = this.head;

            while (temp != null) {

                listaTemporal.addTostart(temp.getData());
                temp = temp.getNext();
            }

            this.head = listaTemporal.getHead();
        }
    }


    public void Delete(String identification) throws ListaDeException {
        if (this.head != null) {
            if (this.head.getData().getIdentification() == identification) {
                this.head = head.getNext();
                this.head.setPrevious(null);
                return;
            } else {
                Node temp = this.head;
                while (temp.getNext() != null) {
                    if (temp.getNext().getData().getIdentification() == identification) {
                        //el que sigue es el que hay que eliminar
                        temp.setNext(temp.getNext().getNext());
                        if (temp.getNext() != null) {
                            temp.getNext().setPrevious(temp);
                        }
                        return;
                    }
                    temp = temp.getNext();
                }

                throw new ListaDeException("El código " + identification + " no existe en la lista");
            }
        }
        throw new ListaDeException("La lista de infantes está vacía");
    }

    /**
     * Metodp para buscar al infante por identificacion
     *
     * @param identification
     * @return
     * @throws ListaDeException se verifica que el condicional de cabeza es igual a null
     *                          luegio la cabeza va acomparar los datos y identificacion e la lista con los de los infantes
     *                          dara la inf
     */
    public Boy searchidentification(String identification) throws ListaDeException {
        if (this.head != null) {
            if (this.head.getData().getIdentification() == identification) {
                return this.head.getData();
            } else {
                Node temp = this.head;

                /**
                 * el ayudante va a recorrer la lista comaprando la informacion y retiornar el darp
                 */
                while (temp != null) {
                    if (temp.getData().getIdentification() == identification) {
                        return temp.getData();
                    }
                    temp = temp.getNext();
                }

                throw new ListaDeException("El código " + identification + " no existe en la lista");
            }
        }
        throw new ListaDeException("La lista de infantes está vacía");
    }

    /**
     * Metoodo para listar la gente mayopr de edad
     * haciendo que el temp o ayudante compare la inforamcio de la lisrta con la edad
     * de esta forma se retornarta el valor max
     *
     * @return
     * @throws ListaDeException
     */
    public Boy listMaxAge() throws ListaDeException {
        if (head != null) {
            Boy max = head.getData();
            Node temp = this.head;
            while (temp != null) {
                if (temp.getData().getAge() > max.getAge()) {
                    max = temp.getData();
                }
                temp = temp.getNext();
            }

            return max;

        }
        throw new ListaDeException("La lista de infantes está vacía");
    }

    public int positionBoy(String identification) throws ListaDeException {
        if (head != null) {
            int cont = 1;
            Node temp = this.head;
            while (temp != null) {
                if (temp.getData().getIdentification() == identification) {
                    return cont;
                }
                temp = temp.getNext();
                cont++;
            }
            throw new ListaDeException("El código ingresado no ");

        }
        throw new ListaDeException("La lista de infantes está vacía");
    }

    public void addNode(Node nodeI) {
        if (this.head == null) {
            this.head = nodeI;
        } else {
            Node temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(nodeI);
            nodeI.setPrevious(temp);
        }

    }

    /**
     * Creamos  un matodo que nos de una lista por cada localizacion
     * @param location
     * @return
     * @throws ListaDeException
     */
    public ListaDE listaDEByLocation (Location location) throws  ListaDeException {
        /**
         * Se crea una nueva lista para  los nodos
         */
        ListaDE listatemp = new ListaDE();
        /**
         * Se agarra pone a al ayudante en al cabeza
         */
                Node temp = this.head;
        /**
         * Se le pone al temporal a llegar al ultimo
         */
        while(temp !=null){
            /**
             * El temporal extrae los datos y locaciones y los compara
             */
                            if (temp.getData().getLocation().equals(location)) {
                                /**
                                 * Se agarran los datos y se crea una nueva lista
                                 */
                                listatemp.add(temp.getData());
                            }

                        }
                        return  listatemp;
    }
    public RhByGrades getGradesRhDTOByGrades(byte grade)throws ListaDeException{

        Node temp = this.head;
        String rh = " ";
        int count = 0;
        while (temp != null){
            if(temp.getData().getGrade() == grade) {
                if (!rh.contains(temp.getData().getRh())) {
                    rh = rh + ", " + temp.getData().getRh();
                }
                count++;
            }
            temp = temp.getNext();
        }
        return new RhByGradesDTO(grade,rh,count);
    }

    public GradesByGenderDTO getGradesByGenderDTO(Gender1 gender)throws ListaDeException{
        validateListEmpty();
        RhByGradesDTO[] rhByGradesDTOS = new RhByGradesDTO[5];
        for (byte i = 1; i <= 5; i++) {
            rhByGradesDTOS[i]= getGradesRhDTOByGrades((byte)(i+1));
        }
        return new GradesByGenderDTO(gender,rhByGradesDTOS);
    }

    public GenderByLocationDTO getGenderByLocation(Location location) throws ListaDeException {
        validateListEmpty();
        List<GradesByGenderDTO> gradesByGenderDTOS = new ArrayList<>();
        int count = 0;
        Node temp = head;
        while (temp != null){
            if (temp.getData().getLocation().getCode().equals(location)) {
                gradesByGenderDTOS.add(getGradesByGenderDTO(temp.getData().getGender()));
                count++;
            }
            temp = temp.getNext();
        }
        GenderByLocationDTO genderByLocationDTO = new GenderByLocationDTO(location,gradesByGenderDTOS, count);
        return genderByLocationDTO;
    }




}
