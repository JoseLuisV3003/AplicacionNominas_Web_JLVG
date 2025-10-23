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

import com.aprendec.dao.NominaDAO;
import com.aprendec.model.Nomina;

/**
 * Servlet implementation class NominaController
 */
@WebServlet(description = "Administra peticiones para la tabla nominas", urlPatterns = { "/nominas" })
public class NominaController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public NominaController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String opcion = request.getParameter("opcion");

        if (opcion == null) {
            opcion = "listar"; // Opción por defecto
        }

        NominaDAO nominaDAO = new NominaDAO();

        try {
            switch (opcion) {
                case "crear":
                    // Mostrar formulario para crear nómina
                    RequestDispatcher crearView = request.getRequestDispatcher("/views/nomina/crear.jsp");
                    crearView.forward(request, response);
                    break;

                case "listar":
                    // Listar todas las nóminas
                    List<Nomina> listaNominas = nominaDAO.obtenerNominas();
                    request.setAttribute("lista", listaNominas);
                    RequestDispatcher listarView = request.getRequestDispatcher("/views/nomina/listar.jsp");
                    listarView.forward(request, response);
                    break;

                case "editar":
                    // Obtener nómina para editar
                    String dniEditar = request.getParameter("dni_empleado");
                    Nomina nominaEdit = nominaDAO.obtenerNomina(dniEditar);
                    request.setAttribute("nomina", nominaEdit);
                    RequestDispatcher editarView = request.getRequestDispatcher("/views/nomina/editar.jsp");
                    editarView.forward(request, response);
                    break;

                case "eliminar":
                    // Eliminar nómina
                    String dniEliminar = request.getParameter("dni_empleado");
                    nominaDAO.eliminar(dniEliminar);
                    response.sendRedirect("nominas?opcion=listar");
                    break;

                case "buscar":
                    // Buscar nómina por DNI para mostrar salario
                    String dniBuscar = request.getParameter("dni_empleado");
                    Nomina nominaBuscada = nominaDAO.obtenerNomina(dniBuscar);
                    request.setAttribute("nominaEncontrada", nominaBuscada);
                    RequestDispatcher buscarView = request.getRequestDispatcher("/salarioExistente.jsp");
                    buscarView.forward(request, response);
                    break;

                default:
                    // Por defecto, listar nóminas
                    List<Nomina> listaDefault = nominaDAO.obtenerNominas();
                    request.setAttribute("lista", listaDefault);
                    RequestDispatcher defaultView = request.getRequestDispatcher("/views/nomina/listar.jsp");
                    defaultView.forward(request, response);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error en la base de datos");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String opcion = request.getParameter("opcion");
        NominaDAO nominaDAO = new NominaDAO();

        try {
            if ("guardar".equals(opcion)) {
                Nomina nomina = new Nomina();

                nomina.setDni_empleado(request.getParameter("dni_empleado"));
                nomina.setSueldo(Integer.parseInt(request.getParameter("sueldo")));

                nominaDAO.guardar(nomina);
                response.sendRedirect("nominas?opcion=listar");

            } else if ("editar".equals(opcion)) {
                Nomina nomina = new Nomina();

                nomina.setDni_empleado(request.getParameter("dni_empleado"));
                nomina.setSueldo(Integer.parseInt(request.getParameter("sueldo")));

                nominaDAO.editar(nomina);
                response.sendRedirect("nominas?opcion=listar");
            } else {
                response.sendRedirect("nominas?opcion=listar");
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
