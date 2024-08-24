package uz.pdp.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import uz.pdp.bugungiNamozVaqtlari.Root;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MonthlyPrayerTimesUtil {
    public static void write() {

        File file = new File("file/namoz_vaqtlari.json");

        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        URL url = null;
        try {
            url = new URL("https://islomapi.uz/api/monthly?region=Toshkent&month=8");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        ObjectMapper objectMapper = new ObjectMapper();

        try {

            List<Root> roots = objectMapper.readValue(url, new TypeReference<List<Root>>() {
            });
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, roots);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
