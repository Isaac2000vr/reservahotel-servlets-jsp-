package reservahotel.controladores;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import reservahotel.modelo.CRUDUsuario;
import reservahotel.modelo.Usuario;

@WebServlet(name = "ServletUsuario", urlPatterns = {"/usuario"})
public class ServletUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String accion = request.getParameter("accion"); // capturar la accion

            if (accion.equals("agregar")) {
                CRUDUsuario crudAlguien = new CRUDUsuario();
                crudAlguien.getAlguien().setClave(request.getParameter("clave"));
                crudAlguien.getAlguien().setNombre(request.getParameter("nombre"));
                crudAlguien.getAlguien().setRol(request.getParameter("rol"));
                crudAlguien.getAlguien().setEmail(request.getParameter("email"));
                crudAlguien.agregarUsuario();
                response.sendRedirect("web/usuario/agregar.jsp?mensaje=Usuario Agregado al Sistema");

            } else if (accion.equals("buscar")) {
                Usuario alguien = CRUDUsuario.consultarUsuario(
                        Integer.parseInt(request.getParameter("id")));
                request.getSession().setAttribute("usuario.buscar", alguien);
                String redireccion = request.getParameter("redir");
                if (redireccion.equals("borrar")) {
                    response.sendRedirect("web/usuario/eliminar.jsp");
                } else if (redireccion.equals("modificar")) {
                    response.sendRedirect("web/usuario/modificar.jsp");
                } else {
                    response.sendRedirect("web/usuario/buscar.jsp");
                }

            } else if (accion.equals("modificar")) {
                CRUDUsuario crudAlguien = new CRUDUsuario();
                crudAlguien.getAlguien().setId(Integer.parseInt(request.getParameter("id")));
                crudAlguien.getAlguien().setClave(request.getParameter("clave"));
                crudAlguien.getAlguien().setNombre(request.getParameter("nombre"));
                crudAlguien.getAlguien().setRol(request.getParameter("rol"));
                crudAlguien.getAlguien().setEmail(request.getParameter("email"));
                crudAlguien.modificarUsuario();
                response.sendRedirect("web/usuario/modificar.jsp?mensaje=Usuario Modificado en el Sistema");

            } else if (accion.equals("borrar")) {
                CRUDUsuario crudAlguien = new CRUDUsuario();
                crudAlguien.getAlguien().setId(Integer.parseInt(request.getParameter("id")));
                crudAlguien.eliminarUsuario();
                response.sendRedirect("web/usuario/eliminar.jsp?mensaje=Usuario Eliminado del Sistema");

            } else if (accion.equals("listartodo")) {
                Usuario[] listado = CRUDUsuario.listarTodosLosUsuarios();
                request.getSession().setAttribute("usuario.listar", listado);
                response.sendRedirect("web/usuario/listar.jsp");

            } else if (accion.equals("login")) {
                Usuario alguien = CRUDUsuario.iniciarSesion(
                        request.getParameter("email"), request.getParameter("clave"));
                request.getSession().setAttribute("usuario.login", alguien);
                response.sendRedirect("index.jsp?mensaje=Bienvenido al Sistema");

            } else if (accion.equals("salir")) {
                request.getSession().setAttribute("usuario.login", null);
                request.getSession().invalidate();
                response.sendRedirect("index.jsp?mensaje=Sesion Finalizada");

            } else {
                response.sendRedirect("web/mensaje.jsp?mensaje=La Accion Solicitada no es Correcta");
            }
        } catch (Exception error) {
            response.sendRedirect("web/mensaje.jsp?mensaje=" + error.getMessage());
        } finally {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}