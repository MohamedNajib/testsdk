package com.nibalaws.ebrahim.law.DataBaseManger;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.widget.Toast;

import com.nibalaws.ebrahim.law.R;
import com.nibalaws.ebrahim.law.rest.DialogSearchDataModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String DBNAME = "niba.db";
    //public static final String DBLOCATION = "/data/data/com.yaser.sqlight_local_db/databases/";
    public static final String DBLOCATION = Environment.getDataDirectory() + "/data/com.nibalaws.ebrahim.law/databases/";
    private Context mContext;
    private SQLiteDatabase mDatabase;
    private static String DB_PATH = "";
    private boolean mNeedUpdate = false;
    private static final int DB_VERSION = 1;


    public DatabaseHelper(Context context) {
        super(context, DBNAME, null, DB_VERSION);
        if (android.os.Build.VERSION.SDK_INT >= 17)
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        else
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        this.mContext = context;

        copyDataBase();

        this.getReadableDatabase();
    }


    public void updateDataBase() throws IOException {
        if (mNeedUpdate) {
            File dbFile = new File(DB_PATH + DBNAME);
            if (dbFile.exists())
                dbFile.delete();

            copyDataBase();

            mNeedUpdate = false;
        }
    }


    private boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DBNAME);
        return dbFile.exists();
    }

    private void copyDataBase() {
        if (!checkDataBase()) {
            this.getReadableDatabase();
            this.close();
            try {
                copyDBFile();
            } catch (IOException mIOException) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    private void copyDBFile() throws IOException {
        InputStream mInput = mContext.getAssets().open(DBNAME);
        //InputStream mInput = mContext.getResources().openRawResource(R.raw.info);
        OutputStream mOutput = new FileOutputStream(DB_PATH + DBNAME);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0)
            mOutput.write(mBuffer, 0, mLength);
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    public boolean openDataBase() throws SQLException {
        mDatabase = SQLiteDatabase.openDatabase(DB_PATH + DBNAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        return mDatabase != null;
    }

    @Override
    public synchronized void close() {
        if (mDatabase != null)
            mDatabase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
            mNeedUpdate = true;
    }


    public void openDatabase() {
        String dbPath = mContext.getDatabasePath(DBNAME).getPath();
        if (mDatabase != null && mDatabase.isOpen()) {
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase() {
        if (mDatabase != null) {
            mDatabase.close();
        }
    }


    public String arbicencodic(String Word) {
        String str = Word;
        String[] startcut = {"الا", "الأ", "الأ", "الأ", "ال", "ا", "أ", "إ", "آ", "والا", "والأ", "والأ", "والإ", "وال", "وا", "وأ", "وإ", "وآ"};
        String[] Endcut = {"ه", "ة", "ء", "ي", "ا", "أ", "إ"};
        for (String x : startcut) {
            if (str.startsWith(x)) {
                str = str.substring(x.length());
            }
        }
        for (String x : Endcut) {
            if (str.endsWith(x)) {
                str = str.substring(0, str.length() - 1);
            }
        }
        return str;
    }


    public String Converten(String Num) {
        Locale locale = new Locale("ar");
        String[] strings = Num.split(",\\s*");
        NumberFormat fmt = null;
        fmt = NumberFormat.getInstance(locale);

        try {
            StringBuilder sb = new StringBuilder();
            for (String s : strings) {
                Number n = fmt.parse(s);
                if (sb.length() != 0) {
                    sb.append(",");
                }
                sb.append(n);
            }

            return sb.toString();
        } catch (ParseException ex) {
        }
        return "";
    }


    public boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public String arbicencodicSub(String str) {


        String txtxxx = "";
        String[] strArr = str.split("\\s+");


        for (String item : strArr) {
            if (item.length() > 2) {

                txtxxx = txtxxx + (arbicencodic(item)) + " ";
            }
        }
        return txtxxx;

    }


    public ArrayList pdf_dawry() {
        ArrayList<Master_Stract> arrayList = new ArrayList<>();
        openDatabase();

        String sqlCheack = "";

        if (Var.Active) {
            sqlCheack = "select * from pdf_dawry ";
        } else {
            sqlCheack = "select * from pdf_dawry limit 5";

        }

        Cursor res = mDatabase.rawQuery(sqlCheack, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            String item1 = res.getString(res.getColumnIndex("name"));
            String item2 = res.getString(res.getColumnIndex("path"));
            arrayList.add(new Master_Stract(item1, item2, "", "", "", "", "", item1));
            res.moveToNext();
        }
        try {

            res.close();
            closeDatabase();

        } catch (Exception e) {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();

            res.close();
            closeDatabase();

        } finally {

            res.close();
            closeDatabase();
        }
        return arrayList;

    }

    //   Ios FUNC getQiods

    public ArrayList getQiods(String T_id) {
        ArrayList<Master_Stract> arrayList = new ArrayList<>();
        openDatabase();

        String sqlCheack = "";

        if (Var.Active) {
            sqlCheack = "SELECT     Niba_books.ID, Niba_books_Details.title, Niba_books_Details.details, Niba_books.Name\n" +
                    "FROM         Niba_books INNER JOIN\n" +
                    "                      Niba_books_Details ON Niba_books.ID = Niba_books_Details.Type_id where Type_id =\" + T_id + \" ";
        } else {
            sqlCheack = "SELECT     Niba_books.ID, Niba_books_Details.title, Niba_books_Details.details, Niba_books.Name\n" +
                    "FROM         Niba_books INNER JOIN\n" +
                    "                      Niba_books_Details ON Niba_books.ID = Niba_books_Details.Type_id where Type_id =" + T_id + " limit 5";

        }


        Cursor res = mDatabase.rawQuery(sqlCheack, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            String item1 = res.getString(res.getColumnIndex("title"));
            String item2 = res.getString(res.getColumnIndex("details"));
            String name = res.getString(res.getColumnIndex("Name"));
            arrayList.add(new Master_Stract(item1, item2, "", "", "", "", "", name));
            res.moveToNext();
        }
        try {

            res.close();
            closeDatabase();

        } catch (Exception e) {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();

            res.close();
            closeDatabase();

        } finally {

            res.close();
            closeDatabase();
        }
        return arrayList;

    }

    //   Ios FUNC getNaqdTopics
    public ArrayList GetahkamTopic(String Type_id) {
        ArrayList<Master_Stract> arrayList = new ArrayList<>();
        openDatabase();
        String sqlCheack = "";
        if (Var.Active) {
            sqlCheack = "select * from Ahkam_Topics where T_id like '" + Type_id + "'";
        } else {
            sqlCheack = "select * from Ahkam_Topics where T_id like '" + Type_id + "' limit 5";
        }
        Cursor res = mDatabase.rawQuery(sqlCheack, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            String Topic_name = res.getString(res.getColumnIndex("Text"));
            String hkm_type = res.getString(res.getColumnIndex("id"));
            String T_id = res.getString(res.getColumnIndex("T_id"));

            arrayList.add(new Master_Stract(Topic_name, hkm_type, "", "", "", "", "", T_id));
            res.moveToNext();
        }
        try {

            res.close();
            closeDatabase();

        } catch (Exception e) {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();

            res.close();
            closeDatabase();

        } finally {

            res.close();
            closeDatabase();
        }
        return arrayList;

    }

    //   Ios FUNC getNaqdYears
    public ArrayList getahkamyear(String Type_id) {
        ArrayList<Master_Stract> arrayList = new ArrayList<>();
        openDatabase();

        String sqlCheack = "";
        if (Var.Active) {
            sqlCheack = "select * from Ahkam_type_years where Type_id like '" + Type_id + "'";
        } else {
            sqlCheack = "select * from Ahkam_type_years where Type_id like '" + Type_id + "' limit 5 ";
        }

        Cursor res = mDatabase.rawQuery(sqlCheack, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            String Topic_year = res.getString(res.getColumnIndex("H_Year"));
            String hkm_type = res.getString(res.getColumnIndex("Type_id"));
            arrayList.add(new Master_Stract(Topic_year, hkm_type, "", "", "", "", "", Topic_year));
            res.moveToNext();
        }
        try {

            res.close();
            closeDatabase();

        } catch (Exception e) {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();

            res.close();
            closeDatabase();

        } finally {

            res.close();
            closeDatabase();
        }
        return arrayList;

    }

    //   Ios FUNC getNaqdAhkam
    public ArrayList getahkamBytopic(String Topic_id, String T_id) {
        ArrayList<Master_Stract> arrayList = new ArrayList<>();
        openDatabase();


        String sqlCheack = "";

        if (Var.Active) {

            sqlCheack = "SELECT  ' الطعن رقم '  ||  Ahkam_master.C_No  ||  ' سنة ' ||    Ahkam_master.C_Year ||  ' بتاريخ  '  ||   Ahkam_master.date_galsa as titel   ,Ahkam_types.Name as Master_name,hkm_mbda  as details,master_id,path,Text as Topic_name\n" +
                    "FROM         Ahkam_Topics INNER JOIN\n" +
                    "                      Ahkam_Topics_Master ON Ahkam_Topics.id = Ahkam_Topics_Master.Topic_id INNER JOIN\n" +
                    "                      Ahkam_master ON Ahkam_Topics_Master.ahkam_id = Ahkam_master.ID_ INNER JOIN\n" +
                    "                      Ahkam_types ON Ahkam_master.MASTER_ID = Ahkam_types.ID\n" +
                    "WHERE     (Ahkam_Topics_Master.Topic_id = " + Topic_id + " and Ahkam_Topics.T_id = " + T_id + " )\n";
        } else {

            sqlCheack = "SELECT  ' الطعن رقم '  ||  Ahkam_master.C_No  ||  ' سنة ' ||    Ahkam_master.C_Year ||  ' بتاريخ  '  ||   Ahkam_master.date_galsa as titel   ,Ahkam_types.Name as Master_name,hkm_mbda  as details,master_id,path,Text as Topic_name\n" +
                    "FROM         Ahkam_Topics INNER JOIN\n" +
                    "                      Ahkam_Topics_Master ON Ahkam_Topics.id = Ahkam_Topics_Master.Topic_id INNER JOIN\n" +
                    "                      Ahkam_master ON Ahkam_Topics_Master.ahkam_id = Ahkam_master.ID_ INNER JOIN\n" +
                    "                      Ahkam_types ON Ahkam_master.MASTER_ID = Ahkam_types.ID\n" +
                    "WHERE     (Ahkam_Topics_Master.Topic_id = " + Topic_id + " and Ahkam_Topics.T_id = " + T_id + " )\n";

        }


        Cursor res = mDatabase.rawQuery(sqlCheack, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            String details = res.getString(res.getColumnIndex("details"));
            String titel = res.getString(res.getColumnIndex("titel"));
            String Topic_name_ = res.getString(res.getColumnIndex("Topic_name"));
            String MASTER = res.getString(res.getColumnIndex("MASTER_ID"));
            String path = res.getString(res.getColumnIndex("path"));
            String name_ = res.getString(res.getColumnIndex("Master_name"));

            arrayList.add(new Master_Stract(titel, details, Topic_name_, "", "", MASTER, path, name_));
            res.moveToNext();
        }
        try {

            res.close();
            closeDatabase();

        } catch (Exception e) {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();

            res.close();
            closeDatabase();

        } finally {

            res.close();
            closeDatabase();
        }
        return arrayList;

    }

    //   Ios FUNC getNaqdAhkam
    public ArrayList getahkamByyear(String MASTER_ID, String hkm_Master_year) {
        ArrayList<Master_Stract> arrayList = new ArrayList<>();
        openDatabase();

        String sqlCheack = "";

        if (Var.Active) {
            sqlCheack = "SELECT  ' الطعن رقم '  ||  Ahkam_master.C_No  ||  ' سنة ' ||    Ahkam_master.C_Year ||  ' بتاريخ  '  ||   Ahkam_master.date_galsa as titel   ,Ahkam_types.Name as Master_name,hkm_mbda  as details,MASTER_ID,path,C_D_Year\n" +
                    "                    FROM        \n" +
                    "                                          Ahkam_master INNER JOIN  \n" +
                    "                                          Ahkam_types ON Ahkam_master.MASTER_ID = Ahkam_types.ID \n" +
                    "                    WHERE     C_D_Year = " + hkm_Master_year + " and master_id = " + MASTER_ID + "";
        } else {
            sqlCheack = "SELECT  ' الطعن رقم '  ||  Ahkam_master.C_No  ||  ' سنة ' ||    Ahkam_master.C_Year ||  ' بتاريخ  '  ||   Ahkam_master.date_galsa as titel   ,Ahkam_types.Name as Master_name,hkm_mbda  as details,MASTER_ID,path,C_D_Year\n" +
                    "                    FROM        \n" +
                    "                                          Ahkam_master INNER JOIN  \n" +
                    "                                          Ahkam_types ON Ahkam_master.MASTER_ID = Ahkam_types.ID \n" +
                    "                    WHERE     C_D_Year = " + hkm_Master_year + " and master_id = " + MASTER_ID + " limit 5";
        }


        Cursor res = mDatabase.rawQuery(sqlCheack, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            String details = res.getString(res.getColumnIndex("details"));
            String titel = res.getString(res.getColumnIndex("titel"));
            String hkm_Master_year_ = res.getString(res.getColumnIndex("C_D_Year"));
            String MASTER = res.getString(res.getColumnIndex("MASTER_ID"));
            String path = res.getString(res.getColumnIndex("path"));
            String name_ = res.getString(res.getColumnIndex("Master_name"));

            arrayList.add(new Master_Stract(titel, details, hkm_Master_year_, "", "", MASTER, path, name_));
            res.moveToNext();
        }
        try {

            res.close();
            closeDatabase();

        } catch (Exception e) {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();

            res.close();
            closeDatabase();

        } finally {

            res.close();
            closeDatabase();
        }
        return arrayList;

    }

    //   Ios FUNC getHaisiats
    public ArrayList Gethitiat(String Type_id) {
        ArrayList<Master_Stract> arrayList = new ArrayList<>();
        openDatabase();

        String sqlCheack = "";

        if (Var.Active) {
            sqlCheack = "SELECT    Hithiat_books.name as Master_name,Hithiat_books_Topics.Name AS  Topic_name ,Hithiat_books_Topics.ID,Type_id\n" +
                    "FROM        Hithiat_books_Topics INNER JOIN\n" +
                    "                     Hithiat_books ON Hithiat_books_Topics.Type_id =  Hithiat_books.id\n" +
                    "WHERE     (Hithiat_books_Topics.Type_id = " + Type_id + ")";
        } else {
            sqlCheack = "SELECT    Hithiat_books.name as Master_name,Hithiat_books_Topics.Name AS  Topic_name ,Hithiat_books_Topics.ID,Type_id\n" +
                    "FROM        Hithiat_books_Topics INNER JOIN\n" +
                    "                     Hithiat_books ON Hithiat_books_Topics.Type_id =  Hithiat_books.id\n" +
                    "WHERE     (Hithiat_books_Topics.Type_id = " + Type_id + ") limit 5";
        }
        Cursor res = mDatabase.rawQuery(sqlCheack, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            String type_id_ = res.getString(res.getColumnIndex("Type_id"));
            String topic_name = res.getString(res.getColumnIndex("Topic_name"));
            String id = res.getString(res.getColumnIndex("ID"));
            arrayList.add(new Master_Stract(topic_name, type_id_, id, null, null, "", "", topic_name));
            res.moveToNext();
        }
        try {

            res.close();
            closeDatabase();

        } catch (Exception e) {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();

            res.close();
            closeDatabase();

        } finally {

            res.close();
            closeDatabase();
        }
        return arrayList;

    }

    //   Ios FUNC  getHithiatLaw
    public ArrayList Gethithitlaw(String type_id, String topic_id) {
        ArrayList<Master_Stract> arrayList = new ArrayList<>();
        openDatabase();


        String sqlCheack = "";
        if (Var.Active) {
            sqlCheack = "SELECT     Hithiat_books_Topics.Type_id, Hithiat_books_Topics.Name, Hithiat_books.name AS type_name, Hithiat_books_Laws.Title AS titel, Hithiat_books_Laws.notes AS details\n" +
                    "FROM         Hithiat_books_Laws INNER JOIN\n" +
                    "                      Hithiat_books_Topics ON Hithiat_books_Laws.Topic_id = Hithiat_books_Topics.ID INNER JOIN\n" +
                    "                      Hithiat_books ON Hithiat_books_Topics.Type_id = Hithiat_books.id\n" +
                    "WHERE     (Hithiat_books_Laws.Topic_id = " + topic_id + ") AND (Hithiat_books_Topics.Type_id = " + type_id + ")";
        } else {
            sqlCheack = "SELECT     Hithiat_books_Topics.Type_id, Hithiat_books_Topics.Name, Hithiat_books.name AS type_name, Hithiat_books_Laws.Title AS titel, Hithiat_books_Laws.notes AS details\n" +
                    "FROM         Hithiat_books_Laws INNER JOIN\n" +
                    "                      Hithiat_books_Topics ON Hithiat_books_Laws.Topic_id = Hithiat_books_Topics.ID INNER JOIN\n" +
                    "                      Hithiat_books ON Hithiat_books_Topics.Type_id = Hithiat_books.id\n" +
                    "WHERE     (Hithiat_books_Laws.Topic_id = " + topic_id + ") AND (Hithiat_books_Topics.Type_id = " + type_id + ") limit 5";

        }
        Cursor res = mDatabase.rawQuery(sqlCheack, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            String titel = res.getString(res.getColumnIndex("titel"));
            String details = res.getString(res.getColumnIndex("details"));
            String tash_name = "";
            String mda_number = "";
            String Master = res.getString(res.getColumnIndex("Type_id"));
            String name_ = res.getString(res.getColumnIndex("type_name"));

            arrayList.add(new Master_Stract(titel, details, tash_name, mda_number, null, "", "", name_));


            res.moveToNext();
        }
        try {

            res.close();
            closeDatabase();

        } catch (Exception e) {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();

            res.close();
            closeDatabase();

        } finally {

            res.close();
            closeDatabase();
        }
        return arrayList;

    }

    //   Ios FUNC getHithiatAhkam
    public ArrayList Gethithitnkd(String type_id, String topic_id) {
        ArrayList<Master_Stract> arrayList = new ArrayList<>();
        openDatabase();
        String sqlCheack = "";
        if (Var.Active) {
            sqlCheack = "SELECT     Hithiat_books.name, Hithiat_books_Ahkam.*\n" +
                    "FROM         Hithiat_books INNER JOIN\n" +
                    "                      Hithiat_books_Ahkam ON Hithiat_books.id = Hithiat_books_Ahkam.type_id\n" +
                    "WHERE     (Hithiat_books_Ahkam.type_id = " + type_id + ") AND (Hithiat_books_Ahkam.Topic_id = " + topic_id + ")";
        } else {
            sqlCheack = "SELECT     Hithiat_books.name, Hithiat_books_Ahkam.*\n" +
                    "FROM         Hithiat_books INNER JOIN\n" +
                    "                      Hithiat_books_Ahkam ON Hithiat_books.id = Hithiat_books_Ahkam.type_id\n" +
                    "WHERE     (Hithiat_books_Ahkam.type_id = " + type_id + ") AND (Hithiat_books_Ahkam.Topic_id = " + topic_id + ") limit 5";

        }
        Cursor res = mDatabase.rawQuery(sqlCheack, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            String titel = res.getString(res.getColumnIndex("Title"));
            String details = res.getString(res.getColumnIndex("notes"));
            String Master = res.getString(res.getColumnIndex("type_id"));
            String name_ = res.getString(res.getColumnIndex("name"));

            arrayList.add(new Master_Stract(titel, details, null, null, null, "", "", name_));


            res.moveToNext();

        }
        try {

            res.close();
            closeDatabase();

        } catch (Exception e) {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();

            res.close();
            closeDatabase();

        } finally {

            res.close();
            closeDatabase();
        }
        return arrayList;

    }


    //   Ios FUNC getTashreaatDetailsByTashID
    public ArrayList gettashinfo(String Tash_id) {
        ArrayList<Master_Stract> arrayList = new ArrayList<>();
        openDatabase();

        String sqlCheack = "";

        if (Var.Active) {
            sqlCheack = "SELECT     Tash_id, Type_ID, pic_path, T_name, T_Type || ' رقم ' || T_No ||   ' لسنة '  ||  T_Year  as T_Short_name,has_lai7a,has_edit,has_ahkam FROM Tash_master WHERE  Tash_id like '" + Tash_id + "'";

        } else {
            sqlCheack = "SELECT     Tash_id, Type_ID, pic_path, T_name, T_Type || ' رقم ' || T_No ||   ' لسنة '  ||  T_Year  as T_Short_name,has_lai7a,has_edit,has_ahkam  FROM Tash_master WHERE  Tash_id like '" + Tash_id + "' limit 5";

        }

        Cursor res = mDatabase.rawQuery(sqlCheack, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {

            String Tash_id_ = res.getString(res.getColumnIndex("Tash_id"));
            String Type_ID = res.getString(res.getColumnIndex("Type_ID"));
            String pic_path = res.getString(res.getColumnIndex("pic_path"));
            String T_name = res.getString(res.getColumnIndex("T_name"));
            String T_Short_name = res.getString(res.getColumnIndex("T_Short_name"));
            String has_lai7a = res.getString(res.getColumnIndex("has_lai7a"));
            String has_edit = res.getString(res.getColumnIndex("has_edit"));
            String has_ahkam = res.getString(res.getColumnIndex("has_ahkam"));

            arrayList.add(new Master_Stract(Tash_id_, Type_ID, pic_path, T_name, T_Short_name, has_lai7a, has_edit, has_ahkam));
            res.moveToNext();
        }
        try {

            res.close();
            closeDatabase();

        } catch (Exception e) {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();

            res.close();
            closeDatabase();

        } finally {

            res.close();
            closeDatabase();
        }
        return arrayList;

    }

    //    Ios FUNC getAllMawadByTashID
    public ArrayList Gettashmowad(String Tash_id) {
        ArrayList<Master_Stract> arrayList = new ArrayList<>();
        openDatabase();
        String sqlCheack = "";
        if (Var.Active) {
            sqlCheack = "SELECT     Tash_mowad.has_ahkam, Tash_mowad.mda_id, Tash_mowad.titel, Tash_mowad.details, Tash_mowad.edit_txt, Tash_master.T_name\n" +
                    "FROM         Tash_master INNER JOIN\n" +
                    "                      Tash_mowad ON Tash_master.Tash_id = Tash_mowad.Tash_id\n" +
                    "WHERE     (Tash_master.Tash_id = " + Tash_id + ") order by number";
        } else {
            sqlCheack = "SELECT     Tash_mowad.has_ahkam, Tash_mowad.mda_id, Tash_mowad.titel, Tash_mowad.details, Tash_mowad.edit_txt, Tash_master.T_name\n" +
                    "FROM         Tash_master INNER JOIN\n" +
                    "                      Tash_mowad ON Tash_master.Tash_id = Tash_mowad.Tash_id\n" +
                    "WHERE     (Tash_master.Tash_id = " + Tash_id + ") order by number";

        }
        Cursor res = mDatabase.rawQuery(sqlCheack, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            String titel = res.getString(res.getColumnIndex("titel"));
            String number = "";
            String edit_txt = res.getString(res.getColumnIndex("edit_txt"));
            String details = res.getString(res.getColumnIndex("details")) + "\n" + " " + "\n" + edit_txt;
            String mda_id = res.getString(res.getColumnIndex("mda_id"));
            String T_name = res.getString(res.getColumnIndex("T_name"));
            arrayList.add(new Master_Stract(titel, details, edit_txt, number, mda_id, "", "", T_name));
            res.moveToNext();
        }
        try {

            res.close();
            closeDatabase();

        } catch (Exception e) {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();

            res.close();
            closeDatabase();

        } finally {

            res.close();
            closeDatabase();
        }
        return arrayList;

    }

    //    Ios FUNC getAllLawaahByTashID
    public ArrayList Gettashlai7a(String Tash_id) {
        ArrayList<Master_Stract> arrayList = new ArrayList<>();
        openDatabase();
        String sqlCheack = "";
        if (Var.Active) {

            sqlCheack = "SELECT       Tash_lai7a.Tash_id_Lai7a as Tash_id, T_Type || ' رقم ' || T_No ||   ' لسنة '  ||  T_Year  as T_Short_name\n" +
                    "FROM         Tash_master INNER JOIN\n" +
                    "                      Tash_lai7a ON Tash_master.Tash_id = Tash_lai7a.Tash_id_Lai7a\n" +
                    "WHERE     (Tash_lai7a.Tash_id  like '" + Tash_id + "')  group by Tash_lai7a.Tash_id_Lai7a order by T_D_Year desc , T_No asc";
        } else {
            sqlCheack = "SELECT       Tash_lai7a.Tash_id_Lai7a as Tash_id, T_Type || ' رقم ' || T_No ||   ' لسنة '  ||  T_Year  as T_Short_name\n" +
                    "FROM         Tash_master INNER JOIN\n" +
                    "                      Tash_lai7a ON Tash_master.Tash_id = Tash_lai7a.Tash_id_Lai7a\n" +
                    "WHERE     (Tash_lai7a.Tash_id  like '" + Tash_id + "')  group by Tash_lai7a.Tash_id_Lai7a order by T_D_Year desc , T_No asc limit 5";

        }
        Cursor res = mDatabase.rawQuery(sqlCheack, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            String Tash_id_ = res.getString(res.getColumnIndex("Tash_id"));
            String T_Short_name = res.getString(res.getColumnIndex("T_Short_name"));


            arrayList.add(new Master_Stract(Tash_id_, T_Short_name, "", "", "", "", "", ""));
            res.moveToNext();
        }
        try {

            res.close();
            closeDatabase();

        } catch (Exception e) {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();

            res.close();
            closeDatabase();

        } finally {

            res.close();
            closeDatabase();
        }
        return arrayList;

    }

    //    Ios FUNC getAllEditsByTashID
    public ArrayList Gettashedit(String Tash_id) {
        ArrayList<Master_Stract> arrayList = new ArrayList<>();
        openDatabase();
        String sqlCheack = "";
        if (Var.Active) {
            sqlCheack = "SELECT       Tash_edit.Tash_id_edit as Tash_id, T_Type || ' رقم ' || T_No ||   ' لسنة '  ||  T_Year  as T_Short_name\n" +
                    "FROM         Tash_master INNER JOIN\n" +
                    "                      Tash_edit ON Tash_master.Tash_id = Tash_edit.Tash_id_edit\n" +
                    "WHERE     (Tash_edit.Tash_id = " + Tash_id + ")  group by Tash_edit.Tash_id_edit order by T_D_Year desc , desc asc";
        } else {
            sqlCheack = "SELECT       Tash_edit.Tash_id_edit as Tash_id, T_Type || ' رقم ' || T_No ||   ' لسنة '  ||  T_Year  as T_Short_name\n" +
                    "FROM         Tash_master INNER JOIN\n" +
                    "                      Tash_edit ON Tash_master.Tash_id = Tash_edit.Tash_id_edit\n" +
                    "WHERE     (Tash_edit.Tash_id = " + Tash_id + ")  group by Tash_edit.Tash_id_edit order by T_D_Year desc , desc asc limit 5";

        }
        Cursor res = mDatabase.rawQuery(sqlCheack, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            String Tash_id_ = res.getString(res.getColumnIndex("Tash_id"));
            String T_Short_name = res.getString(res.getColumnIndex("T_Short_name"));


            arrayList.add(new Master_Stract(Tash_id_, T_Short_name, "", "", "", "", "", ""));
            res.moveToNext();
        }
        try {
            res.close();
            closeDatabase();
        } catch (Exception e) {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
            res.close();
            closeDatabase();
        } finally {
            res.close();
            closeDatabase();
        }
        return arrayList;
    }

    //    Ios FUNC getAllAhkamByTashID
    public ArrayList Gettashahkam(String Tash_id) {
        ArrayList<Master_Stract> arrayList = new ArrayList<>();
        openDatabase();
        String sqlCheack = "";
        if (Var.Active) {

            sqlCheack = "SELECT     ' الطعن رقم '  ||  Ahkam_master.C_No  ||  ' سنة ' ||    Ahkam_master.C_Year ||  ' بتاريخ  '  ||   Ahkam_master.date_galsa as hkm_title , Ahkam_master.hkm_mbda as hkm_details, Ahkam_master.path, \n" +
                    "                      Ahkam_master.MASTER_ID, Ahkam_types.Name as Hkm_type\n" +
                    "FROM         Tash_Ahkam INNER JOIN\n" +
                    "                      Ahkam_master ON Tash_Ahkam.hkm_id = Ahkam_master.ID_ INNER JOIN\n" +
                    "                      Ahkam_types ON Ahkam_master.MASTER_ID = Ahkam_types.ID\n" +
                    "WHERE     (Tash_Ahkam.Tash_id = " + Tash_id + ") limit 50 ";
        } else {
            sqlCheack = "SELECT     ' الطعن رقم '  ||  Ahkam_master.C_No  ||  ' سنة ' ||    Ahkam_master.C_Year ||  ' بتاريخ  '  ||   Ahkam_master.date_galsa as hkm_title , Ahkam_master.hkm_mbda as hkm_details, Ahkam_master.path, \n" +
                    "                      Ahkam_master.MASTER_ID, Ahkam_types.Name as Hkm_type\n" +
                    "FROM         Tash_Ahkam INNER JOIN\n" +
                    "                      Ahkam_master ON Tash_Ahkam.hkm_id = Ahkam_master.ID_ INNER JOIN\n" +
                    "                      Ahkam_types ON Ahkam_master.MASTER_ID = Ahkam_types.ID\n" +
                    "WHERE     (Tash_Ahkam.Tash_id = " + Tash_id + ") limit 5 ";
        }

        Cursor res = mDatabase.rawQuery(sqlCheack, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            String hkm_title = res.getString(res.getColumnIndex("hkm_title"));
            String hkm_details = res.getString(res.getColumnIndex("hkm_details"));
            String path = res.getString(res.getColumnIndex("path"));
            String Hkm_type = res.getString(res.getColumnIndex("Hkm_type"));
            String master_id = res.getString(res.getColumnIndex("MASTER_ID"));

            arrayList.add(new Master_Stract(hkm_title, hkm_details, master_id, null, null, null, path, Hkm_type));
            res.moveToNext();
        }
        try {
            res.close();
            closeDatabase();
        } catch (Exception e) {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
            res.close();
            closeDatabase();
        } finally {
            res.close();
            closeDatabase();
        }
        return arrayList;
    }

    //    Ios FUNC getImportantashreaatSuccess
    public ArrayList GetimportTash() {
        ArrayList<Master_Stract> arrayList = new ArrayList<>();
        openDatabase();
        String sqlCheack = "";
        if (Var.Active) {
            sqlCheack = "select * from Tash_Import_List";
        } else {
            sqlCheack = "select * from Tash_Import_List  limit 5";

        }
        Cursor res = mDatabase.rawQuery(sqlCheack, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            String Tash_id = res.getString(res.getColumnIndex("Tash_id"));
            String import_tash_name = res.getString(res.getColumnIndex("Import_tash_name"));

            arrayList.add(new Master_Stract(Tash_id, import_tash_name, "", "", "", "", "", ""));
            res.moveToNext();
        }
        try {
            res.close();
            closeDatabase();
        } catch (Exception e) {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
            res.close();
            closeDatabase();
        } finally {
            res.close();
            closeDatabase();
        }
        return arrayList;
    }

    public ArrayList gettypeDialogSearchAhkam() {
        ArrayList<DialogSearchDataModel> arrayList = new ArrayList<>();
        openDatabase();
        String sqlCheack = "";
        if (Var.Active) {
            sqlCheack = "select * from Ahkam_types  ORDER BY ID ASC  ";
        } else {
            sqlCheack = "select * from Ahkam_types  ORDER BY ID ASC  limit 5";

        }
        Cursor res = mDatabase.rawQuery(sqlCheack, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            String name_ = res.getString(res.getColumnIndex("Name"));
            String id = res.getString(res.getColumnIndex("ID"));

            arrayList.add(new DialogSearchDataModel(name_, id, false));
            res.moveToNext();
        }
        try {
            res.close();
            closeDatabase();
        } catch (Exception e) {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
            res.close();
            closeDatabase();
        } finally {
            res.close();
            closeDatabase();
        }
        return arrayList;
    }


    public ArrayList<DialogSearchDataModel> gettypeDialogSearch() {
        ArrayList<DialogSearchDataModel> arrayList = new ArrayList<>();
        openDatabase();
        String sqlCheack = "";
        if (Var.Active) {
            sqlCheack = "select * from tash_type  ORDER BY ID ASC  ";
        } else {
            sqlCheack = "select * from tash_type  ORDER BY ID ASC  limit 5";

        }
        Cursor res = mDatabase.rawQuery(sqlCheack, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            String name_ = res.getString(res.getColumnIndex("Name"));
            String id = res.getString(res.getColumnIndex("ID"));

            arrayList.add(new DialogSearchDataModel(name_, id, false));
            res.moveToNext();
        }
        try {
            res.close();
            closeDatabase();
        } catch (Exception e) {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
            res.close();
            closeDatabase();
        } finally {
            res.close();
            closeDatabase();
        }
        return arrayList;
    }

    //    Ios FUNC getAllTashreaatMainSectionSuccess
    public ArrayList gettype() {
        ArrayList<Master_Stract> arrayList = new ArrayList<>();
        openDatabase();
        String sqlCheack = "";
        if (Var.Active) {
            sqlCheack = "select * from tash_type  ORDER BY ID ASC  ";
        } else {
            sqlCheack = "select * from tash_type  ORDER BY ID ASC  limit 5";

        }
        Cursor res = mDatabase.rawQuery(sqlCheack, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            String name_ = res.getString(res.getColumnIndex("Name"));
            String id = res.getString(res.getColumnIndex("ID"));

            arrayList.add(new Master_Stract(name_, id, "", "", null, "", "", name_));
            res.moveToNext();
        }
        try {
            res.close();
            closeDatabase();
        } catch (Exception e) {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
            res.close();
            closeDatabase();
        } finally {
            res.close();
            closeDatabase();
        }
        return arrayList;
    }

    //    Ios FUNC getAllTashreaatYearsById
    public ArrayList gettypeyear(String type_id) {
        ArrayList<Master_Stract> arrayList = new ArrayList<>();
        openDatabase();
        String sqlCheack = "";
        if (Var.Active) {
            sqlCheack = "select * from Tash_Type_Years  where Type_id like '" + type_id + "' ORDER BY T_Year DESC ";
        } else {
            sqlCheack = "select * from Tash_Type_Years  where Type_id like '" + type_id + "' ORDER BY T_Year DESC limit 5";

        }
        Cursor res = mDatabase.rawQuery(sqlCheack, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            String T_Year = res.getString(res.getColumnIndex("T_Year"));
            String Type_id = res.getString(res.getColumnIndex("Type_id"));
            arrayList.add(new Master_Stract(T_Year, Type_id, null, null, null, "", "", T_Year));
            res.moveToNext();
        }

        try {
            res.close();
            closeDatabase();
        } catch (Exception e) {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
            res.close();
            closeDatabase();
        } finally {
            res.close();
            closeDatabase();
        }
        return arrayList;
    }

    //    Ios FUNC getTashreaatByYear
    public ArrayList gettashTypeYear(String typeid, String year) {
        ArrayList<Master_Stract> arrayList = new ArrayList<>();
        openDatabase();
        String sqlCheack = "";
        if (Var.Active) {
            sqlCheack = " select Tash_id ,T_Type || ' رقم ' || T_No ||   ' لسنة '  ||  T_Year  as T_Short_name from Tash_master where Type_id = " + typeid + "  and T_Year = " + year + " order by T_No";

        } else {
            sqlCheack = " select Tash_id ,T_Type || ' رقم ' || T_No ||   ' لسنة '  ||  T_Year  as T_Short_name from Tash_master where Type_id = " + typeid + "  and T_Year = " + year + " order by T_No limit 5";

        }
        Cursor res = mDatabase.rawQuery(sqlCheack, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {

            String Tash_id = res.getString(res.getColumnIndex("Tash_id"));
            String T_Short_name = res.getString(res.getColumnIndex("T_Short_name"));
            arrayList.add(new Master_Stract(Tash_id, T_Short_name, "", "", "", "", "", ""));
            res.moveToNext();

        }
        try {

            res.close();
            closeDatabase();

        } catch (Exception e) {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();

            res.close();
            closeDatabase();

        } finally {

            res.close();
            closeDatabase();
        }
        return arrayList;

    }


    public ArrayList Ahkam_mortbtaMda(String mda_id) {

        ArrayList<Master_Stract> arrayList = new ArrayList<>();
        openDatabase();
        String sqlCheack = "";
        if (Var.Active) {
            sqlCheack = "SELECT     ' الطعن رقم '  ||  Ahkam_master.C_No  ||  ' سنة ' ||    Ahkam_master.C_Year ||  ' بتاريخ  '  ||   Ahkam_master.date_galsa as hkm_title , Ahkam_master.hkm_mbda as hkm_details, Ahkam_master.path, \n" +
                    "                                         Ahkam_master.MASTER_ID, Ahkam_types.Name as Hkm_type\n" +
                    "                    FROM         Tash_Ahkam INNER JOIN\n" +
                    "                                         Ahkam_master ON Tash_Ahkam.hkm_id = Ahkam_master.ID_ INNER JOIN\n" +
                    "                                         Ahkam_types ON Ahkam_master.MASTER_ID = Ahkam_types.ID\n" +
                    "                    WHERE     (Tash_Ahkam.mda_id = " + mda_id + " )  group by hkm_id limit 50 ;";
        } else {
            sqlCheack = "SELECT     ' الطعن رقم '  ||  Ahkam_master.C_No  ||  ' سنة ' ||    Ahkam_master.C_Year ||  ' بتاريخ  '  ||   Ahkam_master.date_galsa as hkm_title , Ahkam_master.hkm_mbda as hkm_details, Ahkam_master.path, \n" +
                    "                                         Ahkam_master.MASTER_ID, Ahkam_types.Name as Hkm_type\n" +
                    "                    FROM         Tash_Ahkam INNER JOIN\n" +
                    "                                         Ahkam_master ON Tash_Ahkam.hkm_id = Ahkam_master.ID_ INNER JOIN\n" +
                    "                                         Ahkam_types ON Ahkam_master.MASTER_ID = Ahkam_types.ID\n" +
                    "                    WHERE     (Tash_Ahkam.mda_id = " + mda_id + " )  group by hkm_id limit 5 ;";

        }
        Cursor res = mDatabase.rawQuery(sqlCheack, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            String hkm_title = res.getString(res.getColumnIndex("hkm_title"));
            String hkm_details = res.getString(res.getColumnIndex("hkm_details"));
            String path = res.getString(res.getColumnIndex("path"));
            String Hkm_type = res.getString(res.getColumnIndex("Hkm_type"));
            arrayList.add(new Master_Stract(hkm_title, hkm_details, null, null, null, null, path, Hkm_type));
            res.moveToNext();
        }

        // Toast.makeText(mContext, String.valueOf(res.getCount()), Toast.LENGTH_SHORT).show();

        try {

            res.close();
            closeDatabase();

        } catch (Exception e) {
            //  Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();

            res.close();
            closeDatabase();

        } finally {

            res.close();
            closeDatabase();
        }
        return arrayList;

    }


    public ArrayList ShowFav() {
        ArrayList<Master_Stract> arrayList = new ArrayList<>();
        openDatabase();
        String sqlCheack = "";
        if (Var.Active) {
            sqlCheack = "select * from Favorite order by nameMaster";
        } else {
            sqlCheack = "select * from Favorite order by nameMaster limit 5";

        }
        Cursor res = mDatabase.rawQuery(sqlCheack, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {

            String itemName = res.getString(res.getColumnIndex("itemName"));
            String itemDetails = res.getString(res.getColumnIndex("itemDetails"));
            String nameMaster = res.getString(res.getColumnIndex("nameMaster"));

            arrayList.add(new Master_Stract(itemName, itemDetails, "", "", "", "", "", nameMaster));
            res.moveToNext();
        }
        try {

            res.close();
            closeDatabase();

        } catch (Exception e) {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();

            res.close();
            closeDatabase();

        } finally {

            res.close();
            closeDatabase();
        }
        return arrayList;

    }

    public boolean insertData(String itemName, String itemDetails, String nameMaster) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("itemName", itemName);
        contentValues.put("itemDetails", itemDetails);
        contentValues.put("nameMaster", nameMaster);

        long result = db.insert("Favorite", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }

    public void deleteData(String itemName) {
        String selectQuery = "DELETE FROM Favorite where itemName like '" + itemName + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(selectQuery);
    }


    public boolean checkFavorite(String ItemName, String nameMaster) {
        String sqlCheack = "";
        if (Var.Active) {
            sqlCheack = "select * from Favorite where itemName like '" + ItemName + "' and nameMaster like '" + nameMaster + "' ";
        } else {
            sqlCheack = "select * from Favorite where itemName like '" + ItemName + "' and nameMaster like '" + nameMaster + "' limit 5";

        }
        ArrayList<Master_Stract> arrayList = new ArrayList<>();
        openDatabase();
        Cursor res = mDatabase.rawQuery(sqlCheack, null);

        if (res.getCount() != 0) {
            return true;
        }
        try {

            res.close();
            closeDatabase();

        } catch (Exception e) {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();

            res.close();
            closeDatabase();

        } finally {

            res.close();
            closeDatabase();
        }
        return false;

    }


    public ArrayList<Master_Stract> SearchAhkam(String Word, String Hkm_num_From, String Hkm_num_To, String Hkm_Year_From, String Hkm_Year_To, String Office_from, String office_To, String Page_FROM, String Page_to, String Part_FROM, String Part_to, String sys_id, String place_id, String date_galsa_From, String date_galsa_To, String Type_ids) {


        String Hkm_NUM = " AND C_No BETWEEN " + Hkm_num_From + " AND " + Hkm_num_To;
        String Year_Num = " AND C_Year BETWEEN " + Hkm_Year_From + " AND " + Hkm_Year_To;
        String Office_Num = " AND O_Year BETWEEN " + Office_from + " AND " + office_To;
        String Page_Num = " AND Page_No BETWEEN " + Page_FROM + " AND " + Page_to;
        String Part_Num = " AND Part_No BETWEEN " + Part_FROM + " AND " + Part_to;
        String Sys = " AND court_hkm_id  ='" + sys_id + "'";
        String place = " AND Geha_id  ='" + place_id + "'";
        String Date_Galsat = " AND date_galsa BETWEEN '" + date_galsa_From + "' AND '" + date_galsa_To + "'";
        //String Types = " AND Master_id in ('" + Type_ids + "')";
        String Types = " AND Master_id in (" + Type_ids + ")";

        String convertword = arbicencodicSub(Word);
        String Str = convertword.replace(" ", "%");
        ArrayList<Master_Stract> arrayList = new ArrayList<>();
        openDatabase();
        String Sql_Q = "SELECT  ' الطعن رقم '  ||  Ahkam_master.C_No  ||  ' سنة ' ||    Ahkam_master.C_Year ||  ' بتاريخ  '  ||   Ahkam_master.date_galsa as titel,master_id,hkm_mbda as details,path from ahkam_master   WHERE hkm_mbda LIKE '%" + Str + "%' ";


        if (Type_ids.length() == 0) {

            Sql_Q = Sql_Q + " AND Master_id in (1,2,3,4,5,6,7,8)";

        } else {

            Sql_Q = Sql_Q + Types;

        }


        if (Hkm_num_From.length() == 0) {

            Sql_Q = Sql_Q;

        } else {

            Sql_Q = Sql_Q + Hkm_NUM;

        }


        if (Hkm_Year_From.length() == 0) {

            Sql_Q = Sql_Q;

        } else {

            Sql_Q = Sql_Q + Year_Num;

        }


        if (Office_from.length() == 0) {

            Sql_Q = Sql_Q;

        } else {

            Sql_Q = Sql_Q + Office_Num;

        }


        if (Page_FROM.length() == 0) {

            Sql_Q = Sql_Q;

        } else {

            Sql_Q = Sql_Q + Page_Num;

        }


        if (Part_FROM.length() == 0) {

            Sql_Q = Sql_Q;

        } else {

            Sql_Q = Sql_Q + Part_Num;

        }


        if (sys_id.length() == 0) {

            Sql_Q = Sql_Q;

        } else {

            Sql_Q = Sql_Q + Sys;

        }


        if (place_id.length() == 0) {

            Sql_Q = Sql_Q;

        } else {

            Sql_Q = Sql_Q + place;

        }


        if (date_galsa_From.length() == 0) {

            Sql_Q = Sql_Q;

        } else {

            Sql_Q = Sql_Q + Date_Galsat;

        }


        String sqlCheack = "";
        if (Var.Active) {
            sqlCheack = Sql_Q + " order by MASTER_ID desc";
        } else {
            sqlCheack = Sql_Q + " order by MASTER_ID desc limit 5";

        }
        Cursor res = mDatabase.rawQuery(sqlCheack, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            String titel = res.getString(res.getColumnIndex("titel"));
            String details = res.getString(res.getColumnIndex("details"));
            String Master = res.getString(res.getColumnIndex("MASTER_ID"));
            String path = res.getString(res.getColumnIndex("path"));
            arrayList.add(new Master_Stract(titel, details, "", "", "", Master, path, ""));
            res.moveToNext();
        }
        try {
            res.close();
            closeDatabase();
        } catch (Exception e) {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();

            res.close();
            closeDatabase();

        } finally {

            res.close();
            closeDatabase();
        }
        return arrayList;

    }


    public ArrayList<Master_Stract> SearchTash(String Tash_num_From, String Tash_num_To,
                                               String Tash_year_From, String Tash_year_To, String Type_ids, String Date_Tash_from,
                                               String Date_Tash_To, int search_mda, String TxtMada, String Tash_name) {
        String convertword = arbicencodicSub(Tash_name);
        String Str = convertword.replace(" ", "%");

        String convertword_ = arbicencodicSub(TxtMada);
        String Str_ = convertword_.replace(" ", "%");


        ArrayList<Master_Stract> arrayList = new ArrayList<>();
        openDatabase();


        String Sql_Q = "";

        if (search_mda == 1) {

            Sql_Q = " SELECT     Tash_master.Type_ID, Tash_master.Tash_id, Tash_mowad.titel, Tash_Type.Name, Tash_master.T_No, Tash_master.T_Year, Tash_master.T_name\n" +
                    "            FROM         Tash_Type INNER JOIN\n" +
                    "            Tash_master ON Tash_Type.ID = Tash_master.Type_ID INNER JOIN\n" +
                    "            Tash_mowad ON Tash_master.Tash_id = Tash_mowad.Tash_id\n" +
                    "            WHERE  1=1 ";

        } else {

            Sql_Q = "SELECT     Tash_Type.Name, Tash_master.T_No, Tash_master.T_Year, Tash_master.Tash_id, Tash_master.T_name\n" +
                    "FROM         Tash_master INNER JOIN\n" +
                    "                      Tash_Type ON Tash_master.Type_ID = Tash_Type.ID\n" +
                    "WHERE    1=1";

        }


        String TashNum = " AND T_No BETWEEN " + Tash_num_From + " AND " + Tash_num_To;
        String TashYear = " AND T_Year BETWEEN " + Tash_year_From + " AND " + Tash_year_To;
        String TypeidS = " AND Type_ID  in " + Type_ids + "";
        String Date_Tash = " AND Date_Tash BETWEEN '" + Date_Tash_from + "' AND '" + Date_Tash_To + "'";
        String TashName = " AND  T_name like '%" + Str + "%'";
        String MdaString = " AND  details like '%" + Str_ + "%'";

        if (Tash_name.length() == 0) {

            Sql_Q = Sql_Q;

        } else {

            Sql_Q = Sql_Q + TashName;

        }

        if (TxtMada.length() == 0) {

            Sql_Q = Sql_Q;

        } else {

            Sql_Q = Sql_Q + MdaString;

        }

        if (Tash_num_From.length() == 0) {

            Sql_Q = Sql_Q;

        } else {

            Sql_Q = Sql_Q + TashNum;

        }

        if (Tash_year_From.length() == 0) {

            Sql_Q = Sql_Q;

        } else {

            Sql_Q = Sql_Q + TashYear;

        }

        if (Type_ids.length() == 0) {

            Sql_Q = Sql_Q;

        } else {

            Sql_Q = Sql_Q + TypeidS;

        }

        if (Date_Tash_from.length() == 0) {

            Sql_Q = Sql_Q;

        } else {

            Sql_Q = Sql_Q + Date_Tash;

        }


        String sqlCheack = "";
        if (Var.Active) {
            sqlCheack = Sql_Q + " ORDER BY Type_ID , T_D_Year";
        } else {
            sqlCheack = Sql_Q + " ORDER BY Type_ID , T_D_Year limit 5";

        }

        String name_ = "";
        Cursor res = mDatabase.rawQuery(sqlCheack, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            String tash_name = res.getString(res.getColumnIndex("T_name"));

            String Tash_id = res.getString(res.getColumnIndex("Tash_id"));

            arrayList.add(new Master_Stract("", tash_name, "", Tash_id, "", "", "", ""));
            res.moveToNext();
        }
        try {

            res.close();
            closeDatabase();

        } catch (Exception e) {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();

            res.close();
            closeDatabase();

        } finally {

            res.close();
            closeDatabase();
        }
        return arrayList;

    }

    public ArrayList searhchTashMowad(String word) {
        String convertword = arbicencodicSub(word);
        String Str = convertword.replace(" ", "%");

        ArrayList<Master_Stract> arrayList = new ArrayList<>();
        openDatabase();
        String sqlCheack = "";
        if (Var.Active) {
            sqlCheack = " SELECT tash_mowad.titel,tash_mowad.number,tash_mowad.details,tash_master.list_txt_name FROM tash_mowad Inner Join tash_master ON tash_master.Tash_id = tash_mowad.Tash_id  WHERE tash_mowad.details LIKE '%" + Str + "%' order by tash_master.Tash_id,tash_mowad.number asc";
        } else {
            sqlCheack = " SELECT tash_mowad.titel,tash_mowad.number,tash_mowad.details,tash_master.list_txt_name FROM tash_mowad Inner Join tash_master ON tash_master.Tash_id = tash_mowad.Tash_id  WHERE tash_mowad.details LIKE '%" + Str + "%' order by tash_master.Tash_id,tash_mowad.number asc limit 5";
        }
        Cursor res = mDatabase.rawQuery(sqlCheack, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {

            String titel = res.getString(res.getColumnIndex("titel"));
            String details = res.getString(res.getColumnIndex("details"));
            String list_txt_name = res.getString(res.getColumnIndex("list_txt_name"));
            arrayList.add(new Master_Stract(titel, details, list_txt_name, "", "", "", "", list_txt_name));
            res.moveToNext();
        }
        try {

            res.close();
            closeDatabase();

        } catch (Exception e) {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();

            res.close();
            closeDatabase();

        } finally {

            res.close();
            closeDatabase();
        }
        return arrayList;

    }


    public ArrayList searchNaqdAhkam(String word) {
        String convertword = arbicencodicSub(word);
        String Str = convertword.replace(" ", "%");
        String sqlCheack = "";
        if (Var.Active) {
            sqlCheack = "select * from ahkam_master WHERE details LIKE '%" + Str + "%' group by details ORDER BY MASTER_ID , hkm_Master_year ";
        } else {
            sqlCheack = "select * from ahkam_master WHERE details LIKE '%" + Str + "%' group by details ORDER BY MASTER_ID , hkm_Master_year limit 5";

        }
        ArrayList<Master_Stract> arrayList = new ArrayList<>();
        openDatabase();
        Cursor res = mDatabase.rawQuery(sqlCheack, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {

            String titel = res.getString(res.getColumnIndex("titel"));
            String details = res.getString(res.getColumnIndex("details"));
            String name_ = res.getString(res.getColumnIndex("Master_name"));


            arrayList.add(new Master_Stract(titel, details, name_, "", "", "", "نص", name_));
            res.moveToNext();
        }
        try {

            res.close();
            closeDatabase();

        } catch (Exception e) {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();

            res.close();
            closeDatabase();

        } finally {

            res.close();
            closeDatabase();
        }
        return arrayList;

    }


    public ArrayList searchAllTash(String word) {
        String convertword = arbicencodicSub(word);
        String Str = convertword.replace(" ", "%");

        ArrayList<Master_Stract> arrayList = new ArrayList<>();
        openDatabase();
        String sqlCheack = "";
        if (Var.Active) {
            sqlCheack = "select * from tash_master WHERE tash_name LIKE '%" + Str + "%' ORDER BY Type_ID , T_D_Year";
        } else {
            sqlCheack = "select * from tash_master WHERE tash_name LIKE '%" + Str + "%' ORDER BY Type_ID , T_D_Year limit 5";

        }
        Cursor res;
        res = mDatabase.rawQuery(sqlCheack, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {

            String list_txt_name = res.getString(res.getColumnIndex("list_txt_name"));
            String T_Type = res.getString(res.getColumnIndex("T_Type"));
            String Tash_id = res.getString(res.getColumnIndex("Tash_id"));
            String info = "التشريعات";
            arrayList.add(new Master_Stract(list_txt_name, T_Type, info, Tash_id, "", "", "تشريع", list_txt_name));
            res.moveToNext();
        }

        String sqlCheack1 = "";
        if (Var.Active) {
            sqlCheack1 = "SELECT tash_mowad.titel,tash_mowad.number,tash_mowad.details,tash_master.list_txt_name,tash_master.Tash_id FROM tash_mowad Inner Join tash_master ON tash_master.Tash_id = tash_mowad.Tash_id  WHERE tash_mowad.details LIKE '%" + Str + "%' group by details ORDER BY tash_mowad.number ASC";
        } else {
            sqlCheack1 = "SELECT tash_mowad.titel,tash_mowad.number,tash_mowad.details,tash_master.list_txt_name,tash_master.Tash_id FROM tash_mowad Inner Join tash_master ON tash_master.Tash_id = tash_mowad.Tash_id  WHERE tash_mowad.details LIKE '%" + Str + "%' group by details ORDER BY tash_mowad.number ASC limit 5";

        }
        res = mDatabase.rawQuery(sqlCheack1, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {

            String titel = res.getString(res.getColumnIndex("titel"));
            String Tash_id = res.getString(res.getColumnIndex("Tash_id"));
            String details = res.getString(res.getColumnIndex("details"));
            String list_txt_name = res.getString(res.getColumnIndex("list_txt_name"));
            String info = " مواد " + res.getString(res.getColumnIndex("list_txt_name"));
            arrayList.add(new Master_Stract(titel, details, info, Tash_id, "", "", "نص", list_txt_name));
            res.moveToNext();
        }


        String sqlCheack2 = "";
        if (Var.Active) {
            sqlCheack2 = "select tash_ahkam.titel,tash_ahkam.Tash_name,ahkam_master.Master_name,ahkam_master.details,ahkam_master.MASTER_ID from tash_ahkam INNER JOIN ahkam_master ON ahkam_master.id = tash_ahkam.hkm_id WHERE ahkam_master.details LIKE '%" + Str + "%' group by ahkam_master.details";
        } else {
            sqlCheack2 = "select tash_ahkam.titel,tash_ahkam.Tash_name,ahkam_master.Master_name,ahkam_master.details,ahkam_master.MASTER_ID from tash_ahkam INNER JOIN ahkam_master ON ahkam_master.id = tash_ahkam.hkm_id WHERE ahkam_master.details LIKE '%" + Str + "%' group by ahkam_master.details limit 5";
        }
        res = mDatabase.rawQuery(sqlCheack2, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {

            String titel = res.getString(res.getColumnIndex("titel"));
            String details = res.getString(res.getColumnIndex("details"));
            String Master = res.getString(res.getColumnIndex("MASTER_ID"));
            String name_ = res.getString(res.getColumnIndex("Master_name"));
            String Tash_name = res.getString(res.getColumnIndex("Tash_name"));

            String info = " الاحكام المرتبطة بـ" + Tash_name;
            arrayList.add(new Master_Stract(titel, details, info, "", "", "", "نص", name_));
            res.moveToNext();
        }


        try {

            res.close();
            closeDatabase();

        } catch (Exception e) {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();

            res.close();
            closeDatabase();

        } finally {

            res.close();
            closeDatabase();
        }
        return arrayList;

    }

    public ArrayList searchQiods(String Word) {
        ArrayList<Master_Stract> arrayList = new ArrayList<>();
        openDatabase();
        String convertword = arbicencodicSub(Word);
        String Str = convertword.replace(" ", "%");
        String sqlCheack = "";
        if (Var.Active) {
            sqlCheack = "select * from tb_niba_Topics WHERE text LIKE '%" + Str + "%' and T_id not like '11' and T_id not like '12' order by T_id";
        } else {
            sqlCheack = "select * from tb_niba_Topics WHERE text LIKE '%" + Str + "%' and T_id not like '11' and T_id not like '12' order by T_id limit 5";

        }


        Cursor res = mDatabase.rawQuery(sqlCheack, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {

            String titel = res.getString(res.getColumnIndex("titel"));
            String details = res.getString(res.getColumnIndex("text"));
            String name = res.getString(res.getColumnIndex("name_"));

            arrayList.add(new Master_Stract(titel, details, name, "", "", "", "نص", name));
            res.moveToNext();
        }
        try {

            res.close();
            closeDatabase();

        } catch (Exception e) {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();

            res.close();
            closeDatabase();

        } finally {

            res.close();
            closeDatabase();
        }
        return arrayList;

    }


    public ArrayList searchHithiat(String Word) {
        String convertword = arbicencodicSub(Word);
        String Str = convertword.replace(" ", "%");

        ArrayList<Master_Stract> arrayList = new ArrayList<>();
        openDatabase();
        String sqlCheack = "";
        if (Var.Active) {
            sqlCheack = " select * from Hithiat_law WHERE details LIKE '%" + Str + "%' group by details order by xtopic_name";
        } else {
            sqlCheack = " select * from Hithiat_law WHERE details LIKE '%" + Str + "%' group by details order by xtopic_name limit 5";

        }
        Cursor res;
        res = mDatabase.rawQuery(sqlCheack, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {

            String titel = res.getString(res.getColumnIndex("titel"));
            String details = res.getString(res.getColumnIndex("details"));
            String type_id = res.getString(res.getColumnIndex("type_id"));
            String name_ = res.getString(res.getColumnIndex("type_name"));
            String topic_name = res.getString(res.getColumnIndex("xtopic_name"));
            String info = name_ + ":::" + topic_name;
            arrayList.add(new Master_Stract(titel, details, info, "", "", "", "نص", info));
            res.moveToNext();
        }

        String sqlCheack1 = "";
        if (Var.Active) {
            sqlCheack1 = " select * from Hithiat_nkd WHERE details LIKE '%" + Str + "%' group by details order by xtopic_name";
        } else {
            sqlCheack1 = " select * from Hithiat_nkd WHERE details LIKE '%" + Str + "%' group by details order by xtopic_name limit 5";

        }

        res = mDatabase.rawQuery(sqlCheack1, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {

            String titel = res.getString(res.getColumnIndex("titel"));
            String details = res.getString(res.getColumnIndex("details"));
            String type_id = res.getString(res.getColumnIndex("type_id"));
            String name_ = res.getString(res.getColumnIndex("type_name"));
            String topic_name = res.getString(res.getColumnIndex("xtopic_name"));
            String info = name_ + ":::" + topic_name;
            arrayList.add(new Master_Stract(titel, details, info, "", "", "", "نص", info));

            res.moveToNext();
        }


        String sqlCheack2 = "";
        if (Var.Active) {
            sqlCheack2 = "select * from Hithiat WHERE topic_name LIKE '%" + Str + "%'";
        } else {
            sqlCheack2 = "select * from Hithiat WHERE topic_name LIKE '%" + Str + "%' limit 5";

        }
        res = mDatabase.rawQuery(sqlCheack2, null);
        res.moveToFirst();
        String name_ = "";
        while (!res.isAfterLast()) {

            String titel = res.getString(res.getColumnIndex("topic_name"));
            String _id = res.getString(res.getColumnIndex("x_id"));
            String Master = res.getString(res.getColumnIndex("type_id"));


            if (Master.contains("2")) {
                name_ = "الحيثيات الجنائية";
            } else if (Master.contains("1")) {
                name_ = "الحيثيات المدنية";

            }
            arrayList.add(new Master_Stract(titel, _id, name_, Master, "", "", "حيثيات", name_));
            res.moveToNext();
        }
        String sqlCheack3 = "";
        if (Var.Active) {
            sqlCheack3 = "select * from tb_niba_Topics WHERE text LIKE '%" + Str + "%' and T_id in (11,12) order by T_id";
        } else {
            sqlCheack3 = "select * from tb_niba_Topics WHERE text LIKE '%" + Str + "%' and T_id in (11,12) order by T_id limit 5";

        }

        res = mDatabase.rawQuery(sqlCheack3, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {


            String titel = res.getString(res.getColumnIndex("titel"));
            String details = res.getString(res.getColumnIndex("text"));
            String name = res.getString(res.getColumnIndex("name_"));


            arrayList.add(new Master_Stract(titel, details, name, "", "", "", "نص", name));
            res.moveToNext();
        }


        try {

            res.close();
            closeDatabase();

        } catch (Exception e) {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();

            res.close();
            closeDatabase();

        } finally {

            res.close();
            closeDatabase();
        }
        return arrayList;

    }

    public ArrayList<Master_Stract> searchListQiods(String Word) {
        ArrayList<Master_Stract> arrayList = new ArrayList<>();
        openDatabase();
        String convertword = arbicencodicSub(Word);
        String Str = convertword.replace(" ", "%");
        String sqlCheack = "";
        if (Var.Active) {
            sqlCheack = "SELECT    Niba_books.Name,Niba_books_Details.title,Niba_books_Details.details\n" +
                    "FROM        Niba_books_Details INNER JOIN\n" +
                    "                     Niba_books ON Niba_books_Details.Type_id =Niba_books.ID\n" +
                    "WHERE     (Niba_books_Details.details LIKE '%" + Word + "%') limit 5";
        } else {
            sqlCheack = "SELECT    Niba_books.Name,Niba_books_Details.title,Niba_books_Details.details\n" +
                    "FROM        Niba_books_Details INNER JOIN\n" +
                    "                     Niba_books ON Niba_books_Details.Type_id =Niba_books.ID\n" +
                    "WHERE     (Niba_books_Details.details LIKE '%" + Word + "%') ";

        }


        Cursor res = mDatabase.rawQuery(sqlCheack, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {

            String titel = res.getString(res.getColumnIndex("title"));
            String details = res.getString(res.getColumnIndex("details"));
            String name = res.getString(res.getColumnIndex("Name"));

            arrayList.add(new Master_Stract(titel, details, name, "", "", "", "نص", name));
            res.moveToNext();
        }
        try {

            res.close();
            closeDatabase();

        } catch (Exception e) {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();

            res.close();
            closeDatabase();

        } finally {

            res.close();
            closeDatabase();
        }
        return arrayList;

    }

    public ArrayList<Master_Stract> searchHithiatLocal(String Word) {
        String convertword = arbicencodicSub(Word);
        String Str = convertword.replace(" ", "%");

        ArrayList<Master_Stract> arrayList = new ArrayList<>();
        openDatabase();
        String sqlCheack = "";
        if (Var.Active) {
            sqlCheack = "SELECT      Hithiat_books.id,  Hithiat_books.name,  Hithiat_books_Topics.Name AS Titel,  Hithiat_books_Topics.Type_id\n" +
                    "FROM          Hithiat_books_Topics INNER JOIN\n" +
                    "                       Hithiat_books ON  Hithiat_books_Topics.Type_id =  Hithiat_books.id\n" +
                    "WHERE     ( Hithiat_books_Topics.Name LIKE '%" + Str + "%') ORDER BY Hithiat_books_Topics.Type_id";
        } else {
            sqlCheack = "SELECT      Hithiat_books.id,  Hithiat_books.name,  Hithiat_books_Topics.Name AS Titel,  Hithiat_books_Topics.Type_id\n" +
                    "FROM          Hithiat_books_Topics INNER JOIN\n" +
                    "                       Hithiat_books ON  Hithiat_books_Topics.Type_id =  Hithiat_books.id\n" +
                    "WHERE     ( Hithiat_books_Topics.Name LIKE '%" + Str + "%')  ORDER BY Hithiat_books_Topics.Type_id limit 5";

        }
        Cursor res;
        res = mDatabase.rawQuery(sqlCheack, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {

            String name = res.getString(res.getColumnIndex("name"));
            String Titel = res.getString(res.getColumnIndex("Titel"));
            String Type_id = res.getString(res.getColumnIndex("Type_id"));

            arrayList.add(new Master_Stract(name, Titel, Type_id, "", "", "", "",""));
            res.moveToNext();
        }
        return arrayList;
    }

}






