package com.mirkowu.fastread.api;


import com.mirkowu.fastread.bean.AutoCompleteBean;
import com.mirkowu.fastread.bean.BookChaptersBean;
import com.mirkowu.fastread.bean.BookDetailBean;
import com.mirkowu.fastread.bean.ContentBean;
import com.mirkowu.fastread.bean.FromBean;
import com.mirkowu.fastread.bean.SearchBookBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface BookService {

    /**
     * 搜索
     *
     * @param query query:关键词
     * @param start start:结果开始位置
     * @param limit limit:结果最大数量
     * @return
     */
    @GET(HostUrl.BOOK_SEARCH)
    Observable<SearchBookBean> search(@Query("query") String query, @Query("start") int start, @Query("limit") int limit);

    /**
     * 书籍名称自动补全
     *
     * @param query 搜索的内容
     * @return
     */
    @GET(HostUrl.BOOK_AUTO_COMPLETE)
    Observable<AutoCompleteBean> autoComplete(@Query("query") String query);

    /**
     * 详情
     *
     * @param bookId
     * @return
     */
    @GET(HostUrl.BOOK_DETAIL)
    Observable<BookDetailBean> detail(@Path("bookId") String bookId);

    /**
     * 获取书籍章节目录
     *
     * @param bookId
     * @return
     */
    @GET(HostUrl.BOOK_CHAPTERS)
    Observable<BookChaptersBean> bookChapters(@Path("bookId") String bookId);

    /**
     * 书籍来源 （优质书源已被加密，不建议使用！）
     *
     * @param view 固定值 summary
     * @param book 书籍id
     * @return
     */
    @GET(HostUrl.BOOK_FROM)
    Observable<List<FromBean>> from(@Query("view") String view, @Query("book") String book);


    /**
     * 章节内容
     *
     * @param link 章节链接
     * @return
     */
    @GET(HostUrl.BOOK_CONTENT)
    Observable<ContentBean> chapterContent( @Path("link") String link/*, @Query("k") String k, @Query("t") String t*/);

}
