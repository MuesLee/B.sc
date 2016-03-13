package de.ts.rfr.domain;

import de.ts.ticketsystem.client.jira.platformapi.objects.Issue;
import de.ts.ticketsystem.client.jira.platformapi.objects.IssueType;
import de.ts.ticketsystem.client.jira.platformapi.objects.Project;

public class RfrParser {

	/**
	 * Parses a line of an Raum für Raum Entry into an {@link Issue} Expected
	 * format: <br>
	 * Terminalnummer;Veranstaltungsname;Raumname;Personalnummer;Beginn;Ende;
	 * Status;Veranstalter;Bucher
	 * 
	 * @param line
	 * @return
	 */
	public static Issue parseLineToIssue(String line) {

		String[] split = line.split(";");

		Project project = new Project("10003");
		IssueType issueType = new IssueType("10005", "Aufgabe");
		String veranstaltung = split[1];
		String raum = split[2];
		String start = split[4];
		String ende = split[5];
		String veranstalter = split[7];
		String bucher = split[8];
		String description = "Veranstaltung: " + veranstaltung + "\nRaum: " + raum + "\nStart: " + start + "\nEnde: "
				+ ende + "\nVeranstaltet von: " + veranstalter + "\nRaum gebucht von: " + bucher;
		String summary = "Veranstaltung: " + veranstaltung + " in Raum " + raum;

		Issue issue = new Issue(project, summary, description, issueType);

		return issue;
	}

}
