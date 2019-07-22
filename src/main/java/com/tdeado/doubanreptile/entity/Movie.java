package com.tdeado.doubanreptile.entity;

import java.math.BigDecimal;

public class Movie {
    /**
     * 主键ID
     */
    private long id;
    /**
     * 电影名
     */
    private String movieName;
    /**
     * 评分总数
     */
    private int score;
    /**
     * 五星好评比例
     */
    private BigDecimal praiseScale;
    /**
     * 五星好评数
     */
    private int praise;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public BigDecimal getPraiseScale() {
        return praiseScale;
    }

    public void setPraiseScale(BigDecimal praiseScale) {
        this.praiseScale = praiseScale;
    }

    public int getPraise() {
        return praise;
    }

    public void setPraise(int praise) {
        this.praise = praise;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieName='" + movieName + '\'' +
                ", score=" + score +
                ", praiseScale=" + praiseScale +
                ", praise=" + praise +
                '}';
    }
}
