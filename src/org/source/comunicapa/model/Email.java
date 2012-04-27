package org.source.comunicapa.model;

public class Email {

	private int id;
	
	private String addressFrom;

	private String addressTo;
	private String addressCC;
	private String addressBCC;
	private String addressCCN;
	private String body;
	private String subject;
	private String contentType;


	private long time;
	
	public Email(){}
	
	public Email(String addressTo, String body, String subject){
		this.setAddressTo(addressTo);
		this.setBody(body);
		this.setSubject(subject);
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public String getAddressTo() {

		if (addressTo == null) {
			return "";
		}
		return addressTo;
	}

	public void setAddressTo(String address) {
		this.addressTo = address;
	}

	public String getBody() {
		if (body == null) {
			return "";
		}
		return body;
	}

	public void setBody(String body) {

		this.body = body;
	}

	public String getSubject() {
		if (subject == null) {
			return "";
		}
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public long getTime() {
		return time;
	}
	
	public String getAddressFrom() {
		return addressFrom;
	}

	public void setAddressFrom(String addressFrom) {
		this.addressFrom = addressFrom;
	}
	
	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}


}
