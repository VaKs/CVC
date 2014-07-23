
package negocio;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Sergio Vacas
 */
public class Rol {

    private int idRol;
    private String nombre;
    private String descripcionRol;
    private Date fechaAlta;
    private Set<Usuario> usuarios = new HashSet<Usuario>();
    
    public Rol() {
    }

    public int getIdRol() {
        return idRol;
    }
    
    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getDescripcionRol() {
        return descripcionRol;
    }

    public void setDescripcionRol(String descripcionRol) {
        this.descripcionRol = descripcionRol;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
