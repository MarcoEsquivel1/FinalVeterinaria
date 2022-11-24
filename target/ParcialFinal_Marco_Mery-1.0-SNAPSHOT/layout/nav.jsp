<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% int idpermiso = (int)(session.getAttribute("idpermiso")); %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Veterinaria</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
    </head>
    <body>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.min.js" integrity="sha384-IDwe1+LCz02ROU9k972gdyvl+AESN10+x7tBKgc9I5HFtuNz0wWnPclzo6p9vxnk" crossorigin="anonymous"></script>

            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
              <div class="container-fluid px-5">
                <a class="navbar-brand">Sistema de gestión veterinaria</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                  <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                  <ul class="navbar-nav ms-auto me-5 mb-2 mb-lg-0">
                  <%
                    if(idpermiso==1){
                  %>
                    <li class="nav-item">
                      <a class="nav-link" href="/users">Usuarios</a>
                    </li>
                  <%
                    }else if(idpermiso==2 || idpermiso==3){
                  %>
                    <li class="nav-item">
                      <a class="nav-link" href="/mascotas">Mascotas</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link" href="/citas">Citas</a>
                    </li>
                  <%
                    }
                  %>
                    <li class="nav-item">
                      <a class="nav-link" href="/cuenta">Cuenta</a>
                    </li>
                  </ul>
                  <div class="d-flex">
                    <a class="btn btn-outline-success" href="/logout" role="button">Cerrar sesión</a>
                  </div>
                </div>
              </div>
            </nav>
            <div class="container-fluid" style="height: 94vh">
