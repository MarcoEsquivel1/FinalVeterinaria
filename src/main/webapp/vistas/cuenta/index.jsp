<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/layout/nav.jsp"></jsp:include>
        <div class="container-fluid container-lg h-100">
            <div class="row mx-5" style="height: 100%">
                <!–– Cambiar segun se necesite 1 columna sin form 2 con form ––>

                <div class="col-12 align-self-lg-center d-flex align-items-center mx-auto" style="height: 90%">
                    <div>
                        <h1 class="display-4 text-center fw-bold mb-5"><span class="text-primary">Cuenta</span> </h1>
                        <div class="card border border-0 shadow-lg p-3 bg-body rounded py-3 align-middle" >
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
                                <form action="/cuenta" method="post">
                                    <input type="hidden" name="id" id="id" <%if(session.getAttribute("id")!=null){%> value="<%=session.getAttribute("id")%>" <%}%>>
                                    <div class="row">
                                        <div class="col-12 my-3">
                                            <div class="form-floating mb-3">
                                                <input type="text" class="form-control" id="fullname" name="fullname" placeholder="#" required <%if(session.getAttribute("fullname")!=null){%> value="<%=session.getAttribute("fullname")%>" <%}%>>
                                                <label for="fullname">Nombre Completo</label>
                                            </div>
                                        </div>
                                        <div class="col-12 my-3">
                                            <div class="form-floating mb-3">
                                                <input type="text" class="form-control" id="username" name="username" placeholder="#" disabled <%if(session.getAttribute("username")!=null){%> value="<%=session.getAttribute("username")%>" <%}%>>
                                                <label for="username">Nombre de usuario</label>
                                            </div>
                                        </div>
                                        <div class="col-12 my-3">
                                            <div class="form-floating mb-3">
                                                <input type="text" class="form-control" max="9" id="tel" name="tel" pattern="[0-9]{4}-[0-9]{4}" required placeholder="#" <%if(session.getAttribute("tel")!=null){%> value="<%=session.getAttribute("tel")%>" <%}%>>
                                                <label for="tel">Telefono</label>
                                            </div>
                                        </div>
                                        <div class="col-12 my-3">
                                            <div class="form-floating mb-3">
                                                <input type="password" class="form-control" id="newpassword" name="newpassword" placeholder="#">
                                                <label for="newpassword">Nueva Contraseña</label>
                                            </div>
                                        </div>
                                        <div class="col-12 my-3">
                                            <div class="form-floating mb-3">
                                                <input type="password" class="form-control" id="password" name="password" placeholder="#">
                                                <label for="password">Contraseña Actual</label>
                                            </div>
                                        </div>
                                        <div class="d-grid gap-2 my-3">
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