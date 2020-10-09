package model;

import java.io.Serializable;
import java.util.Date;

public class Usuario implements Serializable {
		
		private static final long serialVersionUID = 1L;
		private Integer id;
		private String nome;
		private String senha;
		private Date dataCadastro;
		
		public Usuario() {
			
		}
				
		public Usuario(Integer id, String nome, String senha, Date dataCadastro) {
			this.id = id;
			this.nome = nome;
			this.senha = senha;
			this.dataCadastro = dataCadastro;
		}


		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public String getSenha() {
			return senha;
		}
		public void setSenha(String senha) {
			this.senha = senha;
		}
		public Date getDataCadastro() {
			return dataCadastro;
		}
		public void setDataCadastro(Date dataCadastro) {
			this.dataCadastro = dataCadastro;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			return result;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
					return true;
			if (obj == null)
					return false;
			if (getClass() != obj.getClass())
					return false;
			Usuario outro = (Usuario) obj;
			if (id == null) {
					if (outro.id != null)
							return false;
			} else if (!id.equals(outro.id))
					return false;
			return true;
		}
		
		@Override
		public String toString() {
			return "Usuario [id=" + id + ", nome=" + nome + ", senha=" + senha + ", dataCadastro=" + dataCadastro + "]";
		}	
	}


