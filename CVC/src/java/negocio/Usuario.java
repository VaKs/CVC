package negocio;

import java.util.Date;
import org.hibernate.Hibernate;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Sergio Vacas
 */
public class Usuario {
    private int idUsuario;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String login;
    private String password;
    private String correo;
    private String pais;
    private String provincia;
    private String ciudad;
    private String direccion;
    private Rol rol;
    private Date fechaAlta;

    public Usuario() {
    }


    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }
    
    @Autowired
    PasswordEncryptor passwordEncryptor;
    
    public void setPassword(String password) {
        password = passwordEncryptor.encryptPassword(password);
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (Hibernate.getClass(this) != Hibernate.getClass(obj)) {
            return false;
        }

        Usuario usuario2 = (Usuario) obj;
        Object usuario1Login = getLogin();
        Object usuario2Login = usuario2.getLogin();
        if (usuario1Login == null) {
            if (usuario2Login == null) {
                return true;
            } else {
                return false;
            }
        } else if (usuario1Login.equals(usuario2Login) == true) {
            return true;
        } else {
            return false;
        }
    }
}
