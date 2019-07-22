package com.tdeado.doubanreptile.dao;

import com.tdeado.doubanreptile.entity.Movie;


public interface MovieDao {
 
	//插入影片信息
	boolean save(Movie movie);
	
}