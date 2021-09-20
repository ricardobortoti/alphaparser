package alphaparser.recordbuilder.recordbuilder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Value {
    private String id;
    private Integer group;
    private String pattern;

    private Pattern compiledPattern;

    public Value(String id, Integer group, String pattern){
        this.id = id;
        this.group = group;
        this.pattern = pattern;
    }

    public Value(String id, String pattern){
        this.id = id;
        this.group = 1;
        this.pattern = pattern;
    }

    public int getGroup(){
        return Math.max(1, group);
    }

    public String getId(){
        return this.id;
    }

    void compile(){
        if (compiledPattern == null){
            try {
                Pattern valuePattern = Pattern.compile(pattern, Pattern.MULTILINE);
                compiledPattern = valuePattern;
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

}
