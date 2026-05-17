package es.mediamarkt.product.infrastructure.provider.categories.h2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface H2CategoryRepository extends JpaRepository<H2Category, Long> {

    @Query(value = "SELECT NEXT VALUE FOR category_id_seq", nativeQuery = true)
    Long getNextSequenceValue();

    List<H2Category> findByIdIn(Collection<Long> ids);

}
