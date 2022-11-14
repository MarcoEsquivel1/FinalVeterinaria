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
@WebServlet(name = "cuenta", urlPatterns = {"/cuenta"})
public class cuenta extends HttpServlet {

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
            out.println("<title>Servlet cuenta</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet cuenta at " + request.getContextPath() + "</h1>");
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
        if (session.getAttribute("idpermiso") == null) {
            vista = "/";
        } else {
            //redirect
            vista = "vistas/cuenta/index.jsp";
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
        String sesionpassword = (String) session.getAttribute("password");
        String vista = "";
        String fullname = request.getParameter("fullname");
        String username = (String) session.getAttribute("username");
        System.out.println("username: " + username);
        String tel = request.getParameter("tel");
        int id = (int) session.getAttribute("id");

        cls_usuario usuario = new cls_usuario();
        usuario.setId(id);
        usuario.setFullname(fullname);
        usuario.setUsername(username);
        usuario.setTel(tel);

        usuarioDAO usuarioDAO = new usuarioDAO();

        if (request.getParameter("newpassword") != "") {
            if(request.getParameter("password") == "") {
                vista = "vistas/cuenta/index.jsp";
                request.setAttribute("error", "Para cambiar la contraseña, debe ingresar su contraseña actual");
                request.getRequestDispatcher(vista).forward(request, response);
            }else {
                if (!request.getParameter("password").equals(sesionpassword)) {
                    vista = "vistas/cuenta/index.jsp";
                    request.setAttribute("error", "Ingrese su contraseña actual para poder cambiar la contraseña");
                    request.getRequestDispatcher(vista).forward(request, response);
                } else if (request.getParameter("password").equals(sesionpassword)){
                    String newpassword = request.getParameter("newpassword");
                    usuario.setPassword(newpassword);
                    Boolean success = usuarioDAO.actualizar(usuario);
                    if (success) {
                        vista = "vistas/cuenta/index.jsp";
                        request.setAttribute("success", "Se ha actualizado la contraseña");
                        request.getRequestDispatcher(vista).forward(request, response);
                    } else {
                        vista = "vistas/cuenta/index.jsp";
                        request.setAttribute("error", "No se han podido actualizar los datos");
                        request.getRequestDispatcher(vista).forward(request, response);
                    }
                }
            }
        }else {
            //update
            usuario.setPassword(sesionpassword);
            Boolean success = usuarioDAO.actualizar(usuario);
            if (success) {
                session.setAttribute("fullname", usuario.getFullname());
                session.setAttribute("username", usuario.getUsername());
                session.setAttribute("password", usuario.getPassword());
                session.setAttribute("tel", usuario.getTel());
                vista = "vistas/cuenta/index.jsp";
                request.setAttribute("success", "Se han actualizado los datos");
                request.getRequestDispatcher(vista).forward(request, response);
            } else {
                vista = "vistas/cuenta/index.jsp";
                request.setAttribute("error", "No se han podido actualizar los datos");
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
