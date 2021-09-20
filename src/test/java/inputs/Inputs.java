package inputs;

public class Inputs {

    public static String regionTestInput = "Negócios realizados\n" +
            "Q Negociação C/V Tipo mercado Prazo Especificação do título Obs. (*) Quantidade Preço / Ajuste Valor Operação / Ajuste D/C\n" +
            "1-BOVESPA C FRACIONARIO TOTVS          ON EJ NM H 15 34,25 513,75 D\n" +
            "1-BOVESPA C VISTA FII RIOB RC          FFCI11          CI H 100 1,58 158,00 D\n" +
            "NOTA DE NEGOCIAÇÃO\n" +
            "Nr. nota\n" +
            "4310\n" +
            "Folha\n" +
            "1\n" +
            "Data pregão\n" +
            "06/01/2015\n" +
            "Rico Investimentos - Grupo XP\n" +
            "Av. Presidente Juscelino Kubitschek - Torre Sul, 1909 - 25º ANDAR VILA OLIMPIA 4543-907 SÃO PAULO - SP\n" +
            "Tel. 3003-5465   Fax: (55 11) 4007-2465\n" +
            "Internet: www.rico.com.vc SAC: 0800-774-0402 e-mail: atendimento@rico.com.vc\n" +
            "C.N.P.J: 02.332.886/0016-82 Carta Patente:\n" +
            "Ouvidoria: Tel. 08007740402 e-mail ouvidoria:\n" +
            "Cliente\n" +
            "0079706 RICARDO BORTOTI DA SILVA\n" +
            "R DIAMANTINA, 599 VILA VIRGINIA Tel. (011) 97399-6545\n" +
            "08573-080 ITAQUAQUECETUBA - SP\n" +
            "Participante destino do repasse";

    public static String getRegionTestInputExpected = "Q Negociação C/V Tipo mercado Prazo Especificação do título Obs. (*) Quantidade Preço / Ajuste Valor Operação / Ajuste D/C\n" +
            "1-BOVESPA C FRACIONARIO TOTVS          ON EJ NM H 15 34,25 513,75 D\n" +
            "1-BOVESPA C VISTA FII RIOB RC          FFCI11          CI H 100 1,58 158,00 D";
}
