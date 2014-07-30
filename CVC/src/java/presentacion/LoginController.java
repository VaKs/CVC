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
public class LoginController{

    @Autowired
    UsuarioDAO usuarioDAO;
    @Autowired
    RolDAO rolDAO;

    @RequestMapping({"/login.html"})
    public ModelAndView createForLogin(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;
        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("utf-8");
        }
         try {
           Usuario usuario = usuarioDAO.create();
            List<Rol> roles = rolDAO.search(null);

            model.put("usuario", usuario);
            model.put("roles", roles);
            viewName = "tienda/login";

        } catch (BusinessException be) {
            model.put("businessException", be.getBusinessMessages());
            viewName = "tienda/login";
        }            
         return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/loguearse/salir.html"})
    public ModelAndView salir(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("utf-8");
        }
        HttpSession session = request.getSession(true);
        session.invalidate();
        viewName = "redirect:/index.html";
        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/loguearse/login.html"})
    public ModelAndView loguearse(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;
        try { 
            
        String password = request.getParameter("password");
        String login = request.getParameter("login");

            Usuario usuario = usuarioDAO.getUsuarioFromLogin(login);

            if (request.getCharacterEncoding() == null) {
                request.setCharacterEncoding("utf-8");
            }
            if (usuario == null) {
                viewName = "redirect:/login.html";
                return new ModelAndView(viewName, model);
            }
    
            if (!usuario.checkPassword(password)) {
                viewName = "redirect:/login.html";
                return new ModelAndView(viewName, model);
            }
            HttpSession session = request.getSession(true);
            session.setAttribute("login", login);

            model.put("usuario", usuario);
            int idRolLogin = usuario.getRol().getIdRol();

            if (idRolLogin == 1) {
                viewName = "redirect:/admin.html";
                return new ModelAndView(viewName, model);
            }
            viewName = "redirect:/index.html";
            return new ModelAndView(viewName, model);
            
        } catch (BusinessException be) {
            model.put("businessException", be.getBusinessMessages());
            viewName = "redirect:/index.html";
            return new ModelAndView(viewName, model);
        }
    }

    @RequestMapping({"/loguearse/porcuenta.html"})
    public ModelAndView loguearseporcuenta(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();

        String viewName;
        String password = request.getParameter("password");
        String login = request.getParameter("login");

        try {
            Usuario usuario = usuarioDAO.getUsuarioFromLogin(login);

            if (request.getCharacterEncoding() == null) {
                request.setCharacterEncoding("utf-8");
            }
            if (usuario == null) {
                viewName = "redirect:/login.html";
                return new ModelAndView(viewName, model);
            }

            if (!usuario.checkPassword(password)) {
                viewName = "redirect:/login.html";
                return new ModelAndView(viewName, model);
            }
            HttpSession session = request.getSession(true);
            session.setAttribute("login", login);
            model.put("usuario", usuario);
            int idRolLogin = usuario.getRol().getIdRol();

            if (idRolLogin == 1) {
                viewName = "redirect:/admin.html";
                return new ModelAndView(viewName, model);
            }

            viewName = "redirect:/miCuenta.html";
            return new ModelAndView(viewName, model);
        } catch (BusinessException be) {
            model.put("businessException", be.getBusinessMessages());
            model.put("actionMantenimiento", AccionMantenimiento.READ);
            viewName = "redirect:/miCuenta.html";
            return new ModelAndView(viewName, model);
        }
    }
}