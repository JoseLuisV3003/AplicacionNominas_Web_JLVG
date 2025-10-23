<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.aprendec.model.Empleado" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Búsqueda de Empleados</title>
    <style>
        table {
            border-collapse: collapse;
            width: 80%;
            margin-top: 20px;
        }
        table, th, td {
            border: 1px solid #ccc;
            padding: 8px;
        }
        th {
            background-color: #eee;
        }
        form > div {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>

<h2>Buscar Empleados</h2>

<form action="empleados" method="get">
    <!-- Indica que la opción es buscar -->
    <input type="hidden" name="opcion" value="buscar" />
    <div>
        <label for="dni">DNI:</label>
        <input type="text" id="dni" name="dni" />
    </div>
    <div>
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" />
    </div>
    <div>
        <label for="sexo">Sexo:</label>
        <select id="sexo" name="sexo">
            <option value="">-- Seleccionar --</option>
            <option value="M">Masculino</option>
            <option value="F">Femenino</option>
        </select>
    </div>
    <div>
        <label for="categoria">Categoría:</label>
        <input type="number" id="categoria" name="categoria" min="1" />
    </div>
    <div>
        <label for="anyosTrabajados">Años Trabajados:</label>
        <input type="number" id="anyosTrabajados" name="anyosTrabajados" min="0" />
    </div>
    <button type="submit">Buscar</button>
</form>

<%
    List<Empleado> resultados = (List<Empleado>) request.getAttribute("resultados");

    if (resultados != null && !resultados.isEmpty()) {
%>
    <h3>Resultados de la búsqueda</h3>
    <table>
        <thead>
            <tr>
                <th>DNI</th>
                <th>Nombre</th>
                <th>Sexo</th>
                <th>Categoría</th>
                <th>Años Trabajados</th>
            </tr>
        </thead>
        <tbody>
            <%
                for (Empleado e : resultados) {
            %>
            <tr>
                <td><%= e.getDni() %></td>
                <td><%= e.getNombre() %></td>
                <td><%= e.getSexo() %></td>
                <td><%= e.getCategoria() %></td>
                <td><%= e.getAnyosTrabajados() %></td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
<%
    } else if (request.getAttribute("resultados") != null) {
%>
    <p>No se encontraron empleados que coincidan con la búsqueda.</p>
<%
    }
%>

<p><a href="index.jsp">Volver</a></p>

</body>
</html>
