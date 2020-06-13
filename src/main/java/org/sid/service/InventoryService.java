package org.sid.service;

import org.sid.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient("INVENTORY-SERVICE")
public interface InventoryService {
	@GetMapping("/products/{id}")
	public Product findProductById(@PathVariable("id") Long id);

	@GetMapping("/products")
	public PagedModel<Product> findAllProducts();
	
}
