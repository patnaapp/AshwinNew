package bih.nic.in.ashwin.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import bih.nic.in.ashwin.entity.ActivityCategory_entity;
import bih.nic.in.ashwin.entity.Activity_entity;
import bih.nic.in.ashwin.entity.Block_List;
import bih.nic.in.ashwin.entity.District_list;
import bih.nic.in.ashwin.entity.Financial_Month;
import bih.nic.in.ashwin.entity.Financial_Year;
import bih.nic.in.ashwin.entity.Panchayat_List;
import bih.nic.in.ashwin.entity.UserDetails;
import bih.nic.in.ashwin.entity.UserRole;


public class DataBaseHelper extends SQLiteOpenHelper {
    //private static String DB_PATH = "";
    private static String DB_PATH = "/data/data/bih.nic.in.ashwin/databases/";
    //private static String DB_NAME = "eCountingAC.sqlite";
    private static String DB_NAME = "PACSDB1";

    private SQLiteDatabase myDataBase;
    private final Context myContext;

    SQLiteDatabase db;

    public DataBaseHelper(Context context) {

        super(context, DB_NAME, null, 2);
        if (android.os.Build.VERSION.SDK_INT >= 4.2) {


            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        } else {
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }
        this.myContext = context;
    }



    /**
     * Creates a empty database on the system and rewrites it with your own
     * database.
     */
    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if (dbExist) {
            // do nothing - database already exist


        } else {

            // By calling this method and empty database will be created into
            // the default system path
            // of your application so we are gonna be able to overwrite that
            // database with our database.
            this.getReadableDatabase();

            try {

                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }

    }

    /**
     * Check if the database already exist to avoid re-copying the file each
     * time you open the application.
     *
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;

        try {
            String myPath = DB_PATH + DB_NAME;
            //this.getReadableDatabase();

            checkDB = SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.OPEN_READWRITE);


        } catch (SQLiteException e) {

            // database does't exist yet.

        }

        if (checkDB != null) {

            checkDB.close();

        }

        return checkDB != null ? true : false;

    }

    public boolean databaseExist() {


        File dbFile = new File(DB_PATH + DB_NAME);

        return dbFile.exists();
    }

    /**
     * Copies your database from your local assets-folder to the just created
     * empty database in the system folder, from where it can be accessed and
     * handled. This is done by transfering bytestream.
     */
    private void copyDataBase() throws IOException {

        // Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        // Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);
        this.getReadableDatabase().close();
        // transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        // Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();


    }

    public void openDataBase() throws SQLException {

        // Open the database
        this.getReadableDatabase();
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null,
                SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {

        if (myDataBase != null)
            myDataBase.close();

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("ALTER TABLE Ward ADD COLUMN AreaType TEXT");
//        db.execSQL("ALTER TABLE OtherDept_Of_Rural_Dev_Dept ADD COLUMN VillageID TEXT");
//        db.execSQL("ALTER TABLE Menrega_Rural_Dev_Dept ADD COLUMN Work_Structure_Type_Name TEXT");
//        db.execSQL("ALTER TABLE Menrega_Rural_Dev_Dept ADD COLUMN Work_Structure_Type_Other_Name TEXT");
//        db.execSQL("ALTER TABLE OtherDept_Of_Rural_Dev_Dept ADD COLUMN Work_Structure_Type_Name TEXT");
//        db.execSQL("ALTER TABLE OtherDept_Of_Rural_Dev_Dept ADD COLUMN Work_Structure_Type_Other_Name TEXT");

        //modifyTable();
    }

    public void modifyTable(){

        if(isColumnExists("Ward", "AreaType") == false){
            AlterTable("Ward", "AreaType");
        }

//        if(!isColumnExists("OtherDept_Of_Rural_Dev_Dept", "VillageID")){
//            AlterTable("OtherDept_Of_Rural_Dev_Dept", "VillageID");
//        }
//
//
//        if(!isColumnExists("OtherDept_Of_Rural_Dev_Dept", "Work_Structure_Type_Name")){
//            AlterTable("OtherDept_Of_Rural_Dev_Dept", "Work_Structure_Type_Name");
//        }
//
//        if(!isColumnExists("OtherDept_Of_Rural_Dev_Dept", "Work_Structure_Type_Other_Name")){
//            AlterTable("OtherDept_Of_Rural_Dev_Dept", "Work_Structure_Type_Other_Name");
//        }

//        AlterManregTable("Menrega_Rural_Dev_Dept");
//        AlterManregTable("OtherDept_Of_Rural_Dev_Dept");
    }


    public void AlterTable(String tableName, String columnName)
    {
        db = this.getReadableDatabase();

        try{

            db.execSQL("ALTER TABLE "+tableName+" ADD COLUMN "+columnName+" TEXT");
            Log.e("ALTER Done",tableName +"-"+ columnName);
        }
        catch (Exception e)
        {
            Log.e("ALTER Failed",tableName +"-"+ columnName);
        }
    }

    public boolean isColumnExists (String table, String column) {
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("PRAGMA table_info("+ table +")", null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                if (column.equalsIgnoreCase(name)) {
                    return true;
                }
            }
        }
        cursor.close();
        return false;
    }
    public long getUserCount() {

        long x = 0;
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cur = db.rawQuery("Select * from UserLogin", null);

            x = cur.getCount();

            cur.close();
            db.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return x;
    }

