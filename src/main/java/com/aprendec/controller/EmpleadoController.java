package com.aprendec.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aprendec.dao.EmpleadoDAO;
import com.aprendec.model.Empleado;

/**
 * Servlet implementation class EmpleadoController
 */
@WebServlet(description = "Administra peticiones para la tabla empleados", urlPatterns = { "/empleados" })
public class EmpleadoController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EmpleadoController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String opcion = request.getParameter("opcion");

        if (opcion == null) {
            opcion = "listar"; // Opción por defecto
        }

        EmpleadoDAO empleadoDAO = new EmpleadoDAO();

        try {
            switch (opcion) {
                case "crear":
                    // Mostrar formulario para crear empleado
                    RequestDispatcher crearView = request.getRequestDispatcher("/views/empleado/crear.jsp");
                    crearView.forward(request, response);
                    break;

                case "listar":
                    // Listar todos los empleados
                    List<Empleado> listaEmpleados = empleadoDAO.obtenerEmpleados();
                    request.setAttribute("lista", listaEmpleados);
                    RequestDispatcher listarView = request.getRequestDispatcher("/views/empleado/listar.jsp");
                    listarView.forward(request, response);
                    break;

                case "editar":
                    // Obtener empleado para editar
                    int dniEditar = Integer.parseInt(request.getParameter("dni"));
                    Empleado empleadoEdit = empleadoDAO.obtenerEmpleado(dniEditar);
                    request.setAttribute("empleado", empleadoEdit);
                    RequestDispatcher editarView = request.getRequestDispatcher("/views/empleado/editar.jsp");
                    editarView.forward(request, response);
                    break;

                case "eliminar":
                    // Eliminar empleado
                    int dniEliminar = Integer.parseInt(request.getParameter("dni"));
                    empleadoDAO.eliminar(dniEliminar);
                    response.sendRedirect("empleados?opcion=listar");
                    break;
                    
                 
                    
                    
                case "buscar":
                    // Obtiene los datos de busqueda
                    String dni = request.getParameter("dni");
                    String nombre = request.getParameter("nombre");
                    String sexo = request.getParameter("sexo");
                    String categoriaStr = request.getParameter("categoria");
                    String anyosStr = request.getParameter("anyosTrabajados");

                    // Convertir parámetros numéricos si no están vacíos
                    Integer categoria = null;
                    Integer anyosTrabajados = null;

                    try {
                        if (categoriaStr != null && !categoriaStr.isEmpty()) {
                            categoria = Integer.parseInt(categoriaStr);
                        }
                        if (anyosStr != null && !anyosStr.isEmpty()) {
                            anyosTrabajados = Integer.parseInt(anyosStr);
                        }
                    } catch (NumberFormatException e) {
                        // Manejar error formato inválido si quieres
                    }

                    // Llamar a método DAO para búsqueda
                    List<Empleado> resultadosBusqueda = empleadoDAO.buscarEmpleados(dni, nombre, sexo, categoria, anyosTrabajados);

                    // Poner resultados en request
                    request.setAttribute("resultados", resultadosBusqueda);

                    // Enviar a modificarDatos.jsp para mostrar resultados
                    RequestDispatcher rd = request.getRequestDispatcher("modificarDatos.jsp");
                    rd.forward(request, response);
                    break;

                    
                    
                 

                default:
                    // Por defecto, listar empleados
                    List<Empleado> listaDefault = empleadoDAO.obtenerEmpleados();
                    request.setAttribute("lista", listaDefault);
                    RequestDispatcher defaultView = request.getRequestDispatcher("/views/empleado/listar.jsp");
                    defaultView.forward(request, response);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Aquí podrías redirigir a una página de error
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error en la base de datos");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String opcion = request.getParameter("opcion");
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();

        try {
            if ("guardar".equals(opcion)) {
                Empleado empleado = new Empleado();

                empleado.setDni(request.getParameter("dni"));
                empleado.setNombre(request.getParameter("nombre"));
                empleado.setSexo(request.getParameter("sexo"));
                empleado.setCategoria(Integer.parseInt(request.getParameter("categoria")));
                empleado.setAnyosTrabajados(Integer.parseInt(request.getParameter("anyosTrabajados")));

                empleadoDAO.guardar(empleado);
                response.sendRedirect("empleados?opcion=listar");

            } else if ("editar".equals(opcion)) {
                Empleado empleado = new Empleado();

                empleado.setDni(request.getParameter("dni"));
                empleado.setNombre(request.getParameter("nombre"));
                empleado.setSexo(request.getParameter("sexo"));
                empleado.setCategoria(Integer.parseInt(request.getParameter("categoria")));
                empleado.setAnyosTrabajados(Integer.parseInt(request.getParameter("anyosTrabajados")));

                empleadoDAO.editar(empleado);
                response.sendRedirect("empleados?opcion=listar");
            } else {
                response.sendRedirect("empleados?opcion=listar");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error en la base de datos");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Formato de número inválido");
        }
    }
}
