package models;

import lombok.*;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DataBaseSource {
    private String url;
    private String driverName;
    private String userName;
    private String password;
    private String regionId;
}
