package com.aprendec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.aprendec.conexion.Conexion;
import com.aprendec.model.Nomina;

public class NominaDAO {
    private Connection connection;
    private PreparedStatement statement;
    private boolean estadoOperacion;

    // Guardar nómina
    public boolean guardar(Nomina nomina) throws SQLException {
        String sql = null;
        estadoOperacion = false;
        connection = obtenerConexion();

        try {
            connection.setAutoCommit(false);
            sql = "INSERT INTO nominas (dni_empleado, sueldo) VALUES (?, ?)";
            statement = connection.prepareStatement(sql);

            statement.setString(1, nomina.getDni_empleado());
            statement.setInt(2, nomina.getSueldo());

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

    // Editar nómina
    public boolean editar(Nomina nomina) throws SQLException {
        String sql = null;
        estadoOperacion = false;
        connection = obtenerConexion();

        try {
            connection.setAutoCommit(false);
            sql = "UPDATE nominas SET sueldo=? WHERE dni_empleado=?";
            statement = connection.prepareStatement(sql);

            statement.setInt(1, nomina.getSueldo());
            statement.setString(2, nomina.getDni_empleado());

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

    // Eliminar nómina por dni_empleado
    public boolean eliminar(String dni_empleado) throws SQLException {
        String sql = null;
        estadoOperacion = false;
        connection = obtenerConexion();

        try {
            connection.setAutoCommit(false);
            sql = "DELETE FROM nominas WHERE dni_empleado=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, dni_empleado);

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

    // Obtener lista de nominas
    public List<Nomina> obtenerNominas() throws SQLException {
        ResultSet resultSet = null;
        List<Nomina> listaNominas = new ArrayList<>();

        String sql = null;
        connection = obtenerConexion();

        try {
            sql = "SELECT * FROM nominas";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Nomina n = new Nomina();
                n.setDni_empleado(resultSet.getString("dni_empleado"));
                n.setSueldo(resultSet.getInt("sueldo"));
                listaNominas.add(n);
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaNominas;
    }

    // Obtener nómina por dni_empleado
    public Nomina obtenerNomina(String dni_empleado) throws SQLException {
        ResultSet resultSet = null;
        Nomina n = new Nomina();

        String sql = null;
        connection = obtenerConexion();

        try {
            sql = "SELECT * FROM nominas WHERE dni_empleado = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, dni_empleado);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                n.setDni_empleado(resultSet.getString("dni_empleado"));
                n.setSueldo(resultSet.getInt("sueldo"));
            } else {
                // Si no encuentra, devolver objeto con dni null para diferenciar
                n.setDni_empleado(null);
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return n;
    }

    // Obtener conexión
    private Connection obtenerConexion() throws SQLException {
        return Conexion.getConnection();
    }
}
