import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import java.io.File;
import java.util.List;

/**
 * Created by 43890 on 2016/9/19.
 */

public class TestUniVocity{

    public void printRows(){
        String pathName = "D:\\data\\123.csv";
        File file = new File(pathName);
        CsvParserSettings settings = new CsvParserSettings();
        //the file used in the example uses '\n' as the line separator sequence.
        //the line separator sequence is defined here to ensure systems such as MacOS and Windows
        //are able to process this file correctly (MacOS uses '\r'; and Windows uses '\r\n').
        settings.getFormat().setLineSeparator("\n");
        // creates a CSV parser
        CsvParser parser = new CsvParser(settings);
        // parses all rows in one go.
        List<String[]> allRows = parser.parseAll(file,"UTF-8");
        //##CODE_END
        for (String[] row : allRows) {
            for (int i = 0; i < row.length; i++) {
                System.out.print(row[i]+" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        new TestUniVocity().printRows();
    }
}
