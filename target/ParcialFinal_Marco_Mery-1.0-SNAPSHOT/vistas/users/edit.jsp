<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="modelos.cls_usuario" %>
<%
    cls_usuario user = (cls_usuario)request.getAttribute("usuario");
%>
<jsp:include page="/layout/nav.jsp"></jsp:include>
        <div class="container-fluid container-lg" style="height: 100%">
            <div class="row mx-5" style="height: 100%">
                <!–– Cambiar segun se necesite 1 columna sin form 2 con form ––>

                <div class="col-12 align-self-lg-center d-flex align-items-center mx-auto" style="height: 90%">
                    <div>
                        <h1 class="display-4 text-center fw-bold mb-5"><span class="text-primary">Editar usuario</span> </h1>
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
                                <form action="/users/update" method="post">
                                    <input type="hidden" name="id" id="id" <%if(user!=null){%> value="<%=user.getId()%>" <%}%>>
                                    <div class="row">
                                        <div class="col-12 my-2">
                                            <div class="form-floating mb-3">
                                                <input type="text" class="form-control" id="fullname" name="fullname" placeholder="#" required <%if(user!=null){%> value="<%=user.getFullname()%>" <%}%>>
                                                <label for="fullname">Nombre Completo</label>
                                            </div>
                                        </div>
                                        <div class="col-12 my-2">
                                            <div class="form-floating mb-3">
                                                <input type="text" class="form-control disabled" id="username" name="username" placeholder="#" readonly <%if(user!=null){%> value="<%=user.getUsername()%>" <%}%>>
                                                <label for="username">Nombre de usuario</label>
                                            </div>
                                        </div>
                                        <div class="col-12 my-2">
                                            <div class="form-floating mb-3">
                                                <input type="text" class="form-control" max="9" id="tel" name="tel" pattern="[0-9]{4}-[0-9]{4}" required placeholder="#" <%if(user!=null){%> value="<%=user.getTel()%>" <%}%>>
                                                <label for="tel">Telefono</label>
                                            </div>
                                        </div>
                                        <div class="col-12 my-2">
                                            <div class="form-floating mb-3">
                                                <select class="form-select" id="idpermiso" name="idpermiso" required>
                                                    <option value="2" <%if(user!=null && user.getIdpermiso()==2){%> selected <%}%>>Solo Lectura</option>
                                                    <option value="3" <%if(user!=null && user.getIdpermiso()==3){%> selected <%}%>>Modificación</option>
                                                </select>
                                                <label for="idpermiso">Permiso</label>
                                            </div>
                                        </div>
                                        <div class="col-12 my-2">
                                            <div class="form-floating mb-3">
                                                <input type="password" class="form-control" id="newpassword" name="newpassword" value="" placeholder="#">
                                                <label for="newpassword">Nueva Contraseña</label>
                                            </div>
                                        </div>
                                        <div class="col-12 my-2">
                                            <div class="form-floating mb-3">
                                                <input type="password" class="form-control" id="adminpassword" name="adminpassword" value="" placeholder="#">
                                                <label for="adminpassword">Contraseña del administrador</label>
                                            </div>
                                        </div>
                                        <div class="d-grid gap-2 my-2">
                                            <button class="btn btn-primary" type="submit">Actualizar</button>
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