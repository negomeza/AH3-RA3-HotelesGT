
package DAO;

import MODELO.usuarios;
import java.util.LinkedList;

public interface usuariosDAO {
    
    //Vamoa a crear los metodos que queremos ejecutar
    public LinkedList<usuarios> listar();
    
    public usuarios obtener(int usuario_id);
    
    public void crear(usuarios usuario);
    
    public void modificar(usuarios usuario);
    
    public void eliminar(int usuario_id);
    
}
