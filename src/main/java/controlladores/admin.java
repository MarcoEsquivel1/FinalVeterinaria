/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlladores;

import modelos.cls_usuario;
import modelosDAO.usuarioDAO;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "admin", urlPatterns = {"/admin"})
public class admin extends HttpServlet {

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
            out.println("<title>Servlet admin</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet admin at " + request.getContextPath() + "</h1>");
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
        if(session.getAttribute("idpermiso") != null) {
            vista = "/cuenta";
        } else {
            usuarioDAO usuarioDAO = new usuarioDAO();
            boolean existe = usuarioDAO.existAdmin();
            System.out.println(existe);
            if(existe){
                request.setAttribute("ad", "Ya existe un administrador");
                System.out.println(request.getAttribute("ad"));
            }
            vista= "vistas/admin/index.jsp";
            request.getRequestDispatcher(vista).forward(request, response);
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
        if (!password.equals(passwordconf)) {
            request.setAttribute("error", "Las contraseñas no coinciden");
            vista = "vistas/admin/index.jsp";
            request.getRequestDispatcher(vista).forward(request, response);
        } else {
            vista = "vistas/admin/index.jsp";
            String fullname = request.getParameter("fullname");
            String username = request.getParameter("username");
            String tel = request.getParameter("tel");
            String hashed =  utils.encript.hashPassword(password);
            cls_usuario usuario = new cls_usuario();
            usuario.setFullname(fullname);
            usuario.setUsername(username);
            usuario.setPassword(hashed);
            usuario.setTel(tel);
            usuario.setIdpermiso(1);

            usuarioDAO usuarioDAO = new usuarioDAO();
            boolean success = usuarioDAO.agregar(usuario);
            if(success){
                request.setAttribute("success", "Se ha creado el administrador");
                request.setAttribute("ad", "Ya existe un administrador");
                vista = "vistas/admin/index.jsp";
                request.getRequestDispatcher(vista).forward(request, response);
            } else {
                request.setAttribute("error", "No se ha podido crear el administrador");
                vista = "vistas/admin/index.jsp";
                request.getRequestDispatcher(vista).forward(request, response);
            }
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
