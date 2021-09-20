package alphaparser.regionextractor.regionextractor;

import inputs.Inputs;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegionTest {

    @Test
    void getRegionContent() {
        Region region = new Region(Inputs.regionTestInput,
                "Negócios realizados",
                "NOTA DE NEGOCIAÇÃO",
                false,
                false);

        assertEquals(Inputs.getRegionTestInputExpected, region.getRegionContent());
    }
}