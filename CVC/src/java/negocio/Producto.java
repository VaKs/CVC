package negocio;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Sergio Vacas
 */
public class Producto {
    
    private int idProducto;
    private Usuario usuario;
    private String nombreProducto;
    private String descripcionProducto;
    private int permiteMostrar; //si el usuario quiere que sea visible el anuncio.
    private int permiteVender;
    private int permiteCambiar;
    private double precioEuros;
    private Categoria categoria;
    private String nombreCategoria;
    private Date fechaAlta;
    private Set<Categoria> categoriasParaCambio = new HashSet<Categoria>(); //lista de categoria que le interesan al propietario del producto. 
    private Set<Foto> fotos = new HashSet<Foto>(); //lista de urls de las fotos.
    
    public Producto() {
    }

    public int getIdProducto() {
        return idProducto;
    }
    
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Set<Foto> getFotos() {
        return fotos;
    }

    public void setFotos(Set<Foto> fotos) {
        this.setFotos(fotos);
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public int isPermiteMostrar() {
        return permiteMostrar;
    }

    public void setPermiteMostrar(int permiteMostrar) {
        this.permiteMostrar = permiteMostrar;
    }

    public int isPermiteVender() {
        return permiteVender;
    }

    /**
     * @param permiteVender the permiteVender to set
     */
    public void setPermiteVender(int permiteVender) {
        this.permiteVender = permiteVender;
    }

    /**
     * @return the permiteCambiar
     */
    public int isPermiteCambiar() {
        return permiteCambiar;
    }

    /**
     * @param permiteCambiar the permiteCambiar to set
     */
    public void setPermiteCambiar(int permiteCambiar) {
        this.permiteCambiar = permiteCambiar;
    }

    public double getPrecioEuros() {
        return precioEuros;
    }

    public void setPrecioEuros(double precioEuros) {
        this.precioEuros = precioEuros;
    }

    public Set<Categoria> getCategoriasParaCambio() {
        return categoriasParaCambio;
    }

    public void setCategoriasParaCambio(Set<Categoria> categoriasParaCambio) {
        this.setCategoriasParaCambio(categoriasParaCambio);
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getNombreCategoria() {
        return categoria.getNombreCategoria();
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }
}
