package com.mirkowu.fastread.api;

/**
 * @author by DELL
 * @date on 2017/12/22
 * @describe
 */

public interface HostUrl {
    /**
     * app接口Host
     */
    String HOST_URL = "http://api.zhuishushenqi.com/";
    String IMG_BASE_URL = "http://statics.zhuishushenqi.com";
    /*** 模块 */
    String BOOK_SEARCH = "book/fuzzy-search";//搜索书籍?query=一念&start=0&limit=2
    String BOOK_AUTO_COMPLETE = "book/auto-complete";//书籍名称自动补全
    String BOOK_DETAIL = "book/{bookId}";//书籍详情
    String BOOK_CHAPTERS = "mix-atoc/{bookId}?view=chapters";//书籍章节列表
    String BOOK_CONTENT = "http://chapter2.zhuishushenqi.com/chapter/{link}";//书籍内容 ?k=2124b73d7e2e1945&t=1468223717
    //  String BOOK_CHAPTERS = "mix-toc/{bookId}";//书籍章节列表
    String BOOK_FROM = "toc";//书籍来源 ?view=summary&book=57206c3539a913ad65d35c7b
}
