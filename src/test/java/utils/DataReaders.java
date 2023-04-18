package utils;


import com.creditdatamw.zerocell.Reader;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.taf.data_models.TestData;


import java.io.File;
import java.util.Arrays;
import java.util.List;


public class DataReaders {

/*

this method for reading the json data binding
 */
    @SneakyThrows
    public static <T> List<T> getDataFromJson(String filePath, Class<T>dataModelClass) {

        ObjectMapper mapper = new ObjectMapper();
        Class<T[]> arrayClass = (Class<T[]>) Class.forName("[L" + dataModelClass.getName() + ";");
        T[] objects = mapper.readValue(new File(filePath), arrayClass);
        return Arrays.asList(objects);
    }

    @SneakyThrows
    public static <T> List<T> getDataFromExcel(String filePath, String sheet,Class<T>dataModelClass) {

       // Class<T[]> arrayClass = (Class<T[]>) Class.forName("[L" + dataModelClass.getName() + ";");

        return Reader.of(dataModelClass)
                .from(new File(filePath))
                .sheet(sheet)
                .skipHeaderRow(true)
                .list();

    }

}
