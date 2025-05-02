package br.com.pi.atostech.adapters.in.api.dto.response;

public class VerseResponseDto {

    private String book;
    private Integer chapter;
    private Integer verse;
    private String text;

    public VerseResponseDto(String book, Integer chapter, Integer verse, String text) {
        this.book = book;
        this.chapter = chapter;
        this.verse = verse;
        this.text = text;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
