
package MODELO;

import DAO.usuariosDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Date;
import java.util.LinkedList;
import javax.swing.JOptionPane;

public class usuariosDAORelacional implements usuariosDAO{

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    conexion acceso = new conexion();
    
    @Override
    public LinkedList<usuarios> listar() {
        
        String sql = "select * from usuarios";
        LinkedList<usuarios> listar = new LinkedList<>();
        
        try {
            //inicializa la base de datos
            con = acceso.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
               listar.add(new usuarios(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getInt(9), rs.getString(10)));
            }
            return listar;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public usuarios obtener(int usuario_id) {
        String sql = "select * from usuarios WHERE usuario_id = ?";
        LinkedList<usuarios> listar = new LinkedList<>();
        
        try {
            //inicializa la base de datos
            con = acceso.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, usuario_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                usuarios usu = new usuarios(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getInt(9), rs.getString(10));
                return usu;
            } else{
                JOptionPane.showMessageDialog(null, "No se encontrarón resultados");
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public void crear(usuarios usuario) {
        String sql = "INSERT INTO usuarios (nombre,apellido,telefono, direccion,correo_electronico,fecha_nacimiento, rol_id,activo,contrasenia) values(?,?,?,?,?,?,?,?,?);";
        
        try {
             con = acceso.Conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setInt(3, usuario.getTelefono());
            ps.setString(4, usuario.getDireccion());
            ps.setString(5, usuario.getCorreo_electronico());
            ps.setString(6, usuario.getFecha_nacimiento());
            ps.setInt(7, usuario.getRol_id());
            ps.setInt(8, usuario.getActivo());
            ps.setString(9, usuario.getContrasenia());
            ps.executeUpdate();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void modificar(usuarios usuario) {
        String sql = "UPDATE usuarios set nombre =?,apellido=?,telefono=?, direccion=?,correo_electronico=?,fecha_nacimiento=?, rol_id=?,activo=?,contrasenia=? WHERE usuario_id = ?;";
        
        try {
             con = acceso.Conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setInt(3, usuario.getTelefono());
            ps.setString(4, usuario.getDireccion());
            ps.setString(5, usuario.getCorreo_electronico());
            ps.setString(6,  usuario.getFecha_nacimiento());
            ps.setInt(7, usuario.getRol_id());
            ps.setInt(8, usuario.getActivo());
            ps.setString(9, usuario.getContrasenia());
            ps.setInt(10, usuario.getUsuario_id());
            ps.executeUpdate();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void eliminar(int usuario_id) {
        String sql = "DELETE FROM usuarios WHERE usuario_id = ?;";
        
        try {
             con = acceso.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, usuario_id);
            ps.executeUpdate();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
