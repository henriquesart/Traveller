package br.com.fiap.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.model.Reserva;
import br.com.fiap.util.JPAUtil;

public class ReservaDAO {
	
	private EntityManager manager = JPAUtil.getEntityManager();

	public void save(Reserva reserva) {
		manager.getTransaction().begin();
		manager.persist(reserva);
		manager.getTransaction().commit();
		manager.close();
	}

	public List<Reserva> getAll() {
		String jpql = "SELECT s FROM Reserva s";
		TypedQuery<Reserva> query = manager.createQuery(jpql, Reserva.class);
		List<Reserva> resultList = query.getResultList();
		return resultList;
	}

}
