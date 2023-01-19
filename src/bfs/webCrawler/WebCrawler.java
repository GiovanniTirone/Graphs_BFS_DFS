package bfs.webCrawler;

import vertices.Vertex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCrawler {

    private Queue<String> queue;
    private Set<String> discoveredWebsiteSet;

    public WebCrawler() {
        this.queue = new LinkedList<>();
        this.discoveredWebsiteSet = new HashSet<>();
    }

    public<Data> void discoverWeb (String root, Function<String,Data> func) {
        this.queue.add(root);
        this.discoveredWebsiteSet.add(root);

        while(!queue.isEmpty()){
            String v = this.queue.remove();
            String rawHtml = readURL(v);
            String regexp = "https://(\\w+\\.)*(\\w+)";
            Pattern pattern = Pattern.compile(regexp);
            Matcher matcher = pattern.matcher(rawHtml);

            while(matcher.find()){
                String w = matcher.group();

                if(!discoveredWebsiteSet.contains(w)){
                    discoveredWebsiteSet.add(w);
                    func.apply(w);
                    queue.add(w);
                }
            }
        }
    }

    private String readURL(String v) {

        StringBuilder rawHtml = new StringBuilder("");

        try {
            URL url = new URL(v);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = "";
            while((line=reader.readLine())!=null){
                rawHtml.append(line);
            }
            reader.close();

        } catch (IOException e) {
            System.out.println("Problem while crawling.");
        }
        return rawHtml.toString();

    }


}
