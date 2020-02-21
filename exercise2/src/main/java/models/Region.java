package models;

import lombok.*;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Region {
    private String regionId;
    private String name;
}
