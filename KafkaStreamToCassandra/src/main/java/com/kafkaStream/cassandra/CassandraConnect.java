/*
Copyright 2021 C.Young
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
  limitations under the License.
*/
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
