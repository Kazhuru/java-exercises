package models;

import lombok.*;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionProcess {
    private int id;

    private String user;
}
