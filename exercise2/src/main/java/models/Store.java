package models;

import lombok.*;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Store {
    private String name;
    private String address;
    private String phoneNumber;
    private String regionId;
}
