package com.example.mobilproje;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import androidx.annotation.Nullable;

        import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="UserDB";
    private static final int DATABASE_VERSION=2;
    private static final String TABLE_USERS="Users";
    private static final String ID="id";
    private static final String USERNAME="username";
    private static final String PASSWORD="password";
    private static final String IMG="image";

    public Database(@Nullable Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_USERS + "("   //Users tablosunu ve alanlarını oluştur.
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " //ID'nin primary key olduğunu ve otomatik olarak 1er 1er aratacağını belirledik.
                + USERNAME + " TEXT NOT NULL, "
                + PASSWORD + " TEXT NOT NULL, "
                + IMG + " INTEGER NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { //Veritabanı varsa veritabanını silip yeniden oluştur.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public void AddUser(Users user){ //User ekleme.
        SQLiteDatabase db=this.getWritableDatabase(); //Veri tabanını yazmaya açtık.
        try{
            ContentValues cv=new ContentValues();
            cv.put(USERNAME,user.getUserName());
            cv.put(PASSWORD,user.getPassword());
            cv.put(IMG,user.getImageSource());
            db.insert(TABLE_USERS,null,cv); //tabloyu kaydettik.
        }catch (Exception e){

        }
        db.close();
    }

    public ArrayList<Users> GetUsers(){  //Kullanıcıları listelemek için arraylist kullandık.
        ArrayList<Users> UserList =new ArrayList<>();
        String query="Select * From "+ TABLE_USERS; //Users tablosundaki tüm kullanıcıları çektik.
        SQLiteDatabase db=this.getReadableDatabase(); //Veri tabanını okumaya açtık.
        Cursor cursor=db.rawQuery(query,null); //Sorguyu gönderip sonucu cursor'a attık.
        Users user=null;
        if(cursor.moveToFirst()){ //cursor listenin ilk üyesini kontrol etti.
            do {
                user=new Users();
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setUserName(cursor.getString(1));
                user.setPassword(cursor.getString(2));
                user.setImageSource(Integer.parseInt(cursor.getString(3)));
                UserList.add(user); //bilgileri listeye ekledik.
            }while (cursor.moveToNext()); //satır sonuna kadar dolaşıp listeye ekledik.
        }
        return UserList; //bittiğinde userlisti döndürdük.
    }

    public boolean login(String username,String password){ //Login kontrolü.
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * From Users where username=? and password=?",new String[]{username,password});
        if(cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }

    }


}


