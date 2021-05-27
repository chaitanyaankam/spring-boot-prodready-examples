package com.mkyong;

import java.io.File;
import java.io.IOException;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.NodeBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

//http://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-nosql.html#boot-features-connecting-to-elasticsearch-spring-data
//https://github.com/spring-projects/spring-boot/tree/master/spring-boot-samples/spring-boot-sample-data-elasticsearch/src/main/java/sample/data/elasticsearch
//http://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/#elasticsearch.repositories
//http://geekabyte.blogspot.my/2015/08/embedding-elasticsearch-in-spring.html
//https://github.com/spring-projects/spring-data-elasticsearch/wiki/Spring-Data-Elasticsearch---Spring-Boot---version-matrix
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.mkyong.book.repository")
public class EsConfig {

	/* configuration for connecting to an existing elastic search server

    @Value("${elasticsearch.host}")
    private String EsHost;

    @Value("${elasticsearch.port}")
    private int EsPort;

    @Value("${elasticsearch.clustername}")
    private String EsClusterName;

    @Bean
    public Client client() throws Exception {

        Settings esSettings = Settings.settingsBuilder()
                .put("cluster.name", EsClusterName)
                .build();

        //https://www.elastic.co/guide/en/elasticsearch/guide/current/_transport_client_versus_node_client.html
        return TransportClient.builder()
                .settings(esSettings)
                .build()
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(EsHost), EsPort));
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() throws Exception {
        return new ElasticsearchTemplate(client());
    }*/
	
	@Value("${embedded.elasticsearch.workdir.path}")
	String elasticWorkDir;

	//Embedded Elasticsearch Server
	@Bean
	public NodeBuilder nodeBuilder() {
		return new NodeBuilder();
	}

	@Bean
	public ElasticsearchTemplate elasticsearchTemplate() throws IOException {
		File tmpDir = new File(elasticWorkDir+String.valueOf(System.currentTimeMillis()));
		Settings.Builder elasticsearchSettings =
				Settings.settingsBuilder()
				.put("http.enabled", "true")
				.put("index.number_of_shards", "1")
				.put("path.data", new File(tmpDir, "data").getAbsolutePath())
				.put("path.logs", new File(tmpDir, "logs").getAbsolutePath())
				.put("path.work", new File(tmpDir, "work").getAbsolutePath())
				.put("path.home", tmpDir);


		ElasticsearchTemplate elasticsearchTemplate = new ElasticsearchTemplate(nodeBuilder()
				.local(true)
				.settings(elasticsearchSettings.build())
				.node()
				.client());

		return elasticsearchTemplate;
	}


}