package com.example.starwarsappdemo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class StarWarsResponse {


    private int count;
    private Object next;
    private Object previous;
    private List<ResultsBean> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Object getNext() {
        return next;
    }

    public void setNext(Object next) {
        this.next = next;
    }

    public Object getPrevious() {
        return previous;
    }

    public void setPrevious(Object previous) {
        this.previous = previous;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean implements Parcelable {


        private String title;
        private int episode_id;
        private String opening_crawl;
        private String director;
        private String producer;
        private String release_date;
        private String created;
        private String edited;
        private String url;
        private List<String> characters;
        private List<String> planets;
        private List<String> starships;
        private List<String> vehicles;
        private List<String> species;

        protected ResultsBean(Parcel in) {
            title = in.readString();
            episode_id = in.readInt();
            opening_crawl = in.readString();
            director = in.readString();
            producer = in.readString();
            release_date = in.readString();
            created = in.readString();
            edited = in.readString();
            url = in.readString();
            characters = in.createStringArrayList();
            planets = in.createStringArrayList();
            starships = in.createStringArrayList();
            vehicles = in.createStringArrayList();
            species = in.createStringArrayList();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(title);
            dest.writeInt(episode_id);
            dest.writeString(opening_crawl);
            dest.writeString(director);
            dest.writeString(producer);
            dest.writeString(release_date);
            dest.writeString(created);
            dest.writeString(edited);
            dest.writeString(url);
            dest.writeStringList(characters);
            dest.writeStringList(planets);
            dest.writeStringList(starships);
            dest.writeStringList(vehicles);
            dest.writeStringList(species);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<ResultsBean> CREATOR = new Creator<ResultsBean>() {
            @Override
            public ResultsBean createFromParcel(Parcel in) {
                return new ResultsBean(in);
            }

            @Override
            public ResultsBean[] newArray(int size) {
                return new ResultsBean[size];
            }
        };

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getEpisode_id() {
            return episode_id;
        }

        public void setEpisode_id(int episode_id) {
            this.episode_id = episode_id;
        }

        public String getOpening_crawl() {
            return opening_crawl;
        }

        public void setOpening_crawl(String opening_crawl) {
            this.opening_crawl = opening_crawl;
        }

        public String getDirector() {
            return director;
        }

        public void setDirector(String director) {
            this.director = director;
        }

        public String getProducer() {
            return producer;
        }

        public void setProducer(String producer) {
            this.producer = producer;
        }

        public String getRelease_date() {
            return release_date;
        }

        public void setRelease_date(String release_date) {
            this.release_date = release_date;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getEdited() {
            return edited;
        }

        public void setEdited(String edited) {
            this.edited = edited;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<String> getCharacters() {
            return characters;
        }

        public void setCharacters(List<String> characters) {
            this.characters = characters;
        }

        public List<String> getPlanets() {
            return planets;
        }

        public void setPlanets(List<String> planets) {
            this.planets = planets;
        }

        public List<String> getStarships() {
            return starships;
        }

        public void setStarships(List<String> starships) {
            this.starships = starships;
        }

        public List<String> getVehicles() {
            return vehicles;
        }

        public void setVehicles(List<String> vehicles) {
            this.vehicles = vehicles;
        }

        public List<String> getSpecies() {
            return species;
        }

        public void setSpecies(List<String> species) {
            this.species = species;
        }
    }
}
