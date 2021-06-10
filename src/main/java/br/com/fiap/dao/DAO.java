package br.com.fiap.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import br.com.fiap.model.Hotel;
import br.com.fiap.model.Reserva;
import br.com.fiap.model.Setup;
import br.com.fiap.util.JPAUtil;

public class DAO<T> {

	private Class<T> classs;

	public DAO(Class<T> classs) {
		this.classs = classs;
	}

	private EntityManager manager = JPAUtil.getEntityManager();

	public void save(T t) {
		manager.getTransaction().begin();
		manager.persist(t);
		manager.getTransaction().commit();
		manager.close();
	}

	public List<T> getAll() {
		CriteriaQuery<T> query = manager.getCriteriaBuilder().createQuery(classs);
		query.select(query.from(classs));
		TypedQuery<T> createQuery = manager.createQuery(query);
		List<T> resultList = createQuery.getResultList();
		return resultList;
	}

	public T findById(Long id) {
		return manager.find(classs, id);
	}
	
	//Metodos Update
	
	public void update(Setup setup) {
		manager.getTransaction().begin();
		manager.merge(setup);
		manager.flush();
		manager.getTransaction().commit();
	}
	
	public void update2(Hotel hotel) {
		manager.getTransaction().begin();
		manager.merge(hotel);
		manager.flush();
		manager.getTransaction().commit();
	}
	
	public void update3(Reserva reserva) {
		manager.getTransaction().begin();
		manager.merge(reserva);
		manager.flush();
		manager.getTransaction().commit();
	}
	
	//Metodos Delete

	public void delete(Setup setup) {
		manager.getTransaction().begin();
		setup = manager.merge(setup);
		manager.remove(setup);
		manager.flush();
		manager.getTransaction().commit();
	}
	
	public void delete2(Hotel hotel) {
		manager.getTransaction().begin();
		hotel = manager.merge(hotel);
		manager.remove(hotel);
		manager.flush();
		manager.getTransaction().commit();
	}
	
	public void delete3(Reserva reserva) {
		manager.getTransaction().begin();
		reserva = manager.merge(reserva);
		manager.remove(reserva);
		manager.flush();
		manager.getTransaction().commit();
	}

	public boolean exist(Setup setup) {
		TypedQuery<Setup> query = manager
				.createQuery("SELECT u from Setup u WHERE " + "email= :email AND " + "senha = :senha", Setup.class);

		query.setParameter("email", setup.getEmail());
		query.setParameter("senha", setup.getSenha());

		Setup result = query.getSingleResult();
		return result != null;
	}

}