    public long getAssetCount(String assetid) {

        long x = 0;
        try {
            String[] whereArgs = new String[]{assetid};

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cur = db.rawQuery("Select * from AssetNewEntry where asset_id=?", whereArgs);

            x = cur.getCount();

            cur.close();
            db.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return x;
    }


    public long insertUserDetails(UserDetails result) {

        long c = 0;
        try {

            SQLiteDatabase db = this.getReadableDatabase();

            ContentValues values = new ContentValues();

            values.put("UserID", result.getUserID());
            values.put("Password", result.getUserName());
            values.put("isAuthenticated", result.getPassword());
            values.put("UserName", result.getHSCName());
            values.put("UserRole", result.getUserrole());

            values.put("DistCode", result.getAwcCode());
            values.put("DistName", result.getAwcName());
            values.put("DistNameHN", result.getDistrictCode());
            values.put("BlockCode", result.getDistName());
            values.put("BlockName", result.getAwcCode());
            values.put("BlockNameHN", result.getAwcName());
            values.put("PanchayatCode", result.getDistrictCode());
            values.put("PanchayatName", result.getDistName());
            values.put("PanchayatNameHN", result.getAwcCode());
            values.put("AwcName", result.getAwcName());
            values.put("AwcCode", result.getDistrictCode());
            values.put("HSCName", result.getDistName());
            values.put("HSCCode", result.getDistName());

            String[] whereArgs = new String[]{result.getUserID()};

            c = db.update("UserDetails", values, "UserID=? ", whereArgs);

            if (!(c > 0)) {

                //c = db.insert("UserDetail", null, values);
                c = db.insertWithOnConflict("UserDetails", null, values, SQLiteDatabase.CONFLICT_REPLACE);
            }

            db.close();
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        return c;

    }


    public UserDetails getUserDetails(String userId, String pass, String role) {

        UserDetails userInfo = null;

        try {

            SQLiteDatabase db = this.getReadableDatabase();

            String[] params = new String[]{userId.trim(), pass,role};

            Cursor cur = db.rawQuery(
                    "Select * from UserDetails WHERE UserID=? and UserPassword=? and UserRole=?",
                    params);
            int x = cur.getCount();

            while (cur.moveToNext()) {


                userInfo = new UserDetails();
                userInfo.setUserID(cur.getString(cur.getColumnIndex("UserID")));
                userInfo.setPassword(cur.getString(cur.getColumnIndex("Password")));
                userInfo.setIsAuthenticated(Boolean.parseBoolean(cur.getString(cur.getColumnIndex("isAuthenticated"))));
                userInfo.setUserName(cur.getString(cur.getColumnIndex("UserName")));
                userInfo.setUserrole(cur.getString(cur.getColumnIndex("UserRole")));

                userInfo.setDistrictCode(cur.getString(cur.getColumnIndex("DistCode")));
                userInfo.setDistName(cur.getString(cur.getColumnIndex("DistName")));
                userInfo.setDistNameHN(cur.getString(cur.getColumnIndex("DistNameHN")));
                userInfo.setBlockCode(cur.getString(cur.getColumnIndex("BlockCode")));
                userInfo.setBlockName(cur.getString(cur.getColumnIndex("BlockName")));
                userInfo.setBlockNameHN(cur.getString(cur.getColumnIndex("BlockNameHN")));

                userInfo.setPanchayatCode(cur.getString(cur.getColumnIndex("PanchayatCode")));
                userInfo.setPanchayatName(cur.getString(cur.getColumnIndex("PanchayatName")));
                userInfo.setPanchayatNameHN(cur.getString(cur.getColumnIndex("PanchayatNameHN")));
                userInfo.setAwcName(cur.getString(cur.getColumnIndex("AwcName")));
                userInfo.setAwcCode(cur.getString(cur.getColumnIndex("AwcCode")));
                userInfo.setHSCName(cur.getString(cur.getColumnIndex("HSCName")));
                userInfo.setHSCCode(cur.getString(cur.getColumnIndex("HSCCode")));
            }

            cur.close();
            db.close();

        } catch (Exception e) {
            // TODO: handle exception
            userInfo = null;
        }
        return userInfo;
    }


    public ArrayList<UserRole> getUserRoleList(){

        ArrayList<UserRole> userRoleList = new ArrayList<UserRole>();

        try {

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cur = db.rawQuery("Select * from UserRole",null);
            int x = cur.getCount();

            while (cur.moveToNext()) {


                UserRole userRole = new UserRole();

                userRole.setRole(cur.getString(cur.getColumnIndex("UserRole")));
                userRole.setRoleDesc(cur.getString(cur.getColumnIndex("RoleDesc")));
                userRole.setRoleDescHN(cur.getString(cur.getColumnIndex("RoleDescHN")));

                userRoleList.add(userRole);
            }

            cur.close();
            db.close();
            this.getReadableDatabase().close();
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
            //info = null;
        }
        return userRoleList;
    }


    public long setFinyr_Local(ArrayList<Financial_Year> list) {


        long c = -1;

        DataBaseHelper dh = new DataBaseHelper(myContext);
        try {
            dh.createDataBase();


        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return -1;
        }

        ArrayList<Financial_Year> info = list;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //db.delete("Panchayat",null,null);
        if (info != null) {
            try {
                for (int i = 0; i < info.size(); i++) {

                    values.put("fyear_id", info.get(i).getYear_Id());
                    values.put("fyear_name", info.get(i).getFinancial_year());
                    values.put("status", info.get(i).getStatus());

                    String[] whereArgs = new String[]{info.get(i).getYear_Id()};

                    c = db.update("FinancialYear", values, "fyear_id=?", whereArgs);
                    if (!(c > 0)) {

                        c = db.insert("FinancialYear", null, values);
                    }


                }
                db.close();


            } catch (Exception e) {
                e.printStackTrace();
                return c;
            }
        }
        return c;


    }

    public long setFinMonth_Local(ArrayList<Financial_Month> list) {


        long c = -1;

        DataBaseHelper dh = new DataBaseHelper(myContext);
        try {
            dh.createDataBase();


        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return -1;
        }

        ArrayList<Financial_Month> info = list;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //db.delete("Panchayat",null,null);
        if (info != null) {
            try {
                for (int i = 0; i < info.size(); i++) {

                    values.put("Month_id", info.get(i).get_MonthId());
                    values.put("Month_name", info.get(i).get_MonthName());
                    values.put("order_status", info.get(i).get_OrderStatus());

                    String[] whereArgs = new String[]{info.get(i).get_MonthId()};

                    c = db.update("Financial_Month", values, "Month_id=?", whereArgs);
                    if (!(c > 0)) {

                        c = db.insert("Financial_Month", null, values);
                    }


                }
                db.close();


            } catch (Exception e) {
                e.printStackTrace();
                return c;
            }
        }
        return c;


    }

    public long setActivityList_Local(ArrayList<Activity_entity> list) {


        long c = -1;

        DataBaseHelper dh = new DataBaseHelper(myContext);
        try {
            dh.createDataBase();


        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return -1;
        }

        ArrayList<Activity_entity> info = list;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //db.delete("Panchayat",null,null);
        if (info != null) {
            try {
                for (int i = 0; i < info.size(); i++) {

                    values.put("Activity_Id", info.get(i).get_ActivityId());
                    values.put("Activity_Desc", info.get(i).get_ActivityDesc());
                    values.put("Activity_Amt", info.get(i).get_ActivityAmt());
                    values.put("Activity_categ_Id", info.get(i).get_AcitivtyCategoryId());
                    values.put("Order_Status", info.get(i).get_OrderStatus());
                    values.put("Register_Id", info.get(i).get_RegisterId());

                    String[] whereArgs = new String[]{info.get(i).get_ActivityId()};

                    c = db.update("ActivityList_Master", values, "Activity_Id=?", whereArgs);
                    if (!(c > 0)) {

                        c = db.insert("ActivityList_Master", null, values);
                    }


                }
                db.close();


            } catch (Exception e) {
                e.printStackTrace();
                return c;
            }
        }
        return c;


    }

    public long setActivityCategoryList_Local(ArrayList<ActivityCategory_entity> list) {


        long c = -1;

        DataBaseHelper dh = new DataBaseHelper(myContext);
        try {
            dh.createDataBase();


        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return -1;
        }

        ArrayList<ActivityCategory_entity> info = list;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //db.delete("Panchayat",null,null);
        if (info != null) {
            try {
                for (int i = 0; i < info.size(); i++) {

                    values.put("category_id", info.get(i).get_AcitivtyCategoryId());
                    values.put("category_name", info.get(i).get_AcitivtyCategoryDesc());
                    values.put("category_name_hn", info.get(i).get_AcitivtyCategoryDesc_Hn());


                    String[] whereArgs = new String[]{info.get(i).get_AcitivtyCategoryId()};

                    c = db.update("ActivtiyCategory_Master", values, "category_id=?", whereArgs);
                    if (!(c > 0)) {

                        c = db.insert("ActivtiyCategory_Master", null, values);
                    }


                }
                db.close();


            } catch (Exception e) {
                e.printStackTrace();
                return c;
            }
        }
        return c;


    }

    public long setDistrictList_Local(ArrayList<District_list> list) {


        long c = -1;

        DataBaseHelper dh = new DataBaseHelper(myContext);
        try {
            dh.createDataBase();


        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return -1;
        }

        ArrayList<District_list> info = list;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //db.delete("Panchayat",null,null);
        if (info != null) {
            try {
                for (int i = 0; i < info.size(); i++) {

                    values.put("DistCode", info.get(i).getDistt_code());
                    values.put("DistName", info.get(i).getDistt_name());
                    values.put("DistNameHN", info.get(i).getDist_NAME_HN());


                    String[] whereArgs = new String[]{info.get(i).getDistt_code()};

                    c = db.update("Districts", values, "DistCode=?", whereArgs);
                    if (!(c > 0)) {

                        c = db.insert("Districts", null, values);
                    }


                }
                db.close();


            } catch (Exception e) {
                e.printStackTrace();
                return c;
            }
        }
        return c;


    }

    //Get Data

    public ArrayList<Financial_Year> getFinancialYearList(){

        ArrayList<Financial_Year> list = new ArrayList<Financial_Year>();
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cur = db.rawQuery("Select * from FinancialYear",null);

            while (cur.moveToNext()) {

                Financial_Year info = new Financial_Year();

                info.setYear_Id(cur.getString(cur.getColumnIndex("fyear_id")));
                info.setFinancial_year(cur.getString(cur.getColumnIndex("fyear_name")));
                info.setStatus(cur.getString(cur.getColumnIndex("status")));

                list.add(info);
            }

            cur.close();
            db.close();
            this.getReadableDatabase().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Financial_Month> getFinancialMonthList(){

        ArrayList<Financial_Month> list = new ArrayList<Financial_Month>();
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cur = db.rawQuery("Select * from Financial_Month",null);

            while (cur.moveToNext()) {

                Financial_Month info = new Financial_Month();

                info.set_MonthId(cur.getString(cur.getColumnIndex("Month_id")));
                info.set_MonthName(cur.getString(cur.getColumnIndex("Month_name")));
                info.set_OrderStatus(cur.getString(cur.getColumnIndex("order_status")));

                list.add(info);
            }

            cur.close();
            db.close();
            this.getReadableDatabase().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    public long setPanchayatName(ArrayList<Panchayat_List> list,String blkcode) {


        long c = -1;

        DataBaseHelper dh = new DataBaseHelper(myContext);
        try {
            dh.createDataBase();


        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return -1;
        }

        ArrayList<Panchayat_List> info = list;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //db.delete("Panchayat",null,null);
        if (info != null) {
            try {
                for (int i = 0; i < info.size(); i++) {

                    values.put("BlockCode", blkcode);
                    values.put("PanchayatCode", info.get(i).getPanchayat_code());
                    values.put("PanchayatName", info.get(i).getPanchayat_Name());
                    values.put("PanchayatNameHnd", info.get(i).get_Panchayat_NAME_HN());

                    String[] whereArgs = new String[]{info.get(i).getPanchayat_code()};

                    c = db.update("Panchayat", values, "PanchayatCode=?", whereArgs);
                    if (!(c > 0)) {

                        c = db.insert("Panchayat", null, values);
                    }


                }
                db.close();


            } catch (Exception e) {
                e.printStackTrace();
                return c;
            }
        }
        return c;


    }

    public long setBlockLocal(ArrayList<Block_List> list,String distcode) {


        long c = -1;

        DataBaseHelper dh = new DataBaseHelper(myContext);
        try {
            dh.createDataBase();


        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return -1;
        }

        ArrayList<Block_List> info = list;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //db.delete("Panchayat",null,null);
        if (info != null) {
            try {
                for (int i = 0; i < info.size(); i++) {

                    values.put("DistCode", distcode);
                    values.put("BlockCode", info.get(i).getBlk_Code());
                    values.put("BlockName", info.get(i).getBlk_Name());
                    values.put("BlockNameHN", info.get(i).getBlock_NAME_HN());

                    String[] whereArgs = new String[]{info.get(i).getBlk_Code()};

                    c = db.update("Block_Master", values, "BlockCode=?", whereArgs);
                    if (!(c > 0)) {

                        c = db.insert("Block_Master", null, values);
                    }


                }
                db.close();


            } catch (Exception e) {
                e.printStackTrace();
                return c;
            }
        }
        return c;


    }

}