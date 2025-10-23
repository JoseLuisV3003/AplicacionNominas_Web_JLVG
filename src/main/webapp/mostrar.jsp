<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.aprendec.dao.EmpleadoDAO" %>
<%@ page import="com.aprendec.model.Empleado" %>
<%@ page import="java.sql.SQLException" %>
<%
    EmpleadoDAO dao = new EmpleadoDAO();
    List<Empleado> lista = null;
    try {
        lista = dao.obtenerEmpleados();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    request.setAttribute("lista", lista);
%>

<html>
<head>
  <title>Lista de Empleados</title>
</head>
<body>
  <h1>Empleados disponibles</h1>
  <table border="1">
    <tr>
      <th>DNI</th>
      <th>Nombre</th>
      <th>Sexo</th>
      <th>Categoría</th>
      <th>Años Trabajados</th>
    </tr>
    <c:forEach var="empleado" items="${lista}">
      <tr>
        <td>${empleado.dni}</td>
        <td>${empleado.nombre}</td>
        <td>${empleado.sexo}</td>
        <td>${empleado.categoria}</td>
        <td>${empleado.anyosTrabajados}</td>
      </tr>
    </c:forEach>
    <c:if test="${empty lista}">
      <tr>
        <td colspan="5">No hay empleados para mostrar.</td>
      </tr>
    </c:if>
  </table>
  
  <p><a href="index.jsp">Volver</a></p>
</body>
</html>
