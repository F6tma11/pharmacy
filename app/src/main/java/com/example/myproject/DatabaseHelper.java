package com.example.myproject;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.util.Log;

        import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    String id;
    String customerid;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "pharmacy1";
    public class var{
        public static final String TABLE_USERS = "users";
        public static final String KEY_ID = "id";
        public static final String KEY_USERNAME = "username";
        public static final String KEY_PHONE = "phone";
        public static final String KEY_PASSWORD = "password";
    }
    public class var1 {

        public static final String table_name="product1";
        public static final String colID="Id";
        public static final String colName= "medcineName";
        public static final String colPrice= "medicnePrice";
        public static final String colImg = "medcineImage";
        public static final String colCtagory = "category";
    }
    public class var2 {
        public static final String order_table="orders";
        public static final String col_ID="Id";
        public static final String col_Name= "medcineName";
        public static final String col_Price= "medicnePrice";
        public static final String col_Img = "medcineImage";
        public static final String col_Phone = "phone";
        public static final String col_count= "count";

    }


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + var.TABLE_USERS + " ("
                + var.KEY_ID + " INTEGER PRIMARY KEY,"
                + var.KEY_USERNAME + " TEXT,"
                + var.KEY_PHONE + " TEXT,"
                + var.KEY_PASSWORD + " TEXT" + ")";
        db.execSQL(CREATE_USERS_TABLE);

        String createTable ="create table " + var1.table_name
                +" ("+ var1.colID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + var1.colName+" text not null ,"
                + var1.colPrice+" text not null , "
                + var1.colImg +" blob not null,"
                + var1.colCtagory + " text not null)";
        db.execSQL(createTable);
        String CREATE_ORDER_TABLE = "CREATE TABLE " + var2.order_table + " ("
                + var2.col_ID + " INTEGER PRIMARY KEY,"
                + var2.col_Name + " TEXT,"
                + var2.col_Price + " TEXT,"
                + var2.col_Img + " BLOB,"
                + var2.col_Phone + " TEXT,"
                + var2.col_count +" TEXT" +")";
        db.execSQL(CREATE_ORDER_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + var.TABLE_USERS);
        onCreate(db);

    }

    public void addUser(String username,String phone ,String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(var.KEY_USERNAME, username);
        values.put(var.KEY_PHONE, phone);
        values.put(var.KEY_PASSWORD, password);
        db.insert(var.TABLE_USERS, null, values);
        db.close();
    }

    public boolean checkUser(String usernumber, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = var.KEY_PHONE + " = ? AND " + var.KEY_PASSWORD + " = ?";
        String[] selectionArgs = {usernumber, password};
        Cursor cursor = db.query(
                var.TABLE_USERS,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count > 0;
    }

    public long insert_product(card c){
        ContentValues cv =new ContentValues();
        cv.put(var1.colName,c.getName());
        cv.put(var1.colPrice,c.getPrice());
        cv.put(var1.colImg,c.getImg());
        cv.put(var1.colCtagory,c.getCategory());
        SQLiteDatabase conection= this.getWritableDatabase();
        return  conection.insert(var1.table_name , null , cv);

    }

    public boolean Update_product(card c,String id){

        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues contentvalue=new ContentValues();
        contentvalue.put(var1.colName,c.getName());
        contentvalue.put(var1.colPrice,c.getPrice());
        contentvalue.put(var1.colImg,c.getImg());
        contentvalue.put(var1.colCtagory,c.getCategory());
        database.update(var1.table_name,contentvalue,var1.colID + " =? ",new String[]{id});
        return true;
    }

    public ArrayList<card> getData(){
        SQLiteDatabase conection=this.getReadableDatabase();
        Cursor values=conection.query(var1.table_name, null,
                null,null,null,null,null,null);

        ArrayList<card> card=new ArrayList<>();
        while (values.moveToNext()){
             id=values.getString(values.getColumnIndexOrThrow(var1.colID));
            String named=values.getString(values.getColumnIndexOrThrow(var1.colName));
            String priced=values.getString(values.getColumnIndexOrThrow(var1.colPrice));
            byte[] imged=values.getBlob(values.getColumnIndexOrThrow(var1.colImg));
            String category=values.getString(values.getColumnIndexOrThrow(var1.colCtagory));

            card.add(new card(named , priced , imged, category,id));

        }
        return card;
    }

    public ArrayList<card> getDataSpecialData(String catego){
        SQLiteDatabase conection=this.getReadableDatabase();
        String selectQuery="SELECT * FROM "+ var1.table_name +" where "+ var1.colCtagory + " LIKE '%" + catego +"%'";
        Cursor values=conection.rawQuery(selectQuery, null);

        ArrayList<card> card=new ArrayList<>();
        while (values.moveToNext()){
            id=values.getString(values.getColumnIndexOrThrow(var1.colID));
            String named=values.getString(values.getColumnIndexOrThrow(var1.colName));
            String priced=values.getString(values.getColumnIndexOrThrow(var1.colPrice));
            byte[] imged=values.getBlob(values.getColumnIndexOrThrow(var1.colImg));
            String category=values.getString(values.getColumnIndexOrThrow(var1.colCtagory));

            card.add(new card(named , priced , imged, category,id));

        }
        return card;
    }
    public void deleteEntry(String  position) {
        Log.d("myTag", "This is my message");
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(var1.table_name, var1.colID + " = " + position,null);

    }
    public long add_order(cart cart ){
        ContentValues cv =new ContentValues();
        cv.put(var2.col_Name,cart.getName());
        cv.put(var2.col_Price,cart.getPrice());
        cv.put(var2.col_Img,cart.getImg());
        cv.put(var2.col_Phone,cart.getPhone());
        cv.put(var2.col_count,cart.getCount());

        SQLiteDatabase conection= this.getWritableDatabase();
        return  conection.insert(var2.order_table , null , cv);

    }

    public ArrayList<cart> Select_cart(String phone){
        SQLiteDatabase conection=this.getReadableDatabase();
        String selectQuery1="SELECT * FROM "+ var2.order_table +" where "+ var2.col_Phone + " LIKE '%" + phone +"%'";
        Cursor values=conection.rawQuery(selectQuery1,null);

        ArrayList<cart> cart=new ArrayList<>();
        while (values.moveToNext()){
            id=values.getString(values.getColumnIndexOrThrow(var2.col_ID));
            String named=values.getString(values.getColumnIndexOrThrow(var2.col_Name));
            String priced=values.getString(values.getColumnIndexOrThrow(var2.col_Price));
            byte[] imged=values.getBlob(values.getColumnIndexOrThrow(var2.col_Img));
            String phoned=values.getString(values.getColumnIndexOrThrow(var2.col_Phone));
            String counted=values.getString(values.getColumnIndexOrThrow(var2.col_count));

            cart.add(new cart(named , priced ,id, imged, phoned,counted));

        }
        return cart;
    }

}


