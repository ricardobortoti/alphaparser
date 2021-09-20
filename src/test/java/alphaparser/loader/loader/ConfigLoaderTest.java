package alphaparser.loader.loader;

import alphaparser.config.Config;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConfigLoaderTest {

    @Test
    void load() {
        ConfigLoader loader = new ConfigLoader("alphaparser.yml");
        Config config = loader.load();

        assertNotNull(loader);
    }
}