package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuranKarimRoot {
    public int id;
    public String name;
    public String transliteration;
    public String type;
    public int total_verses;
    public ArrayList<Verse> verses;
}