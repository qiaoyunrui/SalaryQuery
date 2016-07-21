package com.juhezi.salaryquery.data;

/**
 * Created by qiaoyunrui on 16-7-21.
 */
public class SalaryDetail {

    private String title;

    private String content;

    public SalaryDetail(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
