package models;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "transactions")
@XmlAccessorType(XmlAccessType.FIELD)
public class Transactions implements Serializable {
    @XmlElement(name = "transaction")
    private List<Transaction> transactions;
}
