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
            </div>
            <div class="col-12 col-lg-6 align-self-lg-center mx-auto">
                <div class="card border border-0 shadow-lg p-3 mb-5 bg-body rounded py-5">
                    <div class="card-body ">
                        <c:if test="${not empty param.error}">
                            <div class="alert alert-danger" role="alert">
                                <h4 class="alert-heading">Credenciales incorrectas</h4>
                            </div>
                        </c:if>
                        <form action="/login" method="post">
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="username" placeholder="#" name="username">
                                <label for="username">Username</label>
                            </div>
                            <div class="form-floating mb-3">
                                <input type="password" class="form-control" id="password" placeholder="#" name="password">
                                <label for="password">Password</label>
                            </div>
                            <div class="d-grid gap-2">
                                <button class="btn btn-primary" type="submit">Login</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>