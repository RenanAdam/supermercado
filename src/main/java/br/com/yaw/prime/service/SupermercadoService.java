package br.com.yaw.prime.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.yaw.prime.model.Supermercado;


@Stateless
public class SupermercadoService extends AbstractPersistence<Supermercado, Long>{

	/**
	 * O container injeta a referência para o <code>EntityManager</code>.
	 */
	@PersistenceContext
	private EntityManager em;
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	public SupermercadoService() {
		super(Supermercado.class);
	}

}
