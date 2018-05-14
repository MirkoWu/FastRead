package com.mirkowu.fastread.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author by DELL
 * @date on 2018/5/11
 * @describe
 */
public class SearchBookBean implements Serializable{


    /**
     * books : [{"_id":"57206c3539a913ad65d35c7b","hasCp":true,"title":"一念永恒","aliases":"","cat":"仙侠","author":"耳根","site":"zhuishuvip","cover":"/agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F1127281%2F_1127281_685974.jpg%2F","shortIntro":"一念成沧海，一念化桑田。一念斩千魔，一念诛万仙。\n    唯我念\u2026\u2026永恒\n    这是耳根继《仙逆》《求魔》《我欲封天》后，创作的第四部长篇小说《一念永恒》","lastChapter":"第1313章 你的选择（终）","retentionRatio":50.12,"banned":0,"allowMonthly":false,"latelyFollower":39509,"wordCount":3751755,"contentType":"txt","superscript":"","sizetype":-1,"highlight":{"title":["一","念"]}},{"_id":"565eb60d4e47b55a5ded7127","hasCp":true,"title":"都市奇门医圣","aliases":"","cat":"都市","author":"一念","site":"zhuishuvip","cover":"/agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F602017%2F602017_c853ec17df2245b3bdb26cf86059b1fc.jpg%2F","shortIntro":"实习医生叶皓轩，意外的得到一本古书上的玄术与医道传承，自此开始了不一样的人生，他银针渡人，术法渡鬼，成就济世仁心，医道问卜、风水勘舆无所不精。且看主角如何弘扬华夏传统文化。","lastChapter":"第3475章　闭嘴","retentionRatio":62.92,"banned":0,"allowMonthly":false,"latelyFollower":18331,"wordCount":8844549,"contentType":"txt","superscript":"","sizetype":-1,"highlight":{"author":["一","念"]}}]
     * total : 777
     * ok : true
     */

    private int total;
    private boolean ok;
    private List<BookBean> books;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<BookBean> getBooks() {
        return books;
    }

    public void setBooks(List<BookBean> books) {
        this.books = books;
    }

}
