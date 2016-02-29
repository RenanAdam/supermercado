package br.com.yaw.prime.mb;

import static javax.faces.context.FacesContext.getCurrentInstance;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.yaw.prime.model.Mercadoria;
import br.com.yaw.prime.model.Supermercado;
import br.com.yaw.prime.model.Usuario;
import br.com.yaw.prime.service.MercadoriaService;
import br.com.yaw.prime.service.SupermercadoService;


@ManagedBean(name="mercadoriaMB")
@ViewScoped
public class MercadoriaMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Container injeta a referencia p/ o ejb MercadoriaService
	 */
	@EJB
	private MercadoriaService service;
	
	@EJB
	private SupermercadoService serviceSupermercado;
	
	private Long idSelecionado;
	
	private Long filtroIdSupermercado;
	
	private Date filtroDataInicial;
	
	private Date filtroDataFinal;
	
	
	/**
	 * Lista com a(s) <code>Mercadoria</code>(s) apresentandas no <code>Datatable</code>.
	 */
	private List<Mercadoria> mercadorias;
	
	/**
	 * Referência para a mercadoria utiliza na inclusão (nova) ou edição.
	 */
	private Mercadoria mercadoria;
	
	
	public MercadoriaMB() {
		this.filtroDataInicial = new Date();
		this.filtroDataFinal = new Date();
	}
	
	public void setIdSelecionado(Long idSelecionado) {
		this.idSelecionado = idSelecionado;
	}
	
	public Long getIdSelecionado() {
		return idSelecionado;
	}
	
	public Mercadoria getMercadoria() {
		return mercadoria;
	}
	
	public Long getFiltroIdSupermercado() {
		return filtroIdSupermercado;
	}

	public void setFiltroIdSupermercado(Long filtroIdSupermercado) {
		this.filtroIdSupermercado = filtroIdSupermercado;
	}

	public Date getFiltroDataInicial() {
		return filtroDataInicial;
	}

	public void setFiltroDataInicial(Date filtroDataInicial) {
		this.filtroDataInicial = filtroDataInicial;
	}

	public Date getFiltroDataFinal() {
		return filtroDataFinal;
	}

	public void setFiltroDataFinal(Date filtroDataFinal) {
		this.filtroDataFinal = filtroDataFinal;
	}

	public void incluir(){
		mercadoria = new Mercadoria();
		Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("login");
		if(us != null){
			Long id_usuario = us.getId();
			mercadoria.setId_usuario(id_usuario);
		}
		//log.debug("Pronto pra incluir");
	}
	
	public void editar() {
		if (idSelecionado == null) {
			return;
		}
		mercadoria = service.find(idSelecionado);
		//log.debug("Pronto pra editar");
	}
	
	public List<Mercadoria> getMercadorias() {
		if (mercadorias == null) {
			//mercadorias = service.findAll();
		}
		return mercadorias;
	}

	public void filtrarListaMercadorias(){
		try{
			mercadorias = service.findFilter(this.filtroIdSupermercado, this.filtroDataInicial, this.filtroDataFinal);
			calculaTotalGeral();
		}catch(Exception ex){
			addMessage(getMessageFromI18N("msg.erro.listar.mercadoria"), ex.getMessage());
		}
	}
	
	public String salvar() {
		try {
			service.save(mercadoria);
		} catch(Exception ex) {
			//log.error("Erro ao salvar mercadoria.", ex);
			addMessage(getMessageFromI18N("msg.erro.salvar.mercadoria"), ex.getMessage());
			return "";
		}
		//log.debug("Salvour mercadoria "+mercadoria.getId());
		return "incluirMercadoria";
	}
	
	public String remover() {
		try {
			service.remove(mercadoria);
		} catch(Exception ex) {
			//log.error("Erro ao remover mercadoria.", ex);
			addMessage(getMessageFromI18N("msg.erro.remover.mercadoria"), ex.getMessage());
			return "";
		}
		//log.debug("Removeu mercadoria "+mercadoria.getId());
		return "filtrarMercadorias";
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
	
	
	public Double calculaValorTotal(Mercadoria mercad){
		Double valorTotal = 0.00;
		if(mercad != null){
			valorTotal = mercad.getPreco()*mercad.getQuantidade();
		}
		return valorTotal;
	}
	
	
	public int getPosicaoListaMercadorias(Mercadoria mercad){
		int posicao = 0;
		if(mercadorias != null && mercad != null){
			posicao = this.mercadorias.indexOf(mercad);
		}
		return ++posicao;
	}
	
	public String getSupermercadoPeloId(Long idSupermercado){
		Supermercado supermercado = new Supermercado();
		if(idSupermercado > 0){
			supermercado = serviceSupermercado.find(idSupermercado);
		}
		return supermercado.getSupermercado();
	}
	
	public String calculaTotalGeral(){	
		double total = 0;
		if(mercadorias != null){
			for (Mercadoria cada : mercadorias) { 
				total += (double)cada.getQuantidade()*cada.getPreco();
			}
		}
		Locale meuLocal = new Locale( "pt", "BR" );  
		NumberFormat formatter = NumberFormat.getCurrencyInstance(meuLocal);
		return formatter.format(total);
	}
	
}
