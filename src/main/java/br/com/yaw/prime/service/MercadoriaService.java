package br.com.yaw.prime.service;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import br.com.yaw.prime.model.Mercadoria;


@Stateless
public class MercadoriaService extends AbstractPersistence<Mercadoria, Long>{

	/**
	 * O container injeta a referência para o <code>EntityManager</code>.
	 */
	@PersistenceContext
	private EntityManager em;
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	public MercadoriaService() {
		super(Mercadoria.class);
	}
	
	
	public List<Mercadoria> findFilter(Long idSupermercado , Date dataInicial , Date dataFinal) {
		String consulta;
		if(idSupermercado > 0){
			consulta = "SELECT m FROM Mercadoria m WHERE m.id_supermercado = :id_supermercado AND m.data BETWEEN :dataInicial AND :dataFinal ORDER BY m.data"; 
			Query q = em.createQuery(consulta);
			q.setParameter("id_supermercado", idSupermercado);
			q.setParameter("dataInicial", dataInicial, TemporalType.DATE);
			q.setParameter("dataFinal", dataFinal, TemporalType.DATE);
			List<Mercadoria> resultList = q.getResultList();
			return resultList;
		}else if(dataInicial != null && dataFinal != null){
			consulta = "SELECT m FROM Mercadoria m WHERE m.data BETWEEN :dataInicial AND :dataFinal ORDER BY m.data"; 
			Query q = em.createQuery(consulta);
			q.setParameter("dataInicial", dataInicial, TemporalType.DATE);
			q.setParameter("dataFinal", dataFinal, TemporalType.DATE);
			List<Mercadoria> resultList = q.getResultList();
			return resultList;
		}else{
			return null;
		}
		
	}

}
