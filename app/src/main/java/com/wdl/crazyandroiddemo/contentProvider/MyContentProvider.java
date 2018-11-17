package com.wdl.crazyandroiddemo.contentProvider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.wdl.crazyandroiddemo.MySQLiteHelper;

import java.util.Objects;

public class MyContentProvider extends ContentProvider {
    private MySQLiteHelper helper;
    private static UriMatcher matcher;
    private static final int BOOKS = 1;
    private static final int BOOK = 2;

    static {
        matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(Books.AUTHORITY, "books", MyContentProvider.BOOKS);
        matcher.addURI(Books.AUTHORITY, "book/#", MyContentProvider.BOOK);
    }

    public MyContentProvider() {
    }

    @Override
    public boolean onCreate() {
        Log.e("wdl", "----------onCreate----------");
        helper = new MySQLiteHelper(getContext(), "demo.db", null, 3);
        return true;
    }

    @Override
    public int delete(@NonNull Uri uri, String where, String[] whereArgs) {
        Log.e("wdl", "----------delete----------");
        SQLiteDatabase db = helper.getReadableDatabase();
        int num;
        switch (matcher.match(uri)) {
            case BOOKS:
                num = db.delete("book", where, whereArgs);
                break;
            case BOOK:
                long id = ContentUris.parseId(uri);
                String whereClause = Books.Book.ID + "=" + id;
                if (!TextUtils.isEmpty(where)) {
                    whereClause = whereClause + " and " + where;
                }
                num = db.delete("book", whereClause, whereArgs);
                break;
            default:
                throw new IllegalArgumentException("未知uri");
        }
        Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
        return num;
    }

    @Override
    public String getType(@NonNull Uri uri) {
        Log.e("wdl", "----------getType----------");
        switch (matcher.match(uri)) {
            case BOOKS:
                return "vnd.android.cursor.dir/wdl.books";
            case BOOK:
                return "vnd.android.cursor.item/wdl.book";
            default:
                throw new IllegalArgumentException("未知uri");
        }

    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        Log.e("wdl", "----------insert----------");
        //获取数据库实例
        SQLiteDatabase db = helper.getReadableDatabase();
        switch (matcher.match(uri)) {
            case BOOKS:
                //插入
                long rowId = db.insert("book", Books.Book.ID, values);
                if (rowId > 0) {
                    //uri末尾添加id
                    Uri bookUri = ContentUris.withAppendedId(uri, rowId);
                    //通知数据已经改变
                    Objects.requireNonNull(getContext()).getContentResolver().notifyChange(bookUri, null);
                    return bookUri;
                }
                break;
            default:
                throw new IllegalArgumentException("Not yet implemented");
        }
        return null;
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Log.e("wdl", "----------query----------");
        SQLiteDatabase db = helper.getReadableDatabase();
        switch (matcher.match(uri)) {
            case BOOKS:
                return db.query("book",
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null, sortOrder);
            case BOOK:
                long id = ContentUris.parseId(uri);
                String whereClause = Books.Book.ID + "=" + id;
                if (!TextUtils.isEmpty(selection)) {
                    whereClause = whereClause + " and " + selection;
                }
                return db.query("book", projection, whereClause, selectionArgs, null, null, sortOrder);
            default:
                throw new IllegalArgumentException("未知uri");
        }
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        Log.e("wdl", "----------update----------");
        SQLiteDatabase db = helper.getReadableDatabase();
        int num = 0;
        switch (matcher.match(uri)) {
            case BOOKS:
                num = db.update("book", values, selection, selectionArgs);
                break;
            case BOOK:
                long id = ContentUris.parseId(uri);
                String whereClause = Books.Book.ID + "=" + id;
                if (!TextUtils.isEmpty(selection)) {
                    whereClause = whereClause + " and " + selection;
                }
                num = db.update("book", values, whereClause, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("未知uri");
        }
        Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
        return num;
    }


}
