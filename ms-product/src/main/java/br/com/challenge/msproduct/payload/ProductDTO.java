package br.com.challenge.msproduct.payload;

import br.com.challenge.msproduct.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private LocalDate date;
    private String name;
    private String description;
    private String imgUrl;
    private BigDecimal price;
    private Set<Category> categories;
}
