package models;

import com.poiji.annotation.ExcelCell;
import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;

@ExcelSheet("items")
public class ItemProduct {

    @ExcelCellName("NOMBRE")
    private String name;
    @ExcelCellName("PRECIO")
    private double precio;

    public String getName() {
        return name;
    }

    public double getPrecio() {
        return precio;
    }
}
