<%@page import="modelos.cls_mascota"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="utils.utils" %>
<%@ page import="java.util.List" %>
<jsp:include page="/layout/nav.jsp"></jsp:include>
<%
    List<cls_mascota> mascotas = (List<cls_mascota>)session.getAttribute("mascotas");
%>

        <div class="row mx-5" style="height: 100%">
            <!–– Cambiar segun se necesite 1 columna sin form 2 con form ––>
            <div class="col-12 col-lg-4 align-self-lg-center mx-auto" style="height: 90%">
                <div class="card border border-0 shadow-lg p-3 mb-5 bg-body rounded py-5 h-100">
                    <div class="card-body ">
                       
                        <form action="/mascotas" method="post">
                            <div class="row">
                                <div class="col-12 my-3">
                                    <div class="form-floating mb-3">
                                        <input type="text" class="form-control" id="nombre" name="nombre" placeholder="#" required>
                                        <label for="fullname">Nombre </label>
                                         </div>
                                    
                                </div>
                                <div class="col-12 my-3">
                                    <div class="form-floating mb-3">
                                        <input type="text" class="form-control" id="username" name="descripcion" placeholder="#" required>
                                        <label for="username">Descripcion</label>
                                    </div>
                                </div>
                                <div class="col-12 my-3">
                                    <div class="form-floating mb-3">
                                        <input type="text" class="form-control" max="9" id="tel" name="duennio"  required placeholder="#">
                                        <label for="tel">Nombre dueño</label>
                                    </div>
                                </div>
                                <div class="col-12 my-3">
                                    <div class="form-floating mb-3">
                                        <input type="text" class="form-control" id="password" name="tel" pattern="[0-9]{4}-[0-9]{4}" placeholder="#" required>
                                        <label for="password">Telefono</label>
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
            <div class="col-12 col-lg-8  align-self-center my-3 p-4" style="height: 90%;">
                <h1 class="display-4 fw-bold text-center"><span class="text-primary">Mascotas</span> </h1>
                <div class="my-3 p-4" style="overflow-y: scroll; height: 90%;">
                    <table class="table table-hover" id="example">
                        <thead>
                            <tr>
                                <th scope="col">Nombre</th>
                                <th scope="col">Descripcion</th>
                                <th scope="col">Dueño</th>
                                <th scope="col">telefono </th>
                                <th scope="col">Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${mascotas}" var="mascota">
                                <tr>
                                    <td>${mascota.getNombre()}</td>
                                    <td>${mascota.getDescripcion()}</td>
                                    <td>${mascota.getNombreOwner()}</td>
                                    <td>${mascota.getTelOwner()}</td>
                                    <td class="d-flex">
                                        <a href="/mascotas/edit?id=${mascota.getId()}" class="btn btn-primary mx-2"><i class="fa-regular fa-pen-to-square"></i></a>
                                         <a href="/vistas/citas/formCitas?id=${mascota.getId()}" class="btn btn-primary mx-2">Agendar Cita</a>
                                        <a href="/mascotas/delete?id=${mascota.getId()}" class="btn btn-danger mx-2"><i class="fa-regular fa-trash-can"></i></a>
                                        

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