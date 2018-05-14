package com.mirkowu.fastread.bean;

import java.io.Serializable;

/**
 * @author by DELL
 * @date on 2018/5/11
 * @describe
 */
public class ChapterBean  implements Serializable {
        /**
         * title : 第1章 他叫白小纯
         * link : http://book.my716.com/getBooks.aspx?method=content&bookId=1127281&chapterFile=U_1127281_201710100931202198_6433_1.txt
         * unreadble : false
         */

        private String title;
        private String link;
        private boolean unreadble;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public boolean isUnreadble() {
            return unreadble;
        }

        public void setUnreadble(boolean unreadble) {
            this.unreadble = unreadble;
        }
}
