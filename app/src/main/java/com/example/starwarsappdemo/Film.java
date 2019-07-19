package com.example.starwarsappdemo;

public class Film
{
    private String title;
    private String releaseDate;
    private String directors;
    private String producers;
    // image
    //private String rating;
    private String characters;
    private String crawlText;

    public Film(String title, String releaseDate, String directors, String producers, String characters, String crawlText) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.directors = directors;
        this.producers = producers;
        this.characters = characters;
        this.crawlText = crawlText;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public String getProducers() {
        return producers;
    }

    public void setProducers(String producers) {
        this.producers = producers;
    }

    public String getCharacters() {
        return characters;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }

    public String getCrawlText() {
        return crawlText;
    }

    public void setCrawlText(String crawlText) {
        this.crawlText = crawlText;
    }
}
