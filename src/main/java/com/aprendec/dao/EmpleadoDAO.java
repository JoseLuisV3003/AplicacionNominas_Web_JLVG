package com.aprendec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aprendec.conexion.Conexion;
import com.aprendec.model.Empleado;

public class EmpleadoDAO {
    private Connection connection;
    private PreparedStatement statement;
    private boolean estadoOperacion;

    // Guardar empleado
    public boolean guardar(Empleado empleado) throws SQLException {
        String sql = null;
        estadoOperacion = false;
        connection = obtenerConexion();

        try {
            connection.setAutoCommit(false);
            sql = "INSERT INTO empleados (dni, nombre, sexo, categoria, anyos_trabajados) VALUES (?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);

            statement.setString(1, empleado.getDni());
            statement.setString(2, empleado.getNombre());
            statement.setString(3, empleado.getSexo());
            statement.setInt(4, empleado.getCategoria());
            statement.setInt(5, empleado.getAnyosTrabajados());

            estadoOperacion = statement.executeUpdate() > 0;

            connection.commit();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        }

        return estadoOperacion;
    }

    // Editar empleado
    public boolean editar(Empleado empleado) throws SQLException {
        String sql = null;
        estadoOperacion = false;
        connection = obtenerConexion();

        try {
            connection.setAutoCommit(false);
            sql = "UPDATE empleados SET nombre=?, sexo=?, categoria=?, anyos_trabajados=? WHERE dni=?";
            statement = connection.prepareStatement(sql);

            statement.setString(1, empleado.getNombre());
            statement.setString(2, empleado.getSexo());
            statement.setInt(3, empleado.getCategoria());
            statement.setInt(4, empleado.getAnyosTrabajados());
            statement.setString(5, empleado.getDni());

            estadoOperacion = statement.executeUpdate() > 0;

            connection.commit();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        }

        return estadoOperacion;
    }

    // Eliminar empleado
    public boolean eliminar(int dni) throws SQLException {
        String sql = null;
        estadoOperacion = false;
        connection = obtenerConexion();

        try {
            connection.setAutoCommit(false);
            sql = "DELETE FROM empleados WHERE dni=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, dni);

            estadoOperacion = statement.executeUpdate() > 0;

            connection.commit();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        }

        return estadoOperacion;
    }

    // Obtener lista de empleados
    public List<Empleado> obtenerEmpleados() throws SQLException {
    	System.out.println("Conectado a la base de datos");
        ResultSet resultSet = null;
        List<Empleado> listaEmpleados = new ArrayList<>();

        String sql = null;
        connection = obtenerConexion();

        try {
            sql = "SELECT * FROM empleados";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Empleado e = new Empleado();
                e.setDni(resultSet.getString("dni"));
                e.setNombre(resultSet.getString("nombre"));
                e.setSexo(resultSet.getString("sexo"));
                e.setCategoria(resultSet.getInt("categoria"));
                e.setAnyosTrabajados(resultSet.getInt("anyosTrabajados"));
                listaEmpleados.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaEmpleados;
    }

    // Obtener empleado por dni
    public Empleado obtenerEmpleado(int dni) throws SQLException {
        ResultSet resultSet = null;
        Empleado e = new Empleado();

        String sql = null;
        connection = obtenerConexion();

        try {
            sql = "SELECT * FROM empleados WHERE dni = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, dni);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                e.setDni(resultSet.getString("dni"));
                e.setNombre(resultSet.getString("nombre"));
                e.setSexo(resultSet.getString("sexo"));
                e.setCategoria(resultSet.getInt("categoria"));
                e.setAnyosTrabajados(resultSet.getInt("anyosTrabajados"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return e;
    }
    
    public List<Empleado> buscarEmpleados(String dni, String nombre, String sexo, Integer categoria, Integer anyosTrabajados) throws SQLException {
        List<Empleado> listaEmpleados = new ArrayList<>();
        ResultSet resultSet = null;
        StringBuilder sql = new StringBuilder("SELECT * FROM empleados WHERE 1=1");
        List<Object> parametros = new ArrayList<>();
        
        // Construir consulta dinámica según parámetros no nulos/no vacíos
        if (dni != null && !dni.trim().isEmpty()) {
            sql.append(" AND dni LIKE ?");
            parametros.add("%" + dni.trim() + "%");
        }
        if (nombre != null && !nombre.trim().isEmpty()) {
            sql.append(" AND nombre LIKE ?");
            parametros.add("%" + nombre.trim() + "%");
        }
        if (sexo != null && !sexo.trim().isEmpty()) {
            sql.append(" AND sexo = ?");
            parametros.add(sexo.trim());
        }
        if (categoria != null) {
            sql.append(" AND categoria = ?");
            parametros.add(categoria);
        }
        if (anyosTrabajados != null) {
            sql.append(" AND anyosTrabajados = ?");
            parametros.add(anyosTrabajados);
        }

        connection = obtenerConexion();

        try {
            statement = connection.prepareStatement(sql.toString());

            // Establecer parámetros en la consulta
            for (int i = 0; i < parametros.size(); i++) {
                statement.setObject(i + 1, parametros.get(i));
            }

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Empleado e = new Empleado();
                e.setDni(resultSet.getString("dni"));
                e.setNombre(resultSet.getString("nombre"));
                e.setSexo(resultSet.getString("sexo"));
                e.setCategoria(resultSet.getInt("categoria"));
                e.setAnyosTrabajados(resultSet.getInt("anyosTrabajados"));
                listaEmpleados.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return listaEmpleados;
    }


    // Obtener conexión
    private Connection obtenerConexion() throws SQLException {
        return Conexion.getConnection();
    }
}
