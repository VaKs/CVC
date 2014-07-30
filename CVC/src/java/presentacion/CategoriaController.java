package presentacion;

import datos.BusinessException;
import datos.CategoriaDAO;
import datos.UsuarioDAO;
import negocio.Categoria;
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
public class CategoriaController{

    @Autowired
    CategoriaDAO categoriaDAO;
    @Autowired
    UsuarioDAO usuarioDAO;

    @RequestMapping({"/categoria/createforinsert.html"})
    public ModelAndView createForInsert(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("utf-8");
        }
        try {
            Categoria categoria = categoriaDAO.create();
            HttpSession session = request.getSession(true);
            if (session.getAttribute("login") != null) {
                Usuario usuario = usuarioDAO.getUsuarioFromLogin((String)session.getAttribute("login"));
                if (usuario.getRol().getIdRol() == 1) {
                    model.put("categoria", categoria);
                    model.put("actionMantenimiento", AccionMantenimiento.INSERT);
                    viewName = "administracion/categoria";
                    return new ModelAndView(viewName, model);
                }
            }
        } catch (BusinessException be) {
            model.put("businessException", be.getBusinessMessages());
            model.put("actionMantenimiento", AccionMantenimiento.INSERT);
            viewName = "redirect:/categoria.html";
            return new ModelAndView(viewName, model);
        }
        viewName = "tienda/login";
        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/categoria/delete.html"})
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("utf-8");
        }
        int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
        HttpSession session = request.getSession(true);
        try {
            if (session.getAttribute("login") != null) {
                Usuario usuario = usuarioDAO.getUsuarioFromLogin((String)session.getAttribute("login"));
                if (usuario.getRol().getIdRol() == 1) {
                    categoriaDAO.delete(idCategoria);
                    viewName = "redirect:/categorias.html";
                    return new ModelAndView(viewName, model);
                }
            }
        } catch (BusinessException be) {
            model.put("businessException", be.getBusinessMessages());
            model.put("actionMantenimiento", AccionMantenimiento.DELETE);
            viewName = "redirect:/categoria.html";
            return new ModelAndView(viewName, model);
        }



        viewName = "tienda/login";
        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/categoria/read.html"})
    public ModelAndView read(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("utf-8");
        }
        try {
            int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
            Categoria categoria = categoriaDAO.get(idCategoria);
            HttpSession session = request.getSession(true);
            if (session.getAttribute("login") != null) {
                Usuario usuario = usuarioDAO.getUsuarioFromLogin((String)session.getAttribute("login"));
                if (usuario.getRol().getIdRol() == 1) {
                    model.put("categoria", categoria);
                    model.put("actionMantenimiento", AccionMantenimiento.READ);
                    viewName = "administracion/categoria";
                    return new ModelAndView(viewName, model);
                }
            }
        } catch (BusinessException be) {
            model.put("businessException", be.getBusinessMessages());
            model.put("actionMantenimiento", AccionMantenimiento.READ);
            viewName = "redirect:/categorias.html";
            return new ModelAndView(viewName, model);
        }
        viewName = "tienda/login";
        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/categoria/readfordelete.html"})
    public ModelAndView readForDelete(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("utf-8");
        }

        int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
        try {
            Categoria categoria = categoriaDAO.get(idCategoria);
            HttpSession session = request.getSession(true);
            if (session.getAttribute("login") != null) {
                Usuario usuario = usuarioDAO.getUsuarioFromLogin((String)session.getAttribute("login"));
                if (usuario.getRol().getIdRol() == 1) {
                    model.put("categoria", categoria);
                    model.put("actionMantenimiento", AccionMantenimiento.DELETE);
                    viewName = "administracion/categoria";
                    return new ModelAndView(viewName, model);
                }
            }
        } catch (BusinessException be) {
            model.put("businessException", be.getBusinessMessages());
            model.put("actionMantenimiento", AccionMantenimiento.DELETE);
            viewName = "redirect:/admin.html";
            return new ModelAndView(viewName, model);
        }
        viewName = "tienda/login";
        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/categoria/readforupdate.html"})
    public ModelAndView readForUpdate(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("utf-8");
        }

        int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
        try {
            Categoria categoria = categoriaDAO.get(idCategoria);
            HttpSession session = request.getSession(true);
            if (session.getAttribute("login") != null) {
                Usuario usuario = usuarioDAO.getUsuarioFromLogin((String)session.getAttribute("login"));
                if (usuario.getRol().getIdRol() == 1) {
                    model.put("categoria", categoria);
                    model.put("actionMantenimiento", AccionMantenimiento.UPDATE);
                    viewName = "administracion/categoria";
                    return new ModelAndView(viewName, model);
                }
            }
        } catch (BusinessException be) {
            model.put("businessException", be.getBusinessMessages());
            model.put("actionMantenimiento", AccionMantenimiento.UPDATE);
            viewName = "redirect:/admin.html";
            return new ModelAndView(viewName, model);
        }
        viewName = "tienda/login";
        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/categoria/insert.html"})
    public ModelAndView insert(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("utf-8");
        }

        String nombreCategoria = request.getParameter("nombreCategoria");
        String descripcionCategoria = request.getParameter("descripcionCategoria");
        try {
            Categoria categoria = categoriaDAO.create();
            categoria.setNombreCategoria(nombreCategoria);
            categoria.setDescripcionCategoria(descripcionCategoria); //
            HttpSession session = request.getSession(true);

            if (session.getAttribute("login") != null) {
                Usuario usuario = usuarioDAO.getUsuarioFromLogin((String)session.getAttribute("login"));
                if (usuario.getRol().getIdRol() == 1) {

                    categoriaDAO.save(categoria);
                    viewName = "redirect:/categorias.html";
                    return new ModelAndView(viewName, model);
                }
            }
        } catch (BusinessException be) {
            model.put("businessException", be.getBusinessMessages());
            model.put("actionMantenimiento", AccionMantenimiento.INSERT);
            viewName = "redirect:/categoria.html";
            return new ModelAndView(viewName, model);


        }
        //si no es administrador se le envia a otra pagina
        viewName = "tienda/login";
        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/categoria/update.html"})
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("utf-8");
        }

        int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
        String nombreCategoria = request.getParameter("nombreCategoria");
        String descripcionCategoria = request.getParameter("descripcionCategoria");
        
        try {
            Categoria categoria = categoriaDAO.get(idCategoria);
            categoria.setIdCategoria(idCategoria);
            categoria.setNombreCategoria(nombreCategoria);
            categoria.setDescripcionCategoria(descripcionCategoria);
            HttpSession session = request.getSession(true);
            if (session.getAttribute("login") != null) {
                Usuario usuario = usuarioDAO.getUsuarioFromLogin((String)session.getAttribute("login"));
                if (usuario.getRol().getIdRol() == 1) {


                    categoriaDAO.update(categoria);
                    viewName = "redirect:/categorias.html";
                    return new ModelAndView(viewName, model);
                }
            }
        } catch (BusinessException be) {
            model.put("businessException", be.getBusinessMessages());
            model.put("actionMantenimiento", AccionMantenimiento.UPDATE);
            viewName = "redirect:/categoria.html";
            return new ModelAndView(viewName, model);


        }
        //si no es administrador se le envia a otra pagina
        viewName = "tienda/login";
        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/categorias.html"})
    public ModelAndView categorias(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("utf-8");
        }

        Map<String, Object> filter = new HashMap<String, Object>();

        HttpSession httpSession = request.getSession(true);

        if ((UtilController.isEmpty(request.getParameter("search_idCategoria")) == false) || (UtilController.isEmpty(request.getParameter("search_nombreCategoria")) == false)) {
            //Viene por la URL
            filter.put("idCategoria", UtilController.parseInt(request.getParameter("search_idCategoria")));
            filter.put("nombreCategoria", request.getParameter("search_nombreCategoria").trim());
        } else {
            //De la sesión
            filter.put("idCategoria", UtilController.parseInt((String) httpSession.getAttribute("search_idCategoria")));
            filter.put("nombreCategoria", httpSession.getAttribute("search_nombreCategoria"));
        }
        try {
            httpSession.setAttribute("search_idCategoria", filter.get("idCategoria"));
            httpSession.setAttribute("search_nombreCategoria", filter.get("nombreCategoria"));

            List<Categoria> categorias = categoriaDAO.search(filter);

            model.put("categorias", categorias);
            model.put("search_idCategoria", filter.get("idCategoria"));
            model.put("search_nombreCategoria", filter.get("nombreCategoria"));

            if (httpSession.getAttribute("login") != null) {
                Usuario usuario = usuarioDAO.getUsuarioFromLogin((String)httpSession.getAttribute("login"));

                if (usuario.getRol().getIdRol() == 1) {
                    viewName = "administracion/categorias";
                    return new ModelAndView(viewName, model);
                }
            }
            //si no es administrador se le envia a otra pagina
            viewName = "tienda/login";
            return new ModelAndView(viewName, model);
        } catch (BusinessException be) {
            model.put("businessException", be.getBusinessMessages());
            model.put("actionMantenimiento", AccionMantenimiento.UPDATE);
            viewName = "redirect:/categoria.html";
            return new ModelAndView(viewName, model);


        }
    }
}
