<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Buscar Salario del Empleado</title>
</head>
<body>
    <h2>Buscar Salario por DNI del Empleado</h2>
    <form action="nominas" method="get">
        <input type="hidden" name="opcion" value="buscar" />
        <label for="dni_empleado">DNI del Empleado:</label>
        <input type="text" id="dni_empleado" name="dni_empleado" required />
        <button type="submit">Buscar Salario</button>
    </form>

    <hr />

    <%
        // Mostrar resultado si existe atributo "nominaEncontrada" en request
        if (request.getAttribute("nominaEncontrada") != null) {
            com.aprendec.model.Nomina nomina = (com.aprendec.model.Nomina) request.getAttribute("nominaEncontrada");
            if (nomina.getDni_empleado() != null) {
    %>
                <h3>Salario encontrado para DNI: <%= nomina.getDni_empleado() %></h3>
                <p>Sueldo: <%= nomina.getSueldo() %> €</p>
    <%
            } else {
    %>
                <p>No se encontró salario para el DNI proporcionado.</p>
    <%
            }
        }
    %>
    
   <p><a href="index.jsp">Volver</a></p>
    
</body>
</html>
