package negocio;

/**
 *
 * @author Sergio Vacas
 */
public class Foto {
    
    private int idFoto;
    private int idProducto;
    private int idUsuario;
    private String URLFoto;
    
    public Foto() {
    }

    public int getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(int idFoto) {
        this.idFoto = idFoto;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getURLFoto() {
        return URLFoto;
    }

    public void setURLFoto(String URLFoto) {
        this.URLFoto = URLFoto;
    }
}
