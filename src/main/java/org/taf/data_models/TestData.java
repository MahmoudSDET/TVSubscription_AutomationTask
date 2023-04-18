package org.taf.data_models;


import com.creditdatamw.zerocell.annotation.Column;

import io.github.sskorol.data.Sheet;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.taf.utils.ExcelReader.StringToDoubleConverter;

@Getter
@ToString
@Data
@Sheet(name = "Sheet1")
public class TestData {

    //POJO
    @Column(name = "Country", index=0 )
    public String country;

    @Column(name = "PlanType", index=1 )
    public String planType;

    @Column(name = "Price", index=2, converterClass = StringToDoubleConverter.class)
    public double price;

    @Column(name = "Currency", index=3 )
    public String currency;

}
