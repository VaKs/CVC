package negocio;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
/**
 *
 * @author Sergio Vacas
 */
public class Categoria {
    private int idCategoria;
    private String nombreCategoria;
    private String descripcionCategoria;
    private Date fechaAlta;
    private Set<Producto> productos = new HashSet<Producto>();
    //Set<Subcategoria> Subcategorias = new HashSet<Subcategoria>();

    public Categoria() {
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public Set<Producto> getProductos() {
        return productos;
    }

    public void setAnuncios(Set<Producto> productos) {
        this.setProductos(productos);
    }

    public String getDescripcionCategoria() {
        return descripcionCategoria;
    }

    public void setDescripcionCategoria(String descripcionCategoria) {
        this.descripcionCategoria = descripcionCategoria;
    }

    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
}
