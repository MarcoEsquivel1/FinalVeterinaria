<%-- 
    Document   : formCitas
    Created on : 11-20-2022, 11:24:31 PM
    Author     : Mery Acevedo
--%><%@page import="modelos.cls_mascota"%>
<%@page import="modelos.cls_registro"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="modelos.cls_usuario" %>
<%@ page import="utils.utils" %>
<%@ page import="java.util.List" %>
<%
    List<cls_registro> citas = (List<cls_registro>)session.getAttribute("registro");
%>
<%
    cls_mascota mascota = (cls_mascota)request.getAttribute("mascota");
%>
<jsp:include page="/layout/nav.jsp"></jsp:include>
        <div class="row mx-5" style="height: 100%">
            <!–– Cambiar segun se necesite 1 columna sin form 2 con form ––>
            <div class="col-12 col-lg-4 align-self-lg-center mx-auto" style="height: 90%">
                <div class="card border border-0 shadow-lg p-3 mb-5 bg-body rounded py-5 h-100">
                    <div class="card-body ">
                        <c:if test="${not empty error}">
                            <div class="alert alert-danger" role="alert">
                                ${error}
                            </div>
                        </c:if>
                        <c:if test="${not empty success}">
                            <div class="alert alert-success" role="alert">
                               ${success}
                            </div>
                        </c:if>
                        <form action="/citas" method="post">
                            <input type="text" value="<%=mascota.getId()%>" class="form-control" id="id" name="idmascota" hidden placeholder="#" readonly>
                            <div class="row">
                                <div class="col-12 my-3">
                                    <div class="form-floating mb-3">
                                        <input type="text" value="<%=mascota.getNombre()%>" class="form-control" id="nombre" name="nombre" placeholder="#" readonly>
                                        <label for="fullname">Nombre Mascota</label>
                                    </div>
                                </div>
                                <div class="col-12 my-3">
                                    <div class="form-floating mb-3">
                                        <input type="date" class="form-control" id="date" name="date" placeholder="#" required>
                                        <label for="username">Fecha</label>
                                    </div>
                                </div>
                                <div class="col-12 my-3">
                                    <div class="form-floating mb-3">
                                        <input type="text" class="form-control" max="9" id="tel" name="sintomas"  required placeholder="#">
                                        <label for="tel">Sintomas</label>
                                    </div>
                                </div>
                              
                                
                                <div class="d-grid gap-2 my-3">
                                    <button class="btn btn-primary" type="submit">Agregar</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>