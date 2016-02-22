package de.ts.ticketsystem.client.jira.servicedeskapi.objects;

public class FieldValue {

	private String fieldId;
	private String label;
	private String value;
	
	public FieldValue() {
	}

	
	
	public FieldValue(String fieldId, String value) {
		super();
		this.fieldId = fieldId;
		this.value = value;
	}

	@Override
	public String toString() {
		return "FieldValue [fieldId=" + fieldId + ", label=" + label + ", value=" + value + "]";
	}

	public String getFieldId() {
		return fieldId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	
}
