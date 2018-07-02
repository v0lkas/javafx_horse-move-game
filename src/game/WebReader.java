package game;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

import static game.Menu.*;

public class WebReader {

    int bgProportion = 0;
    int bgWidth = 0;
    int bgHeight = 0;

    public String reader(String website) throws Exception {

        URL oracle = new URL(website);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(oracle.openStream()));

        String inputLine;
        String html = "";

        while ((inputLine = in.readLine()) != null) {
            html += inputLine;
        }

        in.close();

        return html;
    }

    public ArrayList webImage(int width, int height) throws Exception {

        ArrayList<String> res = new ArrayList();
        res.add("56,360x640,480x854,540x960,720x1280,800x1420,938x1668,1080x1920,1350x2400,1440x2560,2160x3840");
        res.add("60,240x400,480x800");
        res.add("63,800x1280");
        res.add("67,320x480,800x1200");
        res.add("75,240x320");
        res.add("100,1280x1280,2780x2780,3415x3415");
        res.add("125,1280x1024");
        res.add("133,320x240,800x600,1024x768,1152x864,1280x960,1400x1050,1600x1200");
        res.add("160,1280x800,1440x900,1680x1050,1920x1200,2560x1600,3840x2400");
        res.add("171,1024x600");
        res.add("176,960x544");
        res.add("178,1280x720,1366x768,1600x900,1920x1080,2048x1152,2560x1440,3840x2160");
        res.add("237,2560x1080");
        res.add("250,2560x1024");

        double dblProp = (double) width / height * 100;
        int proportion = (int) dblProp;

        for (Object resList : res) {

            String[] list = String.valueOf(resList).split(",");
            int listProportion = Integer.parseInt(list[0]);

            if(listProportion >= proportion) {

                for (int i = 1; i < list.length; ++i) {

                    String[] listRes = list[i].split("x");
                    int listWidth = Integer.parseInt(listRes[0]);
                    int listHeight = Integer.parseInt(listRes[1]);

                    if((listWidth > width && listHeight > height) || i == list.length - 1) {
                        bgProportion = listProportion;
                        bgWidth = listWidth;
                        bgHeight = listHeight;

                        break;
                    }

                }

                break;

            }

        }

        if(bgProportion == 0) {
            bgProportion = 250;
            bgWidth = 2560;
            bgHeight = 1024;
        }

        ArrayList<Object> data = new ArrayList();

        data.add(bgProportion);
        data.add(bgWidth);
        data.add(bgHeight);



        String website = reader("https://wallpaperscraft.com/catalog/"+thematics+"/"+bgWidth+"x"+bgHeight);

        String[] split1 = website.split("href=\"/catalog/"+thematics+"/"+bgWidth+"x"+bgHeight+"/page");
        String[] split2 = split1[split1.length-1].split("\"");
        int maxPages = Integer.parseInt(split2[0]);

        Random rand = new Random();
        int pageNr = rand.nextInt(maxPages) + 1;

        String pageHtml = reader("https://wallpaperscraft.com/catalog/"+thematics+"/"+bgWidth+"x"+bgHeight+"/page"+pageNr);

        String[] split3 = pageHtml.split("class=\"wallpapers__link\" href=\"");
        int imageNr = rand.nextInt(split3.length) + 1;
        String[] split4 = split3[imageNr].split("\"");
        String imagePageUrl = split4[0];

        String imageHtml = reader("https://wallpaperscraft.com"+imagePageUrl);
        String[] split5 = imageHtml.split("class=\"JS-Popup\" href=\"");
        String[] split6 = split5[1].split("\"");

        String randomUrl = split6[0];

        data.add(randomUrl);

        return data;
    }

}
