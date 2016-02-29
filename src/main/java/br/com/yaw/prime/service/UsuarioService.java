package br.com.yaw.prime.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import br.com.yaw.prime.model.Usuario;


@Stateless
public class UsuarioService extends AbstractPersistence<Usuario, Long>{

	/**
	 * O container injeta a referência para o <code>EntityManager</code>.
	 */
	@PersistenceContext
	private EntityManager em;
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	public UsuarioService() {
		super(Usuario.class);
	}
	
	
	public Usuario findUsuario(String usuario , String senha) throws NoResultException{
		String consulta;
		if(usuario != null && senha != null){
			consulta = "SELECT u FROM Usuario u WHERE u.usuario = :usuario AND u.senha =  :senha"; 
			Query q = em.createQuery(consulta);
			q.setParameter("usuario", usuario);
			q.setParameter("senha", senha);
			Usuario us = (Usuario) q.getSingleResult();
			return us;
		}else{
			return null;
		}
	}

}
