package com.theangel.ericsson.ericsson.model;

public class Show {
    private int id;
    private String name;
    private int runtime;
    private String image;
    private String description;
    private double average;

    public Show(int id, String name, int runtime, String image, String description, double average) {
        this.id = id;
        this.name = name;
        this.runtime = runtime;
        this.image = image;
        this.description = description;
        this.average = average;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    @Override
    public String toString() {
        return "Show{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", runtime=" + runtime +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", average=" + average +
                '}';
    }
}
