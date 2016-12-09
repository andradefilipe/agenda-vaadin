package com.andrade;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.VerticalLayout;

@UIScope
@SpringComponent
class ContactList extends VerticalLayout implements ClientChangeListener {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	AgendaRepository repository;
	private List<Client> clients;

	@PostConstruct
	void init() {
		setWidth("80%");
		setSpacing(true);

		update();
	}

	private void update() {
		setClients(repository.findAll());
	}

	private void setClients(List<Client> clients) {
		this.clients = clients;
		removeAllComponents();
		clients.forEach(client -> addComponent(new AgendaLayout(client, this)));
	}

	void addClient(Client client) {
		repository.save(client);
		update();
	}

	@Override
	public void clientChanged(Client client) {
		addClient(client);
	}

	public void deleteCompleted() {
		repository.deleteInBatch(clients.stream().filter(Client::isDeleted).collect(Collectors.toList()));
		update();
	}
}