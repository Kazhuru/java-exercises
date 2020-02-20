package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "transaction")
@XmlAccessorType(XmlAccessType.FIELD)
public class Transaction implements Serializable {

    @XmlElement(name = "account")
    private long account;

    @XmlElement(name = "amount")
    private double amount;

    @XmlElement(name = "reference")
    private String reference;

    @XmlElement(name = "card-name")
    @JsonProperty(value = "card-name")
    private String cardName;

    @XmlElement(name = "card-type")
    @JsonProperty(value = "card-type")
    private String cardType;
}
