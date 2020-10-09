package modelo.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.Usuario;
import modelo.dao.UsuarioDAO;



public class UsuarioDaoJDBC implements UsuarioDAO {
	
	private Connection conn;
	
	public UsuarioDaoJDBC (Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Usuario obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO usuario " 
		         + "(Nome, Senha, DataCadastro) " 
				 + "VALUES " 
		         + "(?, ?, ?)", 
		         Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getNome());
			System.out.println(obj.getNome());
			st.setString(2, obj.getSenha());
			st.setDate(3, new java.sql.Date (obj.getDataCadastro().getTime()));
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
					ResultSet rs = st.getGeneratedKeys();
					if (rs.next()) {
						int id = rs.getInt(1);
						obj.setId(id);
					}
					DB.closeResultSet(rs);
			} else {
				throw new DbException ("Erro");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Usuario obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				 "UPDATE usuario " 
			     + "SET Nome = ?, Senha = ?, DataCadastro = ? " 
				 + "WHERE id = ?");

			st.setString(1, obj.getNome());
			st.setString(2, obj.getSenha());
			st.setDate(3, (Date) obj.getDataCadastro());
			st.setInt(4, obj.getId());

			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}		
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM usuario WHERE Id = ?");

			st.setInt(1, id);

			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public Usuario findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM usuario WHERE id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();

			if (rs.next()) {
				Usuario obj = new Usuario();
				obj.setId(rs.getInt("id"));
				obj.setNome(rs.getString("nome"));
				obj.setSenha(rs.getString("senha"));
				obj.setDataCadastro(rs.getDate("datacadastro"));
				return obj;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Usuario> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM usuario ORDER BY nome");
			rs = st.executeQuery();

			List<Usuario> list = new ArrayList<>();

			while (rs.next()) {
				Usuario obj = new Usuario();
				obj.setId(rs.getInt("id"));
				obj.setNome(rs.getString("nome"));
				obj.setSenha(rs.getString("senha"));
				obj.setDataCadastro(rs.getDate("datacadastro"));
				list.add(obj);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
}