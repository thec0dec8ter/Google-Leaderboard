package dev.thec0dec8ter.googleleaderboard;

import com.google.gson.annotations.SerializedName;

public class Leader implements Comparable<Leader>{

    @SerializedName("name")
    private String name;
    @SerializedName("hours")
    private String hours;
    @SerializedName("score")
    private String score;
    @SerializedName("country")
    private String country;
    @SerializedName("badgeUrl")
    private String badgeUrl;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }

    @Override
    public int compareTo(Leader leader) {
        if(this.hours != null && leader.getHours() != null){
            return (Integer.compare(Integer.parseInt(this.hours), Integer.parseInt(leader.getHours())));
        }else {
            return (Integer.compare(Integer.parseInt(this.score), Integer.parseInt(leader.getScore())));
        }
    }
}
