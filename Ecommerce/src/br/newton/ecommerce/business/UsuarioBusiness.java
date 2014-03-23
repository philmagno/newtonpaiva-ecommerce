package br.newton.ecommerce.business;

import java.util.List;

import br.newton.ecommerce.dao.DAOFactory;
import br.newton.ecommerce.dao.UsuarioDAO;
import br.newton.ecommerce.entity.Usuario;


public class UsuarioBusiness {
	
	private UsuarioDAO usuarioDAO;
	
	public UsuarioBusiness(){
		this.usuarioDAO = DAOFactory.criarUsuarioDAO();
	}
	
	public Usuario carregar(Integer codigo){
		return this.usuarioDAO.carregar(codigo);
	}
	
	public Usuario buscarPorLogin(String login){
		return this.usuarioDAO.buscarPorLogin(login);
	}
	
	public void salvar(Usuario usuario){
		Integer codigo = usuario.getCodigo();
		
		if(codigo == null || codigo == 0){	
			usuario.setAtivo(true);
			this.usuarioDAO.salvar(usuario);			
		}else {
			this.usuarioDAO.atualizar(usuario);
		}
	}
	
	public void excluir(Usuario usuario){
		this.usuarioDAO.excluir(usuario);
	}

	public List<Usuario> listar(){		
		return this.usuarioDAO.listar();
		
	}
	
}
