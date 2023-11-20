package com.example.grishaee.Models;

public class Vote {
    private Long id;
    private String title, dateStart, dateFinish, status;


//  Конструкторы

    public Vote() {
    }

    public Vote(Long id, String title, String dateStart, String dateFinish, String status) {
        this.id = id;
        this.title = title;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
        this.status = status;
    }

    public Vote(String title, String dateStart, String dateFinish, String status) {
        this.title = title;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
        this.status = status;
    }

//  Гетеры

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDateStart() {
        return dateStart;
    }

    public String getDateFinish() {
        return dateFinish;
    }

    public String getStatus() {
        return status;
    }

//  Сетеры

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public void setDateFinish(String dateFinish) {
        this.dateFinish = dateFinish;
    }

    public void setStatus(String status) {
        this.status = status;
    }

//  toString

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", dateStart='" + dateStart + '\'' +
                ", dateFinish='" + dateFinish + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
