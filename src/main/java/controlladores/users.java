/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlladores;

import modelos.cls_usuario;
import modelosDAO.usuarioDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Marco
 */
@WebServlet(name = "users", urlPatterns = {"/users"})
public class users extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet users</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet users at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String vista = "";
        if(session.getAttribute("idpermiso")== null) {
            vista = "/";
        } else if ((int)session.getAttribute("idpermiso") == 1) {
            //redirect
            usuarioDAO usuarioDAO = new usuarioDAO();
            List<cls_usuario> usuarios = usuarioDAO.getUsuarios();
            session.setAttribute("usuarios", usuarios);
            vista= "vistas/users/index.jsp";
            request.getRequestDispatcher(vista).forward(request, response);
        } else if ((int)session.getAttribute("idpermiso") == 2 || (int)session.getAttribute("idpermiso") == 3) {
            vista = "/citas";
        }

        response.sendRedirect(request.getContextPath() + vista);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String vista = "";
        String password = request.getParameter("password");
        String passwordconf = request.getParameter("passwordconf");
        System.out.println(password);
        System.out.println(passwordconf);
        if(!password.equals(passwordconf)) {
            //error
            vista = "vistas/users/index.jsp";
            request.setAttribute("error", "Las contraseñas no coinciden");
            request.getRequestDispatcher(vista).forward(request, response);
        } else {
            vista = "vistas/users/index.jsp";
            String fullname = request.getParameter("fullname");
            String username = request.getParameter("username");
            String tel = request.getParameter("tel");

            cls_usuario usuario = new cls_usuario();
            usuario.setFullname(fullname);
            usuario.setUsername(username);
            usuario.setPassword(password);
            usuario.setTel(tel);
            usuario.setIdpermiso(3);

            usuarioDAO usuarioDAO = new usuarioDAO();
            Boolean success = usuarioDAO.agregar(usuario);
            if(success) {
                request.setAttribute("success", "Usuario agregado correctamente");
                List<cls_usuario> usuarios = usuarioDAO.getUsuarios();
                session.setAttribute("usuarios", usuarios);
            } else {
                request.setAttribute("error", "No se pudo agregar el usuario");
            }

            request.getRequestDispatcher(vista).forward(request, response);

        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
