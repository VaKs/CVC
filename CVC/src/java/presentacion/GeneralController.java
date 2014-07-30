package presentacion;

import datos.BusinessException;
import datos.CategoriaDAO;
import datos.ProductoDAO;
import datos.RolDAO;
import datos.UsuarioDAO;
import negocio.Categoria;
import negocio.Producto;
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
public class GeneralController{

    @Autowired
    UsuarioDAO usuarioDAO;
    @Autowired
    RolDAO rolDAO;
    @Autowired
    CategoriaDAO categoriaDAO;
    @Autowired
    ProductoDAO productoDAO;

    @RequestMapping({"/historial.html"})
    public ModelAndView getHistorial(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;
        HttpSession session = request.getSession(true);
        try {
            if (session.getAttribute("login") != null) {

                Usuario usuario = usuarioDAO.getUsuarioFromLogin((String)session.getAttribute("login"));
                model.put("usuario", usuario);
            }
            viewName = "../../REST/historial";
            return new ModelAndView(viewName, model);
        } catch (BusinessException be) {
            model.put("businessException", be.getBusinessMessages());
            viewName = "redirect:/historial.html";
            return new ModelAndView(viewName, model);
        }
    }

    @RequestMapping({"/admin.html"})
    public ModelAndView getAdmin(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;
        HttpSession session = request.getSession(true);
        try {
            if (session.getAttribute("login") != null) {
                Usuario usuario = usuarioDAO.getUsuarioFromLogin((String)session.getAttribute("login"));
                model.put("usuario", usuario);
                if (usuario.getRol().getIdRol() == 1) {
                    viewName = "administracion/admin";
                    return new ModelAndView(viewName, model);
                }
            }
        } catch (BusinessException be) {
            model.put("businessException", be.getBusinessMessages());
            viewName = "redirect:/admin.html";
            return new ModelAndView(viewName, model);
        }
        viewName = "tienda/login";
        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/registro.html"})
    public ModelAndView getRegistro(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;
        try {
            List<Categoria> categorias = categoriaDAO.search(null);
            model.put("categorias", categorias);

            viewName = "tienda/registro";
            return new ModelAndView(viewName, model);
        } catch (BusinessException be) {
            model.put("businessException", be.getBusinessMessages());
            viewName = "redirect:/registro.html";
            return new ModelAndView(viewName, model);
        }
    }

    @RequestMapping({"/index.html"})
    public ModelAndView getIndex(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;
        HttpSession session = request.getSession(true);
        try {
            if (session.getAttribute("login") != null) {
                Usuario usuario = usuarioDAO.getUsuarioFromLogin((String)session.getAttribute("login"));
                model.put("usuario", usuario);
            }
            Integer idCategoria;
            try {
                idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
            } catch (NumberFormatException ex) {
                idCategoria = null;
            }
            Categoria categoria;
            if (idCategoria != null) {
                categoria = categoriaDAO.get(idCategoria);
            } else {
                categoria = null;
            }
            List<Categoria> categorias = categoriaDAO.search(null);
            model.put("categorias", categorias);

            List<Producto> productos = productoDAO.getProductosFromCategoria(categoria);
            model.put("productos", productos);

            viewName = "index";
            return new ModelAndView(viewName, model);
        } catch (BusinessException be) {
            model.put("businessException", be.getBusinessMessages());
            viewName = "redirect:/index.html";
            return new ModelAndView(viewName, model);
        }
    }

    @RequestMapping({"/anuncio.html"})
    public ModelAndView getAnuncio(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;
        HttpSession session = request.getSession(true);
        try {
            if (session.getAttribute("login") != null) {
                Usuario usuario = usuarioDAO.getUsuarioFromLogin((String)session.getAttribute("login"));
                model.put("usuario", usuario);
            }

            List<Categoria> categorias = categoriaDAO.search(null);
            model.put("categorias", categorias);

            List<Producto> productos = productoDAO.search(null);
            model.put("productos", productos);

            viewName = "tienda/anuncio";
            return new ModelAndView(viewName, model);
        } catch (BusinessException be) {
            model.put("businessException", be.getBusinessMessages());
            viewName = "redirect:/anuncio.html";
            return new ModelAndView(viewName, model);
        }
    }

    @RequestMapping({"/anuncioDos.html"})
    public ModelAndView getAnuncioDos(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;
        HttpSession session = request.getSession(true);
        try {
            if (session.getAttribute("login") != null) {
                Usuario usuario = usuarioDAO.getUsuarioFromLogin((String)session.getAttribute("login"));
                model.put("usuario", usuario);
            }

            List<Categoria> categorias = categoriaDAO.search(null);
            model.put("categorias", categorias);

            List<Producto> productos = productoDAO.search(null);
            model.put("productos", productos);

            viewName = "tienda/anuncioDos";
            return new ModelAndView(viewName, model);
        } catch (BusinessException be) {
            model.put("businessException", be.getBusinessMessages());
            viewName = "redirect:/anuncioDos.html";
            return new ModelAndView(viewName, model);
        }
    }

    @RequestMapping({"/miCuenta.html"})
    public ModelAndView getMiCuenta(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;
        HttpSession session = request.getSession(true);
        try {
            if (session.getAttribute("login") != null) {

                Usuario usuario = usuarioDAO.getUsuarioFromLogin((String)session.getAttribute("login"));
                model.put("usuario", usuario);
            }
            viewName = "tienda/miCuenta";
            return new ModelAndView(viewName, model);
        } catch (BusinessException be) {
            model.put("businessException", be.getBusinessMessages());
            viewName = "redirect:/micuenta.html";
            return new ModelAndView(viewName, model);
        }
    }
 @RequestMapping({"/contacto.html"})
    public ModelAndView getContacto(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;
        HttpSession session = request.getSession(true);
        try {
            if (session.getAttribute("login") != null) {

                Usuario usuario = usuarioDAO.getUsuarioFromLogin((String)session.getAttribute("login"));
                model.put("usuario", usuario);
            }
            viewName = "tienda/contacto";
            return new ModelAndView(viewName, model);
        } catch (BusinessException be) {
            model.put("businessException", be.getBusinessMessages());
            viewName = "redirect:/contacto.html";
            return new ModelAndView(viewName, model);
        }
    }
    @RequestMapping({"/comprar.html"})
    public ModelAndView getComprar(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;
        HttpSession session = request.getSession(true);
        try {
            if (session.getAttribute("login") != null) {

                Usuario usuario = usuarioDAO.getUsuarioFromLogin((String)session.getAttribute("login"));
                model.put("usuario", usuario);
            }
            Integer idCategoria;
            try {
                idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
            } catch (NumberFormatException ex) {
                idCategoria = null;
            }
            Categoria categoria;
            if (idCategoria != null) {
                categoria = categoriaDAO.get(idCategoria);
            } else {
                categoria = null;
            }
            List<Categoria> categorias = categoriaDAO.search(null);
            model.put("categorias", categorias);

            List<Producto> productos = productoDAO.getProductosCompras(categoria);
            model.put("productos", productos);

            viewName = "tienda/comprar";
            return new ModelAndView(viewName, model);
        } catch (BusinessException be) {
            model.put("businessException", be.getBusinessMessages());
            viewName = "redirect:/comprar.html";
            return new ModelAndView(viewName, model);
        }
    }

    @RequestMapping({"/trueque.html"})
    public ModelAndView getTrueque(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;
        HttpSession session = request.getSession(true);
        try {
            if (session.getAttribute("login") != null) {

                Usuario usuario = usuarioDAO.getUsuarioFromLogin((String)session.getAttribute("login"));
                model.put("usuario", usuario);
            }
            Integer idCategoria;
            try {
                idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
            } catch (NumberFormatException ex) {
                idCategoria = null;

            }
            Categoria categoria;
            if (idCategoria != null) {
                categoria = categoriaDAO.get(idCategoria);
            } else {
                categoria = null;
            }
            List<Categoria> categorias = categoriaDAO.search(null);
            model.put("categorias", categorias);

            List<Producto> productos = productoDAO.getProductosTrueques(categoria);
            model.put("productos", productos);

            viewName = "tienda/trueque";
            return new ModelAndView(viewName, model);
        } catch (BusinessException be) {
            model.put("businessException", be.getBusinessMessages());
            viewName = "redirect:/trueque.html";
            return new ModelAndView(viewName, model);
        }
    }

    @RequestMapping({"/categoriasProductos.html"})
    public ModelAndView getCategoriasProductos(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;
        HttpSession session = request.getSession(true);
        try {
            if (session.getAttribute("login") != null) {

                Usuario usuario = usuarioDAO.getUsuarioFromLogin((String)session.getAttribute("login"));
                model.put("usuario", usuario);
            }
            Integer idCategoria;
            try {
                idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
            } catch (NumberFormatException ex) {
                idCategoria = null;
            }
            Categoria categoria;
            if (idCategoria != null) {
                categoria = categoriaDAO.get(idCategoria);
            } else {
                categoria = null;
            }
            List<Categoria> categorias = categoriaDAO.search(null);
            model.put("categorias", categorias);

            List<Producto> productos = productoDAO.getProductosCompras(categoria);
            model.put("productos", productos);

            viewName = "tienda/comprar";
            return new ModelAndView(viewName, model);
        } catch (BusinessException be) {
            model.put("businessException", be.getBusinessMessages());
            viewName = "redirect:/comprar.html";
            return new ModelAndView(viewName, model);
        }
    }
}
