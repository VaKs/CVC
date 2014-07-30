package presentacion;

import datos.BusinessException;
import datos.CategoriaDAO;
import datos.ProductoDAO;
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
public class ProductoController{

    @Autowired
    ProductoDAO productoDAO;
    @Autowired
    CategoriaDAO categoriaDAO;
    @Autowired
    UsuarioDAO usuarioDAO;

    @RequestMapping({"/producto/createforinsert.html"})
    public ModelAndView createForInsert(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("utf-8");
        }
        try {
            Producto producto = productoDAO.create();
            List<Categoria> categorias = categoriaDAO.search(null);

            HttpSession session = request.getSession(true);
            if (session.getAttribute("login") != null) {
                Usuario usuario = usuarioDAO.getUsuarioFromLogin((String)session.getAttribute("login"));
                if (usuario.getRol().getIdRol() == 1) {
                    model.put("usuario", usuario);


                    model.put("producto", producto);
                    model.put("categorias", categorias);
                    model.put("actionMantenimiento", AccionMantenimiento.INSERT);
                    viewName = "administracion/producto";
                    return new ModelAndView(viewName, model);
                }
            }
        } catch (BusinessException be) {
            model.put("businessException", be.getBusinessMessages());
            model.put("actionMantenimiento", AccionMantenimiento.INSERT);
            viewName = "redirect:/producto.html";
            return new ModelAndView(viewName, model);
        }
        viewName = "tienda/login";
        return new ModelAndView(viewName, model);

    }

    @RequestMapping({"/producto/delete.html"})
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("utf-8");
        }
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));

        HttpSession session = request.getSession(true);
        try {
            if (session.getAttribute("login") != null) {
                Usuario usuario = usuarioDAO.getUsuarioFromLogin((String)session.getAttribute("login"));

                if (usuario.getRol().getIdRol() == 1) {
                    
                    productoDAO.delete(idProducto);
                    viewName = "redirect:/productos.html";
                    return new ModelAndView(viewName, model);
                }
            }
        } catch (BusinessException be) {
            model.put("businessException", be.getBusinessMessages());
            model.put("actionMantenimiento", AccionMantenimiento.DELETE);
            viewName = "redirect:/productos.html";
            return new ModelAndView(viewName, model);
        }


        viewName = "tienda/login";
        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/producto/insert.html"})
    public ModelAndView insert(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("utf-8");
        }
        int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
        String nombreProducto = request.getParameter("nombreProducto");
        String descripcionProducto = request.getParameter("descripcionProducto");
        double precioEuros = new Double(request.getParameter("precioEuros")).doubleValue();
        precioEuros = UtilController.redondear(precioEuros);

        int permiteMostrar = Integer.parseInt(request.getParameter("permiteMostrar"));
        int permiteVender = Integer.parseInt(request.getParameter("permiteVender"));
        int permiteCambiar = Integer.parseInt(request.getParameter("permiteCambiar"));



        HttpSession session = request.getSession(true);
        try {
            Producto producto = productoDAO.create();
            Categoria categoria = categoriaDAO.get(idCategoria);
            if (session.getAttribute("login") != null) {
                Usuario usuario = usuarioDAO.getUsuarioFromLogin((String)session.getAttribute("login"));
                if (usuario.getRol().getIdRol() == 1) {
                    producto.setPrecioEuros(precioEuros);
                    producto.setPermiteMostrar(permiteMostrar);
                    producto.setPermiteCambiar(permiteCambiar);
                    producto.setPermiteVender(permiteVender);
                    producto.setCategoria(categoria);
                    producto.setNombreProducto(nombreProducto);
                    producto.setDescripcionProducto(descripcionProducto);
                    producto.setUsuario(usuario);

                    productoDAO.save(producto);
                    model.put("usuario", usuario);
                    viewName = "redirect:/productos.html";
                    return new ModelAndView(viewName, model);
                }

            }
        } catch (BusinessException be) {
            model.put("businessException", be.getBusinessMessages());
            model.put("actionMantenimiento", AccionMantenimiento.INSERT);
            viewName = "redirect:/productos.html";
            return new ModelAndView(viewName, model);
        }


        viewName = "redirect:/login.html";
        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/producto/read.html"})
    public ModelAndView read(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("utf-8");
        }
        try{
            int idProducto = Integer.parseInt(request.getParameter("idProducto"));

            Producto producto = productoDAO.get(idProducto);
            List<Categoria> categorias = categoriaDAO.search(null);

            model.put("producto", producto);
            model.put("categorias", categorias);
            model.put("actionMantenimiento", AccionMantenimiento.READ);

            HttpSession session = request.getSession(true);
            if (session.getAttribute("login") != null) {
                Usuario usuario = usuarioDAO.getUsuarioFromLogin((String)session.getAttribute("login"));

                if (usuario.getRol().getIdRol() == 1) {
                    viewName = "administracion/producto";
                    return new ModelAndView(viewName, model);
                }
            }
        }  catch (BusinessException be) {
            model.put("businessException", be.getBusinessMessages());
            model.put("actionMantenimiento", AccionMantenimiento.READ);
            viewName = "redirect:/producto.html";
            return new ModelAndView(viewName, model);
        }    
        viewName = "tienda/login";
        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/producto/readfordelete.html"})
    public ModelAndView readForDelete(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("utf-8");
        }
        try{
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));

        Producto producto = productoDAO.get(idProducto);
        List<Categoria> categorias = categoriaDAO.search(null);
        HttpSession session = request.getSession(true);
        if (session.getAttribute("login") != null) {
            Usuario usuario = usuarioDAO.getUsuarioFromLogin((String)session.getAttribute("login"));
            if (usuario.getRol().getIdRol() == 1) {
                model.put("usuario", usuario);


                model.put("producto", producto);
                model.put("categorias", categorias);
                model.put("actionMantenimiento", AccionMantenimiento.DELETE);
                viewName = "administracion/producto";
                return new ModelAndView(viewName, model);
            }
        }} catch (BusinessException be) {
            model.put("businessException", be.getBusinessMessages());
            model.put("actionMantenimiento", AccionMantenimiento.DELETE);
            viewName = "redirect:/producto.html";
            return new ModelAndView(viewName, model);
        }
        viewName = "tienda/login";
        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/producto/readforupdate.html"})
    public ModelAndView readForUpdate(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("utf-8");
        }
        try{
            int idProducto = Integer.parseInt(request.getParameter("idProducto"));

            Producto producto = productoDAO.get(idProducto);
            List<Categoria> categorias = categoriaDAO.search(null);

            HttpSession session = request.getSession(true);
            if (session.getAttribute("login") != null) {
                Usuario usuario = usuarioDAO.getUsuarioFromLogin((String)session.getAttribute("login"));
                if (usuario.getRol().getIdRol() == 1) {
                    model.put("usuario", usuario);


                    model.put("producto", producto);
                    model.put("categorias", categorias);
                    model.put("actionMantenimiento", AccionMantenimiento.UPDATE);
                    viewName = "administracion/producto";
                    return new ModelAndView(viewName, model);
                }
            }
        } catch (BusinessException be) {
            model.put("businessException", be.getBusinessMessages());
            model.put("actionMantenimiento", AccionMantenimiento.UPDATE);
            viewName = "redirect:/producto.html";
            return new ModelAndView(viewName, model);
        }
        viewName = "tienda/login";
        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/producto/update.html"})
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
                    int idProducto = Integer.parseInt(request.getParameter("idProducto"));
                    String nombreProducto = request.getParameter("nombreProducto");
                    String descripcionProducto = request.getParameter("descripcionProducto");
                    double precioEuros = new Double(request.getParameter("precioEuros")).doubleValue();
                    precioEuros = UtilController.redondear(precioEuros);
                    int permiteMostrar = Integer.parseInt(request.getParameter("permiteMostrar"));
                    int permiteVender = Integer.parseInt(request.getParameter("permiteVender"));
                    int permiteCambiar = Integer.parseInt(request.getParameter("permiteCambiar"));

                    Producto producto = productoDAO.get(idProducto);
                    producto.setNombreProducto(nombreProducto);
                    producto.setPrecioEuros(precioEuros);
                    producto.setDescripcionProducto(descripcionProducto);
                    producto.setPermiteMostrar(permiteMostrar);
                    producto.setPermiteCambiar(permiteCambiar);
                    producto.setPermiteVender(permiteVender);

                        productoDAO.update(producto);
                        viewName = "redirect:/productos.html";
                        return new ModelAndView(viewName, model);
                }
            }
        } catch (BusinessException be) {
                    model.put("businessException", be.getBusinessMessages());
                    model.put("actionMantenimiento", AccionMantenimiento.UPDATE);
                    viewName = "redirect:/producto.html";
                    return new ModelAndView(viewName, model);
                }
        
        viewName = "redirect:/login.html";
        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/productos.html"})
    public ModelAndView productos(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("utf-8");
        }

        Map<String, Object> filter = new HashMap<String, Object>();

        HttpSession httpSession = request.getSession(true);
        try{
            if ((UtilController.isEmpty(request.getParameter("search_idProducto")) == false) || (UtilController.isEmpty(request.getParameter("search_nombre")) == false)) {
                //Viene por la URL
                filter.put("idProducto", UtilController.parseInt(request.getParameter("search_idProducto")));
                filter.put("nombreProducto", request.getParameter("search_nombreProducto").trim());
            } else {
                //De la sesión
                filter.put("idProducto", UtilController.parseInt((String) httpSession.getAttribute("search_idProducto")));
                filter.put("nombreProducto", httpSession.getAttribute("search_nombreProducto"));
            }

            httpSession.setAttribute("search_idProducto", filter.get("idProducto"));
            httpSession.setAttribute("search_nombreProducto", filter.get("nombreProducto"));

            List<Producto> productos = productoDAO.search(filter);

            model.put("productos", productos);
            model.put("search_idProducto", filter.get("idProducto"));
            model.put("search_nombreProducto", filter.get("nombreProducto"));

            if (httpSession.getAttribute("login") != null) {
                Usuario usuario = usuarioDAO.getUsuarioFromLogin((String)httpSession.getAttribute("login"));

                if (usuario.getRol().getIdRol() == 1) {
                    viewName = "administracion/productos";
                    return new ModelAndView(viewName, model);
                }
            }
        } catch (BusinessException be) {
            model.put("businessException", be.getBusinessMessages());
            model.put("actionMantenimiento", AccionMantenimiento.READ);
            viewName = "redirect:/productos.html";
            return new ModelAndView(viewName, model);
        }
        viewName = "tienda/login";
        return new ModelAndView(viewName, model);
    }
}
