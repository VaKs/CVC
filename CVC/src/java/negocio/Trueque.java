package negocio;

import java.io.Serializable;
import java.util.Date;

public class Trueque implements Serializable {

    private int idTrueque;
    private String descripcion;
    private Producto articulo1;
    private Producto articulo2;
    private Usuario cliente1;
    private Usuario cliente2;
    private Date fechaTrueque;

    
    public Trueque(){
        
    }
    
    public int getIdTrueque() {
        return idTrueque;
    }

    public void setIdTrueque(int idTrueque) {
        this.idTrueque = idTrueque;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Producto getArticulo1() {
        return articulo1;
    }

    public void setArticulo1(Producto articulo1) {
        this.articulo1 = articulo1;
    }

    public Producto getArticulo2() {
        return articulo2;
    }

    public void setArticulo2(Producto articulo2) {
        this.articulo2 = articulo2;
    }

    public Usuario getCliente1() {
        return cliente1;
    }

    public void setCliente1(Usuario cliente1) {
        this.cliente1 = cliente1;
    }

    public Usuario getCliente2() {
        return cliente2;
    }

    public void setCliente2(Usuario cliente2) {
        this.cliente2 = cliente2;
    }

    public Date getFechaTrueque() {
        return fechaTrueque;
    }

    public void setFechaTrueque(Date fechaTrueque) {
        this.fechaTrueque = fechaTrueque;
    }
}
