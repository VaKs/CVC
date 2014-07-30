package presentacion;

import datos.BusinessException;
import datos.RolDAO;
import datos.UsuarioDAO;
import negocio.Rol;
import negocio.Usuario;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Sergio Vacas
 */
@Controller
public class MisDatosController{

    @Autowired
    UsuarioDAO usuarioDAO;
    @Autowired
    RolDAO rolDAO;

    @RequestMapping({"/misDatos/readforupdate.html"})
    public ModelAndView readForUpdate(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("utf-8");
        }
        HttpSession session = request.getSession(true);
        if (session.getAttribute("login") != null) {
                try{
                    int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
                    Usuario usuario = usuarioDAO.get(idUsuario);
                    List<Rol> roles = rolDAO.search(null);

                    model.put("usuario", usuario);
                    model.put("roles", roles);
                    model.put("actionMantenimiento", AccionMantenimiento.UPDATE);
                    viewName = "tienda/misDatos";

                    return new ModelAndView(viewName, model);
                } catch (BusinessException be) {
                    model.put("businessException", be.getBusinessMessages());
                    model.put("actionMantenimiento", AccionMantenimiento.UPDATE);
                    viewName = "redirect:/misDatos.html";
                    return new ModelAndView(viewName, model);
                
            }
        }

        viewName = "tienda/login";
        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/misDatos/updateMisDatos.html"})
    public ModelAndView updateMisDatos(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("utf-8");
        }
        HttpSession session = request.getSession(true);
        if (session.getAttribute("login") != null) {

            
                try {
                    Usuario usuarioAntiguo = usuarioDAO.getUsuarioFromLogin((String)session.getAttribute("login"));
                    int idRol = usuarioAntiguo.getRol().getIdRol();
                    Rol rol = rolDAO.get(idRol);
                    
                    int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
                    Usuario usuarioNuevo = usuarioDAO.get(idUsuario);
                    
                    String nombre = request.getParameter("nombre");
                    String provincia = request.getParameter("provincia");
                    String login = request.getParameter("login");
                    String direccion = request.getParameter("direccion");
                    String pais = request.getParameter("pais");
                    String correo = request.getParameter("correo");
                    String telefono = request.getParameter("telefono");
                    String password = usuarioAntiguo.getPassword();
                    String ciudad = request.getParameter("ciudad");
                    String apellidos = request.getParameter("apellidos");


                    usuarioNuevo.setIdUsuario(idUsuario);
                    usuarioNuevo.setNombre(nombre);
                    usuarioNuevo.setRol(rol);
                    usuarioNuevo.setApellidos(apellidos);
                    usuarioNuevo.setCiudad(ciudad);
                    usuarioNuevo.setCorreo(correo);
                    usuarioNuevo.setDireccion(direccion);
                    usuarioNuevo.setLogin(login);
                    usuarioNuevo.setTelefono(telefono);
                    usuarioNuevo.setPais(pais);
                    usuarioNuevo.setProvincia(provincia);
                    usuarioNuevo.setPassword(password);
                            model.put("usuario", usuarioNuevo);
                            List<Rol> roles = rolDAO.search(null);
                            model.put("roles", roles);

                    usuarioDAO.update(usuarioNuevo);
                    
                    viewName = "redirect:/misDatos/readforupdate.html?idUsuario="+idUsuario;
                    return new ModelAndView(viewName, model);
                } catch (BusinessException be) {
                    model.put("businessException", be.getBusinessMessages());
                    model.put("actionMantenimiento", AccionMantenimiento.UPDATE);
                    viewName = "tienda/misDatos";
                    return new ModelAndView(viewName, model);
                } catch (Exception ex) {
                    throw new RuntimeException("MIRA A VER QUE:.... El idUsuario no es válido:"+request.getParameter("idUsuario")+" o...: "+ex);
                }
            
        }

        viewName = "tienda/login";
        return new ModelAndView(viewName, model);
    }

}
