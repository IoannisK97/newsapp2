package com.example.jandroid.newsapp2;

public class News {

    private final String nameArticle;
    private final String sectionName;
    private final String datePublished;
    private final String urlArticle;

    public News(String nameArticle, String sectionName, String datePublished, String urlArticle) {
        this.nameArticle = nameArticle;
        this.sectionName = sectionName;
        this.datePublished = datePublished;
        this.urlArticle = urlArticle;
    }

    public String getNameArticle() {
        return nameArticle;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public String getUrlArticle() {
        return urlArticle;
    }
}
