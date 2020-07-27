import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {

    private static final String SPACE_PATTERN = "\\s+";
    private static final String WRAP_PATTERN = "\n";
    private static final String SINGLE_SPACE = " ";
    private static final String CALCULATE_ERROR_MESSAGE = "Calculate Error";

    public String getResult(String sentence) {

        if (sentence.split(SPACE_PATTERN).length == 1) {
            return sentence + " 1";
        } else {
            try {
                String[] word = sentence.split(SPACE_PATTERN);

                List<WordInfo> wordInfos = new ArrayList<>();
                for (String letter : word) {
                    WordInfo wordInfo = new WordInfo(letter, 1);
                    wordInfos.add(wordInfo);
                }

                wordInfos = calculateWordCount(wordInfos);

                wordInfos.sort((firstWordInfo, secondWordInfo) -> secondWordInfo.getWordCount() - firstWordInfo.getWordCount());

                StringJoiner joiner = new StringJoiner(WRAP_PATTERN);
                return getWordInfosResult(wordInfos, joiner);
            } catch (Exception e) {
                return CALCULATE_ERROR_MESSAGE;
            }
        }
    }

    private List<WordInfo> calculateWordCount(List<WordInfo> wordInfos) {
        Map<String, List<WordInfo>> map = getListMap(wordInfos);

        List<WordInfo> tempWordInfos = new ArrayList<>();
        for (Map.Entry<String, List<WordInfo>> entry : map.entrySet()) {
            WordInfo wordInfo = new WordInfo(entry.getKey(), entry.getValue().size());
            tempWordInfos.add(wordInfo);
        }
        return tempWordInfos;
    }

    private String getWordInfosResult(List<WordInfo> wordInfos, StringJoiner joiner) {
        for (WordInfo wordInfo : wordInfos) {
            String wordInfoResult = wordInfo.getValue() + SINGLE_SPACE + wordInfo.getWordCount();
            joiner.add(wordInfoResult);
        }
        return joiner.toString();
    }

    private Map<String, List<WordInfo>> getListMap(List<WordInfo> inputList) {
        Map<String, List<WordInfo>> map = new HashMap<>();
        for (WordInfo wordInfo : inputList) {
            if (!map.containsKey(wordInfo.getValue())) {
                ArrayList wordInfos = new ArrayList<>();
                wordInfos.add(wordInfo);
                map.put(wordInfo.getValue(), wordInfos);
            } else {
                map.get(wordInfo.getValue()).add(wordInfo);
            }
        }
        return map;
    }
}
