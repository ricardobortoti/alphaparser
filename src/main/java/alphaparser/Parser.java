package alphaparser;

import alphaparser.config.Config;
import alphaparser.config.Delimiter;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;

public class Parser {
    private final BufferedReader reader;
    private final Config config;

    public Parser(Config config, Reader in){
        this.config = config;
        this.reader = new BufferedReader(in);

    }

    public Map<String, String> next() {
        String rawRecord = getNextRecord();
        if (null == rawRecord) {
            return null;
        } else {
            Map<String, String> record = config.BuildRecord(rawRecord);
            return record;
        }
    }

    private String getNextRecord() {
        StringBuffer buffer = new StringBuffer();
        String currentLine = null;
        boolean isRecordLoaded = false;

        try{
            while (!isRecordLoaded) {
                currentLine = reader.readLine();

                if (StringUtils.isNotBlank(currentLine)) {
                    //for now, we will have one type od delimiter
                    Delimiter delimiter = config.checkMatchingDelimiter(currentLine);

                    if (null != delimiter){
                        switch (delimiter.getType()){
                            case PerLine -> {
                                buffer.append(currentLine+ "\n");
                                isRecordLoaded = true;
                                break;
                            }
                            case Header -> {
                                buffer.append(currentLine+ "\n");
                            }
                        }
                    }
                    else {
                        buffer.append(currentLine+ "\n");
                    }
                }
                else
                {
                    if (currentLine == null) {
                        return null;
                    } else
                        isRecordLoaded = true;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed Loading Records");
        }

        return buffer.toString();
    }
}
