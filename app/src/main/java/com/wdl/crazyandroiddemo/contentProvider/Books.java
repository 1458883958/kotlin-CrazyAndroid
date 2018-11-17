package com.wdl.crazyandroiddemo.contentProvider;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * author：   wdl
 * time： 2018/11/17 13:43
 * des：    TODO
 */
@SuppressWarnings("unused")
public class Books {
    //定义contentProvider 的Authorities值
    public static final String AUTHORITY = "org.wdl.book";

    //定义静态内部类，定义contentProvider所包含的数据列
    public static final class Book implements BaseColumns {
        //定义所允许操作的三个数据列
        public final static String ID = "id";
        public final static String NAME = "name";
        public final static String PRICE = "price";
        public final static String DATE = "publishdate";

        //定义提供服务的两个Uri
        public final static Uri BOOKS_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/books");
        public final static Uri BOOK_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/book");
    }
}
