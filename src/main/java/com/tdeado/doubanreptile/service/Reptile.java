package com.tdeado.doubanreptile.service;

import com.tdeado.doubanreptile.dao.MovieDao;
import com.tdeado.doubanreptile.dao.MovieDaoImpl;
import com.tdeado.doubanreptile.entity.Movie;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.InetSocketAddress;
import java.net.Proxy;

public class Reptile {
    private MovieDao movieDao;

    public Reptile() {
        //初始化插数据库dao
        movieDao = new MovieDaoImpl();
    }
    /**
     * 爬取豆瓣top250
     *
     * @param page 页码
     */
    public void crawlTopList(int page) throws IOException {
        if (page<1)throw new RuntimeException("页数不能小于1");
        page--;
        //获取网页源码 并解析dom
        Document p = Jsoup.connect("https://movie.douban.com/top250?start=" + page * 25 + "&filter=").proxy(new Proxy(Proxy.Type.HTTP,new InetSocketAddress("1.197.204.141",9999))).get();
        //通过class获取影片列表
        Elements movie_list = p.getElementsByClass("grid_view").first().getElementsByClass("item");
        for (Element element : movie_list) {//循环列表
            //循环获取电影主题内容并保存数据库
            crawlSubject(element.getElementsByTag("a").first().attr("href"));
        }
    }

    public void crawlSubject(String url) throws IOException {
        Movie movie = new Movie();
        //获取电源内容页面
        Document view = Jsoup.connect(url).proxy(new Proxy(Proxy.Type.HTTP,new InetSocketAddress("1.197.204.141",9999))).get();
        movie.setMovieName(view.getElementsByTag("H1").first().text());
        //获取总评分数
        movie.setScore(Integer.parseInt(view.getElementsByClass("rating_people").first().getElementsByTag("span").text()));
        //获取五星好评比例
        movie.setPraiseScale(new BigDecimal(view.getElementsByClass("rating_per").first().text().replace("%", "")));
        //计算五星好评数并向下取整数
        movie.setPraise(movie.getPraiseScale().multiply(new BigDecimal(0.01)).multiply(new BigDecimal(movie.getScore())).intValue());
        //插入MySQL数据库
        boolean insert = movieDao.save(movie);
        if (insert) {
            System.out.println("保存电影:" + movie.getMovieName() + " 成功");
        } else {
            System.out.println("保存电影:" + movie.getMovieName() + " 失败");
        }
    }

}
