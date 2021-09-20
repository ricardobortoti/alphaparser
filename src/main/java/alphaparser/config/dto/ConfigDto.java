package alphaparser.config.dto;

import java.util.List;

public class ConfigDto {
    private List<ValueDto> values;

    public List<DelimiterDto> getDelimiters() {
        return delimiters;
    }

    public void setDelimiters(List<DelimiterDto> delimiters) {
        this.delimiters = delimiters;
    }

    private List<DelimiterDto> delimiters;

    public List<ValueDto> getValues() {
        return values;
    }

    public void setValues(List<ValueDto> values) {
        this.values = values;
    }
}
