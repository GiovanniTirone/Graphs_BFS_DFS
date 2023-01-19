package bfs.webCrawler;

import java.util.function.Function;

public class PrintWebSite implements Function<String,Void> {

    @Override
    public Void apply(String w) {
        System.out.println("Website found: " + w);
        return null;
    }
}
