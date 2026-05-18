package es.mediamarkt.product.infrastructure.provider.categories.h2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface H2CategoryRepository extends JpaRepository<H2Category, Long> {

    List<H2Category> findByIdIn(Collection<Long> ids);

    @Query(value = "SELECT COALESCE(MAX(id), 999999) + 1 FROM categories", nativeQuery = true)
    Long getNextSequenceValue();

}