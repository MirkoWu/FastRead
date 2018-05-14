package com.mirkowu.fastread.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author by DELL
 * @date on 2018/5/11
 * @describe
 */
public class BookBean implements Serializable {

    /**
     * _id : 57206c3539a913ad65d35c7b
     * hasCp : true
     * title : 一念永恒
     * aliases :
     * cat : 仙侠
     * author : 耳根
     * site : zhuishuvip
     * cover : /agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F1127281%2F_1127281_685974.jpg%2F
     * shortIntro : 一念成沧海，一念化桑田。一念斩千魔，一念诛万仙。
     * 唯我念……永恒
     * 这是耳根继《仙逆》《求魔》《我欲封天》后，创作的第四部长篇小说《一念永恒》
     * lastChapter : 第1313章 你的选择（终）
     * retentionRatio : 50.12
     * banned : 0
     * allowMonthly : false
     * latelyFollower : 39509
     * wordCount : 3751755
     * contentType : txt
     * superscript :
     * sizetype : -1
     * highlight : {"title":["一","念"]}
     */

    private String _id;
    private boolean hasCp;
    private String title;
    private String aliases;
    private String cat;
    private String author;
    private String site;
    private String cover;
    private String shortIntro;
    private String lastChapter;
    private double retentionRatio;
    private int banned;
    private boolean allowMonthly;
    private int latelyFollower;
    private int wordCount;
    private String contentType;
    private String superscript;
    private int sizetype;
    private HighlightBean highlight;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public boolean isHasCp() {
        return hasCp;
    }

    public void setHasCp(boolean hasCp) {
        this.hasCp = hasCp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAliases() {
        return aliases;
    }

    public void setAliases(String aliases) {
        this.aliases = aliases;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getShortIntro() {
        return shortIntro;
    }

    public void setShortIntro(String shortIntro) {
        this.shortIntro = shortIntro;
    }

    public String getLastChapter() {
        return lastChapter;
    }

    public void setLastChapter(String lastChapter) {
        this.lastChapter = lastChapter;
    }

    public double getRetentionRatio() {
        return retentionRatio;
    }

    public void setRetentionRatio(double retentionRatio) {
        this.retentionRatio = retentionRatio;
    }

    public int getBanned() {
        return banned;
    }

    public void setBanned(int banned) {
        this.banned = banned;
    }

    public boolean isAllowMonthly() {
        return allowMonthly;
    }

    public void setAllowMonthly(boolean allowMonthly) {
        this.allowMonthly = allowMonthly;
    }

    public int getLatelyFollower() {
        return latelyFollower;
    }

    public void setLatelyFollower(int latelyFollower) {
        this.latelyFollower = latelyFollower;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getSuperscript() {
        return superscript;
    }

    public void setSuperscript(String superscript) {
        this.superscript = superscript;
    }

    public int getSizetype() {
        return sizetype;
    }

    public void setSizetype(int sizetype) {
        this.sizetype = sizetype;
    }

    public HighlightBean getHighlight() {
        return highlight;
    }

    public void setHighlight(HighlightBean highlight) {
        this.highlight = highlight;
    }

    public static class HighlightBean {
        private List<String> title;

        public List<String> getTitle() {
            return title;
        }

        public void setTitle(List<String> title) {
            this.title = title;
        }
    }
}
