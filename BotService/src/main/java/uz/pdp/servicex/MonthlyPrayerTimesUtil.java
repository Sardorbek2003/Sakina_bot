package uz.pdp.servicex;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import uz.pdp.model.PrayerTime;
import uz.pdp.util.FilePath;
import uz.pdp.util.JsonUtil;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MonthlyPrayerTimesUtil {

    public static void write() {
        File file = new File(FilePath.PATH_NAMOZ_VAQTLARI);
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
            List<PrayerTime> roots = objectMapper.readValue(url, new TypeReference<>() {});
            JsonUtil.writeGson(FilePath.PATH_NAMOZ_VAQTLARI, roots);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
