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
public class UsuarioController{

    @Autowired
    UsuarioDAO usuarioDAO;
    @Autowired
    RolDAO rolDAO;

    @RequestMapping({"/usuario/createforinsert.html"})
    public ModelAndView createForInsert(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("utf-8");
        }
        HttpSession session = request.getSession(true);
        if (session.getAttribute("login") != null) {
            try{
            Usuario usuarioEnSesion = usuarioDAO.getUsuarioFromLogin((String)session.getAttribute("login"));
            if (usuarioEnSesion.getRol().getIdRol() == 1) {
                
                Usuario usuario = usuarioDAO.create();
                List<Rol> roles = rolDAO.search(null);

                model.put("usuario", usuario);
                model.put("roles", roles);
                model.put("actionMantenimiento", AccionMantenimiento.INSERT);
                viewName = "administracion/usuario";
                return new ModelAndView(viewName, model);
                }
            }
            catch (BusinessException be) {
                    model.put("businessException", be.getBusinessMessages());
                    model.put("actionMantenimiento", AccionMantenimiento.INSERT);
                    viewName = "redirect:/usuario.html";
                    return new ModelAndView(viewName, model);
                
            }
        }
        viewName = "tienda/login";
        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/usuario/delete.html"})
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("utf-8");
        }
        HttpSession session = request.getSession(true);
        if (session.getAttribute("login") != null) {
            try {
            Usuario usuarioEnSesion = usuarioDAO.getUsuarioFromLogin((String)session.getAttribute("login"));
            if (usuarioEnSesion.getRol().getIdRol() == 1) {
                int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
                           
                            Usuario usuario=usuarioDAO.get(idUsuario);
                            model.put("usuario", usuario);
                            List<Rol> roles = rolDAO.search(null);
                            model.put("roles", roles);

                    usuarioDAO.delete(idUsuario);
                    viewName = "redirect:/usuarios.html";
                    return new ModelAndView(viewName, model);
                }
             } catch (BusinessException be) {
                    model.put("businessException", be.getBusinessMessages());
                    model.put("actionMantenimiento", AccionMantenimiento.DELETE);
                    viewName = "administracion/usuario";
                    return new ModelAndView(viewName, model);
                }
                
        }
        viewName = "tienda/login";
        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/usuario/insert.html"})
    public ModelAndView insert(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName="";

        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("utf-8");
        }
        HttpSession session = request.getSession(true);
        if (session.getAttribute("login") != null) {
           try {
 
            Usuario usuarioEnSesion = usuarioDAO.getUsuarioFromLogin((String)session.getAttribute("login"));
            if (usuarioEnSesion.getRol().getIdRol() == 1) {

                int idRol = Integer.parseInt(request.getParameter("idRol"));


                String provincia = request.getParameter("provincia");
                String login = request.getParameter("login");
                String direccion = request.getParameter("direccion");
                String pais = request.getParameter("pais");
                String correo = request.getParameter("correo");
                String telefono = request.getParameter("telefono");
                String password = request.getParameter("password");
                String ciudad = request.getParameter("ciudad");
                String apellidos = request.getParameter("apellidos");
                String nombre = request.getParameter("nombre");
               
                
                Usuario usuario = usuarioDAO.create();
                Rol rol = rolDAO.get(idRol);
                usuario.setNombre(nombre);
                usuario.setRol(rol);
                usuario.setApellidos(apellidos);
                usuario.setCiudad(ciudad);
                usuario.setCorreo(correo);
                usuario.setDireccion(direccion);
                usuario.setLogin(login);
                usuario.setTelefono(telefono);
                usuario.setPais(pais);
                usuario.setProvincia(provincia);
                usuario.setPassword(password);
                
                model.put("usuario", usuario);
                List<Rol> roles = rolDAO.search(null);
                model.put("roles", roles);

                usuarioDAO.save(usuario);
                viewName = "redirect:/usuarios.html";
                    
                    
              }
                }catch (BusinessException be) {
                    model.put("businessException", be.getBusinessMessages());
                    model.put("actionMantenimiento", AccionMantenimiento.INSERT);
                  
                    viewName = "administracion/usuario";
                    return new ModelAndView(viewName, model);

                
                }
                     return new ModelAndView(viewName, model);
           
        }

        viewName = "tienda/login";
        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/usuario/read.html"})
    public ModelAndView read(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("utf-8");
        }
        HttpSession session = request.getSession(true);
        if (session.getAttribute("login") != null) {
            try{
            Usuario usuarioEnSesion = usuarioDAO.getUsuarioFromLogin((String)session.getAttribute("login"));
            if (usuarioEnSesion.getRol().getIdRol() == 1) {

                int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
                    Usuario usuario = usuarioDAO.get(idUsuario);
                    List<Rol> roles = rolDAO.search(null);

                    model.put("usuario", usuario);
                    model.put("roles", roles);
                    model.put("actionMantenimiento", AccionMantenimiento.READ);
                    viewName = "administracion/usuario";
                    return new ModelAndView(viewName, model);
                }
                }
                catch (BusinessException be){
                    model.put("businessException", be.getBusinessMessages());
                    model.put("actionMantenimiento", AccionMantenimiento.READ);
                    viewName = "administracion/usuario";
                    return new ModelAndView(viewName, model);
                }
            
        }
        viewName = "tienda/login";
        return new ModelAndView(viewName, model);

    }

    @RequestMapping({"/usuario/readfordelete.html"})
    public ModelAndView readForDelete(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("utf-8");
        }

        HttpSession session = request.getSession(true);
        if (session.getAttribute("login") != null) {
            try{
            Usuario usuarioEnSesion = usuarioDAO.getUsuarioFromLogin((String)session.getAttribute("login"));
            if (usuarioEnSesion.getRol().getIdRol() == 1) {
                int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
                Usuario usuario = usuarioDAO.get(idUsuario);
                List<Rol> roles = rolDAO.search(null);

                model.put("usuario", usuario);
                model.put("roles", roles);
                model.put("actionMantenimiento", AccionMantenimiento.DELETE);
                viewName = "administracion/usuario";
                return new ModelAndView(viewName, model);
                }
                }
                catch (BusinessException be) {
                    model.put("businessException", be.getBusinessMessages());
                    model.put("actionMantenimiento", AccionMantenimiento.DELETE);
                    viewName = "redirect:/usuario.html";
                    return new ModelAndView(viewName, model);
                }
            
        }
        viewName = "tienda/login";
        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/usuario/readforupdate.html"})
    public ModelAndView readForUpdate(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("utf-8");
        }
        HttpSession session = request.getSession(true);
        if (session.getAttribute("login") != null) {
                try{
                    Usuario usuarioEnSesion = usuarioDAO.getUsuarioFromLogin((String)session.getAttribute("login"));
                    if (usuarioEnSesion.getRol().getIdRol() == 1) {
                        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
                        Usuario usuario = usuarioDAO.get(idUsuario);
                        List<Rol> roles = rolDAO.search(null);

                        model.put("usuario", usuario);
                        model.put("roles", roles);
                        model.put("actionMantenimiento", AccionMantenimiento.UPDATE);
                        viewName = "administracion/usuario";

                        return new ModelAndView(viewName, model);
                    }
                }catch (BusinessException be) {
                    model.put("businessException", be.getBusinessMessages());
                    model.put("actionMantenimiento", AccionMantenimiento.UPDATE);
                    viewName = "redirect:/usuario.html";
                    return new ModelAndView(viewName, model);
                
            }
        }

        viewName = "tienda/login";
        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/usuario/update.html"})
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("utf-8");
        }
        HttpSession session = request.getSession(true);
        if (session.getAttribute("login") != null) {
           try {
            
           Usuario usuarioEnSesion = usuarioDAO.getUsuarioFromLogin((String)session.getAttribute("login"));
            if (usuarioEnSesion.getRol().getIdRol() == 1) {

                int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
                Usuario usuario = usuarioDAO.get(idUsuario);

                int idRol = Integer.parseInt(request.getParameter("idRol"));
                Rol rol = rolDAO.get(idRol);

                String nombre = request.getParameter("nombre");
                String provincia = request.getParameter("provincia");
                String login = request.getParameter("login");
                String direccion = request.getParameter("direccion");
                String pais = request.getParameter("pais");
                String correo = request.getParameter("correo");
                String telefono = request.getParameter("telefono");
                String password = request.getParameter("password");
                String ciudad = request.getParameter("ciudad");
                String apellidos = request.getParameter("apellidos");
                
                usuario.setIdUsuario(idUsuario);
                usuario.setNombre(nombre);
                usuario.setRol(rol);
                usuario.setApellidos(apellidos);
                usuario.setCiudad(ciudad);
                usuario.setCorreo(correo);
                usuario.setDireccion(direccion);
                usuario.setLogin(login);
                usuario.setTelefono(telefono);
                usuario.setPais(pais);
                usuario.setProvincia(provincia);
                usuario.setPassword(password);
                            model.put("usuario", usuario);
                            List<Rol> roles = rolDAO.search(null);
                            model.put("roles", roles);

                    usuarioDAO.update(usuario);
                    viewName = "redirect:/usuarios.html";
                    return new ModelAndView(viewName, model);
                }
                }
                catch (BusinessException be) {
                    model.put("businessException", be.getBusinessMessages());
                    model.put("actionMantenimiento", AccionMantenimiento.UPDATE);
                    viewName = "administracion/usuario";
                    return new ModelAndView(viewName, model);
                }
            
        }

        viewName = "tienda/login";
        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/usuarios.html"})
    public ModelAndView usuarios(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("utf-8");
        }

        Map<String, Object> filter = new HashMap<String, Object>();

        HttpSession httpSession = request.getSession(true);

        if ((UtilController.isEmpty(request.getParameter("search_idUsuario")) == false) || (UtilController.isEmpty(request.getParameter("search_nombre")) == false)) {
            //Viene por la URL
            filter.put("idUsuario", UtilController.parseInt(request.getParameter("search_idUsuario")));
            filter.put("nombre", request.getParameter("search_nombre").trim());
        } else {
            //De la sesión
            filter.put("idUsuario", UtilController.parseInt((String) httpSession.getAttribute("search_idUsuario")));
            filter.put("nombre", httpSession.getAttribute("search_nombre"));
        }

        httpSession.setAttribute("search_idUsuario", filter.get("idUsuario"));
        httpSession.setAttribute("search_nombre", filter.get("nombre"));
        try{
            List<Usuario> usuarios = usuarioDAO.search(filter);

            model.put("usuarios", usuarios);
            model.put("search_idUsuario", filter.get("idUsuario"));
            model.put("search_nombre", filter.get("nombre"));


            if (httpSession.getAttribute("login") != null) {
                Usuario usuario = usuarioDAO.getUsuarioFromLogin((String)httpSession.getAttribute("login"));

                if (usuario.getRol().getIdRol() == 1) {
                    viewName = "administracion/usuarios";
                    return new ModelAndView(viewName, model);
                }
            }
        }catch(BusinessException be){
            model.put("businessException", be.getBusinessMessages());
            viewName="redirect:/usuarios.html";
            return new  ModelAndView(viewName, model);
        }
        viewName = "tienda/login";
        return new ModelAndView(viewName, model);
    }
}
