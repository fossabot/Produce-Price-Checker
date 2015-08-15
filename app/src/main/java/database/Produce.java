package database;

import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jarvis Lin on 2015/6/13.
 */
@ModelContainer
@Table(databaseName = ProduceDatabase.NAME)
public class Produce extends BaseModel implements Serializable {

    @PrimaryKey(autoincrement = true)
    @Column
    public int id;

    @Column
    public String produceName;

    @Column
    public String produceNumber;

    @Column
    public String topPrice;

    @Column
    public String middlePrice;

    @Column
    public String lowPrice;

    @Column
    public String averagePrice;

    @Column
    public String transactionDate;

    @Column
    public String transactionAmount;

    @SerializedName("main_category")
    @Column
    public String mainCategory;

    @SerializedName("sub_category")
    @Column
    public String subCategory;

    @Column
    public String marketNumber;

    @Column
    public String marketName;

    @Override
    public String toString() {
        return "ClassPojo [transactionAmount = " + transactionAmount + ", lowPrice = " + lowPrice + ", transactionDate = " + transactionDate + ", main_category = " + mainCategory + ", produceName = " + produceName + ", middlePrice = " + middlePrice + ", averagePrice = " + averagePrice + ", marketNumber = " + marketNumber + ", marketName = " + marketName + ", topPrice = " + topPrice + ", sub_category = " + subCategory + ", produceNumber = " + produceNumber + "]";
    }


}
