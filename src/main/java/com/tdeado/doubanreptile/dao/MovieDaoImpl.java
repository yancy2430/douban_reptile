package com.tdeado.doubanreptile.dao;

import com.tdeado.doubanreptile.entity.Movie;

public class MovieDaoImpl extends BaseDao implements MovieDao {
    @Override
    public boolean save(Movie movie) {
        return this.executeSql("insert into movie_list values(null,?,?,?,?)",
                movie.getMovieName(),
                movie.getScore(),
                movie.getPraiseScale(),
                movie.getPraise()
        );

    }

}
