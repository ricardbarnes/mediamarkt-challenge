package es.mediamarkt.product.infrastructure.provider.products.h2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface H2ProductRepository extends JpaRepository<H2Product, Long> {

    @Query(value = "SELECT NEXT VALUE FOR product_id_seq", nativeQuery = true)
    Long getNextSequenceValue();

}
