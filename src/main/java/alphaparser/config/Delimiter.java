package alphaparser.config;

import alphaparser.enums.enums.DelimiterTypeEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Delimiter {
    private DelimiterTypeEnum delimiterType;
    private Pattern compiledPattern;
    private String pattern;

    public Delimiter(String pattern, DelimiterTypeEnum delimiterType){
        this.pattern = pattern;
        this.delimiterType = delimiterType;
    }

    public DelimiterTypeEnum getType(){
        return delimiterType;
    }

    void compile(){
        if (compiledPattern == null){
            try {
                compiledPattern = Pattern.compile(pattern, Pattern.MULTILINE);
            } catch (PatternSyntaxException e) {
                throw new IllegalArgumentException("invalid Regex");
            }
        }
    }

    Matcher buildMatcher(String recordText) {
        if (compiledPattern == null){
            compile();
        }
        return compiledPattern.matcher(recordText);
    }

    boolean matches(String candidateLine) {
        boolean match = false;
        Matcher matcher = null;

        if (null != this.pattern){
            matcher = buildMatcher(candidateLine);
        }

        switch (delimiterType) {
            default -> {
                assert matcher != null;
                if (matcher.matches()){
                    match = true;
                }
            }
        }

        return match;
    }
}
