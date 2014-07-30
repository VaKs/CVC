package presentacion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Contiene los datos de una página.
 * A esta clase se le pasan todos los datos y esta clase obtiene los datos de la página que se solicita.
 * @author Lorenzo González
 */
public class Page {

    List totalData=new ArrayList();
    List content = null;
    int size;
    int number;

    /**
     * Crea el objeto
     *
     * @param data La lista con todos los datos de TODAS las páginas
     * @param pageSize Tamaño de la página
     * @param number El Nº de página que queremos retornar. Su valor es de 0 a
     * getTotalPages()-1
     */
    public Page(List totalData, int pageSize, int number) {
        if (totalData==null) {
            throw new RuntimeException("El argumento 'totalData' no puede ser null");
        }
        if (pageSize<=0) {
            throw new RuntimeException("El argumento 'pageSize' no puede ser menor o igual a 0");
        }
        if (number<0) {
            throw new RuntimeException("El argumento 'number' no puede ser menor que 0");
        }        
        
        this.totalData.addAll(totalData);
        this.size = pageSize;
        this.number = number;
    }

    public List getContent() {
        if (this.content == null) {
            if (getTotalElements() == 0) {
                //Si no hay filas simplemente retornamos la lista completa pq está vacía
                this.content = this.totalData;
            } else {
                int lastRow = (getNumber() + 1) * getSize();
                if (lastRow > getTotalElements()) {
                    //Si no hay suficientes filas para llenar una página, se retorna la última fila y ya está.
                    lastRow = getTotalElements();
                }

                int firstRow = (getNumber()) * getSize();

                if (firstRow > lastRow) {
                    firstRow = lastRow;
                }
                this.content = this.totalData.subList(firstRow, lastRow);
            }
        }

        return Collections.unmodifiableList(content);

    }

    /**
     * Nº actual de la página que se está retornado.
     *
     * @return Nº actual de la página que se retorna. Vale de 0 a
     * getTotalPages()-1
     */
    public int getNumber() {
        return number;
    }

    /**
     * Obtiene el nº de páginas totales
     *
     * @return Nª de página de datos. Si no hay ninguna fila retornará 0.
     */
    public int getTotalPages() {
        int numPages;

        if (getTotalElements() == 0) {
            numPages = 0;
        } else {
            numPages = (int) (Math.ceil(((double) getTotalElements()) / ((double) getSize())));
        }

        return numPages;
    }

    /**
     * Obtiene el nº total de elementos de todas las páginas
     *
     * @return Nª total de filas
     */
    public int getTotalElements() {
        if (this.totalData==null) {
            return 0;
        } else {
            return this.totalData.size();
        }
    }

    /**
     * Obtiene el nº total de elementos de esta página
     *
     * @return Nª total de filas
     */
    public int getNumberOfElements() {
        if (this.getContent()==null) {
            return 0;
        } else {
            return this.getContent().size();
        }
    }

    /**
     * Obtiene el tamaño de la página
     *
     * @return Nº de elementos por página
     */
    public int getSize() {
        return size;
    }
}
