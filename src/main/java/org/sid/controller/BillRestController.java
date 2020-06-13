package org.sid.controller;

import org.sid.entity.Bill;
import org.sid.repository.BillRepository;
import org.sid.repository.ProductItemRepository;
import org.sid.service.CustomerService;
import org.sid.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillRestController {
	@Autowired
	private BillRepository billRepository;
	@Autowired
	private ProductItemRepository productItemRepository;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private InventoryService inventoryService;

	@GetMapping("/fullBill/{id}")
	public Bill getBill(@PathVariable(name = "id") Long id) {
		Bill bill = billRepository.findById(id).get();
		bill.setCustomer(customerService.findCustomerById(bill.getCustomerId()));
		bill.getProductItems().forEach(pi->{
			pi.setProduct(inventoryService.findProductById(pi.getProductId()));
		});
		return bill;
	}

}
