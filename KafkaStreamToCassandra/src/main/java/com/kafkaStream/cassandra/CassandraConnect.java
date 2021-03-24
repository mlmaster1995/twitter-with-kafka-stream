package com.kafkaStream.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class CassandraConnect {

    private String nodeURL=null;
    private Cluster cluster=null;
    private Session session=null;

    public CassandraConnect(){}

    public void connectToCluster(String nodeURL){
        this.nodeURL=nodeURL;
        this.cluster = Cluster.builder().addContactPoint(this.nodeURL).build();
        this.session = this.cluster.connect();
    }

    public Session getSession(){ return this.session; }

    public void closeCassandraCollect(){
        if(cluster!=null) cluster.close();
        if(session!=null) session.close();
    }
}
