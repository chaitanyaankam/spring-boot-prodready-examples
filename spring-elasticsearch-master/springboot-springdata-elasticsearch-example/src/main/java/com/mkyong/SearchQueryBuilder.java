package com.mkyong;

import java.io.IOException;
import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

import com.mkyong.book.model.Book;

@Component
public class SearchQueryBuilder{

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	/*
    Query in Json format. Only description ans location are used for the search.
    SEARCHTEXT is can be for example: 'Tas Reg'
    This SEARCHTEXT will find all Tassen in Regals.
            {
          "bool" : {
            "should" : [ {
              "query_string" : {
                "query" : "SEARCHTEXT",
                "fields" : [ "description", "location" ],
                "lenient" : true
              }
            }, {
              "query_string" : {
                "query" : "*SEARCHTEXT*",
                "fields" : [ "description", "location" ],
                "lenient" : true
              }
            } ]
          }
        }
	 */

	public List<Book> getAll(String text) throws IOException {
		QueryBuilder query = QueryBuilders.boolQuery()
				.should(
						QueryBuilders.queryStringQuery(text)
						.lenient(true)
						.field("title")
						.field("author")
						).should(QueryBuilders.queryStringQuery("*" + text + "*")
								.lenient(true)
								.field("title")
								.field("author"));

		NativeSearchQuery build = new NativeSearchQueryBuilder()
				.withQuery(query)
				.build();


		return elasticsearchTemplate.queryForList(build, Book.class);
	}
}