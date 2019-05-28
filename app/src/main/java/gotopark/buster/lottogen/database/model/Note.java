package gotopark.buster.lottogen.database.model;

/**
 * Created by ravi on 20/02/18.
 */

public class Note {
    public static final String TABLE_NAME = "notes";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOTE = "note";
    public static final String COLUMN_AUTOALOT = "alot";
    public static final String COLUMN_MAGROUP = "magroup";
    public static final String COLUMN_TIMESTAMP = "timestamp";

    public  int id;
    private String note;
    private String alot;
    private String magroup;
    private String timestamp;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NOTE + " TEXT,"
                    + COLUMN_AUTOALOT + " TEXT,"
                    + COLUMN_MAGROUP + " TEXT,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT (datetime('now','localtime'))"
                    + ")";

    public Note() {
    }

    public Note(int id, String note, String alot, String magroup ,String timestamp) {
        this.id = id;
        this.note = note;
        this.alot = alot;
        this.magroup = magroup;
        this.timestamp = timestamp;
    }

    public String getMagroup() {
        return magroup;
    }

    public void setMagroup(String magroup) {
        this.magroup = magroup;
    }

    public String getAlot() {
        return alot;
    }

    public void setAlot(String alot) {
        this.alot = alot;
    }


    public int getId() {
        return id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
