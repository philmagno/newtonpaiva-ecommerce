package br.newton.ecommerce.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.util.DigestUtils;

import br.newton.ecommerce.business.UsuarioBusiness;
import br.newton.ecommerce.entity.Endereco;
import br.newton.ecommerce.entity.Usuario;

@Named("usuarioBean")
@RequestScoped
public class UsuarioBean {

	private Usuario usuario = new Usuario();
	private String confirmarSenha;
	private List<Usuario> lista;
	private String destinoSalvar;
	private String senhaCriptografada;
	private String permissaoSel;
	private String senhaAntiga;
	private String senhaNova;
	private String confirma_email;
	private String confirma_senha;
	private String nome_comprador;
	private Endereco endereco;

	@PostConstruct
	public void init() {
		endereco = new Endereco();
	}

	public String atribuiPermissao(Usuario usuario, String permissao) {
		this.usuario = usuario;
		java.util.Set<String> permissoes = this.usuario.getPermissao();

		if (permissoes.contains(permissao)) {
			permissoes.remove(permissao);
		} else {
			permissoes.add(permissao);
		}

		return null;
	}

	public String novo() {
		this.destinoSalvar = "usuarioSucesso";
		this.usuario = new Usuario();
		this.usuario.setAtivo(true);
		return "usuario";

	}

	public String iniciaCadastro() {
		return "cadastro";
	}

	public String editar() {
		return "cadastro";
	}

	public String salvar() {
		System.out.println(endereco);
		this.usuario.getPermissao().add("ROLE_USUARIO");

		FacesContext context = FacesContext.getCurrentInstance();

		String senha = this.usuario.getSenha();
		this.usuario.getPermissao().add("ROLE_USUARIO");

		if (senha != null && senha.trim().length() > 0
				&& !senha.equals(this.confirma_senha)) {
			FacesMessage facesMessage = new FacesMessage(
					"A senha n�o foi confirmada corretamente");
			context.addMessage(null, facesMessage);

			return null;
		}

		if (senha != null && senha.trim().length() == 0) {
			this.usuario.setSenha(this.senhaCriptografada);
		} else {
			String senhaCripto = DigestUtils.md5DigestAsHex(senha.getBytes());
			this.usuario.setSenha(senhaCripto);
		}
		UsuarioBusiness usuarioRN = new UsuarioBusiness();
		usuarioRN.salvar(this.usuario);

		return "index";
	}

	public void setLista(List<Usuario> lista) {
		this.lista = lista;
	}

	public String excluir() {
		UsuarioBusiness usuarioRN = new UsuarioBusiness();
		usuarioRN.excluir(this.usuario);
		this.lista = null;

		return null;
	}

	public String ativar() {
		if (this.usuario.isAtivo())
			this.usuario.setAtivo(false);
		else
			this.usuario.setAtivo(true);

		UsuarioBusiness usuarioRN = new UsuarioBusiness();
		usuarioRN.salvar(this.usuario);

		return null;
	}

	public List<Usuario> getLista() {
		if (this.lista == null) {
			UsuarioBusiness usuarioRN = new UsuarioBusiness();
			this.lista = usuarioRN.listar();
		}

		return this.lista;
	}

	public String trocarSenha() {
		FacesContext context = FacesContext.getCurrentInstance();
		String senhaCripto = DigestUtils.md5DigestAsHex(senhaAntiga.getBytes());

		this.usuario = getUsuarioLogado();

		if (!senhaCripto.equals(this.usuario.getSenha())) {
			FacesMessage facesMessage = new FacesMessage(
					"A senha antiga n�o confere");
			context.addMessage(null, facesMessage);
			return null;
		}

		if (!senhaNova.equals(confirmarSenha)) {

			FacesMessage facesMessage = new FacesMessage(
					"A senha confirmada n�o confere");
			context.addMessage(null, facesMessage);
			return null;

		}

		String senhaCriptoNova = DigestUtils.md5DigestAsHex(senhaNova
				.getBytes());
		this.usuario.setSenha(senhaCriptoNova);

		UsuarioBusiness usuarioRN = new UsuarioBusiness();
		usuarioRN.salvar(this.usuario);

		FacesMessage facesMessage = new FacesMessage(
				"Senha alterada com sucesso");
		context.addMessage(null, facesMessage);
		return null;

	}

	public String getNomeUsuario() {
		this.usuario = getUsuarioLogado();
		this.nome_comprador = this.usuario.getNome();

		return this.nome_comprador;
	}

	public Usuario getUsuarioLogado() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		String login = external.getRemoteUser();

		if (login != null) {

			UsuarioBusiness usuarioRN = new UsuarioBusiness();
			this.usuario = usuarioRN.buscarPorLogin(login);

		}

		return this.usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

	public String getDestinoSalvar() {
		return destinoSalvar;
	}

	public void setDestinoSalvar(String destinoSalvar) {
		this.destinoSalvar = destinoSalvar;
	}

	public String getSenhaCriptografada() {
		return senhaCriptografada;
	}

	public void setSenhaCriptografada(String senhaCriptografada) {
		this.senhaCriptografada = senhaCriptografada;
	}

	public String getPermissaoSel() {
		return permissaoSel;
	}

	public void setPermissaoSel(String permissaoSel) {
		this.permissaoSel = permissaoSel;
	}

	public String getSenhaAntiga() {
		return senhaAntiga;
	}

	public void setSenhaAntiga(String senhaAntiga) {
		this.senhaAntiga = senhaAntiga;
	}

	public String getSenhaNova() {
		return senhaNova;
	}

	public void setSenhaNova(String senhaNova) {
		this.senhaNova = senhaNova;
	}

	public String getConfirma_email() {
		return confirma_email;
	}

	public void setConfirma_email(String confirma_email) {
		this.confirma_email = confirma_email;
	}

	public String getConfirma_senha() {
		return confirma_senha;
	}

	public void setConfirma_senha(String confirma_senha) {
		this.confirma_senha = confirma_senha;
	}

	public String getNome_comprador() {
		return nome_comprador;
	}

	public void setNome_comprador(String nome_comprador) {
		this.nome_comprador = nome_comprador;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}
