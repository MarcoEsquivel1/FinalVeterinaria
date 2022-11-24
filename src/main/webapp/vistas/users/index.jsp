<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="modelos.cls_usuario" %>
<%@ page import="utils.utils" %>
<%@ page import="java.util.List" %>
<%
    List<cls_usuario> usuarios = (List<cls_usuario>)session.getAttribute("usuarios");
%>
<jsp:include page="/layout/nav.jsp"></jsp:include>
        <div class="row mx-5" style="height: 100%">
            <!–– Cambiar segun se necesite 1 columna sin form 2 con form ––>
            <div class="col-12 col-lg-6 align-self-lg-center mx-auto" style="height: 90%">
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
                        <form action="/users" method="post">
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
                                    <button class="btn btn-primary" type="submit">Agregar</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="col-12 col-lg-6  align-self-center my-3 p-4" style="height: 90%;">
                <h1 class="display-4 fw-bold text-center"><span class="text-primary">Usuarios</span> </h1>
                <div class="my-3 p-4" style="overflow-y: scroll; height: 90%;">
                    <table class="table table-hover" id="example">
                        <thead>
                            <tr>
                                <th scope="col">Fullname</th>
                                <th scope="col">Username</th>
                                <th scope="col">Tel</th>
                                <th scope="col">idPermiso</th>
                                <th scope="col">Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${usuarios}" var="usuario">
                                <tr>
                                    <td>${usuario.getFullname()}</td>
                                    <td>${usuario.getUsername()}</td>
                                    <td>${usuario.getTel()}</td>
                                    <td>${utils.convertIdpermiso(usuario.getIdpermiso())}</td>
                                    <td class="d-flex">
                                        <a href="/users/edit?id=${usuario.getId()}" class="btn btn-primary mx-2"><i class="fa-regular fa-pen-to-square"></i></a>
                                        <a href="/users/delete?id=${usuario.getId()}" class="btn btn-danger mx-2"><i class="fa-regular fa-trash-can"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js.5.1.js"></script>

<script src="https://cdn.datatables.net/buttons/2.2.3/js/dataTables.buttons.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
<script src="https://cdn.datatables.net/buttons/2.2.3/js/buttons.print.min.js"></script>
<script src="https://cdn.datatables.net/buttons/2.2.3/js/buttons.html5.min.js"></script>
<script src="https://cdn.datatables.net/buttons/2.3.2/css/buttons.dataTables.min.css"></script>

<script src="https://cdn.datatables.net/1.13.1/css/jquery.dataTables.min.css"></script>
<script type="application/javascript">
    $(document).ready( function () {
    $('#example').DataTable( {
    paging: false,
      dom: 'Bfrtip',
   
} );
} );
</script>