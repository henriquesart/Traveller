package br.com.fiap.bean;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.com.fiap.dao.DAO;
import br.com.fiap.model.Reserva;

@Named
@RequestScoped
public class ReservaBean {
	
	private Reserva reserva = new Reserva();

	public void save() {
		new DAO<Reserva>(Reserva.class).save(this.reserva);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Reserva cadastrada com sucesso"));
	}
	
	public List<Reserva> getReservas(){
		return new DAO<Reserva>(Reserva.class).getAll();
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

}
