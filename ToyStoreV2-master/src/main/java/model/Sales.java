package model;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Sales {
    private Integer invoice_number;
    private LocalDate date;
}
