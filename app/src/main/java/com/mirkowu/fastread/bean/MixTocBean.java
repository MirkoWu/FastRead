package com.mirkowu.fastread.bean;

import java.util.List;

/**
 * @author by DELL
 * @date on 2018/5/11
 * @describe
 */
public  class MixTocBean {
    /**
     * _id : 572072a2e3ee1dcc0accdb9a
     * book : 57206c3539a913ad65d35c7b
     * chaptersCount1 : 288
     * chaptersUpdated : 2018-02-09T16:07:19.051Z
     * chapters : [{"title":"第1章 他叫白小纯","link":"http://book.my716.com/getBooks.aspx?method=content&bookId=1127281&chapterFile=U_1127281_201710100931202198_6433_1.txt","unreadble":false},{"title":"第2章 火灶房","link":"http://book.my716.com/getBooks.aspx?method=content&bookId=1127281&chapterFile=U_1127281_201802091821174355_0248_1314.txt","unreadble":false}]
     * updated : 2018-02-15T06:49:30.258Z
     */

    private String _id;
    private String book;
    private int chaptersCount1;
    private String chaptersUpdated;
    private String updated;
    private List<ChapterBean> chapters;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public int getChaptersCount1() {
        return chaptersCount1;
    }

    public void setChaptersCount1(int chaptersCount1) {
        this.chaptersCount1 = chaptersCount1;
    }

    public String getChaptersUpdated() {
        return chaptersUpdated;
    }

    public void setChaptersUpdated(String chaptersUpdated) {
        this.chaptersUpdated = chaptersUpdated;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public List<ChapterBean> getChapters() {
        return chapters;
    }

    public void setChapters(List<ChapterBean> chapters) {
        this.chapters = chapters;
    }

}