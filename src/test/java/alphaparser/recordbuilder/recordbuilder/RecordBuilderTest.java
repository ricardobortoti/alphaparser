package alphaparser.recordbuilder.recordbuilder;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecordBuilderTest {
    private RecordBuilder recordBuilder;
    private String validLineInput;
    private List<Value> values;

    void whenValidLineInput() {
        validLineInput = "1-BOVESPA C FRACIONARIO HYPERA          ON EJ NM 60 32,74 1.964,40 D";
        values = new ArrayList<>();
        values.add(new Value("Compra/Venda", "(?s)((?<=BOVESPA)(.*?)(?=VISTA|FRACIONARIO*?))"));
        values.add(new Value("Ativo", "(?s)((?<=FRACIONARIO|VISTA)(.*?)(?=[0-9]+))"));
        values.add(new Value("Quantidade", 1,"(\\b ([\\d,.]+)\\b)(\\b ([\\d,.]+)\\b)(\\b ([\\d,.]+)\\b)"));
        values.add(new Value("Preco", 3,"(\\b ([\\d,.]+)\\b)(\\b ([\\d,.]+)\\b)(\\b ([\\d,.]+)\\b)"));
        values.add(new Value("Valor Operacao", 5,"(\\b ([\\d,.]+)\\b)(\\b ([\\d,.]+)\\b)(\\b ([\\d,.]+)\\b)"));
    }

    @Test
    void getRecordValues() {
        whenValidLineInput();

        var result = recordBuilder.getRecordValues(validLineInput, values);

        assertTrue(result.containsKey("Compra/Venda"));
        assertTrue(result.containsKey("Ativo"));

        assertEquals("C", result.get("Compra/Venda"));
        assertEquals("HYPERA          ON EJ NM", result.get("Ativo"));
        assertEquals("60", result.get("Quantidade"));
        assertEquals("32,74", result.get("Preco"));
        assertEquals("1.964,40", result.get("Valor Operacao"));
    }
}