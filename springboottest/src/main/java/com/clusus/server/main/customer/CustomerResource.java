package com.clusus.server.main.customer;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

@RestController
public class CustomerResource {

	@Autowired
	private CustomerDaoService service;
	
	@GetMapping("/customers/csv")
	public void retrieveAllCSV(HttpServletResponse response) throws Exception{ 
		String filename = "allCustomer.csv";
		
		response.setContentType("text/csv");
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");
		
		StatefulBeanToCsv<Customer> writer = new StatefulBeanToCsvBuilder<Customer>(response.getWriter())
				.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
				.withSeparator(CSVWriter.DEFAULT_SEPARATOR)
				.withOrderedResults(false)
				.build();
				
		writer.write(service.findAll());
	}
	
	@GetMapping("/customers/csv/{id}")
	public void retrieveCSV(HttpServletResponse response, @PathVariable int id) throws Exception{ 
		String filename = "oneCustomer.csv";
		
		response.setContentType("text/csv");
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");
		
		StatefulBeanToCsv<Customer> writer = new StatefulBeanToCsvBuilder<Customer>(response.getWriter())
				.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
				.withSeparator(CSVWriter.DEFAULT_SEPARATOR)
				.withOrderedResults(false)
				.build();
				
		writer.write(service.findOne(id));
	}
	
	@GetMapping(value="/customers/xml", produces=MediaType.APPLICATION_XML_VALUE)
	public List<Customer> retriveAllCustomer(){
		return service.findAll();
	}
	
	@GetMapping(value="/customers/xml/{id}", produces=MediaType.APPLICATION_XML_VALUE)
	public Customer retriveCustomer(@PathVariable int id) throws CustomerNotFoundException{
		Customer cust = service.findOne(id);
		if(cust == null) 
			throw new CustomerNotFoundException("Id: " + id + " not found");
		return cust;	
	}
	
	@PostMapping(value="/customers/xml", consumes=MediaType.APPLICATION_XML_VALUE, produces=MediaType.APPLICATION_XML_VALUE)
	public Customer createCustomer(@RequestBody Customer customer) {
		return service.save(customer);
	}
	
	@DeleteMapping(value="/customers/xml/{id}", produces=MediaType.APPLICATION_XML_VALUE)
	public void deleteCustomer(@PathVariable int id) {
		Customer cust = service.deleteById(id);
		if(cust == null) 
			throw new CustomerNotFoundException("Id: " + id + " not found");
	}
	@PutMapping(value="/customers/xml/{id}", consumes=MediaType.APPLICATION_XML_VALUE, produces=MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Object> updateCustomer(@RequestBody Customer customer, @PathVariable int id) {
		Customer cust = service.findOne(id);
		
		if(cust == null) 
			throw new CustomerNotFoundException("Id: " + id + " not found");
		
		cust.setName(customer.getName());
		cust.setDateOfPurchase(customer.getDateOfPurchase());
		
		final Customer updatedCust = service.save(cust);
		return ResponseEntity.ok(updatedCust);
		
			
	}
	
}
