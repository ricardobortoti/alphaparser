package alphaparser;

import alphaparser.Parser;
import alphaparser.config.Config;
import alphaparser.config.Delimiter;
import alphaparser.enums.enums.DelimiterTypeEnum;
import org.junit.jupiter.api.Test;
import alphaparser.recordbuilder.recordbuilder.Value;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
class ParserTest {
    private static String textRecordPerLine;

    @Test
    public void ParseOneRecordPerLine(){
        String oneRecordPerLine = "Q Negociação C/V Tipo mercado Prazo Especificação do título Obs. (*) Quantidade Preço / Ajuste Valor Operação / Ajuste D/C\r\n"+
                                  "1-BOVESPA C VISTA FII RIOB RC          RCRB11          CI 2 142,49 284,98 D\r\n"+
                                  "1-BOVESPA V VISTA PETROBRAS          PETR4          CI 2 14,49 28,98 C\r\n";

        String headerPattern = "Q Negociação C\\/V Tipo mercado Prazo Especificação do título Obs\\. \\(\\*\\) Quantidade Preço \\/ Ajuste Valor Operação \\/ Ajuste D\\/C";
        String linePattern = "^[0-9].*(D|C)$";

        Reader reader = new StringReader(oneRecordPerLine);

        List<Value> values = new ArrayList<>();
        values.add(new Value("Compra/Venda", "(?s)((?<=BOVESPA)(.*?)(?=VISTA|FRACIONARIO*?))"));
        values.add(new Value("Ativo", "(?s)((?<=FRACIONARIO|VISTA)(.*?)(?=\\b[0-9]+))"));
        values.add(new Value("Quantidade", 1,"(\\b ([\\d,.]+)\\b)(\\b ([\\d,.]+)\\b)(\\b ([\\d,.]+)\\b)"));
        values.add(new Value("Preco", 3,"(\\b ([\\d,.]+)\\b)(\\b ([\\d,.]+)\\b)(\\b ([\\d,.]+)\\b)"));
        values.add(new Value("Valor Operacao", 5,"(\\b ([\\d,.]+)\\b)(\\b ([\\d,.]+)\\b)(\\b ([\\d,.]+)\\b)"));

        Config config = new Config(values);
        config.addDelimiter(new Delimiter(linePattern, DelimiterTypeEnum.PerLine));
        config.addDelimiter(new Delimiter(headerPattern, DelimiterTypeEnum.Header));

        Parser parser = new Parser(config,reader);

        List<Map<String, String>> result = new ArrayList<>();
        Map<String, String> record;
        do {
            record = parser.next();
            if (record != null) {
                result.add(record);
            }
        } while (record != null);

        assertNotNull(result);
        assertEquals("C", result.get(0).get("Compra/Venda"));
        assertEquals("FII RIOB RC          RCRB11          CI", result.get(0).get("Ativo"));
        assertEquals("2", result.get(0).get("Quantidade"));
        assertEquals("142,49", result.get(0).get("Preco"));
        assertEquals("284,98", result.get(0).get("Valor Operacao"));

        assertEquals("V", result.get(1).get("Compra/Venda"));
        assertEquals("PETROBRAS          PETR4          CI", result.get(1).get("Ativo"));
        assertEquals("2", result.get(1).get("Quantidade"));
        assertEquals("14,49", result.get(1).get("Preco"));
        assertEquals("28,98", result.get(1).get("Valor Operacao"));

    }
}