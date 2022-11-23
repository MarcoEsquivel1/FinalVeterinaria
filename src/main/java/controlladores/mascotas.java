/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelos.cls_mascota;
import modelos.cls_usuario;
import modelosDAO.MascotaDAO;
import modelosDAO.RegistrosDAO;
import modelosDAO.usuarioDAO;
import utils.encript;

/**
 *
 * @author Marco
 */

@WebServlet(name = "mascotas", urlPatterns = {"/mascotas", "/mascotas/edit", "/mascotas/delete", "/mascotas/update", "/vistas/citas/formCitas"})
public class mascotas extends HttpServlet {
    MascotaDAO mascotaDAO2 = new MascotaDAO();

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
            out.println("<title>Servlet mascotas</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet mascotas at " + request.getContextPath() + "</h1>");
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
        
           System.out.println(session.getAttribute("idpermiso"));
             String accion = request.getServletPath();
            System.out.println(accion);
          if (accion.equals("/mascotas/edit")) {
                System.out.println(accion);
                String vista2 = "../vistas/mascotas/edit.jsp";
                int id = Integer.parseInt(request.getParameter("id"));
                MascotaDAO mascotaDao = new MascotaDAO();
                cls_mascota mascota = mascotaDao.findMascota(id);
                request.setAttribute("mascota", mascota);
                request.getRequestDispatcher(vista2).forward(request, response);
            }else if(accion.equals("/mascotas/delete")){
                    System.out.println(accion);
                      String vista2 = "../vistas/mascotas/index.jsp";
                      int id = Integer.parseInt(request.getParameter("id"));
                      MascotaDAO mascotaDao = new MascotaDAO();
                  try {
                      boolean a = mascotaDao.delete(id);
                  } catch (ClassNotFoundException ex) {
                      Logger.getLogger(mascotas.class.getName()).log(Level.SEVERE, null, ex);
                  } catch (SQLException ex) {
                      Logger.getLogger(mascotas.class.getName()).log(Level.SEVERE, null, ex);
                  }
                      List<cls_mascota> mascotas = mascotaDao.getMascotas(Integer.parseInt(session.getAttribute("id").toString()));
                      session.setAttribute("mascotas", mascotas);
                      request.getRequestDispatcher(vista2).forward(request, response);  
            }else if(accion.equals("/vistas/citas/formCitas")){
                System.out.println(accion);
                 String vista2 = "../citas/formCitas.jsp";
                 int id = Integer.parseInt(request.getParameter("id"));
                 MascotaDAO mascotaDao = new MascotaDAO();
                cls_mascota mascota = mascotaDao.findMascota(id);
                request.setAttribute("mascota", mascota);
                      request.getRequestDispatcher(vista2).forward(request, response);
                      
                
                
                
            }
            
          else{ 
        if(session.getAttribute("idpermiso") == null) {
            vista = "/mascotas";
            
        } else if (session.getAttribute("idpermiso").toString().equals("1")) {
            //redirect
            vista = "/mascotas";
        } else if ((int)session.getAttribute("idpermiso")==2 ) {
          
               
              MascotaDAO mascotaDao = new MascotaDAO();
                List<cls_mascota> mascotas = mascotaDao.getMascotas(Integer.parseInt(session.getAttribute("id").toString()));
                session.setAttribute("mascotas", mascotas);
               
                vista = "vistas/mascotas/index.jsp";
                request.getRequestDispatcher(vista).forward(request, response);
          
            
         
           
        }}

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

        String accion = request.getServletPath();
        if (accion.equals("/mascotas/update")) {
            updateMascota(request, response);
        }else if(accion.equals("/mascotas/delete")){
            try {
                delete(request,response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(mascotas.class.getName()).log(Level.SEVERE, null, ex);
            }
            } else {
            createMascota(request, response);
        }
    }
    
     public void createMascota(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String vista = "";
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String duennio = request.getParameter("duennio");
        String tel = request.getParameter("tel");
        String usuario = session.getAttribute("id").toString();
            vista = "vistas/mascotas/index.jsp";
            
  System.out.println(usuario);
            cls_mascota mascota = new cls_mascota();
            mascota.setNombre(nombre);
            mascota.setDescripcion(descripcion);
            mascota.setNombreOwner(duennio);
            mascota.setTelOwner(tel);
            mascota.setIdusuario(Integer.parseInt(usuario));
          
            Boolean success = mascotaDAO2.agregar(mascota);
            if (success) {
                request.setAttribute("success", "mascota agregado correctamente");
                List<cls_mascota> usuarios = mascotaDAO2.getMascotas(Integer.parseInt(usuario));
                session.setAttribute("mascotas", usuarios);
            } else {
                request.setAttribute("error", "No se pudo agregar la mascota");
            }

            request.getRequestDispatcher(vista).forward(request, response);

        
    }
     
     public void updateMascota(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String vista = "/";
        String id = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String Descripcion= request.getParameter("descripcion");
        String duennio =request.getParameter("duennio");
        String tel = request.getParameter("tel");
        

        cls_mascota mascota = new cls_mascota();
        mascota.setId(Integer.parseInt(id));
        mascota.setNombre(nombre);
        mascota.setDescripcion(Descripcion);
        mascota.setTelOwner(tel);
        mascota.setNombreOwner(duennio);
        System.out.println(mascota.getIdusuario());
        System.out.println(mascota.getId());
              System.out.println(mascota.getNombreOwner());
        
        
                    Boolean success = mascotaDAO2.actualizar(mascota);
                    if (success) {

                        vista = "/mascotas";
                        //success
                        response.sendRedirect(vista );
                    } else {
                        vista = "/mascotas/edit?id=" + id;
                        //error
                        response.sendRedirect(vista + "&error=No se pudo actualizar la mascota");
                    }
               
    }
     
     public void delete(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {
       HttpSession session = request.getSession();
        String vista = "/vistas/mascotas/index.jsp";
        String id = request.getParameter("id");
                MascotaDAO mascotaDao = new MascotaDAO();
                cls_mascota mascota = mascotaDao.findMascota(Integer.parseInt(id));
        try {
            mascotaDao.delete(Integer.parseInt(id));
        } catch (SQLException ex) {
            Logger.getLogger(mascotas.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<cls_mascota> usuarios = mascotaDAO2.getMascotas(Integer.parseInt(session.getAttribute("id").toString()));
        session.setAttribute("mascotas", usuarios);
        request.getRequestDispatcher(vista).forward(request, response);
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
