package alphaparser.loader.loader;

import alphaparser.config.Config;
import alphaparser.config.Delimiter;
import alphaparser.config.dto.ConfigDto;
import alphaparser.enums.enums.DelimiterTypeEnum;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import alphaparser.recordbuilder.recordbuilder.Value;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ConfigLoader {
    private String yamlFile;
    private ConfigDto configDto;

    public ConfigLoader(String yamlFile) {
        this.yamlFile = yamlFile;
    }

    public Config load() {
        Yaml yaml = new Yaml(new Constructor(ConfigDto.class));

        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(yamlFile);

        configDto = yaml.load(inputStream);

        return loadConfig();
    }

    private Config loadConfig() {
        return new Config(loadValues(), loadDelimiters());
    }

    private List<Value> loadValues() {
        List<Value> values = new ArrayList<>();
        for (var value:configDto.getValues()) {
            values.add(new Value(value.getId(), value.getPattern()));
        }
        return values;
    }

    private List<Delimiter> loadDelimiters() {
        List<Delimiter> delimiters = new ArrayList<>();
        for (var delimiter:configDto.getDelimiters()) {
            DelimiterTypeEnum type = DelimiterTypeEnum.valueOf(delimiter.getType());
            delimiters.add(new Delimiter(delimiter.getPattern(), type));
        }
        return delimiters;
    }
}
