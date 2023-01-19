package tests.bfs;

import bfs.webCrawler.PrintWebSite;
import bfs.webCrawler.WebCrawler;

public class TestWebCrawler {
    public static void main(String[] args) {
        WebCrawler wc = new WebCrawler();
        PrintWebSite printFunc = new PrintWebSite();
        wc.discoverWeb("https://www.bbc.com",printFunc);
    }



}
