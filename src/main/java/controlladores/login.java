/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlladores;

import modelos.cls_usuario;
import modelosDAO.usuarioDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
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
@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

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
            out.println("<title>Servlet login</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet login at " + request.getContextPath() + "</h1>");
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
            vista= "index.jsp";
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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        cls_usuario usuario = new cls_usuario();
        usuario.setUsername(username);
        usuario.setPassword(password);

        usuarioDAO usuarioDAO = new usuarioDAO();
        List<cls_usuario> lista = usuarioDAO.login(usuario);
        cls_usuario per = null;

        try (PrintWriter out = response.getWriter()) {
            if(lista.isEmpty()) {
                out.print("No hay registros");
                String vista = "/";

                response.sendRedirect(vista+"?error=Datos incorrectos");
            } else {
                Iterator<cls_usuario> it = lista.iterator();
                HttpSession sesion = request.getSession();
                while(it.hasNext()) {
                    per = it.next();
                    sesion.setAttribute("id", per.getId());
                    sesion.setAttribute("fullname", per.getFullname());
                    sesion.setAttribute("username", per.getUsername());
                    sesion.setAttribute("password", per.getPassword());
                    sesion.setAttribute("tel", per.getTel());
                    sesion.setAttribute("idpermiso", per.getIdpermiso());

                    if (per.getIdpermiso() == 1) {
                        response.sendRedirect( "/users");
                    } else {
                        response.sendRedirect("/citas");
                    }
                }
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
