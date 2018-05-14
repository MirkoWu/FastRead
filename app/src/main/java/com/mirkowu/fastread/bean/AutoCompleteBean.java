package com.mirkowu.fastread.bean;

import java.util.List;

/**
 * @author by DELL
 * @date on 2018/5/11
 * @describe
 */
public class AutoCompleteBean {

    /**
     * keywords : ["一念永恒","一念（红薯网）","一念汪洋","一念","一念乱天机","一念红尘","一念百花开","一念长空","一念情起","一念灵台方寸间"]
     * ok : true
     */

    private boolean ok;
    private List<String> keywords;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }
}
