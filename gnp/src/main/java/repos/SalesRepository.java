package repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import models.Sales;

public interface SalesRepository extends JpaRepository<Sales, Integer> {
	List<Sales> findFirst28ByOrderByDateDesc();
}
