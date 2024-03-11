package model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class Toy {
    private String toy_name;
    private TypeToy type;
    private Integer amount;
    private Integer price_unit;
    private Integer price_total;
}
