package com.clusus.server.main.customer;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class CustomerDaoService {
	public static int customerCount = 5;
	public static List<Customer> customer = new ArrayList<>();
	
	static {
		customer.add(new Customer(001, "Ajanta Uprety",	  new Date()));
		customer.add(new Customer(002, "Laxmi Ojha", 	  new Date()));
		customer.add(new Customer(003, "Babin Shiwakoti", new Date()));
		customer.add(new Customer(004, "Laxmi Kharel", 	  new Date()));
		customer.add(new Customer(005, "Pritina Maharjan",new Date()));
		customer.add(new Customer(006, "Kabita Maharjan", new Date()));
	}
	
	public List<Customer> findAll(){
		return customer;
	}
	
	public Customer save(Customer cust) {
		if(cust.getId()== 0) {
			cust.setId(++customerCount);
		}
		customer.add(cust);
		return cust;
	}
	
	public Customer findOne(int id){
		for(Customer cust:customer) {
			if(cust.getId() == id)
				return cust;
		}
		return null;
	}
	public Customer deleteById(int id) {
		Iterator<Customer> iterator = customer.iterator();
		while(iterator.hasNext()) {
			Customer cust = iterator.next();
			if(cust.getId() == id) {
				iterator.remove();
				return cust;
			}
		}
		return null;
	}
}
