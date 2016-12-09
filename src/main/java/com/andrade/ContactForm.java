package com.andrade;

import com.vaadin.data.validator.NullValidator;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

// Define a sub-window by inheritance
class ContactForm extends Window {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private FormLayout form;

	private Button createContactButton = new Button("Create Contact");
	private Button cancelButton = new Button("Cancel");

	public void cleanAllFields(TextField name, TextField email, TextField phoneNumber, TextField city) {
		name.setValue("");
		email.setValue("");
		city.setValue("");
		phoneNumber.setValue("");
	}

	public ContactForm(ContactList contactList) {
		super("Add new contact");
		center();
		form = new FormLayout();
		form.setSpacing(true);
		form.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		setContent(form);
		setHeight("400px");
		setWidth("600px");
		setModal(true);

		HorizontalLayout horizontal = new HorizontalLayout();
		horizontal.setWidth("100%");
		horizontal.setHeight("100%");

		TextField name = new TextField("Name");
		name.setRequired(true);
		name.setWidth("250px");
		name.addValidator(new NullValidator("Must be given", false));

		TextField email = new TextField("Email");
		email.setRequired(true);
		email.setWidth("250px");
		email.addValidator(new NullValidator("Must be given", false));

		horizontal.addComponents(name, email);

		form.addComponent(horizontal);

		horizontal = new HorizontalLayout();
		horizontal.setWidth("100%");
		horizontal.setHeight("100%");

		TextField phoneNumber = new TextField("Phone Number");
		phoneNumber.setRequired(true);
		phoneNumber.setWidth("250px");
		phoneNumber.addValidator(new NullValidator("Must be given", false));

		TextField city = new TextField("City");
		city.setRequired(true);
		city.setWidth("250px");
		city.addValidator(new NullValidator("Must be given", false));
		horizontal.addComponents(city, phoneNumber);
		form.addComponent(horizontal);
		addActionButtons();

		name.focus();

		createContactButton.addClickListener(click -> {
			contactList
					.addClient(new Client(name.getValue(), email.getValue(), city.getValue(), phoneNumber.getValue()));
			cleanAllFields(name, email, phoneNumber, city);
			close();
		});
		cancelButton.addClickListener(click -> {
			cleanAllFields(name, email, phoneNumber, city);
			close();
		});
		createContactButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
	}

	private void addActionButtons() {
		HorizontalLayout horizontal = new HorizontalLayout();
		horizontal.setSpacing(true);
		horizontal.addComponents(createContactButton, cancelButton);

		form.addComponent(horizontal);
	}
}