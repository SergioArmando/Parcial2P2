package metodos;

import PaqueteGrfico.empleados;
import conexcion.ClsConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class metodosUsuario {

     public void Verificar(String usuario, String contra) {

          Connection conn = null;
          PreparedStatement stmt = null;
          ResultSet rs = null;
          String sql = "select *from tabla_usuarios where usuario = '" + usuario + "'and contra = '" + contra + "' ";

          try {
               conn = ClsConexion.OpenConection();
               stmt = conn.prepareStatement(sql);
               rs = stmt.executeQuery();

               if (rs.next()) {
                    empleados form = new empleados();
                    form.setVisible(true);

               } else {
                    JOptionPane.showMessageDialog(null, "Credenciales Invalidas");

               }

          } catch (SQLException ex) {
               ex.printStackTrace(System.out);
          } finally {
               ClsConexion.CloseConection(stmt);
               ClsConexion.CloseConection(rs);
               ClsConexion.CloseConection(conn);
          }

     }

}
