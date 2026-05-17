package es.mediamarkt.product.infrastructure.provider.products.h2;

import es.mediamarkt.product.domain.products.model.ProductOnlineStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
@Data
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class H2Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "online_status")
    private ProductOnlineStatus onlineStatus;

    @Lob
    @Column(name = "long_description")
    private String longDescription;

    @Column(name = "short_description")
    private String shortDescription;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "product_categories", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "category_id")
    private Set<Long> categoryIds = new HashSet<>();

}
