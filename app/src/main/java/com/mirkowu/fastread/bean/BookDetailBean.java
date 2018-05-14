package com.mirkowu.fastread.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;

/**
 * @author by DELL
 * @date on 2018/5/11
 * @describe
 */
@Entity
public class BookDetailBean {

    /**
     * _id : 57206c3539a913ad65d35c7b
     * title : 一念永恒
     * author : 耳根
     * longIntro : 一念成沧海，一念化桑田。一念斩千魔，一念诛万仙。唯我念……永恒
     * cover : /agent/http://image.cmfu.com/books/1003354631/1003354631.jpg
     * cat : 幻想修仙
     * majorCate : 仙侠
     * minorCate : 幻想修仙
     * creater : Xiaomi Redmi 3
     * _le : false
     * allowMonthly : false
     * allowVoucher : true
     * allowBeanVoucher : false
     * hasCp : true
     * postCount : 33937
     * latelyFollower : 195114
     * followerCount : 0
     * wordCount : 1987456
     * serializeWordCount : 5972
     * retentionRatio : 75.68
     * updated : 2017-03-27T09:24:35.945Z
     * isSerial : true
     * chaptersCount : 683
     * lastChapter : 第683章 实力再涨
     * gender : ["male"]
     * tags : ["仙侠"]
     * donate : false
     * copyright : 阅文集团正版授权
     */
    @Id
    private String _id;
    private String title;
    private String author;
    private String longIntro;
    private String cover;
    private String cat;
    private String majorCate;
    private String minorCate;
    private String creater;
    private boolean _le;
    private boolean allowMonthly;
    private boolean allowVoucher;
    private boolean allowBeanVoucher;
    private boolean hasCp;
    private int postCount;
    private int latelyFollower;
    private int followerCount;
    private int wordCount;
    private int serializeWordCount;
    private String retentionRatio;
    private String updated;
    private boolean isSerial;
    private int chaptersCount;
    private String lastChapter;
    private boolean donate;
    private String copyright;
    @Transient
    private List<String> gender;
    @Transient
    private List<String> tags;

    @Generated(hash = 476668896)
    public BookDetailBean(String _id, String title, String author, String longIntro,
            String cover, String cat, String majorCate, String minorCate,
            String creater, boolean _le, boolean allowMonthly, boolean allowVoucher,
            boolean allowBeanVoucher, boolean hasCp, int postCount,
            int latelyFollower, int followerCount, int wordCount,
            int serializeWordCount, String retentionRatio, String updated,
            boolean isSerial, int chaptersCount, String lastChapter, boolean donate,
            String copyright) {
        this._id = _id;
        this.title = title;
        this.author = author;
        this.longIntro = longIntro;
        this.cover = cover;
        this.cat = cat;
        this.majorCate = majorCate;
        this.minorCate = minorCate;
        this.creater = creater;
        this._le = _le;
        this.allowMonthly = allowMonthly;
        this.allowVoucher = allowVoucher;
        this.allowBeanVoucher = allowBeanVoucher;
        this.hasCp = hasCp;
        this.postCount = postCount;
        this.latelyFollower = latelyFollower;
        this.followerCount = followerCount;
        this.wordCount = wordCount;
        this.serializeWordCount = serializeWordCount;
        this.retentionRatio = retentionRatio;
        this.updated = updated;
        this.isSerial = isSerial;
        this.chaptersCount = chaptersCount;
        this.lastChapter = lastChapter;
        this.donate = donate;
        this.copyright = copyright;
    }

    @Generated(hash = 1036855212)
    public BookDetailBean() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLongIntro() {
        return longIntro;
    }

    public void setLongIntro(String longIntro) {
        this.longIntro = longIntro;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getMajorCate() {
        return majorCate;
    }

    public void setMajorCate(String majorCate) {
        this.majorCate = majorCate;
    }

    public String getMinorCate() {
        return minorCate;
    }

    public void setMinorCate(String minorCate) {
        this.minorCate = minorCate;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public boolean is_le() {
        return _le;
    }

    public void set_le(boolean _le) {
        this._le = _le;
    }

    public boolean isAllowMonthly() {
        return allowMonthly;
    }

    public void setAllowMonthly(boolean allowMonthly) {
        this.allowMonthly = allowMonthly;
    }

    public boolean isAllowVoucher() {
        return allowVoucher;
    }

    public void setAllowVoucher(boolean allowVoucher) {
        this.allowVoucher = allowVoucher;
    }

    public boolean isAllowBeanVoucher() {
        return allowBeanVoucher;
    }

    public void setAllowBeanVoucher(boolean allowBeanVoucher) {
        this.allowBeanVoucher = allowBeanVoucher;
    }

    public boolean isHasCp() {
        return hasCp;
    }

    public void setHasCp(boolean hasCp) {
        this.hasCp = hasCp;
    }

    public int getPostCount() {
        return postCount;
    }

    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }

    public int getLatelyFollower() {
        return latelyFollower;
    }

    public void setLatelyFollower(int latelyFollower) {
        this.latelyFollower = latelyFollower;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public int getSerializeWordCount() {
        return serializeWordCount;
    }

    public void setSerializeWordCount(int serializeWordCount) {
        this.serializeWordCount = serializeWordCount;
    }

    public String getRetentionRatio() {
        return retentionRatio;
    }

    public void setRetentionRatio(String retentionRatio) {
        this.retentionRatio = retentionRatio;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public boolean isIsSerial() {
        return isSerial;
    }

    public void setIsSerial(boolean isSerial) {
        this.isSerial = isSerial;
    }

    public int getChaptersCount() {
        return chaptersCount;
    }

    public void setChaptersCount(int chaptersCount) {
        this.chaptersCount = chaptersCount;
    }

    public String getLastChapter() {
        return lastChapter;
    }

    public void setLastChapter(String lastChapter) {
        this.lastChapter = lastChapter;
    }

    public boolean isDonate() {
        return donate;
    }

    public void setDonate(boolean donate) {
        this.donate = donate;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public List<String> getGender() {
        return gender;
    }

    public void setGender(List<String> gender) {
        this.gender = gender;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public boolean get_le() {
        return this._le;
    }

    public boolean getAllowMonthly() {
        return this.allowMonthly;
    }

    public boolean getAllowVoucher() {
        return this.allowVoucher;
    }

    public boolean getAllowBeanVoucher() {
        return this.allowBeanVoucher;
    }

    public boolean getHasCp() {
        return this.hasCp;
    }

    public boolean getIsSerial() {
        return this.isSerial;
    }

    public boolean getDonate() {
        return this.donate;
    }
}
