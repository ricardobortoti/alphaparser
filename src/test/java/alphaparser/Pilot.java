package alphaparser;

import alphaparser.Parser;
import alphaparser.config.Config;
import alphaparser.config.Delimiter;
import alphaparser.enums.enums.DelimiterTypeEnum;
import alphaparser.loader.loader.PdfLoader;
import org.junit.jupiter.api.Test;
import alphaparser.recordbuilder.recordbuilder.Value;
import alphaparser.regionextractor.regionextractor.Region;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pilot {
    @Test
    public void pilot() throws IOException {
                /*
        just a pilot class for me to test in my env
        PdfLoader pdfLoader = new PdfLoader("/home/ricardo/repo/alphaparser/files");
        List<String> docs = pdfLoader.loadDocs();

        String headerPattern = "Q Negociação C\\/V Tipo mercado Prazo Especificação do título Obs\\. \\(\\*\\) Quantidade Preço \\/ Ajuste Valor Operação \\/ Ajuste D\\/C";
        String linePattern = "^[0-9].*(D|C)$";

        Map<Integer, List<Map<String,String>>> all = new HashMap<>();
        Integer count = 0;
        for (var doc:docs) {
            Region region = new Region(doc,
                    "Negócios realizados",
                    "NOTA DE NEGOCIAÇÃO",
                    false,
                    false);

            var content = region.getRegionContent();

            Reader reader = new StringReader(content);

            List<Value> values = new ArrayList<>();
            values.add(new Value("Compra/Venda", "(?s)((?<=BOVESPA)(.*?)(?=VISTA|FRACIONARIO*?))"));
            values.add(new Value("Ativo", "(?s)((?<=FRACIONARIO|VISTA)(.*?)(?=[0-9]+))"));
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

            all.put(count++, result);
        }

        List<String> strippedDocs = new ArrayList<>();
                 */
    }
}
