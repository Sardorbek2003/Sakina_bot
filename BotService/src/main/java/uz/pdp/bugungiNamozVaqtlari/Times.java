package uz.pdp.bugungiNamozVaqtlari;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public  class Times{
    private String tong_saharlik;
    private String quyosh;
    private String peshin;
    private String asr;
    private String shom_iftor;
    private String hufton;
}