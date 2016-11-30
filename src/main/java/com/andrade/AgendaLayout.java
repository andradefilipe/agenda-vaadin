package com.andrade;

import java.util.Arrays;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

public class AgendaLayout extends HorizontalLayout {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private final CheckBox deleted;
	private final TextField firstName;
	private final TextField lastName;
	private final TextField email;
	private final TextField city;
	private final TextField phoneNumber;
	private final TextField zipCode;
	private final TextField street;

	public AgendaLayout(Client client, ClientChangeListener changeListener) {
		setWidth("100%");
		setSpacing(true);
		setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);

		deleted = new CheckBox();
		firstName = new TextField();
		firstName.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);
		lastName = new TextField();
		lastName.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);
		email = new TextField();
		email.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);
		city = new TextField();
		city.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);
		phoneNumber = new TextField();
		phoneNumber.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);
		zipCode = new TextField();
		zipCode.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);
		street = new TextField();
		street.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);

		FieldGroup fieldGroup = new FieldGroup(new BeanItem<>(client));
		fieldGroup.setBuffered(false);
		fieldGroup.bindMemberFields(this);
		addComponents(deleted, firstName, lastName, email, city, phoneNumber, zipCode, street);
		setExpandRatio(firstName, 1);

		Arrays.asList(deleted, firstName, lastName, email, city, phoneNumber, zipCode, street).forEach(field -> {
			field.addValueChangeListener(change -> changeListener.clientChanged(client));
		});
	}
}