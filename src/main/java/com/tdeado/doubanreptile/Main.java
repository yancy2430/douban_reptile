package com.tdeado.doubanreptile;


import com.tdeado.doubanreptile.service.Reptile;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Reptile reptile=new Reptile();
        //爬取第一页
        reptile.crawlTopList(1);
    }
}
