package org.techtown.AndroidStudioAigoyak;


import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataAdapter
{
    protected static final String TAG = "DataAdapter";

    // TODO : TABLE 이름을 명시해야함
    protected static final String TABLE_WITH = "TABLE_WITH";//병용금기
    protected static final String TABLE_AGE = "TABLE_AGE";//연령금기
    protected static final String TABLE_PREG = "TABLE_PREG";//임부금기
    protected static final String TABLE_OLD = "TABLE_OLD";//노인주의
    protected static final String TABLE_AMOUNT = "TABLE_AMOUNT";//용량주의
    protected static final String TABLE_PERIOD = "TABLE_PERIOD";//투여기간 주의
    protected static final String TABLE_INGR = "TABLE_INGR";//성분

    private final Context mContext;
    private SQLiteDatabase mDb;
    private DataBaseHelper mDbHelper;

    public DataAdapter(Context context)
    {
        this.mContext = context;
        mDbHelper = new DataBaseHelper(mContext);
    }

    public DataAdapter createDatabase() throws SQLException
    {
        try
        {
            mDbHelper.createDataBase();
        }
        catch (IOException mIOException)
        {
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }

    public DataAdapter open() throws SQLException
    {
        try
        {
            mDbHelper.openDataBase();
            mDbHelper.close();
            mDb = mDbHelper.getReadableDatabase();
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "open >>"+ mSQLException.toString());
            throw mSQLException;
        }
        return this;
    }

    public void close()
    {
        mDbHelper.close();
    }

    public List getTableData1(String code)
    {
        try
        {
            // Table 이름 -> antpool_bitcoin 불러오기
            String sql ="SELECT * FROM " + TABLE_WITH + " WHERE code_a = '" + code + "'";

            // 모델 넣을 리스트 생성
            List resultList = new ArrayList();//최종 돌려줄 list

            Dur3 dur3 =null;//sql 사용
            //TABLE_WITH
            Cursor cursor1 = mDb.rawQuery(sql, null);
            if (cursor1.getCount()!=0)
            {
                while( cursor1.moveToNext() ) {
                    dur3 = new Dur3();
                    // code, code2, code2_name, content
                    dur3.setCode(cursor1.getString(0));
                    dur3.setCode2(cursor1.getString(1));
                    dur3.setCodeName(cursor1.getString(2));
                    dur3.setContent(cursor1.getString(3));

                    // 리스트에 넣기
                    resultList.add(dur3);
                }

            }
            else{
                dur3 = new Dur3();
                // code, code2, content
                dur3.setCode("(없음)");
                dur3.setCode2("(없음)");
                dur3.setCodeName("(없음)");
                dur3.setContent("(없음)");
                // 리스트에 넣기
                resultList.add(dur3);
            }
            cursor1.close();
            return resultList;
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }

    public List getTableData2(String code)
    {
        try
        {
            // Table 이름 -> antpool_bitcoin 불러오기
            String sql2 = "SELECT * FROM " + TABLE_AGE + " WHERE code = '" + code + "'";


            // 모델 넣을 리스트 생성
            List resultList = new ArrayList();//최종 돌려줄 list

            Dur5 dur5 =null;//sql2 사용
            //TABLE_AGE
            Cursor cursor2 = mDb.rawQuery(sql2, null);
            if (cursor2.getCount()!=0)
            {
                while( cursor2.moveToNext() ) {
                    dur5 = new Dur5();
                    // code, limit_nm, limit, content
                    dur5.setCode(cursor2.getString(0));
                    dur5.setLimit_nm(cursor2.getString(1));
                    dur5.setUnit(cursor2.getString(2));
                    dur5.setStandard(cursor2.getString(3));
                    dur5.setContent(cursor2.getString(4));
                    // 리스트에 넣기
                    resultList.add(dur5);
                }
            }
            else{
                dur5 = new Dur5();
                // code, limit_nm, limit, content
                dur5.setCode("(없음)");
                dur5.setLimit_nm("(없음)");
                dur5.setUnit("(없음)");
                dur5.setStandard("(없음)");
                dur5.setContent("(없음)");
                // 리스트에 넣기
                resultList.add(dur5);
            }
            cursor2.close();
            return resultList;
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }
    public List getTableData3(String code)
    {
        try
        {
            // Table 이름 -> antpool_bitcoin 불러오기
            String sql3 = "SELECT * FROM " + TABLE_PREG + " WHERE code = '" + code +"'";
            String sql4 = "SELECT * FROM " + TABLE_OLD + " WHERE code = '" + code +"'";
            String sql5 = "SELECT * FROM " + TABLE_AMOUNT + " WHERE code = '" + code+"'";
            String sql6 = "SELECT * FROM " + TABLE_PERIOD + " WHERE code = '" + code+"'";

            // 모델 넣을 리스트 생성
            List resultList = new ArrayList();//최종 돌려줄 list

            Dur2 dur2 =null;//sql3,4,5,6 사용

            //TABLE_PREG
            Cursor cursor3 = mDb.rawQuery(sql3, null);
            if (cursor3.getCount()!=0)
            {
                while( cursor3.moveToNext() ) {
                    dur2 = new Dur2();
                    // code, content
                    dur2.setCode(cursor3.getString(0));
                    if(cursor3.getString(1).equals("-")){
                        dur2.setContent("임부 복용이 금기되는 의약품입니다.");

                    }
                    else {
                        dur2.setContent(cursor3.getString(1));
                    }
                    // 리스트에 넣기
                    resultList.add(dur2);
                }
            }
            else{
                dur2 = new Dur2();
                // code, content
                dur2.setCode("(없음)");
                dur2.setContent("(없음)");
                // 리스트에 넣기
                resultList.add(dur2);
            }
            cursor3.close();
            dur2 =null;
            //TABLE_OLD
            Cursor cursor4 = mDb.rawQuery(sql4, null);
            if (cursor4.getCount()!=0)
            {
                while( cursor4.moveToNext() ) {

                    dur2 = new Dur2();
                    // code, content
                    dur2.setCode(cursor4.getString(0));
                    dur2.setContent(cursor4.getString(1));
                    // 리스트에 넣기
                    resultList.add(dur2);
                }
            }
            else{
                dur2 = new Dur2();
                // code, content
                dur2.setCode("(없음)");
                dur2.setContent("(없음)");
                // 리스트에 넣기
                resultList.add(dur2);
            }
            cursor4.close();
            dur2 =null;
            //TABLE_AMOUNT
            Cursor cursor5 = mDb.rawQuery(sql5, null);
            if (cursor5.getCount()!=0)
            {
                while( cursor5.moveToNext() ) {

                    dur2 = new Dur2();
                    // code, content
                    dur2.setCode(cursor5.getString(0));
                    dur2.setContent(cursor5.getString(1));
                    // 리스트에 넣기
                    resultList.add(dur2);
                }
            }
            else{
                dur2 = new Dur2();
                // code, content
                dur2.setCode("(없음)");
                dur2.setContent("(없음)");
                // 리스트에 넣기
                resultList.add(dur2);
            }
            cursor5.close();
            dur2 =null;
            //TABLE_PERIOD
            Cursor cursor6 = mDb.rawQuery(sql6, null);
            if (cursor6.getCount()!=0)
            {
                while( cursor6.moveToNext() ) {
                    dur2 = new Dur2();
                    // code, content
                    dur2.setCode(cursor6.getString(0));
                    dur2.setContent(cursor6.getString(1));
                    // 리스트에 넣기
                    resultList.add(dur2);
                }
            }
            else{
                dur2 = new Dur2();
                // code, content
                dur2.setCode("(없음)");
                dur2.setContent("(없음)");
                // 리스트에 넣기
                resultList.add(dur2);
            }
            cursor6.close();
            return resultList;
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }
    public List getTableData4(String code)
    {
        try
        {
            String sql7 = "SELECT * FROM " + TABLE_INGR + " WHERE code = '" + code + "'";

            List resultList = new ArrayList();//최종 돌려줄 list

            Ingredient ingredient =null;
            //TABLE_INGR
            Cursor cursor7 = mDb.rawQuery(sql7, null);
            if (cursor7.getCount()!=0)
            {
                while( cursor7.moveToNext() ) {
                    ingredient = new Ingredient();
                    // code, name, corp, ingr, add, addwarn
                    ingredient.setCode(cursor7.getString(0));
                    ingredient.setName(cursor7.getString(1));
                    ingredient.setCorp(cursor7.getString(2));
                    ingredient.setIngr(cursor7.getString(3));
                    ingredient.setAdd(cursor7.getString(4));
                    ingredient.setAddWarn(cursor7.getString(5));


                    // 리스트에 넣기
                    resultList.add(ingredient);
                }
            }
            else{
                ingredient = new Ingredient();
                // code, ingr, add, url
                ingredient.setCode("(없음)");
                ingredient.setName("(없음)");
                ingredient.setCorp("(없음)");
                ingredient.setIngr("(없음)");
                ingredient.setAdd("(없음)");
                ingredient.setAddWarn("(없음)");

                // 리스트에 넣기
                resultList.add(ingredient);

            }
            cursor7.close();
            return resultList;
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }
    public ArrayList ingrSearch(String text)//나중에 바꿔야 함.
    {
        try
        {
            String sql7 = "SELECT * FROM " + TABLE_INGR;

            ArrayList<Search> resultList = new ArrayList<Search>();//최종 돌려줄 list

            //TABLE_INGR
            Cursor cursor7 = mDb.rawQuery(sql7, null);
            if (cursor7.getCount()!=0)
            {
                int i=0;
                while( cursor7.moveToNext() ) {

                    if(cursor7.getString(3) !=null){
                         if(cursor7.getString(3).contains(text)){//검색 결과가 ingr에 포함될 때
                            resultList.add(new Search(i, cursor7.getString(1), cursor7.getString(2), cursor7.getString(0)));//id, name, corp, code
                            i++;
                      }
                   }
                }
            }
            cursor7.close();
            return resultList;
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }
    public Cursor rawQuery(String SQL) {

        Cursor c1 = null;
        try {
            c1 = mDb.rawQuery(SQL, null);
        } catch(Exception ex) {
            Log.e(TAG, "Exception in executeQuery", ex);
        }

        return c1;
    }
}