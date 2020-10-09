package aplicacao;

import java.util.List;
import java.util.Date;
import java.util.Scanner;

import model.Usuario;
import modelo.dao.DaoFactory;
import modelo.dao.UsuarioDAO;


public class Programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
				
		UsuarioDAO usuarioDAO = DaoFactory.createUsuarioDAO();
		
		System.out.println("=== TEST 1: usuario findById ===");
		Usuario usuario = usuarioDAO.findById(3);		
		System.out.println(usuario);	
				
		//System.out.println("\n=== TEST 2: usuario findAll ===");
		//Usuario usuario1 = new Usuario(0, null);
		//List<Usuario> list = usuarioDAO.findAll(usuario1);
		//for (Usuario obj : list) {
			//System.out.println(obj);
		//}
		
		//System.out.println("\n=== TEST 3: usuario insert ===");
		//Usuario newUsuario = new Usuario(null, "Teste 01", "0155422", new Date(2020-10-11));
		//usuarioDAO.insert(newUsuario);
		//System.out.println("Inserido com Sucesso! Novo id = " + newUsuario.getId());
		
	    //System.out.println("\n=== TEST 4: usuario update ===");
		//usuario = usuarioDAO.findById(1);
		//usuario.setSenha("gya38NJDHs");
		//usuarioDAO.update(usuario);
		//System.out.println("Update efetuado com sucesso! ");
	
		
		System.out.println("\n=== TEST 5: usuario delete ===");	
	    System.out.println("Insira o ID a ser deletado");
	    int id = sc.nextInt();
	    usuarioDAO.deleteById(id);
	    System.out.println("ID Deletado com sucesso! ");
	   
	    sc.close();
	}

}
