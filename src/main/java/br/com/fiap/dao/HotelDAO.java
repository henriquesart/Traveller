package br.com.fiap.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.model.Hotel;
import br.com.fiap.util.JPAUtil;

public class HotelDAO {
	
	private EntityManager manager = JPAUtil.getEntityManager();

	public void save(Hotel hotel) {
		manager.getTransaction().begin();
		manager.persist(hotel);
		manager.getTransaction().commit();
		manager.close();
	}

	public List<Hotel> getAll() {
		String jpql = "SELECT s FROM Hotel s";
		TypedQuery<Hotel> query = manager.createQuery(jpql, Hotel.class);
		List<Hotel> resultList = query.getResultList();
		return resultList;
	}

}
