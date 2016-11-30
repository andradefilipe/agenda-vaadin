package com.andrade;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringUI
@Theme("valo")
public class AgendaUI extends UI {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private VerticalLayout layout;

	@Autowired
	ClientList clientList;

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		setupLayout();
		addHeader();
		addForm();
		addClientList();
		addActionButtons();
	}

	private void setupLayout() {
		layout = new VerticalLayout();
		layout.setSpacing(true);
		layout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		setContent(layout);
	}

	private void addHeader() {
		Label header = new Label("AGENDA");
		header.addStyleName(ValoTheme.LABEL_H1);
		header.setSizeUndefined();
		layout.addComponent(header);

	}

	private void addForm() {
		HorizontalLayout formLayout = new HorizontalLayout();
		formLayout.setWidth("90%");
		formLayout.setSpacing(true);

		TextField name = new TextField("Insert the Name");
		TextField city = new TextField("City");
		TextField phoneNumber = new TextField("Phone Number");
		TextField email = new TextField("Email");

		name.focus();
		Button addButton = new Button("");

		formLayout.addComponents(name, city, phoneNumber, email, addButton);
		layout.addComponent(formLayout);

		addButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
		addButton.setIcon(FontAwesome.PLUS);

		addButton.addClickListener(click -> {
			clientList
					.addClient(new Client(name.getValue(), city.getValue(), phoneNumber.getValue(), email.getValue()));
			name.setValue("");
			city.setValue("");
			phoneNumber.setValue("");
			email.setValue("");
			name.focus();
		});
		addButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
	}

	private void addClientList() {
		layout.addComponent(clientList);
	}

	private void addActionButtons() {
		Button deleteButton = new Button("Delete");

		deleteButton.addClickListener(click -> clientList.deleteCompleted());

		layout.addComponent(deleteButton);

	}
}