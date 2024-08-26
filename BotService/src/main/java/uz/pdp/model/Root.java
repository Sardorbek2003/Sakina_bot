package uz.pdp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
    public String weekday;
    public Times times;
}
