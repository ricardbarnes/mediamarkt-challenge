package es.mediamarkt.product.infrastructure.provider.categories.h2;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "categories",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_category_name_per_catalog", columnNames = {"name", "catalog_id"})
        }
)
@Data
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class H2Category {

    @Id
    private Long id;

    private String name;

    @Column(name = "catalog_id")
    private Long catalogId;

}