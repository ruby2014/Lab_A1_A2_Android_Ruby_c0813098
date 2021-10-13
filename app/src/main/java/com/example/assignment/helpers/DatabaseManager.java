package com.example.assignment.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.assignment.models.Product;
import com.example.assignment.models.Provider;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper  {

    private static final String DB_NAME = "assignment_db";
    private static final int DB_VERSION = 1;


    private static final String PROVIDER_TABLE_NAME = "providers";
    private static final String PROVIDER_PKAIID_COL = "pkaiid";
    private static final String PROVIDER_ID_COL = "ID";
    private static final String PROVIDER_NAME_COL = "name";
    private static final String PROVIDER_EMAIL_COL = "email";
    private static final String PROVIDER_PHONE_COL = "phone";
    private static final String PROVIDER_LAT_COL = "lat";
    private static final String PROVIDER_LNG_COL = "lng";

    private static final String PRODUCT_TABLE_NAME = "products";
    private static final String PRODUCT_PKAIID_COL = "pkaiid";
    private static final String PRODUCT_ID_COL = "ID";
    private static final String PRODUCT_NAME_COL = "name";
    private static final String PRODUCT_DESCRIPTION_COL = "description";
    private static final String PRODUCT_PRICE_COL = "price";
    private static final String PRODUCT_PROVIDER_ID_COL = "provider";


    public DatabaseManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + PROVIDER_TABLE_NAME + " ("
                + PROVIDER_PKAIID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PROVIDER_ID_COL + " TEXT,"
                + PROVIDER_NAME_COL + " TEXT,"
                + PROVIDER_EMAIL_COL + " TEXT,"
                + PROVIDER_PHONE_COL + " TEXT,"
                + PROVIDER_LAT_COL + " REAL,"
                + PROVIDER_LNG_COL + " REAL)";
        db.execSQL(query);

        query = "INSERT INTO "+ PROVIDER_TABLE_NAME +" ("+
                PROVIDER_ID_COL +", "+
                PROVIDER_NAME_COL +", "+
                PROVIDER_EMAIL_COL +", "+
                PROVIDER_PHONE_COL +", "+
                PROVIDER_LAT_COL +", "+
                PROVIDER_LNG_COL +") "+
                "VALUES ('PROV1', 'Smith&Smith', 'info@smith.com', '+1 (101) 101', -33.865143, 151.2093)";
        db.execSQL(query);

        query = "INSERT INTO "+ PROVIDER_TABLE_NAME +" ("+
                PROVIDER_ID_COL +", "+
                PROVIDER_NAME_COL +", "+
                PROVIDER_EMAIL_COL +", "+
                PROVIDER_PHONE_COL +", "+
                PROVIDER_LAT_COL +", "+
                PROVIDER_LNG_COL +") "+
                "VALUES ('PROV2', 'Deaken', 'info@deaken.com', '+1 (101) 202', -31.953512, 115.8613)";
        db.execSQL(query);

        query = "INSERT INTO "+ PROVIDER_TABLE_NAME +" ("+
                PROVIDER_ID_COL +", "+
                PROVIDER_NAME_COL +", "+
                PROVIDER_EMAIL_COL +", "+
                PROVIDER_PHONE_COL +", "+
                PROVIDER_LAT_COL +", "+
                PROVIDER_LNG_COL +") "+
                "VALUES ('PROV3', 'Wells', 'info@wells.com', '+1 (101) 303', 40.730610, 74.0060)";
        db.execSQL(query);

        query = "CREATE TABLE " + PRODUCT_TABLE_NAME + " ("
                + PRODUCT_PKAIID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PRODUCT_ID_COL + " TEXT,"
                + PRODUCT_NAME_COL + " TEXT,"
                + PRODUCT_DESCRIPTION_COL + " TEXT,"
                + PRODUCT_PRICE_COL + " TEXT,"
                + PRODUCT_PROVIDER_ID_COL + " TEXT)";
        db.execSQL(query);

        query = "INSERT INTO "+ PRODUCT_TABLE_NAME +" ("+
                PRODUCT_ID_COL +", "+
                PRODUCT_NAME_COL +", "+
                PRODUCT_DESCRIPTION_COL +", "+
                PRODUCT_PRICE_COL +", "+
                PRODUCT_PROVIDER_ID_COL +") "+
                "VALUES ('PROD1', 'Light Bulb', 'Just another light bulb.', '$50', 'PROV1')";
        db.execSQL(query);

        query = "INSERT INTO "+ PRODUCT_TABLE_NAME +" ("+
                PRODUCT_ID_COL +", "+
                PRODUCT_NAME_COL +", "+
                PRODUCT_DESCRIPTION_COL +", "+
                PRODUCT_PRICE_COL +", "+
                PRODUCT_PROVIDER_ID_COL +") "+
                "VALUES ('PROD2', 'Yellow Bulb', 'Just another light bulb, but yellow.', '$75', 'PROV2')";
        db.execSQL(query);

        query = "INSERT INTO "+ PRODUCT_TABLE_NAME +" ("+
                PRODUCT_ID_COL +", "+
                PRODUCT_NAME_COL +", "+
                PRODUCT_DESCRIPTION_COL +", "+
                PRODUCT_PRICE_COL +", "+
                PRODUCT_PROVIDER_ID_COL +") "+
                "VALUES ('PROD3', 'Red Bulb', 'Just another light bulb, but red.', '$75', 'PROV3')";
        db.execSQL(query);

        query = "INSERT INTO "+ PRODUCT_TABLE_NAME +" ("+
                PRODUCT_ID_COL +", "+
                PRODUCT_NAME_COL +", "+
                PRODUCT_DESCRIPTION_COL +", "+
                PRODUCT_PRICE_COL +", "+
                PRODUCT_PROVIDER_ID_COL +") "+
                "VALUES ('PROD4', 'Blue Bulb', 'Just another light bulb, but blue.', '$75', 'PROV3')";
        db.execSQL(query);

    }

    public String getProviderNameById(String Id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT " + PROVIDER_NAME_COL + " FROM " + PROVIDER_TABLE_NAME + " WHERE " + PROVIDER_ID_COL + " = ?", new String[] {Id});
        String column1 = null;
        if (c.moveToFirst()){
            do {
                column1 = c.getString(0);
            } while(c.moveToNext());
        }
        c.close();
        db.close();
        return column1;
    }

    public ArrayList<Provider> getAllProviders() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " +
                PROVIDER_ID_COL + ", " +
                PROVIDER_NAME_COL + ", " +
                PROVIDER_EMAIL_COL + ", " +
                PROVIDER_PHONE_COL + ", " +
                PROVIDER_LAT_COL + ", " +
                PROVIDER_LNG_COL + " FROM " + PROVIDER_TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<Provider> providers = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                providers.add(new Provider(cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)));
            } while (cursor.moveToNext());
        }
        db.close();
        return providers;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PROVIDER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PRODUCT_TABLE_NAME);
        onCreate(db);
    }

    public ArrayList<Product> getAllProducts() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " +
                PRODUCT_ID_COL + ", " +
                PRODUCT_NAME_COL + ", " +
                PRODUCT_DESCRIPTION_COL + ", " +
                PRODUCT_PRICE_COL + ", " +
                PRODUCT_PROVIDER_ID_COL + " FROM " + PRODUCT_TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<Product> products = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                products.add(new Product(cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        getProviderNameById(cursor.getString(4))));
            } while (cursor.moveToNext());
        }
        db.close();
        return products;
    }

    public void addNewProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "INSERT INTO "+ PRODUCT_TABLE_NAME +" ("+
                PRODUCT_ID_COL +", "+
                PRODUCT_NAME_COL +", "+
                PRODUCT_DESCRIPTION_COL +", "+
                PRODUCT_PRICE_COL +", "+
                PRODUCT_PROVIDER_ID_COL +") "+
                "VALUES ('" +
                product.getId() + "', '" +
                product.getName() + "', '" +
                product.getDescription() + "', '" +
                product.getPrice() + "', '" +
                product.getProvider() +"')";
        db.execSQL(query);
    }

    public void removeProduct(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String table = PRODUCT_TABLE_NAME;
        String whereClause = PRODUCT_ID_COL + "=?";
        String[] whereArgs = new String[] { String.valueOf(id) };
        db.delete(table, whereClause, whereArgs);
    }

    public void removeProvider(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String table = PROVIDER_TABLE_NAME;
        String whereClause = PROVIDER_ID_COL + "=?";
        String[] whereArgs = new String[] { String.valueOf(id) };
        db.delete(table, whereClause, whereArgs);
        table = PRODUCT_TABLE_NAME;
        whereClause = PRODUCT_PROVIDER_ID_COL + "=?";
        whereArgs = new String[] { String.valueOf(id) };
        db.delete(table, whereClause, whereArgs);
    }

    public void updateProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PRODUCT_NAME_COL, product.getName());
        cv.put(PRODUCT_DESCRIPTION_COL, product.getDescription());
        cv.put(PRODUCT_PRICE_COL, product.getPrice());
        cv.put(PRODUCT_PROVIDER_ID_COL, product.getProvider());
        db.update(PRODUCT_TABLE_NAME, cv, PRODUCT_ID_COL + " = ?", new String[]{product.getId()});
    }

    public String getProductCount(String providerId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT COUNT(*) FROM " + PRODUCT_TABLE_NAME + " WHERE " + PRODUCT_PROVIDER_ID_COL + " = ?", new String[] {providerId});
        String column1 = null;
        if (c.moveToFirst()){
            do {
                column1 = c.getString(0);
            } while(c.moveToNext());
        }
        c.close();
        db.close();
        return column1;
    }
}