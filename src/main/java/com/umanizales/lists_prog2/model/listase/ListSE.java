package com.umanizales.lists_prog2.model.listase;
/**
 * Clase que me permite gestionar una lista simplemente enlazada
 * contiene los métodos u operaciones ....
 * solo cuenta con los atributo head = que representa el primer niño
 * ...
 */

import com.umanizales.lists_prog2.exception.ListaSeException;
import com.umanizales.lists_prog2.model.Boy;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListSE {
    /**
     * Atributo que representa el inicio de la lista y es
     */
    private Node head;
    private int count;

    /**
     * Método que adiciona un niño al final de la lista
     *
     * @param boy
     * @throws ListaSeException
     */
    public void add(Boy boy) throws ListaSeException {
        /**
         * Se invoca el método encontrar por identificación, para verificar si
         * el niño que está ingresando ya existe
         */
        Boy boyExist = findByIdentification(boy.getIdentification());
        if (boyExist != null) {
            /**
             * Si el niño ya existe se lanza la excepeción para comunicar al usuario en el controller
             */
            throw new ListaSeException("La identificación ingresada ya existe");
        }
        /** se pide que se cumpla la condición para que la cabeza no este agarrando nada
         */
        if (head == null) {
            /**
             *  Si la condición anterior de la cabeza se cumple, se va agregar nuevo niño
             */
            head = new Node(boy);
        }
        //Pero
        else {
            /**
             *  El ayudante que va recorrer la lista
             */
            Node temp = head;
            /**
             *  El ayudante recore la lista hasta llegar a null, "preguntandole al  ultimo, si no esta agarrando nada"
             */
            while (temp.getNext() != null) {
                /**
                 * El ayudante pasa al siguiente
                 */
                temp = temp.getNext();
            }
            /**
             * El quedo parado en el ultimo y agrega el nuevo "niño"
             */
            temp.setNext(new Node(boy));
        }
        /**
         * Se agrega al contador
         */
        count++;
    }

    /**
     * Metodo adiccionar niño al principio
     *
     * @param boy
     * @throws ListaSeException
     */
    public void addToStart(Boy boy) throws ListaSeException {
        /**
         * Esta linea va a comparar si el niño  que se agrego, no esta en la lista
         */
        Boy boyExist = findByIdentification(boy.getIdentification());
        /**
         * si la condicion verifica que el niño sea diferente de null
         */
        if (boyExist != null) {
            /**
             * Si no es diferente de nul, va a entrar a la exception
             */
            throw new ListaSeException("La identificación ingresada ya existe");
        }
        /**
         * Condiccion que le pregunta a la cabeza si esta agarrando null"
         */

        if (this.head == null) {
            /**
             * Si la condicion anterior es verdadera va a agregase un niño ahi mismo
             */
            this.head = new Node(boy);
        } else {

            /**
             * Si no se cumple lo anterior
             * Temp va a agregar a un nuevo niño
             */
            Node temp = new Node(boy);
            /**
             * diciendole a cabeza  que lo ponga delante de el
             */
            temp.setNext(this.head);
            /**
             * Cabeza se vuelve temporal
             */
            this.head = temp;
        }

        /**
         * se agrega al contador
         */
        count++;
    }

    /**
     * Metodo para saber la posición, de los niños en la lista
     *
     * @param boy
     * @param position
     * @throws ListaSeException
     */
    public void addByPosition(Boy boy, int position) throws ListaSeException {
        /**
         * Se invoca el método encontrar por identificación, para verificar si
         * el niño que está ingresando ya existe
         */
        Boy boyExist = findByIdentification(boy.getIdentification());
        /**
         * si la condicion verifica que el niño sea diferente de null
         */
        if (boyExist != null) {
            /**
             * Si el niño ya existe se lanza la excepeción para comunicar al usuario en el controller
             */
            throw new ListaSeException("La identificación ingresada ya existe");
        }
        /**
         * Validación de la position
         */
        if (position > count) {
            // Va abuscar en la lista y como es mayor y no se encuentra
            this.add(boy);
            /**
             * Va a retornar a la excepcion
             */
            return;
            //throw  new ListaSeException("La posición ingresada no es válida");
        }
        /**
         * Si la position que se ingresa es 1
         */
        if (position == 1) {
            /**
             * se va a invocar el método addToStart y mostrarte el niño del inicio
             */
            addToStart(boy);
        } else {
            /**
             *si la psoicion que se elige para agragar es 1
             */
            int cont = 1;
            /**
             * Cómo no me puedo mover de la cabeza por que s eme vuelan todos los niños,
             * LLamo a un ayudante y lo ubico en la cabeza o inicio
             */
            Node temp = this.head;
            /**
             * Creo un ciclo para recorrer la lista SE de principio a fin
             * llego al final cuando mi ayudante queda para en vacío (null)
             */
            while (temp != null) {
                /**\
                 * Para el ciclo para que no siga  contando niños
                 */
                if (cont == position - 1) {
                    break;
                }
                /**
                 * Ayudante pasa al siguinete nodo
                 */
                temp = temp.getNext();
                cont++;
            }
            //Ayudante va estar posicionado en la posición anterior
            Node nodeNew = new Node(boy);
            nodeNew.setNext(temp.getNext());
            temp.setNext(nodeNew);
            count++;
        }
    }

    public void invert() throws ListaSeException {
        if (this.head != null) {
            ListSE listTemp = new ListSE();
            /**
             * Cómo no me puedo mover de la cabeza por que s eme vuelan todos los niños,
             * LLamo a un ayudante y lo ubico en la cabeza o inicio
             */
            Node temp = this.head;

            /**
             * Creo un ciclo para recorrer la lista SE de principio a fin
             * llego al final cuando mi ayudante queda para en vacío (null)
             */
            while (temp != null) {
                /**
                 *Se crea una nueva lista y se invoca la fincion añádir al inicio para que la empiece a añadir los datos desde ahi
                 */
                listTemp.addToStart(temp.getData());
                /**
                 * Ayudante pasa al siguinete nodo
                 */
                temp = temp.getNext();

            }
            /**
             * Cabeza va a terminar va empezar siendo todos los datosn de la listaTemp
             */
            this.head = listTemp.getHead();
        }


    }

    /**
     * Metodo par contar los niños (metodo que esta distribuido en gran parte del codigo)
     *
     * @return
     */
    public int count() {
        /**
         * El contador enpieza en 0 y se queda en 0 si:
         */
        int count = 0;
        /**
         * el condicional de cabeza es igual a null
         */
        if (this.head != null) {
            /**
             * Cómo no me puedo mover de la cabeza por que s eme vuelan todos los niños,
             * LLamo a un ayudante y lo ubico en la cabeza o inicio
             */
            Node temp = this.head;
            /**
             * Creo un ciclo para recorrer la lista SE de principio a fin
             * llego al final cuando mi ayudante queda para en vacío (null)
             */
            while (temp != null) {
                /**\
                 * Se agraga uno mas al contador
                 */
                count++;
                /**
                 * Ayudante pasa al siguinete nodo
                 */
                temp = temp.getNext();
            }
        }
        return count;
    }

    /**
     * Metodo para concvertir la lista en un arreglo
     *
     * @return
     * @throws ListaSeException
     */

    public List<Boy> list() throws ListaSeException {
        if (this.head != null) {
            /**
             * Cómo no me puedo mover de la cabeza por que s eme vuelan todos los niños,
             * LLamo a un ayudante y lo ubico en la cabeza o inicio
             */
            Node temp = this.head;
            List<Boy> list = new ArrayList<>();
            /**
             * Creo un ccilo para recorrer la lista SE de principio a fin
             * llego al final cuando mi ayudante queda para en vacío (null)
             */
            while (temp != null) {
                /**
                 * Se connvierte la lista en un arreglo  y re agrega la informacion
                 *
                 */
                list.add(temp.getData());
                /**
                 * Ayuddante pasa al sigueinte nnodo
                 */
                temp = temp.getNext();
            }
            /**
             * Se regrsea de arreglo de nuevo a lista
             */
            return list;
        }
        throw new ListaSeException("No hay datos en la lista");
        //return null;

    }

    /**
     * Metodo para cambiar los Extremso de la lista
     *
     * @throws ListaSeException
     */
    public void changeXtremes() throws ListaSeException {
        if (this.head != null && this.head.getNext() != null) {
            Boy start = this.head.getData();
            Node temp = head;
            /**
             * Creo un ciclo para recorrer la lista SE de principio a fin
             * llego al ante penultimo
             */
            while (temp.getNext() != null) {

                /**
                 * Ayudante pasa al siguinete nodo
                 */
                temp = temp.getNext();
            }
            /**
             * Cabeza usa el constructor para sacar los datos
             * y darle los datos a aytudanta
             */
            this.head.setData(temp.getData());
            /**
             * El ayudante crea una nueva lista agragando los datos al inicio
             */
            temp.setData(start);

        } else {
            throw new ListaSeException("NO es posible ejecutar el cambio de extremos");
        }


    }

    /**
     * Metodo para eliminar un nodo/ niño de la Lista SE
     *
     * @param identification
     * @throws ListaSeException
     */

    public void delete(String identification) throws ListaSeException {
        if (this.head != null) {
            if (this.head.getData().getIdentification().equals(identification)) {
                this.head = this.head.getNext();
            } else {
                /**
                 * Cómo no me puedo mover de la cabeza por que s eme vuelan todos los niños,
                 * LLamo a un ayudante y lo ubico en la cabeza o inicio
                 */
                Node temp = this.head;
                /**
                 * Creo un ccilo para recorrer la lista SE de principio a fin
                 * llego al final cuando mi ayudante queda para en vacío (null)
                 */
                while (temp != null) {
                    /**
                     *Se rompe el ciclo cuando mi ayudante se pasa al sigueinte nodo
                     */
                    if (temp.getNext() != null &&
                            temp.getNext().getData().getIdentification().equals(identification)) {
                        break;
                    }
                    /**
                     * Ayudante pasa al siguinete nodo
                     */
                    temp = temp.getNext();
                }
                /** Temp va estar parado en el anterior al que debo eliminar o va a ser null
                 *
                 */
                if (temp != null) {
                    temp.setNext(temp.getNext().getNext());
                } else {
                    throw new ListaSeException("La identificación " + identification + " no existe en la lista");
                }
            }
        } else {
            throw new ListaSeException("NO hay datos en la lista");
        }
    }

    /**
     * Método que me busca en la lista simplemente enlazada, un niño a partir de la identificación
     * Si no encuentra el niño retorna vacío (null)
     *
     * @param identification Cédula, TI, CE , Sisben que identifica el niño que voy a buscar
     * @return El niño que encontré con todos sus datos
     */
    public Boy findByIdentification(String identification) {
        /**
         * Cómo no me puedo mover de la cabeza por que s eme vuelan todos los niños,
         * LLamo a un ayudante y lo ubico en la cabeza o inicio
         */
        Node temp = this.head;
        /**
         * Creo un ccilo para recorrer la lista SE de principio a fin
         * llego al final cuando mi ayudante queda para en vacío (null)
         */
        while (temp != null) {
            /**
             * Pregunto si el niño en el cual está ubicado mi ayudante es el de la identificación
             * que estoy buscando ingresado en el parámetro identificacion
             */
            if (temp.getData().getIdentification().equals(identification)) {
                /**
                 * Lo encontré y lo retorno de inmediato
                 * Finaliza mi método
                 */
                return temp.getData();
            }
            /**
             * Mi ayudante se pasa al siguiente nodo
             */
            temp = temp.getNext();
        }
        /**
         * Si llega a esta línea significa que no encontré el niño y retorno vacío
         */
        return null;
    }

    /**
     * Metod para verificar que la list este vacia
     *
     * @throws ListaSeException
     */
    public void validateListEmpty() throws ListaSeException {
        /**
         * Se hace un condicional para para saber si la cabeza esta agarrando a null
         */
        if (this.head == null) {
            throw new ListaSeException("No hay datos en la lista");
        }
    }

    /**
     * Metodo que muestra todos los hombres que estan en la lista
     * @param gender
     * @return
     * @throws ListaSeException
     */
    public ListSE getListSeBoysByGender(String gender) throws ListaSeException {
        validateListEmpty();
        /**
         * Cómo no me puedo mover de la cabeza por que s eme vuelan todos los niños,
         * LLamo a un ayudante y lo ubico en la cabeza o inicio
         */
        Node temp = this.head;
        /**
         * Se crea una lista nueva en la cual se va agragar la lista de generos
         */
        ListSE listTemp = new ListSE();
        /**
         * Creo un ciclo para recorrer la lista SE de principio a fin
         * llego al final cuando mi ayudante queda para en vacío (null)
         */
        while (temp != null) {
            /**
             * condicional el cual el ayudante saca informacion del
             * genero, datos, nombre....
             */
            if (temp.getData().getGender().name().equals(gender)) {
                /**
                 * se añaden los datos a la lista
                 */
                listTemp.add(temp.getData());
            }
            /**
             * El ayudante queda en el final
             */
            temp = temp.getNext();
        }
        return listTemp;
    }

    public void variantBoys() throws ListaSeException {
        validateListEmpty();
        ListSE kids = this.getListSeBoysByGender("MASCULINO");
        ListSE girls = this.getListSeBoysByGender("FEMENINO");
        ListSE minList = null;
        ListSE maxList = null;
        if (kids.getCount() > girls.getCount()) {
            minList = girls;
            maxList = kids;
        } else {
            minList = kids;
            maxList = girls;
        }
        Node temp = minList.getHead();
        int pos = 2;
        while (temp != null) {
            maxList.addByPosition(temp.getData(), pos);
            pos = pos + 2;
            temp = temp.getNext();
        }
        this.head = maxList.getHead();

    }



    /**
     * Método que recibe el código de una ciudad y retorna la cantidad de niños
     */
    public int getCountBoysByLocation(String code) {
        /**
         * El ayudante llega a la cabeza y la extrae
         */
        Node temp = this.getHead();
        /**
         * el cotador empieza en 0
         */
        int count = 0;
        /**
         * Creo un ciclo para recorrer la lista SE de principio a fin
         * llego al final cuando mi ayudante queda para en vacío (null)
         */
        while (temp != null) {
            /**
             * Condicianal el cual saca los datos de ciudaad que se pidieron y
             * retorna al contador
             */
            if (temp.getData().getLocation().getCode().equals(code)) {
                count++;
            }
            /**
             * El ayudante pasa al siguiente
             */
            temp = temp.getNext();
        }
        return count;

    }

}

