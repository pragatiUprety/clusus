package com.clusus.server.main.customer;

import java.util.Date;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.opencsv.bean.CsvBindByPosition;

@JacksonXmlRootElement(localName = "Customer")
public class Customer {
	
	@CsvBindByPosition(position = 0)
	@JacksonXmlProperty(localName = "Idghg")
	private int id;
	
	@CsvBindByPosition(position = 1)
	@JacksonXmlProperty(localName = "Name")
	private String name;
	
	@CsvBindByPosition(position = 2)
	@JacksonXmlProperty(localName = "DateOfPurchase")
	private Date dateOfPurchase;
	
	public Customer(int id, String name, Date dateOfPurchase) {
		super();
		this.id = id;
		this.name = name;
		this.dateOfPurchase = dateOfPurchase;
	}
	
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", dateOfPurchase=" + dateOfPurchase + ", getId()=" + getId()
				+ ", getName()=" + getName() + ", getDateOfPurchase()=" + getDateOfPurchase() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDateOfPurchase() {
		return dateOfPurchase;
	}
	public void setDateOfPurchase(Date dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}
	
	
}
