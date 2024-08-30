package uz.pdp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import uz.pdp.bugungiNamozVaqtlari.HijriDate;

import java.util.Date;

@Data
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public  class Root{
    public String region;
    public int regionNumber;
    public int month;
    public int day;
    public Date date;
    public HijriDate hijriyDate;
    public String weekday;
    public Times times;

    @Data
    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class Times {
        private String tong_saharlik;
        private String quyosh;
        private String peshin;
        private String asr;
        private String shom_iftor;
        private String hufton;
    }
}
