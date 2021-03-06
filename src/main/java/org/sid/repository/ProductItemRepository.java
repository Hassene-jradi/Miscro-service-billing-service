package org.sid.repository;

import org.sid.entity.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource
public interface ProductItemRepository extends JpaRepository<ProductItem, Long>{

}
