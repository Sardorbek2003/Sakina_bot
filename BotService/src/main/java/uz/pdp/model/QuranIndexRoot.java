package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuranIndexRoot {
    public int id;
    public String name;
    public String transliteration;
    public String type;
    public int total_verses;
    public String link;
}
