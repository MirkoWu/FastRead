package com.mirkowu.fastread.bean;

public class BookChaptersBean {

    /**
     * mixToc : {"_id":"572072a2e3ee1dcc0accdb9a","book":"57206c3539a913ad65d35c7b","chaptersCount1":288,"chaptersUpdated":"2018-02-09T16:07:19.051Z","chapters":[{"title":"第1章 他叫白小纯","link":"http://book.my716.com/getBooks.aspx?method=content&bookId=1127281&chapterFile=U_1127281_201710100931202198_6433_1.txt","unreadble":false},{"title":"第2章 火灶房","link":"http://book.my716.com/getBooks.aspx?method=content&bookId=1127281&chapterFile=U_1127281_201802091821174355_0248_1314.txt","unreadble":false}],"updated":"2018-02-15T06:49:30.258Z"}
     * ok : true
     */

    private MixTocBean mixToc;
    private boolean ok;

    public MixTocBean getMixToc() {
        return mixToc;
    }

    public void setMixToc(MixTocBean mixToc) {
        this.mixToc = mixToc;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }


}
