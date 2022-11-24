<%@page import="modelos.cls_registro"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/layout/nav.jsp"></jsp:include>
<%
    List<cls_registro> registros = (List<cls_registro>)session.getAttribute("registro");
%>
        <div class="row mx-12" style="height: 100%">
            <!–– Cambiar segun se necesite 1 columna sin form 2 con form ––>
            
            <div class="col-12 col-lg-12  align-self-center my-3 p-4" style="height: 90%;">
                 <div class="col-12 col-lg-12  align-self-center my-3 p-4" style="height: 90%;">
                <h1 class="display-4 fw-bold text-center"><span class="text-primary">Citas</span> </h1>
                <div class="my-3 p-4" style="overflow-y: scroll; height: 90%;">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col">Mascota</th>
                                <th scope="col">Sintomas</th>
                                <th scope="col">Fechas</th>
                                <th scope="col">Dueño</th>
                                <th scope="col">Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${registros}" var="registro">
                                <tr>
                                    <td>${registro.getIdmascota()}</td>
                                    <td>${registro.getSintomas()}</td>
                                    <td>${registro.getFecha()}</td>
                                    
                                    <td class="d-flex">
                                        <a href="/citas/edit?id=${registro.getId()}" class="btn btn-primary mx-2 <% if((int)session.getAttribute("idpermiso") != 3){ %> disabled <% } %>"><i class="fa-regular fa-pen-to-square"></i></a>
                                        <a href="/citas/delete?id=${mascota.getId()}" class="btn btn-danger mx-2 <% if((int)session.getAttribute("idpermiso") != 3){ %> disabled <% } %>"><i class="fa-regular fa-trash-can"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            </div>
        </div>
    </div>
</body>
</html>