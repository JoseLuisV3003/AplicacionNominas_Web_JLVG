<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menu de Opciones</title>

<style>

  html{
    height: 100%;
  }

#titulo{
  text-align: center;
  font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
  background-color: white;
  }

#opciones{
  text-align: center;
  background-color: white;
  margin: 0 auto;
  
}

body{
    background-image: url("https://www.ayto-sanjuan.es/.galleries/Imagenes-2021/IES-Sotero-16-06-2021-2.jpeg");
      background-size: cover;
      background-repeat: no-repeat;
      background-position: center;
      background-size: cover;
}

#nombre{
  text-align: center;
  background-color: white;
}

</style>

</head>
<body>
 
 <h1 id="titulo">Empleados y Nominas</h1>
<p id="nombre">Jose Luis Valverde Gallego</p>

 <table border="1" id="opciones">
  <tr>
    <td><a href="mostrar.jsp"> Mostrar Empleados</a></td>
  </tr>
  <tr>
  <td><a href="salarioExistente.jsp">Buscar Salario por DNI</a>
  </tr>
  <tr>
  <td><a href="modificarDatos.jsp">Modificar datos de un empleado</a>
  </tr>
  
</table>

</body>
</html>