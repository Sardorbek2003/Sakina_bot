package uz.pdp.service;

import com.fasterxml.jackson.core.type.TypeReference;
import uz.pdp.model.SurahDigitState;
import uz.pdp.model.Surahs;
import uz.pdp.util.FilePath;
import uz.pdp.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class KoranService {
    public List<Surahs> getStringList(List<Surahs> surahs, int n) {
        List<Surahs> surahsList = new ArrayList<>();
        for (int i = n; i < n + 10 && n <= 114; i++) {
            Surahs surah = surahs.get(i);
            surahsList.add(surah);
        }
        return surahsList;
    }

    public List<Surahs> getSurahs() {
        return JsonUtil.readGson(FilePath.PATH_SURAHS, new TypeReference<List<Surahs>>() {
        });
    }

    public void setSurahs(List<Surahs> surahs) {
        JsonUtil.writeGson(FilePath.PATH_SURAHS, surahs);
    }

    public List<SurahDigitState> getSurahsState() {
        return JsonUtil.readGson(FilePath.PATH_SURAHSTATE, new TypeReference<List<SurahDigitState>>() {
        });
    }

    public void setSurahsState(List<SurahDigitState> surahsState) {
        JsonUtil.writeGson(FilePath.PATH_SURAHSTATE, surahsState);
    }

    public void newMenyu(long chatId, int n) {
        int k = 0;
        List<SurahDigitState> states = new ArrayList<>();
        for (SurahDigitState state : states) {
            if (state.getChatId() == chatId) {
                k = 10;
                if (n == 0) {
                    state.setId(0);
                } else if (n == 10 || n == -10) {
                    state.setId(state.getId() + n);
                }
            }
        }
        if (k == 10) {
            setSurahsState(states);
        }else {
            setSurahsState(List.of(new SurahDigitState(0,chatId)));
        }
    }
}
