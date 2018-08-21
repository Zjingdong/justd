package org.solr;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;

public class test {
    public static void main(String[] args) throws IOException, SolrServerException {
        HttpSolrClient.Builder builder = new    HttpSolrClient.Builder("http://localhost:8983/solr/democore");
        HttpSolrClient solrClient = builder.build();
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", System.currentTimeMillis() + "");
        document.addField("title", "计算机科学与技术" + System.currentTimeMillis());
        solrClient.add(document);
        solrClient.commit();
        solrClient.close();
    }
}
