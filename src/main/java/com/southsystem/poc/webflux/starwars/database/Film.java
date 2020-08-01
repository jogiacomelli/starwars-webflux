package com.southsystem.poc.webflux.starwars.database;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import java.util.Objects;

@Document(collection = "films")
public class Film {

    @Id
    private int episodeId;

    private String title;
    private Date releaseDate;

    public Film(String title, int episodeId, Date releaseDate) {
        this.title = title;
        this.episodeId = episodeId;
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(int episodeId) {
        this.episodeId = episodeId;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return episodeId == film.episodeId &&
                title.equals(film.title) &&
                releaseDate.equals(film.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(episodeId, title, releaseDate);
    }

    @Override
    public String toString() {
        return "Film{" +
                "episodeId=" + episodeId +
                ", title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
