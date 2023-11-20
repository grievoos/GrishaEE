package com.example.grishaee.Models;

public class Question {

    private Long id, voteId;
    private String content, dateVote;
    private Vote vote;

    //  Конструкторы

    public Question() {
    }

    public Question(Long id, Long voteId, String content, String dateVote, Vote vote) {
        this.id = id;
        this.voteId = voteId;
        this.content = content;
        this.dateVote = dateVote;
        this.vote = vote;
    }

    public Question(Long voteId, String content, String dateVote, Vote vote) {
        this.voteId = voteId;
        this.content = content;
        this.dateVote = dateVote;
        this.vote = vote;
    }

    public Question(String content, String dateVote, Vote vote) {
        this.content = content;
        this.dateVote = dateVote;
        this.vote = vote;
    }
    //  Гетеры

    public Long getId() {
        return id;
    }

    public Long getVoteId() {
        return voteId;
    }

    public String getContent() {
        return content;
    }

    public String getDateVote() {
        return dateVote;
    }

    public Vote getVote() {
        return vote;
    }

    //  Сетеры

    public void setId(Long id) {
        this.id = id;
    }

    public void setVoteId(Long voteId) {
        this.voteId = voteId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDateVote(String dateVote) {
        this.dateVote = dateVote;
    }

    public void setVote(Vote vote) {
        this.vote = vote;
    }

    //  toString

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", voteIds=" + voteId +
                ", content='" + content + '\'' +
                ", dateVote='" + dateVote + '\'' +
                ", vote=" + vote +
                '}';
    }
}
