package de.ts.ticketsystem.client.main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import de.ts.ticketsystem.client.jira.AbstractJiraInstance;
import de.ts.ticketsystem.client.jira.platformapi.JiraPlatform;
import de.ts.ticketsystem.client.jira.servicedeskapi.JiraServicedesk;

public class ClientGUI extends JFrame {

	private static final int _REQUESTTYPE_ID_TECHNISCHERSERVICE = 1;
	private static final int _SERVICEDESK_ID_HEIZUNG = 1;
	private static URI jiraServerUri = UriBuilder.fromUri("https://callmats.atlassian.net").build(); //$NON-NLS-1$

	private static final long serialVersionUID = 1L;

	private Map<JiraApiEnum, AbstractJiraInstance> jiraInstances;
	private JPasswordField passwordInput;
	private JTextField userNameInput;

	public ClientGUI() {

		initMap();
		initComponents();
	}

	private void initMap() {
		jiraInstances = new HashMap<>(2);
	}

	private void initComponents() {

		setLayout(new BorderLayout());

		JPanel upperPanel = new JPanel();
		upperPanel.setLayout(new BorderLayout());
		upperPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));

		GridLayout loginLayout = new GridLayout(2, 2);
		JPanel loginPanel = new JPanel(loginLayout);

		JiraApiEnum[] items = JiraApiEnum.values();
		JComboBox<JiraApiEnum> apiChooser = new JComboBox<JiraApiEnum>(items);

		userNameInput = new JTextField();
		passwordInput = new JPasswordField();
		JLabel userNameLabel = new JLabel("Benutzer");
		JLabel passwordLabel = new JLabel("Passwort");

		upperPanel.add(apiChooser, BorderLayout.CENTER);
		upperPanel.add(loginPanel, BorderLayout.SOUTH);
		loginPanel.add(userNameLabel);
		loginPanel.add(userNameInput);
		loginPanel.add(passwordLabel);
		loginPanel.add(passwordInput);

		JPanel mainPanel = new JPanel();
		GridLayout layout = new GridLayout(2, 2);
		layout.setVgap(1);
		mainPanel.setLayout(layout);
		JTextField shortDescInput = new JTextField();
		JTextField longDescInput = new JTextField();
		JLabel shortDescLabel = new JLabel("Kurzbeschreibung");
		JLabel longDescLabel = new JLabel("Beschreibung");
		mainPanel.add(shortDescLabel);
		mainPanel.add(shortDescInput);
		mainPanel.add(longDescLabel);
		mainPanel.add(longDescInput);

		JPanel lowerPanel = new JPanel();
		JButton createTicketButton = new JButton("Erstellen");
		createTicketButton.addActionListener(e -> {
			handleSubmit((JiraApiEnum) apiChooser.getSelectedItem());
		});
		lowerPanel.setLayout(new BoxLayout(lowerPanel, BoxLayout.LINE_AXIS));
		lowerPanel.add(createTicketButton);

		this.add(upperPanel, BorderLayout.NORTH);
		this.add(mainPanel, BorderLayout.CENTER);
		this.add(lowerPanel, BorderLayout.SOUTH);

		this.setTitle("Jira Client");
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		this.pack();
	}

	private void handleSubmit(JiraApiEnum selectedItem) {
		AbstractJiraInstance abstractJiraInstance = jiraInstances.get(selectedItem);

		if (abstractJiraInstance == null) {
			abstractJiraInstance = createJiraInstance(selectedItem);
			jiraInstances.put(selectedItem, abstractJiraInstance);
		}
	}

	private AbstractJiraInstance createJiraInstance(JiraApiEnum selectedItem) {

		char[] password = passwordInput.getPassword();
		String passwordString = new String(password);
		HttpAuthenticationFeature basic = HttpAuthenticationFeature.basic(userNameInput.getText(), passwordString);

		switch (selectedItem) {
		case JiraPlatform:
			return new JiraPlatform(jiraServerUri, basic);
		case JiraServicedesk:
			return new JiraServicedesk(jiraServerUri, basic);
		}
		return null;
	}
}
