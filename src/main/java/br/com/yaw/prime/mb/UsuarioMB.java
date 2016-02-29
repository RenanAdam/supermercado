package br.com.yaw.prime.mb;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;

import org.primefaces.context.RequestContext;

import br.com.yaw.prime.model.Usuario;
import br.com.yaw.prime.service.UsuarioService;

@ManagedBean(name="usuarioMB")
@SessionScoped
public class UsuarioMB implements Serializable {

	private static final long serialVersionUID = 1L;

	public UsuarioMB() {
		// TODO Auto-generated constructor stub
	}
	
	private Usuario usuario;
	private String user;
	private String pswd;

	@EJB
	private UsuarioService service;
	
	public Usuario getUsuario() {
		Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("login");
		if(us != null){
			this.usuario = us;
		}
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPswd() {
		return pswd;
	}
	public void setPswd(String pswd) {
		this.pswd = pswd;
	}
	public UsuarioService getService() {
		return service;
	}
	public void setService(UsuarioService service) {
		this.service = service;
	}
	
	public void initUsuario(){
		this.usuario = new Usuario();
	}
	
	@SuppressWarnings("finally")
	public String verificaUsuario() throws NoResultException{
		Usuario us = null;
		try{
			us = service.findUsuario(this.user , this.pswd);
		}catch(NoResultException ex){
			System.out.println("USUARIO INVALIDO");
		}finally{
			if(us != null){
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("login", us);
				return "filtrarMercadorias";
			}else{
				final FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Falha no login", "Usuário ou senha inválidos.");
		        RequestContext.getCurrentInstance().showMessageInDialog(message);
				return "";	
			}
			
		}
		
	}
	
	public String sair(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "login";
	}
	
	
}
