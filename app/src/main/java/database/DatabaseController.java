package database;


import com.jarvislin.producepricechecker.ProduceData;
import com.jarvislin.producepricechecker.util.Constants;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.sql.language.Update;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jarvis Lin on 2015/6/13.
 */
public class DatabaseController {
    public static ArrayList<Produce> getProduces(String kind) {
        return new ArrayList<Produce>(new Select().from(Produce.class).where(Condition.column(Produce$Table.KIND).
                is(kind)).queryList());

    }

    public static boolean isBookmark(String name, String type) {
        return !new ArrayList<Produce>(new Select().from(Produce.class)
                .where(Condition.column(Produce$Table.NAME).is(name))
                .and(Condition.column(Produce$Table.TYPE).is(type))
                .and(Condition.column(Produce$Table.KIND).is(Constants.BOOKMARK))
                .queryList()).isEmpty();
    }

    public static void clearTable() {
        Delete.table(Produce.class);
    }

    public static void clearTable(String kind) {
        new Delete()
                .from(Produce.class)
                .where(Condition.column(Produce$Table.KIND).is(kind)).query();
    }

    public static void deleteBookMark(String name, String type) {
        new Delete()
                .from(Produce.class)
                .where(Condition.column(Produce$Table.NAME).is(name))
                .and(Condition.column(Produce$Table.TYPE).is(type))
                .and(Condition.column(Produce$Table.KIND).is(Constants.BOOKMARK))
                .query();
    }

    public static void insertBookmark(Produce object) {
        Produce bookmark = new Produce();
        bookmark.name = object.name;
        bookmark.type = object.type;
        bookmark.topPrice = object.topPrice;
        bookmark.mediumPrice = object.mediumPrice;
        bookmark.lowPrice = object.lowPrice;
        bookmark.averagePrice = object.averagePrice;
        bookmark.date = object.date;
        bookmark.kind = Constants.BOOKMARK;
        bookmark.save();
    }

    public static void updateBookmark(ArrayList<Produce> produces) {
        ArrayList<Produce> bookmarks = getProduces(Constants.BOOKMARK);
        for(Produce bookmark : bookmarks){
            for(Produce produce : produces){
                if(bookmark.name.equals(produce.name) && bookmark.type.equals(produce.type)){
                    Condition[] conditions = {
                            Condition.column(Produce$Table.TOPPRICE).eq(produce.topPrice)
                            ,Condition.column(Produce$Table.MEDIUMPRICE).eq(produce.mediumPrice)
                            ,Condition.column(Produce$Table.LOWPRICE).eq(produce.lowPrice)
                            ,Condition.column(Produce$Table.AVERAGEPRICE).eq(produce.averagePrice)
                            ,Condition.column(Produce$Table.DATE).eq(produce.date)
                    };

                    new Update(Produce.class)
                            .set(conditions)
                            .where(Condition.column(Produce$Table.TYPE).is(produce.type))
                            .and(Condition.column(Produce$Table.NAME).is(produce.name))
                            .and(Condition.column(Produce$Table.KIND).is(Constants.BOOKMARK))
                            .queryClose();
                }
            }
        }

    }
}
