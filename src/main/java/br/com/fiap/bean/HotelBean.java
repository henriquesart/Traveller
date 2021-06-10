package br.com.fiap.bean;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.com.fiap.dao.DAO;
import br.com.fiap.model.Hotel;

@Named
@RequestScoped
public class HotelBean {
	
	private Hotel hotel = new Hotel();

	public void save() {
		new DAO<Hotel>(Hotel.class).save(this.hotel);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Hotel cadastrado com sucesso"));
	}
	
	public List<Hotel> getHotels(){
		return new DAO<Hotel>(Hotel.class).getAll();
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	
}