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
	private final TextField email;
	private final TextField city;
	private final TextField phoneNumber;

	public AgendaLayout(Client client, ClientChangeListener changeListener) {
		setWidthUndefined();

		setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);

		deleted = new CheckBox();

		firstName = new TextField();
		firstName.setWidth("300px");
		firstName.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);

		email = new TextField();
		email.setWidth("300px");
		email.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);

		city = new TextField();
		city.setWidth("200px");
		city.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);

		phoneNumber = new TextField();
		phoneNumber.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);

		FieldGroup fieldGroup = new FieldGroup(new BeanItem<>(client));
		fieldGroup.setBuffered(false);
		fieldGroup.bindMemberFields(this);

		addComponents(deleted, firstName, email, city, phoneNumber);
		// setExpandRatio(firstName, 1);
		// setExpandRatio(email, 1);
		// setExpandRatio(city, 1);
		// setExpandRatio(phoneNumber, 1);

		Arrays.asList(deleted, firstName, email, city, phoneNumber).forEach(field -> {
			field.addValueChangeListener(change -> changeListener.clientChanged(client));
		});
	}
}