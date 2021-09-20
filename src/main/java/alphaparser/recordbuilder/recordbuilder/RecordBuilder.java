package alphaparser.recordbuilder.recordbuilder;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;

public class RecordBuilder {
    public static Map<String, String> getRecordValues(String candidatetext, List<Value> values) {
        //if (StringUtils.isBlank(candidatetext)) return null;
        Map<String, String> recordValues = new TreeMap<>();
        for (var value:values) {
            Matcher matcher = value.buildMatcher(candidatetext);

            if (matcher.find()){
                String valueText = matcher.group(value.getGroup());
                recordValues.put(value.getId(), valueText.trim());
            }
        }

        return recordValues;
    }
}