package de.ts.ticketsystem.client.main;

import java.awt.BorderLayout;
import java.awt.Desktop;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import de.ts.ticketsystem.client.jira.AbstractJiraInstance;
import de.ts.ticketsystem.client.jira.platformapi.JiraPlatform;
import de.ts.ticketsystem.client.jira.platformapi.objects.Issue;
import de.ts.ticketsystem.client.jira.platformapi.objects.IssueType;
import de.ts.ticketsystem.client.jira.platformapi.objects.Project;
import de.ts.ticketsystem.client.jira.servicedeskapi.JiraServicedesk;
import de.ts.ticketsystem.client.jira.servicedeskapi.objects.NewRestRequest;
import de.ts.ticketsystem.client.jira.servicedeskapi.objects.NewRestRequestFieldValue;
import de.ts.ticketsystem.client.jira.servicedeskapi.objects.Request;

public class ClientGUI extends JFrame {

	private static final int _REQUESTTYPE_ID_TECHNISCHERSERVICE = 1;
	private static final int _SERVICEDESK_ID_HEIZUNG = 1;
	private static URI jiraServerUri = UriBuilder.fromUri("https://callmats.atlassian.net").build(); //$NON-NLS-1$

	private static final long serialVersionUID = 1L;

	private Map<JiraApiEnum, AbstractJiraInstance> jiraInstances;
	private JPasswordField passwordInput;
	private JTextField userNameInput;
	private JTextField shortDescInput;
	private JTextField longDescInput;

	public ClientGUI() {

		initMap();
		initComponents();
	}

	private void initMap() {
		jiraInstances = new HashMap<>(2);
	}

	private void initComponents() {

		setLayout(new BorderLayout());

		final JPanel upperPanel = new JPanel();
		upperPanel.setLayout(new BorderLayout());
		upperPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));

		final GridLayout loginLayout = new GridLayout(2, 2);
		final JPanel loginPanel = new JPanel(loginLayout);

		final JiraApiEnum[] items = JiraApiEnum.values();
		final JComboBox<JiraApiEnum> apiChooser = new JComboBox<JiraApiEnum>(items);

		userNameInput = new JTextField();
		passwordInput = new JPasswordField();
		final JLabel userNameLabel = new JLabel("Benutzer");
		final JLabel passwordLabel = new JLabel("Passwort");

		upperPanel.add(apiChooser, BorderLayout.CENTER);
		upperPanel.add(loginPanel, BorderLayout.SOUTH);
		loginPanel.add(userNameLabel);
		loginPanel.add(userNameInput);
		loginPanel.add(passwordLabel);
		loginPanel.add(passwordInput);

		final JPanel mainPanel = new JPanel();
		final GridLayout layout = new GridLayout(2, 2);
		layout.setVgap(1);
		mainPanel.setLayout(layout);
		shortDescInput = new JTextField();
		longDescInput = new JTextField();
		final JLabel shortDescLabel = new JLabel("Kurzbeschreibung");
		final JLabel longDescLabel = new JLabel("Beschreibung");
		mainPanel.add(shortDescLabel);
		mainPanel.add(shortDescInput);
		mainPanel.add(longDescLabel);
		mainPanel.add(longDescInput);

		final JPanel lowerPanel = new JPanel();
		final JButton createTicketButton = new JButton("Erstellen");
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

	private void handleSubmit(final JiraApiEnum selectedItem) {
		
		AbstractJiraInstance abstractJiraInstance = jiraInstances.get(selectedItem);

		if (abstractJiraInstance == null) {
			abstractJiraInstance = createJiraInstance(selectedItem);
			jiraInstances.put(selectedItem, abstractJiraInstance);
		}
		final String errorText = "Fehler bei Ticketerstellung: ";
		final String successText = "Ticket mit erstellt mit id: ";

		try {
			final String issueId = postTicket(abstractJiraInstance);
			final int showConfirmDialog = JOptionPane.showConfirmDialog(this, successText +  issueId  + "\nTicket im Browser anschauen?", "Erfolg",
					JOptionPane.YES_NO_OPTION);
			if (showConfirmDialog == JOptionPane.YES_OPTION) {
				  Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
				    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
				        try {
				            String str = jiraServerUri.toString() + "/browse/" + issueId;
				            str.trim();
							desktop.browse(URI.create(str));
				        } catch (Exception e) {
				            e.printStackTrace();
				        }
				    }
			}
		} catch (final Exception e) {
			JOptionPane.showMessageDialog(this, errorText + e.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
		}
	}

	private String postTicket(final AbstractJiraInstance abstractJiraInstance) {

		final String shortDesc = shortDescInput.getText();
		final String longDesc = longDescInput.getText();

		String resultText = "";


		if (abstractJiraInstance instanceof JiraServicedesk) {
			final NewRestRequest newRestRequest = new NewRestRequest(_SERVICEDESK_ID_HEIZUNG,
					_REQUESTTYPE_ID_TECHNISCHERSERVICE, new NewRestRequestFieldValue(shortDesc, longDesc));
			final Request createRequest = ((JiraServicedesk) abstractJiraInstance).createRequest(newRestRequest);
			resultText = createRequest.getIssueKey();

		} else if (abstractJiraInstance instanceof JiraPlatform) {
			final String projectId = "10003";
			final Project project = new Project(projectId);
			final String issueTypeId = "10005";
			final String issueTypeName = "Aufgabe";
			final Issue issue = new Issue(project, shortDesc, longDesc, new IssueType(issueTypeId, issueTypeName));
			final Issue createIssue = ((JiraPlatform) abstractJiraInstance).createIssue(issue);
			resultText = createIssue.getKey();
		}
		return resultText;
	}

	private AbstractJiraInstance createJiraInstance(final JiraApiEnum selectedItem) {

		final char[] password = passwordInput.getPassword();
		final String passwordString = new String(password);
		final HttpAuthenticationFeature basic = HttpAuthenticationFeature.basic(userNameInput.getText(), passwordString);

		switch (selectedItem) {
		case JiraPlatform:
			return new JiraPlatform(jiraServerUri, basic);
		case JiraServicedesk:
			return new JiraServicedesk(jiraServerUri, basic);
		}
		return null;
	}
}
