import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {

    public static final String SPACE_PATTERN = "\\s+";
    public static final String WRAP_PATTERN = "\n";
    public static final String SINGLE_SPACE = " ";
    public static final String CALCULATE_ERROR_MESSAGE = "Calculate Error";

    public String getResult(String sentence) {

        if (sentence.split(SPACE_PATTERN).length == 1) {
            return sentence + " 1";
        } else {
            try {
                String[] word = sentence.split(SPACE_PATTERN);

                List<WordInfo> wordInfos = new ArrayList<>();
                for (String s : word) {
                    WordInfo wordInfo = new WordInfo(s, 1);
                    wordInfos.add(wordInfo);
                }

                Map<String, List<WordInfo>> map = getListMap(wordInfos);

                List<WordInfo> list = new ArrayList<>();
                for (Map.Entry<String, List<WordInfo>> entry : map.entrySet()) {
                    WordInfo input = new WordInfo(entry.getKey(), entry.getValue().size());
                    list.add(input);
                }
                wordInfos = list;

                wordInfos.sort((firstWordInfo, secondWordInfo) -> secondWordInfo.getWordCount() - firstWordInfo.getWordCount());

                StringJoiner joiner = new StringJoiner(WRAP_PATTERN);
                for (WordInfo w : wordInfos) {
                    String s = w.getValue() + SINGLE_SPACE + w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {
                return CALCULATE_ERROR_MESSAGE;
            }
        }
    }

    private Map<String, List<WordInfo>> getListMap(List<WordInfo> inputList) {
        Map<String, List<WordInfo>> map = new HashMap<>();
        for (WordInfo input : inputList) {
            if (!map.containsKey(input.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getValue(), arr);
            } else {
                map.get(input.getValue()).add(input);
            }
        }
        return map;
    }
}
