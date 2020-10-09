package modelo.dao;

import java.util.List;

import model.Usuario;

public interface UsuarioDAO {
	
	void insert (Usuario obj);
	
	void update (Usuario obj);
	
	void deleteById (Integer id);
	
	Usuario findById (Integer id);
	
	List<Usuario> findAll();
	
}
