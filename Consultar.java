
package metodos;

import Datos.Clsperson;
import conexcion.ClsConexion;
import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Consultar {

     Clsperson datos = new Clsperson();

     public boolean Registrar(Clsperson pro) {
          PreparedStatement ps = null;
          boolean t = false;
          Connection conn = null;
          String sql = "INSERT INTO consumidor (Nombre, Enero, Febrero, Marzo, Promedio, Total) VALUES(?,?,?,?,?,?)";

          try {

               conn = ClsConexion.OpenConection();
               ps = conn.prepareStatement(sql);
               ps.setString(1, pro.getNombre());
               ps.setDouble(2, pro.getEnero());
               ps.setDouble(3, pro.getFebrero());
               ps.setDouble(4, pro.getMarzo());
               ps.setDouble(5, (pro.getEnero() + pro.getFebrero() + pro.getMarzo()) / 3);
               ps.setDouble(6, (pro.getEnero() + pro.getFebrero() + pro.getMarzo()));
               ps.execute();
               t = true;

          } catch (SQLException e) {
               JOptionPane.showMessageDialog(null, e + "No se registro");
               return false;
          } finally {
               ClsConexion.CloseConection(conn);
               ClsConexion.CloseConection(ps);
          }
          return t;
     }

     public void Consultar(JTable tabla) {

          DefaultTableModel molde = new DefaultTableModel();

          molde.addColumn("Codigo");
          molde.addColumn("Nombre");
          molde.addColumn("Enero");
          molde.addColumn("Febrero");
          molde.addColumn("Marzo");
          molde.addColumn("Promedio");
          molde.addColumn("Total");

          Connection conn = null;
          PreparedStatement stmt = null;
          ResultSet rs = null;

          String sql = "select *from consumidor ";
          java.util.List<Clsperson> milista = new ArrayList<Clsperson>();

          try {
               conn = ClsConexion.OpenConection();
               stmt = conn.prepareStatement(sql);
               rs = stmt.executeQuery();

               while (rs.next()) {
                    datos.setCodigo(rs.getInt(1));
                    datos.setNombre(rs.getString(2));
                    datos.setEnero(rs.getDouble(3));
                    datos.setFebrero(rs.getDouble(4));
                    datos.setMarzo(rs.getDouble(5));
                    datos.setPromedio(rs.getDouble(6));
                    datos.setTotal(rs.getDouble(7));
                    milista.add(datos);

                    String arreglo[] = new String[7];
                    for (int i = 0; i < milista.size(); i++) {
                         arreglo[0] = String.valueOf(datos.getCodigo());
                         arreglo[1] = datos.getNombre();
                         arreglo[2] = String.valueOf(datos.getEnero());
                         arreglo[3] = String.valueOf(datos.getFebrero());
                         arreglo[4] = String.valueOf(datos.getMarzo());
                         arreglo[5] = String.valueOf(datos.getTotal());
                         arreglo[6] = String.valueOf(datos.getPromedio());

                    }
                    molde.addRow(arreglo);
               }
               tabla.setModel(molde);

          } catch (SQLException e) {
               JOptionPane.showMessageDialog(null, e + " Hubo un error al mostrar ");
          } finally {
               ClsConexion.CloseConection(stmt);
               ClsConexion.CloseConection(conn);
               ClsConexion.CloseConection(rs);
          }
     }

     public boolean Modificar(Clsperson pro) {
          Connection conn = null;
          PreparedStatement stmt = null;
          boolean bool = false;

          String query = "Update consumidor set Nombre =?, Enero=?, Febrero=?, Marzo=?, Promedio=?, Total=? where Codigo=?";
          try {
               conn = ClsConexion.OpenConection();
               stmt = conn.prepareStatement(query);
               stmt.setString(1, pro.getNombre());
               stmt.setDouble(2, pro.getEnero());
               stmt.setDouble(3, pro.getFebrero());
               stmt.setDouble(4, pro.getMarzo());
               stmt.setDouble(5, pro.getTotal());
               stmt.setDouble(6, pro.getPromedio());
               stmt.setInt(7, pro.getCodigo());
               stmt.execute();
               bool = true;

          } catch (SQLException e) {
               JOptionPane.showMessageDialog(null, e + " Algo salio mal al modificar");
          } finally {
               ClsConexion.CloseConection(stmt);
               ClsConexion.CloseConection(conn);
          }
          return bool;
     }
     
     public boolean eliminar(Clsperson pro) {
          Connection conn = null;
          PreparedStatement stmt = null;
          boolean bool = false;

          String sql = "delete from consumidor where Codigo=?";

          try {
               conn = ClsConexion.OpenConection();
               stmt = conn.prepareStatement(sql);
               stmt.setInt(1, pro.getCodigo());
               stmt.execute();
               bool = true;
          } catch (SQLException e) {
               JOptionPane.showMessageDialog(null, e + " Falla al eliminar");
          } finally {
               ClsConexion.CloseConection(stmt);
               ClsConexion.CloseConection(conn);
          }

          return bool;
     }
     
}
