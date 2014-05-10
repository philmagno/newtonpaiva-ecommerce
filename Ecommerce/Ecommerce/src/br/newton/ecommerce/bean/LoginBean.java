package br.newton.ecommerce.bean;

import javax.faces.bean.ManagedBean;

import br.newton.ecommerce.entity.Usuario;

@ManagedBean(name="loginBean")
public class LoginBean {
	
	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
