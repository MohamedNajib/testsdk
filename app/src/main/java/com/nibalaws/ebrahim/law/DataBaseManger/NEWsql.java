//package com.nibalaws.ebrahim.law.DataBaseManger;
//
//import android.database.Cursor;
//import android.widget.Toast;
//
//import java.util.ArrayList;
//
//public class NEWsql {
//
//    //  	    new tash_path = "https://www.niaba-laws.com/Images/Index?path=file&folder=folder_&T=1" ;
//    //
//    //    # note : replace "folder_" by  type_id coulmn , replace "file" by path coulmn
//    //
//
//
//
//    //    new ahkam_path ="https://www.niaba-laws.com/Images/Index?path=file&folder=folder_&T=2/" ;
//
//    //    # note : replace "folder_" by  type_id coulmn , replace "file" by path coulmn
//
//
//
//    //   Ios FUNC getQiods
//
//
//
//
//
//    public ArrayList Ahkam_mortbtaMda( String  mda_id) {
//
//        ArrayList<Master_Stract> arrayList = new ArrayList<>();
//        openDatabase();
//        String sqlCheack = "" ;
//        if (Var.Active) {
//            sqlCheack = "SELECT     ' الطعن رقم '  ||  Ahkam_master.C_No  ||  ' سنة ' ||    Ahkam_master.C_Year ||  ' بتاريخ  '  ||   Ahkam_master.date_galsa as hkm_title , Ahkam_master.hkm_mbda as hkm_details, Ahkam_master.path, \n" +
//                    "                                         Ahkam_master.MASTER_ID, Ahkam_types.Name as Hkm_type\n" +
//                    "                    FROM         Tash_Ahkam INNER JOIN\n" +
//                    "                                         Ahkam_master ON Tash_Ahkam.hkm_id = Ahkam_master.ID_ INNER JOIN\n" +
//                    "                                         Ahkam_types ON Ahkam_master.MASTER_ID = Ahkam_types.ID\n" +
//                    "                    WHERE     (Tash_Ahkam.mda_id = " + mda_id + " )  group by hkm_id limit 50 ;";
//        }else{
//            sqlCheack = "SELECT     ' الطعن رقم '  ||  Ahkam_master.C_No  ||  ' سنة ' ||    Ahkam_master.C_Year ||  ' بتاريخ  '  ||   Ahkam_master.date_galsa as hkm_title , Ahkam_master.hkm_mbda as hkm_details, Ahkam_master.path, \n" +
//                    "                                         Ahkam_master.MASTER_ID, Ahkam_types.Name as Hkm_type\n" +
//                    "                    FROM         Tash_Ahkam INNER JOIN\n" +
//                    "                                         Ahkam_master ON Tash_Ahkam.hkm_id = Ahkam_master.ID_ INNER JOIN\n" +
//                    "                                         Ahkam_types ON Ahkam_master.MASTER_ID = Ahkam_types.ID\n" +
//                    "                    WHERE     (Tash_Ahkam.mda_id = " + mda_id + " )  group by hkm_id limit 5 ;";
//
//        }
//        Cursor res = mDatabase.rawQuery(sqlCheack, null);
//        res.moveToFirst();
//        while (!res.isAfterLast()) {
//            String hkm_title = res.getString(res.getColumnIndex("hkm_title"));
//            String hkm_details = res.getString(res.getColumnIndex("hkm_details"));
//            String path = res.getString(res.getColumnIndex("path"));
//            String Hkm_type = res.getString(res.getColumnIndex("Hkm_type"));
//            arrayList.add(new Master_Stract(hkm_title,hkm_details,null,null,null,null,path,Hkm_type));
//            res.moveToNext();
//        }
//
//        // Toast.makeText(mContext, String.valueOf(res.getCount()), Toast.LENGTH_SHORT).show();
//
//        try  {
//
//            res.close();
//            closeDatabase();
//
//        } catch (Exception e) {
//            //  Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
//
//            res.close();
//            closeDatabase();
//
//        }finally{
//
//            res.close();
//            closeDatabase();
//        }
//        return arrayList;
//
//    }
//
//
//
//
//
//
//
//
//    public ArrayList getQiods(String  T_id) {
//        ArrayList<Master_Stract> arrayList = new ArrayList<>();
//        openDatabase();
//
//        String sqlCheack = "" ;
//
//        if (Var.Active) {
//            sqlCheack = "SELECT     Niba_books.ID, Niba_books_Details.title, Niba_books_Details.details, Niba_books.Name\n" +
//                    "FROM         Niba_books INNER JOIN\n" +
//                    "                      Niba_books_Details ON Niba_books.ID = Niba_books_Details.Type_id where Type_id =\" + T_id + \" ";
//        }else{
//            sqlCheack = "SELECT     Niba_books.ID, Niba_books_Details.title, Niba_books_Details.details, Niba_books.Name\n" +
//                    "FROM         Niba_books INNER JOIN\n" +
//                    "                      Niba_books_Details ON Niba_books.ID = Niba_books_Details.Type_id where Type_id =" + T_id + " limit 5";
//
//        }
//
//
//        Cursor res = mDatabase.rawQuery(sqlCheack, null);
//        res.moveToFirst();
//        while (!res.isAfterLast()) {
//            String item1 = res.getString(res.getColumnIndex("title"));
//            String item2 = res.getString(res.getColumnIndex("details"));
//            String name = res.getString(res.getColumnIndex("Name"));
//            arrayList.add(new Master_Stract(item1,item2,"","","","","",name));
//            res.moveToNext();
//        }
//        try  {
//
//            res.close();
//            closeDatabase();
//
//        } catch (Exception e) {
//            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
//
//            res.close();
//            closeDatabase();
//
//        }finally{
//
//            res.close();
//            closeDatabase();
//        }
//        return arrayList;
//
//    }
//
//    //   Ios FUNC getNaqdTopics
//    public ArrayList GetahkamTopic( String  Type_id) {
//        ArrayList<Master_Stract> arrayList = new ArrayList<>();
//        openDatabase();
//        String sqlCheack = "" ;
//        if (Var.Active) {
//            sqlCheack = "select * from Ahkam_Topics where T_id like '" + Type_id + "'";
//        }else{
//            sqlCheack = "select * from Ahkam_Topics where T_id like '" + Type_id + "' limit 5";
//        }
//        Cursor res = mDatabase.rawQuery(sqlCheack, null);
//        res.moveToFirst();
//        while (!res.isAfterLast()) {
//            String Topic_name = res.getString(res.getColumnIndex("Text"));
//            String hkm_type = res.getString(res.getColumnIndex("id"));
//            String T_id = res.getString(res.getColumnIndex("T_id"));
//
//            arrayList.add(new Master_Stract(Topic_name,hkm_type,"","","","","",T_id));
//            res.moveToNext();
//        }
//        try  {
//
//            res.close();
//            closeDatabase();
//
//        } catch (Exception e) {
//            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
//
//            res.close();
//            closeDatabase();
//
//        }finally{
//
//            res.close();
//            closeDatabase();
//        }
//        return arrayList;
//
//    }
//    //   Ios FUNC getNaqdYears
//    public ArrayList getahkamyear( String  Type_id) {
//        ArrayList<Master_Stract> arrayList = new ArrayList<>();
//        openDatabase();
//
//        String sqlCheack = "" ;
//        if (Var.Active) {
//            sqlCheack = "select * from Ahkam_type_years where type_id like '" + Type_id + "'";
//        }else{
//            sqlCheack = "select * from Ahkam_type_years where type_id like '" + Type_id + "' limit 5 ";
//        }
//
//        Cursor res = mDatabase.rawQuery(sqlCheack, null);
//        res.moveToFirst();
//        while (!res.isAfterLast()) {
//            String Topic_year = res.getString(res.getColumnIndex("H_Year"));
//            String hkm_type = res.getString(res.getColumnIndex("Type_id"));
//            arrayList.add(new Master_Stract(Topic_year,hkm_type,"","","","","",Topic_year));
//            res.moveToNext();
//        }
//        try  {
//
//            res.close();
//            closeDatabase();
//
//        } catch (Exception e) {
//            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
//
//            res.close();
//            closeDatabase();
//
//        }finally{
//
//            res.close();
//            closeDatabase();
//        }
//        return arrayList;
//
//    }
//    //   Ios FUNC getNaqdAhkam
//    public ArrayList getahkamBytopic( String  Topic_id,String  T_id) {
//        ArrayList<Master_Stract> arrayList = new ArrayList<>();
//        openDatabase();
//
//
//        String sqlCheack = "" ;
//
//        if (Var.Active) {
//
//            sqlCheack = "SELECT  ' الطعن رقم '  ||  Ahkam_master.C_No  ||  ' سنة ' ||    Ahkam_master.C_Year ||  ' بتاريخ  '  ||   Ahkam_master.date_galsa as titel   ,Ahkam_types.Name as Master_name,hkm_mbda  as details,master_id,path,Text as Topic_name\n" +
//                    "FROM         Ahkam_Topics INNER JOIN\n" +
//                    "                      Ahkam_Topics_Master ON Ahkam_Topics.id = Ahkam_Topics_Master.Topic_id INNER JOIN\n" +
//                    "                      Ahkam_master ON Ahkam_Topics_Master.ahkam_id = Ahkam_master.ID_ INNER JOIN\n" +
//                    "                      Ahkam_types ON Ahkam_master.MASTER_ID = Ahkam_types.ID\n" +
//                    "WHERE     (Ahkam_Topics_Master.Topic_id = " + Topic_id +  " and Ahkam_Topics.T_id = " + T_id +  " )\n";
//        }else{
//
//            sqlCheack = "SELECT  ' الطعن رقم '  ||  Ahkam_master.C_No  ||  ' سنة ' ||    Ahkam_master.C_Year ||  ' بتاريخ  '  ||   Ahkam_master.date_galsa as titel   ,Ahkam_types.Name as Master_name,hkm_mbda  as details,master_id,path,Text as Topic_name\n" +
//                    "FROM         Ahkam_Topics INNER JOIN\n" +
//                    "                      Ahkam_Topics_Master ON Ahkam_Topics.id = Ahkam_Topics_Master.Topic_id INNER JOIN\n" +
//                    "                      Ahkam_master ON Ahkam_Topics_Master.ahkam_id = Ahkam_master.ID_ INNER JOIN\n" +
//                    "                      Ahkam_types ON Ahkam_master.MASTER_ID = Ahkam_types.ID\n" +
//                    "WHERE     (Ahkam_Topics_Master.Topic_id = " + Topic_id +  " and Ahkam_Topics.T_id = " + T_id +  " )\n";
//
//        }
//
//
//        Cursor res = mDatabase.rawQuery(sqlCheack, null);
//        res.moveToFirst();
//        while (!res.isAfterLast()) {
//            String details = res.getString(res.getColumnIndex("details"));
//            String titel = res.getString(res.getColumnIndex("titel"));
//            String Topic_name_ = res.getString(res.getColumnIndex("Topic_name"));
//            String MASTER = res.getString(res.getColumnIndex("MASTER_ID"));
//            String path = res.getString(res.getColumnIndex("path"));
//            String name_ = res.getString(res.getColumnIndex("Master_name"));
//
//            arrayList.add(new Master_Stract(titel,details,Topic_name_,"","",MASTER,path,name_));
//            res.moveToNext();
//        }
//        try  {
//
//            res.close();
//            closeDatabase();
//
//        } catch (Exception e) {
//            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
//
//            res.close();
//            closeDatabase();
//
//        }finally{
//
//            res.close();
//            closeDatabase();
//        }
//        return arrayList;
//
//    }
//    //   Ios FUNC getNaqdAhkam
//    public ArrayList getahkamByyear( String  MASTER_ID , String hkm_Master_year) {
//        ArrayList<Master_Stract> arrayList = new ArrayList<>();
//        openDatabase();
//
//        String sqlCheack = "" ;
//
//        if (Var.Active) {
//            sqlCheack = "SELECT  ' الطعن رقم '  ||  Ahkam_master.C_No  ||  ' سنة ' ||    Ahkam_master.C_Year ||  ' بتاريخ  '  ||   Ahkam_master.date_galsa as titel   ,Ahkam_types.Name as Master_name,hkm_mbda  as details,MASTER_ID,path,C_D_Year\n" +
//                    "                    FROM        \n" +
//                    "                                          Ahkam_master INNER JOIN  \n" +
//                    "                                          Ahkam_types ON Ahkam_master.MASTER_ID = Ahkam_types.ID \n" +
//                    "                    WHERE     C_D_Year = " +  hkm_Master_year + " and master_id = " + MASTER_ID + "";
//        }else{
//            sqlCheack = "SELECT  ' الطعن رقم '  ||  Ahkam_master.C_No  ||  ' سنة ' ||    Ahkam_master.C_Year ||  ' بتاريخ  '  ||   Ahkam_master.date_galsa as titel   ,Ahkam_types.Name as Master_name,hkm_mbda  as details,MASTER_ID,path,C_D_Year\n" +
//                    "                    FROM        \n" +
//                    "                                          Ahkam_master INNER JOIN  \n" +
//                    "                                          Ahkam_types ON Ahkam_master.MASTER_ID = Ahkam_types.ID \n" +
//                    "                    WHERE     C_D_Year = " +  hkm_Master_year + " and master_id = " + MASTER_ID + " limit 5";
//        }
//
//
//
//        Cursor res = mDatabase.rawQuery(sqlCheack, null);
//        res.moveToFirst();
//        while (!res.isAfterLast()) {
//            String details = res.getString(res.getColumnIndex("details"));
//            String titel = res.getString(res.getColumnIndex("titel"));
//            String hkm_Master_year_ = res.getString(res.getColumnIndex("C_D_Year"));
//            String MASTER = res.getString(res.getColumnIndex("MASTER_ID"));
//            String path = res.getString(res.getColumnIndex("path"));
//            String name_ = res.getString(res.getColumnIndex("Master_name"));
//
//            arrayList.add(new Master_Stract(titel ,details,hkm_Master_year_,"","",MASTER,path,name_));
//            res.moveToNext();
//        }
//        try  {
//
//            res.close();
//            closeDatabase();
//
//        } catch (Exception e) {
//            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
//
//            res.close();
//            closeDatabase();
//
//        }finally{
//
//            res.close();
//            closeDatabase();
//        }
//        return arrayList;
//
//    }
//
//    //   Ios FUNC getHaisiats
//    public ArrayList Gethitiat(String  Type_id) {
//        ArrayList<Master_Stract> arrayList = new ArrayList<>();
//        openDatabase();
//
//        String sqlCheack = "" ;
//
//        if (Var.Active) {
//            sqlCheack = "SELECT    Hithiat_books.name as Master_name,Hithiat_books_Topics.Name AS  Topic_name ,Hithiat_books_Topics.ID,Type_id\n" +
//                    "FROM        Hithiat_books_Topics INNER JOIN\n" +
//                    "                     Hithiat_books ON Hithiat_books_Topics.Type_id =  Hithiat_books.id\n" +
//                    "WHERE     (Hithiat_books_Topics.Type_id = " + Type_id + ")";
//        }else{
//            sqlCheack = "SELECT    Hithiat_books.name as Master_name,Hithiat_books_Topics.Name AS  Topic_name ,Hithiat_books_Topics.ID,Type_id\n" +
//                    "FROM        Hithiat_books_Topics INNER JOIN\n" +
//                    "                     Hithiat_books ON Hithiat_books_Topics.Type_id =  Hithiat_books.id\n" +
//                    "WHERE     (Hithiat_books_Topics.Type_id = " + Type_id + ") limit 5";
//        }
//        Cursor res = mDatabase.rawQuery(sqlCheack, null);
//        res.moveToFirst();
//        while (!res.isAfterLast()) {
//            String type_id_ = res.getString(res.getColumnIndex("Type_id"));
//            String topic_name = res.getString(res.getColumnIndex("Topic_name"));
//            String id = res.getString(res.getColumnIndex("ID"));
//            arrayList.add(new Master_Stract(topic_name,type_id_,id,null,null,"","",topic_name));
//            res.moveToNext();
//        }
//        try  {
//
//            res.close();
//            closeDatabase();
//
//        } catch (Exception e) {
//            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
//
//            res.close();
//            closeDatabase();
//
//        }finally{
//
//            res.close();
//            closeDatabase();
//        }
//        return arrayList;
//
//    }
//    //   Ios FUNC  getHithiatLaw
//    public ArrayList Gethithitlaw( String  type_id , String topic_id) {
//        ArrayList<Master_Stract> arrayList = new ArrayList<>();
//        openDatabase();
//
//
//
//        String sqlCheack = "" ;
//        if (Var.Active) {
//            sqlCheack = "SELECT     Hithiat_books_Topics.Type_id, Hithiat_books_Topics.Name, Hithiat_books.name AS type_name, Hithiat_books_Laws.Title AS titel, Hithiat_books_Laws.notes AS details\n" +
//                    "FROM         Hithiat_books_Laws INNER JOIN\n" +
//                    "                      Hithiat_books_Topics ON Hithiat_books_Laws.Topic_id = Hithiat_books_Topics.ID INNER JOIN\n" +
//                    "                      Hithiat_books ON Hithiat_books_Topics.Type_id = Hithiat_books.id\n" +
//                    "WHERE     (Hithiat_books_Laws.Topic_id = " + topic_id + ") AND (Hithiat_books_Topics.Type_id = " + type_id + ")";
//        }else{
//            sqlCheack = "SELECT     Hithiat_books_Topics.Type_id, Hithiat_books_Topics.Name, Hithiat_books.name AS type_name, Hithiat_books_Laws.Title AS titel, Hithiat_books_Laws.notes AS details\n" +
//                    "FROM         Hithiat_books_Laws INNER JOIN\n" +
//                    "                      Hithiat_books_Topics ON Hithiat_books_Laws.Topic_id = Hithiat_books_Topics.ID INNER JOIN\n" +
//                    "                      Hithiat_books ON Hithiat_books_Topics.Type_id = Hithiat_books.id\n" +
//                    "WHERE     (Hithiat_books_Laws.Topic_id = " + topic_id + ") AND (Hithiat_books_Topics.Type_id = " + type_id + ") limit 5";
//
//        }
//        Cursor res = mDatabase.rawQuery(sqlCheack, null);
//        res.moveToFirst();
//        while (!res.isAfterLast()) {
//            String titel = res.getString(res.getColumnIndex("titel"));
//            String details = res.getString(res.getColumnIndex("details"));
//            String tash_name = "";
//            String mda_number = "";
//            String Master = res.getString(res.getColumnIndex("Type_id"));
//            String name_ = res.getString(res.getColumnIndex("type_name"));
//
//            arrayList.add(new Master_Stract(titel,details,tash_name,mda_number,null,"","",name_));
//
//
//            res.moveToNext();
//        }
//        try  {
//
//            res.close();
//            closeDatabase();
//
//        } catch (Exception e) {
//            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
//
//            res.close();
//            closeDatabase();
//
//        }finally{
//
//            res.close();
//            closeDatabase();
//        }
//        return arrayList;
//
//    }
//    //   Ios FUNC getHithiatAhkam
//    public ArrayList Gethithitnkd( String  type_id , String topic_id) {
//        ArrayList<Master_Stract> arrayList = new ArrayList<>();
//        openDatabase();
//        String sqlCheack = "" ;
//        if (Var.Active) {
//            sqlCheack = "SELECT     Hithiat_books.name, Hithiat_books_Ahkam.*\n" +
//                    "FROM         Hithiat_books INNER JOIN\n" +
//                    "                      Hithiat_books_Ahkam ON Hithiat_books.id = Hithiat_books_Ahkam.type_id\n" +
//                    "WHERE     (Hithiat_books_Ahkam.type_id = " + type_id + ") AND (Hithiat_books_Ahkam.Topic_id = " +  topic_id + ")";
//        }else{
//            sqlCheack = "SELECT     Hithiat_books.name, Hithiat_books_Ahkam.*\n" +
//                    "FROM         Hithiat_books INNER JOIN\n" +
//                    "                      Hithiat_books_Ahkam ON Hithiat_books.id = Hithiat_books_Ahkam.type_id\n" +
//                    "WHERE     (Hithiat_books_Ahkam.type_id = " + type_id + ") AND (Hithiat_books_Ahkam.Topic_id = " +  topic_id + ") limit 5";
//
//        }
//        Cursor res = mDatabase.rawQuery(sqlCheack, null);
//        res.moveToFirst();
//        while (!res.isAfterLast()) {
//            String titel = res.getString(res.getColumnIndex("Title"));
//            String details = res.getString(res.getColumnIndex("notes"));
//            String Master = res.getString(res.getColumnIndex("type_id"));
//            String name_ = res.getString(res.getColumnIndex("name"));
//
//            arrayList.add(new Master_Stract(titel,details,null,null,null,"","",name_));
//
//
//            res.moveToNext();
//
//        }
//        try  {
//
//            res.close();
//            closeDatabase();
//
//        } catch (Exception e) {
//            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
//
//            res.close();
//            closeDatabase();
//
//        }finally{
//
//            res.close();
//            closeDatabase();
//        }
//        return arrayList;
//
//    }
//
//
//    //   Ios FUNC getTashreaatDetailsByTashID
//    public ArrayList gettashinfo( String  Tash_id) {
//        ArrayList<Master_Stract> arrayList = new ArrayList<>();
//        openDatabase();
//
//        String sqlCheack = "" ;
//
//        if (Var.Active) {
//            sqlCheack = "SELECT     Tash_id, Type_ID, pic_path, T_name, T_Type || ' رقم ' || T_No ||   ' لسنة '  ||  T_Year  as T_Short_name,has_lai7a,has_edit,has_ahkam FROM Tash_master WHERE  Tash_id like '" + Tash_id  + "'";
//
//        }else{
//            sqlCheack = "SELECT     Tash_id, Type_ID, pic_path, T_name, T_Type || ' رقم ' || T_No ||   ' لسنة '  ||  T_Year  as T_Short_name,has_lai7a,has_edit,has_ahkam  FROM Tash_master WHERE  Tash_id like '" + Tash_id  + "' limit 5";
//
//        }
//
//        Cursor res = mDatabase.rawQuery(sqlCheack, null);
//        res.moveToFirst();
//        while (!res.isAfterLast()) {
//
//            String Tash_id_ = res.getString(res.getColumnIndex("Tash_id"));
//            String Type_ID = res.getString(res.getColumnIndex("Type_ID"));
//            String pic_path = res.getString(res.getColumnIndex("pic_path"));
//            String T_name = res.getString(res.getColumnIndex("T_name"));
//            String T_Short_name = res.getString(res.getColumnIndex("T_Short_name"));
//            String has_lai7a = res.getString(res.getColumnIndex("has_lai7a"));
//            String has_edit = res.getString(res.getColumnIndex("has_edit"));
//            String has_ahkam = res.getString(res.getColumnIndex("has_ahkam"));
//
//            arrayList.add(new Master_Stract(Tash_id_,Type_ID,pic_path,T_name,T_Short_name,has_lai7a,has_edit,has_ahkam));
//            res.moveToNext();
//        }
//        try  {
//
//            res.close();
//            closeDatabase();
//
//        } catch (Exception e) {
//            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
//
//            res.close();
//            closeDatabase();
//
//        }finally{
//
//            res.close();
//            closeDatabase();
//        }
//        return arrayList;
//
//    }
//    //    Ios FUNC getAllMawadByTashID
//    public ArrayList Gettashmowad( String  Tash_id) {
//        ArrayList<Master_Stract> arrayList = new ArrayList<>();
//        openDatabase();
//        String sqlCheack = "" ;
//        if (Var.Active) {
//            sqlCheack = "SELECT     Tash_mowad.has_ahkam, Tash_mowad.mda_id, Tash_mowad.titel, Tash_mowad.details, Tash_mowad.edit_txt, Tash_master.T_name\n" +
//                    "FROM         Tash_master INNER JOIN\n" +
//                    "                      Tash_mowad ON Tash_master.Tash_id = Tash_mowad.Tash_id\n" +
//                    "WHERE     (Tash_master.Tash_id = " + Tash_id + ") order by number";
//        }else{
//            sqlCheack = "SELECT     Tash_mowad.has_ahkam, Tash_mowad.mda_id, Tash_mowad.titel, Tash_mowad.details, Tash_mowad.edit_txt, Tash_master.T_name\n" +
//                    "FROM         Tash_master INNER JOIN\n" +
//                    "                      Tash_mowad ON Tash_master.Tash_id = Tash_mowad.Tash_id\n" +
//                    "WHERE     (Tash_master.Tash_id = " + Tash_id + ") order by number";
//
//        }
//        Cursor res = mDatabase.rawQuery(sqlCheack, null);
//        res.moveToFirst();
//        while (!res.isAfterLast()) {
//            String titel = res.getString(res.getColumnIndex("titel"));
//            String number = "";
//            String edit_txt = res.getString(res.getColumnIndex("edit_txt"));
//            String details =  res.getString(res.getColumnIndex("details"))  + "\n" + " "  + "\n"  + edit_txt;
//            String mda_id = res.getString(res.getColumnIndex("mda_id"));
//            String T_name = res.getString(res.getColumnIndex("T_name"));
//            arrayList.add(new Master_Stract(titel,details,edit_txt,number,mda_id,"","",T_name));
//            res.moveToNext();
//        }
//        try  {
//
//            res.close();
//            closeDatabase();
//
//        } catch (Exception e) {
//            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
//
//            res.close();
//            closeDatabase();
//
//        }finally{
//
//            res.close();
//            closeDatabase();
//        }
//        return arrayList;
//
//    }
//    //    Ios FUNC getAllLawaahByTashID
//    public ArrayList Gettashlai7a( String  Tash_id) {
//        ArrayList<Master_Stract> arrayList = new ArrayList<>();
//        openDatabase();
//        String sqlCheack = "" ;
//        if (Var.Active) {
//
//            sqlCheack = "SELECT       Tash_lai7a.Tash_id_Lai7a as Tash_id, T_Type || ' رقم ' || T_No ||   ' لسنة '  ||  T_Year  as T_Short_name\n" +
//                    "FROM         Tash_master INNER JOIN\n" +
//                    "                      Tash_lai7a ON Tash_master.Tash_id = Tash_lai7a.Tash_id_Lai7a\n" +
//                    "WHERE     (Tash_lai7a.Tash_id  like '" + Tash_id + "')  group by Tash_lai7a.Tash_id_Lai7a order by T_D_Year desc , T_No asc";
//        }else{
//            sqlCheack = "SELECT       Tash_lai7a.Tash_id_Lai7a as Tash_id, T_Type || ' رقم ' || T_No ||   ' لسنة '  ||  T_Year  as T_Short_name\n" +
//                    "FROM         Tash_master INNER JOIN\n" +
//                    "                      Tash_lai7a ON Tash_master.Tash_id = Tash_lai7a.Tash_id_Lai7a\n" +
//                    "WHERE     (Tash_lai7a.Tash_id  like '" + Tash_id + "')  group by Tash_lai7a.Tash_id_Lai7a order by T_D_Year desc , T_No asc limit 5";
//
//        }
//        Cursor res = mDatabase.rawQuery(sqlCheack, null);
//        res.moveToFirst();
//        while (!res.isAfterLast()) {
//            String Tash_id_ = res.getString(res.getColumnIndex("Tash_id"));
//            String T_Short_name = res.getString(res.getColumnIndex("T_Short_name"));
//
//
//            arrayList.add(new Master_Stract( Tash_id_,T_Short_name,"","" ,"","","",""));
//            res.moveToNext();
//        }
//        try  {
//
//            res.close();
//            closeDatabase();
//
//        } catch (Exception e) {
//            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
//
//            res.close();
//            closeDatabase();
//
//        }finally{
//
//            res.close();
//            closeDatabase();
//        }
//        return arrayList;
//
//    }
//    //    Ios FUNC getAllEditsByTashID
//    public ArrayList Gettashedit( String  Tash_id) {
//        ArrayList<Master_Stract> arrayList = new ArrayList<>();
//        openDatabase();
//        String sqlCheack = "" ;
//        if (Var.Active) {
//            sqlCheack = "SELECT       Tash_edit.Tash_id_edit as Tash_id, T_Type || ' رقم ' || T_No ||   ' لسنة '  ||  T_Year  as T_Short_name\n" +
//                    "FROM         Tash_master INNER JOIN\n" +
//                    "                      Tash_edit ON Tash_master.Tash_id = Tash_edit.Tash_id_edit\n" +
//                    "WHERE     (Tash_edit.Tash_id = " + Tash_id + ")  group by Tash_edit.Tash_id_edit order by T_D_Year desc , T_No asc";
//        }else{
//            sqlCheack = "SELECT       Tash_edit.Tash_id_edit as Tash_id, T_Type || ' رقم ' || T_No ||   ' لسنة '  ||  T_Year  as T_Short_name\n" +
//                    "FROM         Tash_master INNER JOIN\n" +
//                    "                      Tash_edit ON Tash_master.Tash_id = Tash_edit.Tash_id_edit\n" +
//                    "WHERE     (Tash_edit.Tash_id = " + Tash_id + ")  group by Tash_edit.Tash_id_edit order by T_D_Year desc , T_No asc limit 5";
//
//        }
//        Cursor res = mDatabase.rawQuery(sqlCheack, null);
//        res.moveToFirst();
//        while (!res.isAfterLast()) {
//            String Tash_id_ = res.getString(res.getColumnIndex("Tash_id"));
//            String T_Short_name = res.getString(res.getColumnIndex("T_Short_name"));
//
//
//            arrayList.add(new Master_Stract( Tash_id_,T_Short_name,"","" ,"","","",""));
//            res.moveToNext();
//        }
//        try  {
//            res.close();
//            closeDatabase();
//        } catch (Exception e) {
//            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
//            res.close();
//            closeDatabase();
//        }finally{
//            res.close();
//            closeDatabase();
//        }
//        return arrayList;
//    }
//    //    Ios FUNC getAllAhkamByTashID
//    public ArrayList Gettashahkam( String  Tash_id) {
//        ArrayList<Master_Stract> arrayList = new ArrayList<>();
//        openDatabase();
//        String sqlCheack = "" ;
//        if (Var.Active) {
//
//            sqlCheack = "SELECT     ' الطعن رقم '  ||  Ahkam_master.C_No  ||  ' سنة ' ||    Ahkam_master.C_Year ||  ' بتاريخ  '  ||   Ahkam_master.date_galsa as hkm_title , Ahkam_master.hkm_mbda as hkm_details, Ahkam_master.path, \n" +
//                    "                      Ahkam_master.MASTER_ID, Ahkam_types.Name as Hkm_type\n" +
//                    "FROM         Tash_Ahkam INNER JOIN\n" +
//                    "                      Ahkam_master ON Tash_Ahkam.hkm_id = Ahkam_master.ID_ INNER JOIN\n" +
//                    "                      Ahkam_types ON Ahkam_master.MASTER_ID = Ahkam_types.ID\n" +
//                    "WHERE     (Tash_Ahkam.Tash_id = " + Tash_id + ") limit 50 ";
//        }else{
//            sqlCheack = "SELECT     ' الطعن رقم '  ||  Ahkam_master.C_No  ||  ' سنة ' ||    Ahkam_master.C_Year ||  ' بتاريخ  '  ||   Ahkam_master.date_galsa as hkm_title , Ahkam_master.hkm_mbda as hkm_details, Ahkam_master.path, \n" +
//                    "                      Ahkam_master.MASTER_ID, Ahkam_types.Name as Hkm_type\n" +
//                    "FROM         Tash_Ahkam INNER JOIN\n" +
//                    "                      Ahkam_master ON Tash_Ahkam.hkm_id = Ahkam_master.ID_ INNER JOIN\n" +
//                    "                      Ahkam_types ON Ahkam_master.MASTER_ID = Ahkam_types.ID\n" +
//                    "WHERE     (Tash_Ahkam.Tash_id = " + Tash_id + ") limit 5 ";
//        }
//
//        Cursor res = mDatabase.rawQuery(sqlCheack, null);
//        res.moveToFirst();
//        while (!res.isAfterLast()) {
//            String hkm_title = res.getString(res.getColumnIndex("hkm_title"));
//            String hkm_details = res.getString(res.getColumnIndex("hkm_details"));
//            String path = res.getString(res.getColumnIndex("path"));
//            String Hkm_type = res.getString(res.getColumnIndex("Hkm_type"));
//            arrayList.add(new Master_Stract(hkm_title,hkm_details,null,null,null,null,path,Hkm_type));
//            res.moveToNext();
//        }
//        try  {
//            res.close();
//            closeDatabase();
//        } catch (Exception e) {
//            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
//            res.close();
//            closeDatabase();
//        }finally{
//            res.close();
//            closeDatabase();
//        }
//        return arrayList;
//    }
//    //    Ios FUNC getImportantashreaatSuccess
//    public ArrayList GetimportTash() {
//        ArrayList<Master_Stract> arrayList = new ArrayList<>();
//        openDatabase();
//        String sqlCheack = "" ;
//        if (Var.Active) {
//            sqlCheack = "select * from Tash_Import_List";
//        }else{
//            sqlCheack = "select * from Tash_Import_List  limit 5";
//
//        }
//        Cursor res = mDatabase.rawQuery(sqlCheack, null);
//        res.moveToFirst();
//        while (!res.isAfterLast()) {
//            String Tash_id = res.getString(res.getColumnIndex("Tash_id"));
//            String import_tash_name = res.getString(res.getColumnIndex("import_tash_name"));
//
//            arrayList.add(new Master_Stract(Tash_id,import_tash_name,"","","","","",""));
//            res.moveToNext();
//        }
//        try  {
//            res.close();
//            closeDatabase();
//        } catch (Exception e) {
//            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
//            res.close();
//            closeDatabase();
//        }finally{
//            res.close();
//            closeDatabase();
//        }
//        return arrayList;
//    }
//    //    Ios FUNC getAllTashreaatMainSectionSuccess
//    public ArrayList gettype() {
//        ArrayList<Master_Stract> arrayList = new ArrayList<>();
//        openDatabase();
//        String sqlCheack = "" ;
//        if (Var.Active) {
//            sqlCheack = "select * from tash_type  ORDER BY ID ASC  ";
//        }else{
//            sqlCheack = "select * from tash_type  ORDER BY ID ASC  limit 5";
//
//        }
//        Cursor res = mDatabase.rawQuery(sqlCheack, null);
//        res.moveToFirst();
//        while (!res.isAfterLast()) {
//            String name_ = res.getString(res.getColumnIndex("Name"));
//            String id = res.getString(res.getColumnIndex("ID"));
//
//            arrayList.add(new Master_Stract(name_,id,"","",null,"","",name_));
//            res.moveToNext();
//        }
//        try  {
//            res.close();
//            closeDatabase();
//        } catch (Exception e) {
//            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
//            res.close();
//            closeDatabase();
//        }finally{
//            res.close();
//            closeDatabase();
//        }
//        return arrayList;
//    }
//    //    Ios FUNC getAllTashreaatYearsById
//    public ArrayList gettypeyear(String  type_id) {
//        ArrayList<Master_Stract> arrayList = new ArrayList<>();
//        openDatabase();
//        String sqlCheack = "" ;
//        if (Var.Active) {
//            sqlCheack = "select * from Tash_Type_Years  where Type_id like '" + type_id + "' ORDER BY T_Year DESC ";
//        }else{
//            sqlCheack = "select * from Tash_Type_Years  where Type_id like '" + type_id + "' ORDER BY T_Year DESC limit 5";
//
//        }
//        Cursor res = mDatabase.rawQuery(sqlCheack, null);
//        res.moveToFirst();
//        while (!res.isAfterLast()) {
//            String T_Year = res.getString(res.getColumnIndex("T_Year"));
//            String Type_id = res.getString(res.getColumnIndex("Type_id"));
//            arrayList.add(new Master_Stract(T_Year,Type_id,null,null,null,"","",T_Year));
//            res.moveToNext();
//        }
//
//        try  {
//            res.close();
//            closeDatabase();
//        } catch (Exception e) {
//            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
//            res.close();
//            closeDatabase();
//        }finally{
//            res.close();
//            closeDatabase();
//        }
//        return arrayList;
//    }
//    //    Ios FUNC getTashreaatByYear
//    public ArrayList gettashTypeYear( String  typeid , String year) {
//        ArrayList<Master_Stract> arrayList = new ArrayList<>();
//        openDatabase();
//        String sqlCheack = "" ;
//        if (Var.Active) {
//            sqlCheack = " select Tash_id ,T_Type || ' رقم ' || T_No ||   ' لسنة '  ||  T_Year  as T_Short_name from Tash_master where Type_id = "  + typeid +  "  and T_Year = "  + year +  " order by T_No" ;
//
//        }else{
//            sqlCheack = " select Tash_id ,T_Type || ' رقم ' || T_No ||   ' لسنة '  ||  T_Year  as T_Short_name from Tash_master where Type_id = "  + typeid +  "  and T_Year = "  + year +  " order by T_No limit 5" ;
//
//        }
//        Cursor res = mDatabase.rawQuery(sqlCheack, null);
//        res.moveToFirst();
//        while (!res.isAfterLast()) {
//
//            String Tash_id = res.getString(res.getColumnIndex("Tash_id"));
//            String T_Short_name = res.getString(res.getColumnIndex("T_Short_name"));
//            arrayList.add(new Master_Stract(Tash_id,T_Short_name,"","","","","",""));
//            res.moveToNext();
//
//        }
//        try  {
//
//            res.close();
//            closeDatabase();
//
//        } catch (Exception e) {
//            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
//
//            res.close();
//            closeDatabase();
//
//        }finally{
//
//            res.close();
//            closeDatabase();
//        }
//        return arrayList;
//
//    }
//
//
//
//
//}
