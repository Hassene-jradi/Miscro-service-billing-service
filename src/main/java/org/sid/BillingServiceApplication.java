package org.sid;

import java.util.Date;

import org.sid.entity.Bill;
import org.sid.entity.ProductItem;
import org.sid.model.Customer;
import org.sid.model.Product;
import org.sid.repository.BillRepository;
import org.sid.repository.ProductItemRepository;
import org.sid.service.CustomerService;
import org.sid.service.InventoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(BillRepository billRepository, ProductItemRepository productItemRepository,
			CustomerService customerService, InventoryService inventoryService) {
		return args -> {
			Customer c1 = customerService.findCustomerById(1L);
			System.out.println("****************");
			System.out.println("ID : " + c1.getId() +",  nom : "+ c1.getName()+" , email : " +c1.getEmail());
			System.out.println("****************");
			Bill bill1 = billRepository.save(new Bill(null, new Date(), c1.getId(), null, null));
//			Product p1 = inventoryService.getProductById(1L);
//			Product p2 = inventoryService.getProductById(2L);
//			Product p3 = inventoryService.getProductById(3L);
			PagedModel<Product> products = inventoryService.findAllProducts();
			products.getContent().forEach(p->{
				productItemRepository.save(new ProductItem(null, p.getId(), null, p.getPrice(), 12, bill1));
			});
//			productItemRepository.save(new ProductItem(null, p1.getId(), p1.getPrice(), 12, bill1));
//			productItemRepository.save(new ProductItem(null, p2.getId(), p2.getPrice(), 12, bill1));
//			productItemRepository.save(new ProductItem(null, p3.getId(), p3.getPrice(), 12, bill1));
		};
	}
}
