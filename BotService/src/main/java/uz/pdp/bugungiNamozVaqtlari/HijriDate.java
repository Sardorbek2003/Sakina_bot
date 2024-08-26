package uz.pdp.bugungiNamozVaqtlari;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public  class aaaa {
    public String month;
    public int day;
}
