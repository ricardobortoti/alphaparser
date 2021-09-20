package alphaparser.config;

import alphaparser.recordbuilder.recordbuilder.RecordBuilder;
import alphaparser.recordbuilder.recordbuilder.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Config {
    private List<Delimiter> delimiters = new ArrayList<>();
    private List<Value> values = new ArrayList<>();

    public void addDelimiter(Delimiter delimiter){
        delimiters.add(delimiter);
    }

    public Config(List<Value> values) {
        this.values = values;
    }

    public Delimiter checkMatchingDelimiter(String candidateLine){
        for(Delimiter delimiter : delimiters){
            if (delimiter.matches(candidateLine)) {
                return delimiter;
            }
        }
        return null;
    }

    public Map<String, String> BuildRecord (String candidate) {
        return RecordBuilder.getRecordValues(candidate, values);
    }
}
