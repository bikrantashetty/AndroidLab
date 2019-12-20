package com.mrbing.lab3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    private static String CREATE_TABLE1;
    static String DATABASE_NAME = "EmployeeRecords";
    public static final String TABLE1_NAME = "employee";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String SALARY = "salary";

    private ContentValues cValues;
    private SQLiteDatabase dataBase = null;
    private Cursor cursor;

    public DbHelper(Context context) {
        super(context, context.getExternalFilesDir(null).getAbsolutePath() + "/" + DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        CREATE_TABLE1 = "CREATE TABLE " + TABLE1_NAME + "(" + ID  + " INTEGER PRIMARY KEY autoincrement, " + NAME + " TEXT, " + SALARY + " TEXT)";
        db.execSQL(CREATE_TABLE1);
        System.out.println("Table is created!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE1_NAME);
        onCreate(db);
    }

    public void inserRecord(String name, String salary) {
        dataBase = getWritableDatabase();
        cValues = new ContentValues();

        cValues.put(NAME, name);
        cValues.put(SALARY, salary);

        dataBase.insert(TABLE1_NAME, null, cValues);

        dataBase.close();
    }

    public boolean updateRecord(String name, String salary) {
        dataBase = getWritableDatabase();
        cValues = new ContentValues();
        cValues.put(NAME, name);
        cValues.put(SALARY, salary);

        dataBase.update(DbHelper.TABLE1_NAME, cValues,null, null);
        dataBase.close();
        return true;
    }

    public Cursor selectRecords() {
        dataBase = getReadableDatabase();

        cursor = dataBase.rawQuery("select * from " + TABLE1_NAME, null);
        return cursor;
    }

    public void deleteRecord() {
        dataBase = getWritableDatabase();
        dataBase.delete(TABLE1_NAME, null, null);
        dataBase.close();
    }
}
