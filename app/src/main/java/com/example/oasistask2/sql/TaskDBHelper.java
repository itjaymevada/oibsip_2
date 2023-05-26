package com.example.oasistask2.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.oasistask2.Tasks;

import java.util.ArrayList;

public class TaskDBHelper extends SQLiteOpenHelper {
    private	static final int DATABASE_VERSION =	5;
    private	static final String	DATABASE_NAME = "user_task";
    private	static final String TABLE_TASK = "tasks";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "task";
    private static final String COLUMN_NO = "done";

    public TaskDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String	CREATE_CONTACTS_TABLE = "CREATE	TABLE " + TABLE_TASK + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME + " TEXT," + COLUMN_NO + " BOOLEAN" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);
        onCreate(db);
    }

    public ArrayList<Tasks> listTasks(){
        String sql = "select * from " + TABLE_TASK;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Tasks> storeTasks = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString(0));
                String task = cursor.getString(1);
                boolean done = Boolean.parseBoolean(cursor.getString(2));
                storeTasks.add(new Tasks(id, task, done));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeTasks;
    }

    public void addTasks(Tasks tasks){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, tasks.getTask());
        values.put(COLUMN_NO, tasks.isDoneOrNot());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_TASK, null, values);
    }

    public void deleteTask(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TASK, COLUMN_ID	+ "	= ?", new String[] { String.valueOf(id)});
    }

}
