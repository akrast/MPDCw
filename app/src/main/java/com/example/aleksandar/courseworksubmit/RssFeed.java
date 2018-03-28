package com.example.aleksandar.courseworksubmit;

/**
 * Created by Aleksandar Krastev S1433655 on 28/03/2018.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class RssFeed extends AsyncTask<Void, Void, Void> {
    Context contxt;
    String address = "http://trafficscotland.org/rss/feeds/currentincidents.aspx";
    ProgressDialog progressDialog;
    ArrayList<RssItem> rssItems;
    RecyclerView recyclerView;
    URL url;

    public RssFeed(Context contxt, RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        this.contxt = contxt;
        progressDialog = new ProgressDialog(contxt);
        progressDialog.setMessage("Processing...");
    }

    @Override
    protected void onPreExecute() {
        progressDialog.show();
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... param) {
        ProcessXml(GatherData());
        return null;
    }

    @Override
    protected void onPostExecute(Void newVoid) {
        super.onPostExecute(newVoid);
        progressDialog.dismiss();
        RssAdapter adapter = new RssAdapter(rssItems,contxt);
        recyclerView.setLayoutManager(new LinearLayoutManager(contxt));
        recyclerView.addItemDecoration(new VerticalPoint(20));
        recyclerView.setAdapter(adapter);

    }

    private void ProcessXml(Document data) {
        if (data != null) {
            rssItems = new ArrayList<>();
            Element root = data.getDocumentElement();
            Node channel = root.getChildNodes().item(1);
            NodeList items = channel.getChildNodes();
            for (int i = 0; i < items.getLength(); i++) {
                Node cureentchild = items.item(i);
                if (cureentchild.getNodeName().equalsIgnoreCase("item")) {
                    RssItem item = new RssItem();
                    NodeList itemchilds = cureentchild.getChildNodes();
                    for (int j = 0; j < itemchilds.getLength(); j++) {
                        Node cureent = itemchilds.item(j);
                        if (cureent.getNodeName().equalsIgnoreCase("title")) {
                            item.setTitle(cureent.getTextContent());
                        } else if (cureent.getNodeName().equalsIgnoreCase("description")) {
                            item.setDescription(cureent.getTextContent());
                        } else if (cureent.getNodeName().equalsIgnoreCase("pubDate")) {
                            item.setPubDate(cureent.getTextContent());
                        } else if (cureent.getNodeName().equalsIgnoreCase("link")) {
                            item.setLink(cureent.getTextContent());
                        }
                    }
                    rssItems.add(item);
                }
            }
        }
    }

    public Document GatherData() {
        try {
            url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xmlDoc = builder.parse(inputStream);
            return xmlDoc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}