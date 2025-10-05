package data;

import com.poiji.bind.Poiji;
import models.ItemProduct;

import java.io.File;
import java.util.List;

public class ExcelReader {

    private static final String excelPath = "src/test/resources/data/dataExcel.xlsx";

    public static List<ItemProduct> readListItemProductExcel(){

        return Poiji.fromExcel(new File(excelPath), ItemProduct.class);
    }
}
