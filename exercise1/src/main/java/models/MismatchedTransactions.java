package models;

import lombok.*;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MismatchedTransactions {
    private Transactions inputTransactions;

    private Transactions targetTransactions;
}
