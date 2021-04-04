package hongik.hicc.practice;

public class ContactDB {
    public static final String TABLE_NAME = "SCHEDULE_T" ;
    public static final String COL_NO = "NO" ;
    public static final String COL_TITLE = "TITLE" ;
    public static final String COL_DATE = "DATE" ;

    public static final String SQL_CREATE_TBL =//
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    COL_NO + " INTEGER PRIMARY KEY," +
                    COL_TITLE + " varchar(255) NOT NULL," +
                    COL_DATE + " DATE NOT NULL default CURRENT_TIMESTAMP )";

    // DROP TABLE IF EXISTS CONTACT_                                                                                                                              T
    //public static final String SQL_DROP_TBL = "DROP TABLE IF EXISTS " + TBL_CONTACT ;

    // SELECT * FROM CONTACT_T
    public static final String SQL_SELECT = "SELECT * FROM " + TABLE_NAME ;

    public static final String SQL_DROP_TBL = "DROP TABLE "+ TABLE_NAME  ;

    public static final String SQL_INSERT = "INSERT OR REPLACE INTO " + TABLE_NAME + " " +
            "( "+ COL_TITLE + ", " + COL_DATE + ") VALUES " ;

    // DELETE FROM CONTACT_T
    public static final String SQL_DELETE = "DELETE FROM " + TABLE_NAME + "where col_title is "+COL_TITLE;

}
