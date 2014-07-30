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
public class RolController{

    @Autowired
    RolDAO rolDAO;
    @Autowired
    UsuarioDAO usuarioDAO;

    @RequestMapping({"/rol/createforinsert.html"})
    public ModelAndView createForInsert(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("utf-8");
        }
        try{
            HttpSession session = request.getSession(true);
            if (session.getAttribute("login") != null) {
                Usuario usuario = usuarioDAO.getUsuarioFromLogin((String)session.getAttribute("login"));
                if (usuario.getRol().getIdRol() == 1) {
                    Rol rol = rolDAO.create();

                    model.put("rol", rol);
                    model.put("actionMantenimiento", AccionMantenimiento.INSERT);
                    viewName = "administracion/rol";
                    return new ModelAndView(viewName, model);
                }
            }
        } catch (BusinessException be) {
                    model.put("businessException", be.getBusinessMessages());
                    model.put("actionMantenimiento", AccionMantenimiento.INSERT);
                    viewName = "redirect:/rol.html";
                    return new ModelAndView(viewName, model);
                
            }

        viewName = "tienda/login";
        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/rol/delete.html"})
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("utf-8");
        } 
        try {
            HttpSession session = request.getSession(true);
            if (session.getAttribute("login") != null) {
                Usuario usuario = usuarioDAO.getUsuarioFromLogin((String)session.getAttribute("login"));
                if (usuario.getRol().getIdRol() == 1) {

                    int idCiclo = Integer.parseInt(request.getParameter("idRol"));

                        rolDAO.delete(idCiclo);
                        viewName = "redirect:/roles.html";
                        return new ModelAndView(viewName, model);
                    }
            }
        } catch (BusinessException be) {
                    model.put("businessException", be.getBusinessMessages());
                    model.put("actionMantenimiento", AccionMantenimiento.DELETE);
                    viewName = "redirect:/rol.html";
                    return new ModelAndView(viewName, model);
                }

        viewName = "tienda/login";
        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/rol/insert.html"})
    public ModelAndView insert(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("utf-8");
        }
        HttpSession session = request.getSession(true);
        try {
            if (session.getAttribute("login") != null) {
                Usuario usuario = usuarioDAO.getUsuarioFromLogin((String)session.getAttribute("login"));
                if (usuario.getRol().getIdRol() == 1) {

                    String nombre = request.getParameter("nombre");
                    Rol rol = rolDAO.create();
                    rol.setNombre(nombre);

                        rolDAO.save(rol);
                        viewName = "redirect:/roles.html";
                        return new ModelAndView(viewName, model);
                }
            }
        }catch (BusinessException be) {
                    model.put("businessException", be.getBusinessMessages());
                    model.put("actionMantenimiento", AccionMantenimiento.INSERT);
                    viewName = "redirect:/rol.html";
                    return new ModelAndView(viewName, model);
                }
            
        
        viewName = "tienda/login";
        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/rol/read.html"})
    public ModelAndView read(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("utf-8");
        }
        HttpSession session = request.getSession(true);
        try{
            if (session.getAttribute("login") != null) {
                Usuario usuario = usuarioDAO.getUsuarioFromLogin((String)session.getAttribute("login"));
                if (usuario.getRol().getIdRol() == 1) {
                    int idRol = Integer.parseInt(request.getParameter("idRol"));

                    Rol rol = rolDAO.get(idRol);

                    model.put("rol", rol);
                    model.put("actionMantenimiento", AccionMantenimiento.READ);
                    viewName = "administracion/rol";
                    return new ModelAndView(viewName, model);
                }
            }
        }catch (BusinessException be) {
                    model.put("businessException", be.getBusinessMessages());
                    model.put("actionMantenimiento", AccionMantenimiento.READ);
                    viewName = "redirect:/rol.html";
                    return new ModelAndView(viewName, model);
                }
        viewName = "tienda/login";
        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/rol/readfordelete.html"})
    public ModelAndView readForDelete(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("utf-8");
        }
        HttpSession session = request.getSession(true);
        try{
            if (session.getAttribute("login") != null) {
                Usuario usuario = usuarioDAO.getUsuarioFromLogin((String)session.getAttribute("login"));
                if (usuario.getRol().getIdRol() == 1) {

                    int idRol = Integer.parseInt(request.getParameter("idRol"));

                    Rol rol = rolDAO.get(idRol);
                    model.put("rol", rol);
                    model.put("actionMantenimiento", AccionMantenimiento.DELETE);
                    viewName = "administracion/rol";
                    return new ModelAndView(viewName, model);
                }
            }
        }catch (BusinessException be) {
                    model.put("businessException", be.getBusinessMessages());
                    model.put("actionMantenimiento", AccionMantenimiento.DELETE);
                    viewName = "redirect:/rol.html";
                    return new ModelAndView(viewName, model);
                }
        viewName = "tienda/login";
        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/rol/readforupdate.html"})
    public ModelAndView readForUpdate(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("utf-8");
        }
        HttpSession session = request.getSession(true);
        try{
            if (session.getAttribute("login") != null) {
                Usuario usuario = usuarioDAO.getUsuarioFromLogin((String)session.getAttribute("login"));
                if (usuario.getRol().getIdRol() == 1) {

                    int idRol = Integer.parseInt(request.getParameter("idRol"));

                    Rol rol = rolDAO.get(idRol);

                    model.put("rol", rol);
                    model.put("actionMantenimiento", AccionMantenimiento.UPDATE);
                    viewName = "administracion/rol";

                    return new ModelAndView(viewName, model);
                }
            }
        }catch (BusinessException be) {
                    model.put("businessException", be.getBusinessMessages());
                    model.put("actionMantenimiento", AccionMantenimiento.UPDATE);
                    viewName = "redirect:/rol.html";
                    return new ModelAndView(viewName, model);
                }
        viewName = "tienda/login";
        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/rol/update.html"})
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("utf-8");
        }
        HttpSession session = request.getSession(true);
        try{
            if (session.getAttribute("login") != null) {
                Usuario usuario = usuarioDAO.getUsuarioFromLogin((String)session.getAttribute("login"));
                if (usuario.getRol().getIdRol() == 1) {

                    int idRol = Integer.parseInt(request.getParameter("idRol"));
                    String nombre = request.getParameter("nombre");

                    Rol rol = rolDAO.get(idRol);
                    rol.setIdRol(idRol);
                    rol.setNombre(nombre);


                        rolDAO.update(rol);
                        viewName = "redirect:/roles.html";
                        return new ModelAndView(viewName, model);
                    }
            }
        } catch (BusinessException be) {
                    model.put("businessException", be.getBusinessMessages());
                    model.put("actionMantenimiento", AccionMantenimiento.UPDATE);
                    viewName = "redirect:/rol.html";
                    return new ModelAndView(viewName, model);
                }
            
        
        viewName = "tienda/login";
        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/roles.html"})
    public ModelAndView roles(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("utf-8");
        }
        HttpSession session = request.getSession(true);
        try{
            if (session.getAttribute("login") != null) {
                Usuario usuario = usuarioDAO.getUsuarioFromLogin((String)session.getAttribute("login"));
                if (usuario.getRol().getIdRol() == 1) {

                    Map<String, Object> filter = new HashMap<String, Object>();

                    HttpSession httpSession = request.getSession(true);

                    if ((UtilController.isEmpty(request.getParameter("search_idRol")) == false) || (UtilController.isEmpty(request.getParameter("search_nombre")) == false)) {
                        //Viene por la URL
                        filter.put("idRol", UtilController.parseInt(request.getParameter("search_idRol")));
                        filter.put("nombre", request.getParameter("search_nombre").trim());
                    } else {
                        //De la sesión
                        filter.put("idRol", UtilController.parseInt((String) httpSession.getAttribute("search_idRol")));
                        filter.put("nombre", httpSession.getAttribute("search_nombre"));
                    }

                    httpSession.setAttribute("search_idRol", filter.get("idRol"));
                    httpSession.setAttribute("search_nombre", filter.get("nombre"));

                    List<Rol> roles = rolDAO.search(filter);

                    model.put("roles", roles);
                    model.put("search_idRol", filter.get("idRol"));
                    model.put("search_nombre", filter.get("nombre"));
                    viewName = "administracion/roles";

                    return new ModelAndView(viewName, model);
                }
            }
        }catch (BusinessException be) {
                    model.put("businessException", be.getBusinessMessages());
                    model.put("actionMantenimiento", AccionMantenimiento.READ);
                    viewName = "redirect:/roles.html";
                    return new ModelAndView(viewName, model);
                }
        viewName = "tienda/login";
        return new ModelAndView(viewName, model);
    }
}
