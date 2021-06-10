package br.com.fiap.bean;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.com.fiap.dao.DAO;
import br.com.fiap.model.Setup;

@Named
@RequestScoped
public class SetupBean {

	private Setup setup = new Setup();

	public String login() {
		boolean exist = new DAO<Setup>(Setup.class).exist(this.setup);

		FacesContext context = FacesContext.getCurrentInstance();
		if (exist) {
			context.getExternalContext().getSessionMap().put("setup", this.setup);
			return "index?faces-redirect=true";
		}

		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login inválido", "erro"));
		return "login?faces-redirect=true";

	}

	public void save() {
		new DAO<Setup>(Setup.class).save(this.setup);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuário cadastrado com sucesso"));
	}

	public List<Setup> getSetups() {
		return new DAO<Setup>(Setup.class).getAll();
	}

	public Setup getSetup() {
		return setup;
	}

	public void setSetup(Setup setup) {
		this.setup = setup;
	}

}
