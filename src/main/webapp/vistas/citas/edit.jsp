<%-- 
    Document   : edit
    Created on : 11-20-2022, 11:45:35 PM
    Author     : Mery Acevedo
--%>
<%@page import="modelos.cls_registro"%>
<%@page import="modelos.cls_mascota"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
    cls_registro registro = (cls_registro)request.getAttribute("registro");
%>
<jsp:include page="/layout/nav.jsp"></jsp:include>
        <div class="container-fluid container-lg" style="height: 100%">
            <div class="row mx-5" style="height: 100%">
                <!–– Cambiar segun se necesite 1 columna sin form 2 con form ––>

                <div class="col-12 align-self-lg-center d-flex align-items-center mx-auto" style="height: 90%">
                    <div>
                        <h1 class="display-4 text-center fw-bold mb-5"><span class="text-primary">Editar mascota</span> </h1>
                        <div class="card border border-0 shadow-lg p-3 bg-body rounded py-3 align-middle" >
                            <div class="card-body ">
                                <c:if test="${param.error != null}">
                                    <div class="alert alert-danger" role="alert">
                                        <p> ${param.error} </p>
                                    </div>
                                </c:if>
                                <c:if test="${param.success != null}">
                                    <div class="alert alert-success" role="alert">
                                        <p> ${param.success} </p>
                                    </div>
                                </c:if>
                                <form action="/citas/update" method="post">
                                     <input type="hidden" name="idmascota" id="idmascota" <%if(registro!=null){%> value="<%=registro.getIdmascota()%>" <%}%>>
                                   
                                    <input type="hidden" name="id" id="id" <%if(registro!=null){%> value="<%=registro.getId()%>" <%}%>>
                                    <div class="row">
                                       <div class="col-12 my-3">
                                    <div class="form-floating mb-3">
                                        <input type="text" value="<%=registro.getMascota()%>" class="form-control" id="nombre" name="nombre" placeholder="#" readonly>
                                        <label for="nombre">Nombre Mascota</label>
                                    </div>
                                </div>
                                <div class="col-12 my-3">
                                    <div class="form-floating mb-3">
                                        <input type="date" class="form-control" value="<%=registro.getFecha()%>" id="date" name="date" placeholder="#" required>
                                        <label for="date">Fecha</label>
                                    </div>
                                </div>
                                <div class="col-12 my-3">
                                    <div class="form-floating mb-3">
                                        <input type="text" class="form-control" max="9" id="tel" value="<%=registro.getSintomas()%>" name="sintomas"  required placeholder="#">
                                        <label for="sintomas">Sintomas</label>
                                    </div>
                                </div>
                              
                                        <div class="d-grid gap-2 my-2">
                                            <button class="btn btn-primary" type="submit" <% if((int)session.getAttribute("idpermiso") != 3){ %> disabled <% } %>>Actualizar</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
