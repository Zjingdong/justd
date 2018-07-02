package org.solr;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class Query {
    public static void main(String[] args){
        HttpSolrClient.Builder builder = new HttpSolrClient.Builder("http://localhost:8983/solr/democore");
        HttpSolrClient solrClient = builder.build();
        SolrQuery query = new SolrQuery();
        query.set("q", "title:计算机科学与技术1514167320249");
        QueryResponse response = null;
        try {
            response = solrClient.query(query);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SolrDocumentList list = response.getResults();
        try {
            solrClient.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.err.println("匹配数量："+list.getNumFound());
        Iterator itr = list.iterator();
        while (itr.hasNext()) {
            SolrDocument solrDocument = (SolrDocument) itr.next();
            List title = (List) solrDocument.getFieldValue("title");
            System.err.println(title.get(0));
            System.out.println(title);
        }
    }
}
