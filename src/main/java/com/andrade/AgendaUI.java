package com.andrade;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

@SpringUI
@Theme("valo")
public class AgendaUI extends UI {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private FormLayout form;

	Button addButton = new Button("Add Contact");

	@Autowired
	ContactList contactList;

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		setupLayout();
		addHeader();
		addActionButtons();
		addClientList();
	}

	private void setupLayout() {
		form = new FormLayout();
		form.setSpacing(true);
		form.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		setContent(form);
	}

	private void addHeader() {
		Label header = new Label("AGENDA");
		header.addStyleName(ValoTheme.LABEL_H1);
		// header.setSizeUndefined();
		form.addComponent(header);

	}

	private void addClientList() {
		form.addComponent(contactList);
	}

	private void addActionButtons() {
		HorizontalLayout horizontal = new HorizontalLayout();
		horizontal.setSpacing(true);

		Button deleteButton = new Button("Delete");
		deleteButton.addClickListener(click -> contactList.deleteCompleted());

		addButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
		addButton.setIcon(FontAwesome.PLUS);
		addButton.addClickListener(click -> {
			ContactForm sub = new ContactForm(contactList);
			UI.getCurrent().addWindow(sub);

		});

		horizontal.addComponents(addButton, deleteButton);
		form.addComponent(horizontal);
	}
}