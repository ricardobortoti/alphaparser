package alphaparser.regionextractor.regionextractor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Region {
    private String regionContent;
    private Pattern rulePattern;
    private Matcher ruleMatcher;
    private String regionStart;
    private String regionEnd;
    private Boolean inclusiveRegionStart;
    private Boolean inclusiveRegionEnd;

    public Region(String input, String regionstart, String regionEnd, boolean inclusiveRegionStart, boolean inclusiveRegionEnd ){
        this.regionStart = regionstart;
        this.regionEnd = regionEnd;
        this.inclusiveRegionStart = inclusiveRegionEnd;
        this.inclusiveRegionEnd = inclusiveRegionEnd;

        rulePattern = Pattern.compile(this.buildRegex(), Pattern.MULTILINE);
        ruleMatcher = rulePattern.matcher(input);
    }

    private String match(){
        if (ruleMatcher.find())
        {
            return ruleMatcher.group(0);
        }
        else
        {
            return "";
        }
    }

    private void trimRegion() {
        this.regionContent = this.regionContent.strip();
    }

    public String getRegionContent() {
        if (ruleMatcher.find())
        {
            this.regionContent = ruleMatcher.group(0);
            trimRegion();
        }
        else
        {
            return "";
        }
        return this.regionContent;
    }

    private String buildRegex(){
        StringBuilder expression = new StringBuilder();

        expression.append("(?s)(");

        if (!inclusiveRegionStart)
        {
            expression.append(String.format("(?<=%s)", regionStart));
        }
        else {
            expression.append(String.format("(%s)", regionStart));
        }

        expression.append("(.*?)");

        if (!inclusiveRegionEnd)
        {
            expression.append(String.format("(?=%s.*?)", regionEnd));
        }
        else {
            expression.append(String.format("(%s.*?)", regionEnd));
        }

        expression.append(")");

        return expression.toString();
    }
}
