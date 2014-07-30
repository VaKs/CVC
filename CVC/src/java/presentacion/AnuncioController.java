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
public class AnuncioController{

    @Autowired
    ProductoDAO productoDAO;
    @Autowired
    CategoriaDAO categoriaDAO;
    @Autowired
    UsuarioDAO usuarioDAO;

    @RequestMapping({"/anuncio/createforinsert.html"})
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
                if (usuario.getRol().getIdRol() == 1 || usuario.getRol().getIdRol() == 3) {
                    model.put("usuario", usuario);
                    model.put("producto", producto);
                    model.put("categorias", categorias);
                    model.put("actionMantenimiento", AccionMantenimiento.INSERT);
                    viewName = "tienda/anuncioDos";
                    return new ModelAndView(viewName, model);
                }
            }
        } catch (BusinessException be) {
            model.put("businessException", be.getBusinessMessages());
            model.put("actionMantenimiento", AccionMantenimiento.INSERT);
            viewName = "redirect:/index.html";
            return new ModelAndView(viewName, model);
        }

        viewName = "tienda/login";
        return new ModelAndView(viewName, model);

    }

    @RequestMapping({"/anuncio/delete.html"})
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

                if (usuario.getRol().getIdRol() == 1 | usuario.getRol().getIdRol() == 3) {


                    productoDAO.delete(idProducto);
                    viewName = "redirect:/index.html";
                    return new ModelAndView(viewName, model);
                }
            }
        } catch (BusinessException be) {
            model.put("businessException", be.getBusinessMessages());
            model.put("actionMantenimiento", AccionMantenimiento.DELETE);
            viewName = "redirect:/index.html";
            return new ModelAndView(viewName, model);
        }



        //si no es administrador se le envia a otra pagina
        viewName = "tienda/login";
        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/anuncio/insert.html"})
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

        int permiteMostrar = 1;
        int permiteVender = 0;
        int permiteCambiar = 0;


        if (request.getParameter("permiteVender") != null) {
            permiteVender = 1;
        }
        if (request.getParameter("permiteCambiar") != null) {
            permiteCambiar = 1;
        }


        HttpSession session = request.getSession(true);
        try {
            Producto producto = productoDAO.create();
            Categoria categoria = categoriaDAO.get(idCategoria);
            if (session.getAttribute("login") != null) {
                Usuario usuario = usuarioDAO.getUsuarioFromLogin((String)session.getAttribute("login"));
                if (usuario.getRol().getIdRol() == 1 || usuario.getRol().getIdRol() == 3) {
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
                    viewName = "redirect:/index.html";
                    return new ModelAndView(viewName, model);
                }

            }
        } catch (BusinessException be) {
            model.put("businessException", be.getBusinessMessages());
            model.put("actionMantenimiento", AccionMantenimiento.INSERT);
            viewName = "redirect:/anuncioDos.html";
            return new ModelAndView(viewName, model);
        }


        viewName = "redirect:/login.html";

        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/anuncio/read.html"})
    public ModelAndView read(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("utf-8");
        }
        try {
            int idProducto = Integer.parseInt(request.getParameter("idProducto"));

            Producto producto = productoDAO.get(idProducto);
            List<Categoria> categorias = categoriaDAO.search(null);

            model.put("producto", producto);
            model.put("categorias", categorias);
            model.put("actionMantenimiento", AccionMantenimiento.READ);

            HttpSession session = request.getSession(true);
            if (session.getAttribute("login") != null) {
                Usuario usuario = usuarioDAO.getUsuarioFromLogin((String)session.getAttribute("login"));

                if (usuario.getRol().getIdRol() == 1 || usuario.getRol().getIdRol() == 3) {
                    viewName = "administracion/producto";
                    return new ModelAndView(viewName, model);
                }
            }
        } catch (BusinessException be) {
            model.put("businessException", be.getBusinessMessages());
            model.put("actionMantenimiento", AccionMantenimiento.READ);
            viewName = "redirect:/index.html";
            return new ModelAndView(viewName, model);
        }
        //si no es administrador se le envia a otra pagina
        //viewName = "tienda/login";
        viewName = "index";
        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/anuncio/readfordelete.html"})
    public ModelAndView readForDelete(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("utf-8");
        }
        try {
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
            }
        } catch (BusinessException be) {
            model.put("businessException", be.getBusinessMessages());
            model.put("actionMantenimiento", AccionMantenimiento.DELETE);
            viewName = "redirect:/index.html";
            return new ModelAndView(viewName, model);
        }
        //viewName = "tienda/login";
        viewName = "index";
        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/anuncio/readforupdate.html"})
    public ModelAndView readForUpdate(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("utf-8");
        }
        try {
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
            viewName = "redirect:/index.html";
            return new ModelAndView(viewName, model);
        }
        //viewName = "tienda/login";
        viewName = "index";
        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/anuncio/update.html"})
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
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


        //si no es administrador nada de de nada
        //viewName = "redirect:/login.html";
        viewName = "redirect:/index.html";
        return new ModelAndView(viewName, model);
    }
}
