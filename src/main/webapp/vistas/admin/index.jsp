<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
if(session.getAttribute("idpermiso") != null){
    int idpermiso = (int) session.getAttribute("idpermiso");
    String vista = "";
    if(idpermiso == 1){
        vista = "/users";
    }else if(idpermiso == 2 || idpermiso == 3){
        vista = "/citas";
    }
    response.sendRedirect("/users");
}
%>
<jsp:include page="/layout/nonav.jsp"></jsp:include>
        <div class="row vh-100 mx-5">
            <div class="col-12 col-lg-6 text-center align-self-center">
                <h1 class="display-4 fw-bold"><span class="text-primary">Sistema de administración</span> Veterinaria MyM</h1>
                <h2 class="display-6 fw-bold">Registrar administrador</h2>
            </div>
            <div class="col-12 col-lg-6 align-self-lg-center mx-auto">
                <div class="card border border-0 shadow-lg p-3 mb-5 bg-body rounded py-5">
                    <div class="card-body ">
                        <c:if test="${not empty ad}">
                            <div class="alert alert-danger" role="alert">
                                ${ad}
                            </div>
                        </c:if>
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
                        <form action="/admin" method="post">
                            <div class="row">
                                <div class="col-12 my-3">
                                    <div class="form-floating mb-3">
                                        <input type="text" class="form-control" id="fullname" name="fullname" placeholder="#" required>
                                        <label for="fullname">Nombre Completo</label>
                                    </div>
                                </div>
                                <div class="col-12 my-3">
                                    <div class="form-floating mb-3">
                                        <input type="text" class="form-control" id="username" name="username" placeholder="#" required>
                                        <label for="username">Nombre de usuario</label>
                                    </div>
                                </div>
                                <div class="col-12 my-3">
                                    <div class="form-floating mb-3">
                                        <input type="text" class="form-control" max="9" id="tel" name="tel" pattern="[0-9]{4}-[0-9]{4}" required placeholder="#">
                                        <label for="tel">Telefono</label>
                                    </div>
                                </div>
                                <div class="col-12 my-3">
                                    <div class="form-floating mb-3">
                                        <input type="password" class="form-control" id="password" name="password" placeholder="#" required>
                                        <label for="password">Contraseña</label>
                                    </div>
                                </div>
                                <div class="col-12 my-3">
                                    <div class="form-floating mb-3">
                                        <input type="password" class="form-control" id="passwordconf" name="passwordconf" placeholder="#" required>
                                        <label for="passwordconf">Confirmar Contraseña</label>
                                    </div>
                                </div>
                                <div class="d-grid gap-2 my-3">
                                    <button class="btn btn-primary" type="submit"  <c:if test="${not empty ad}"> disabled</c:if> >Registrar</button>
                                </div>
                                <div class="d-grid gap-2 my-3">
                                    <a href="/" class="" type="submit">Iniciar Sesión</a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>