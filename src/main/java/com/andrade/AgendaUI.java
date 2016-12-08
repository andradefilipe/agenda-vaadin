package com.andrade;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
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
	Button cancelButton = new Button("Cancel");

	@Autowired
	ClientList clientList;

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		setupLayout();
		addHeader();
		addForm();
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
		header.setSizeUndefined();
		form.addComponent(header);

	}

	private void addForm() {
		HorizontalLayout horizontal = new HorizontalLayout();
		horizontal.setWidth("100%");
		horizontal.setHeight("100%");

		TextField name = new TextField("Name");
		name.setRequired(true);
		name.setWidth("60%");
		name.addValidator(new NullValidator("Must be given", false));

		TextField email = new TextField("Email");
		email.setRequired(true);
		email.setWidth("40%");
		email.addValidator(new NullValidator("Must be given", false));

		horizontal.addComponents(name, email);
		form.addComponent(horizontal);

		horizontal = new HorizontalLayout();
		horizontal.setWidth("100%");
		horizontal.setHeight("100%");

		TextField phoneNumber = new TextField("Phone Number");
		phoneNumber.setRequired(true);
		phoneNumber.setWidth("40%");
		phoneNumber.addValidator(new NullValidator("Must be given", false));

		TextField city = new TextField("City");
		city.setRequired(true);
		city.setWidth("60%");
		city.addValidator(new NullValidator("Must be given", false));
		horizontal.addComponents(city, phoneNumber);
		form.addComponent(horizontal);

		name.focus();

		addButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
		addButton.setIcon(FontAwesome.PLUS);

		addButton.addClickListener(click -> {
			clientList
					.addClient(new Client(name.getValue(), email.getValue(), city.getValue(), phoneNumber.getValue()));
			cleanAllFields(name, email, phoneNumber, city);
		});
		cancelButton.addClickListener(click -> {
			cleanAllFields(name, email, phoneNumber, city);
		});
		addButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
	}

	public void cleanAllFields(TextField name, TextField email, TextField phoneNumber, TextField city) {
		name.setValue("");
		email.setValue("");
		city.setValue("");
		phoneNumber.setValue("");
	}

	private void addClientList() {
		form.addComponent(clientList);
	}

	private void addActionButtons() {
		HorizontalLayout horizontal = new HorizontalLayout();
		horizontal.setSpacing(true);

		Button deleteButton = new Button("Delete");

		deleteButton.addClickListener(click -> clientList.deleteCompleted());

		horizontal.addComponents(addButton, cancelButton, deleteButton);

		form.addComponent(horizontal);

	}
}