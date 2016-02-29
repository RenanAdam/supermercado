package br.com.yaw.prime.mb;

import static javax.faces.context.FacesContext.getCurrentInstance;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.yaw.prime.model.Supermercado;
import br.com.yaw.prime.service.SupermercadoService;


@ManagedBean(name="supermercadoMB")
@ViewScoped
public class SupermercadoMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Container injeta a referencia p/ o ejb MercadoriaService
	 */
	@EJB
	private SupermercadoService service;
	
	private Long idSelecionado;
	
	/**
	 * Lista com o(s) <code>Supermercado</code>(s) apresentandas no <code>Datatable</code>.
	 */
	private List<Supermercado> supermercados;
	
	/**
	 * Referência para o supermercado utiliza na inclusão (nova) ou edição.
	 */
	private Supermercado supermercado;
	
	
	public SupermercadoMB() {
	}
	
	public void setIdSelecionado(Long idSelecionado) {
		this.idSelecionado = idSelecionado;
	}
	
	public Long getIdSelecionado() {
		return idSelecionado;
	}
	
	public Supermercado getSupermercado() {
		return supermercado;
	}
	

	public void incluir(){
		supermercado = new Supermercado();
		//log.debug("Pronto pra incluir");
	}
	
	public void editar() {
		if (idSelecionado == null) {
			return;
		}
		supermercado = service.find(idSelecionado);
		//log.debug("Pronto pra editar");
	}
	
	public List<Supermercado> getSupermercados() {
		if (supermercados == null) {
			supermercados = service.findAll();
		}
		return supermercados;
	}

	
	public String salvar() {
		try {
			service.save(supermercado);
		} catch(Exception ex) {
			//log.error("Erro ao salvar mercadoria.", ex);
			addMessage(getMessageFromI18N("msg.erro.salvar.supermercado"), ex.getMessage());
			return "";
		}
		//log.debug("Salvour mercadoria "+mercadoria.getId());
		return "listaSupermercados";
	}
	
	public String remover() {
		try {
			service.remove(supermercado);
		} catch(Exception ex) {
			//log.error("Erro ao remover mercadoria.", ex);
			addMessage(getMessageFromI18N("msg.erro.remover.supermercado"), ex.getMessage());
			return "";
		}
		//log.debug("Removeu mercadoria "+mercadoria.getId());
		return "listaSupermercados";
	}
	
	/**
	 * @param key
	 * @return Recupera a mensagem do arquivo properties <code>ResourceBundle</code>.
	 */
	private String getMessageFromI18N(String key) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages_labels", getCurrentInstance().getViewRoot().getLocale());
		return bundle.getString(key);
	}
	
	/**
	 * Adiciona um mensagem no contexto do Faces (<code>FacesContext</code>).
	 * @param summary
	 * @param detail
	 */
	private void addMessage(String summary, String detail) {
		getCurrentInstance().addMessage(null, new FacesMessage(summary, summary.concat("<br/>").concat(detail)));
	}
	
	
	public int getPosicaoListaSupermercados(Supermercado superm){
		int posicao = 0;
		if(supermercados != null && superm != null){
			posicao = this.supermercados.indexOf(superm);
		}
		return ++posicao;
	}
	
}
