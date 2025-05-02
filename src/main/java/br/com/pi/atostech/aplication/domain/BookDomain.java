package br.com.pi.atostech.aplication.domain;

public class BookDomain {

    private String book;
    private Integer chapter;
    private Integer verse;

    public BookDomain(String book, Integer chapter, Integer verse) {
        this.book = book;
        this.chapter = chapter;
        this.verse = verse;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public Integer getChapter() {
        return chapter;
    }

    public void setChapter(Integer chapter) {
        this.chapter = chapter;
    }

    public Integer getVerse() {
        return verse;
    }

    public void setVerse(Integer verse) {
        this.verse = verse;
    }

}
