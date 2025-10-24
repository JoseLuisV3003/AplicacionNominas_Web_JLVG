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

        body{
    background-image: url("https://www.ayto-sanjuan.es/.galleries/Imagenes-2021/IES-Sotero-16-06-2021-2.jpeg");
      background-size: cover;
      background-repeat: no-repeat;
      background-position: center;
      background-size: cover;
      background-attachment: fixed;
        }

div {
  background-color: white;
  border: 1px solid #ccc;          
  border-radius: 8px;              
  padding: 20px;                   
  margin: 15px auto;              
  width: 80%;                      
  max-width: 400px;               
  box-shadow: 0 4px 8px rgba(0,0,0,0.1); 
}

#volver{
    background-color: white;
  border: 1px solid #ccc;          
  border-radius: 8px;              
  padding: 20px;                   
  margin: 15px auto;              
  width: 20%;                      
  max-width: 100px;               
  box-shadow: 0 4px 8px rgba(0,0,0,0.1); 
  text-align: center;
}

#buscar {
  display: block;      /* Hace que ocupe toda la línea */
  margin: 10px auto;   /* Lo centra horizontalmente */
  padding: 10px 20px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
}

#buscar:hover {
  background-color: #0056b3;
}

h2{
	text-align: center;
	background-color: white;
}

#resultados{
    background-color: white;
    text-align: center;
    margin: 0 auto;
}

#sinDatos{
    background-color: white;
  border: 1px solid #ccc;          
  border-radius: 8px;              
  padding: 20px;                   
  margin: 15px auto;              
  width: 80%;                      
  max-width: 400px;               
  box-shadow: 0 4px 8px rgba(0,0,0,0.1); 
  text-align: center;
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
    <button id="buscar" type="submit">Buscar</button>
</form>

<%
    List<Empleado> resultados = (List<Empleado>) request.getAttribute("resultados");

    if (resultados != null && !resultados.isEmpty()) {
%>
    <h3>Resultados de la búsqueda</h3>
    
    <table id="resultados">
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
    <p id="sinDatos">No se ha encontrado ningun empleado que coincida con los datos proporcionados</p>
<%
    }
%>

<p id="volver"><a href="index.jsp">Volver</a></p>

</body>
</html>
