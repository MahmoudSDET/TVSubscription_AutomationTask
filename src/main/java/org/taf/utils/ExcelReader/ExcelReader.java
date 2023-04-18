package org.taf.utils.ExcelReader;

import com.creditdatamw.zerocell.Reader;
import org.taf.data_models.TestData;

import java.io.File;
import java.util.List;

public final class ExcelReader {

    private ExcelReader(){}

    public static List<TestData> getTestdatas() {
        return testdatas;
    }

    private static List<TestData> testdatas = null;

    static{
       testdatas = Reader.of(TestData.class)
                .from(new File("testdata.xlsx"))
                .sheet("Sheet1")
                .skipHeaderRow(true)
                .list();

    }
}
