package com.spring.edm.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "BOOKS")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="bookSequence")
	@SequenceGenerator(name="bookSequence",sequenceName="BOOK_SEQ", allocationSize=1)
	@Column(name = "ID")
	@ApiModelProperty(notes = "The database generated Book ID")
	private int id;	

	@Column(name = "NAME")
	@ApiModelProperty(notes = "The book name")
	private String name; 
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "AUTHOR_ID", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JsonManagedReference
	@ApiModelProperty(notes = "The Author of the book")
	private Author author;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "GENRE_ID", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JsonManagedReference
	@ApiModelProperty(notes = "The Genre of the book")
	private Genre genre;
		
	@JsonFormat(pattern = "dd-MM-YYYY")
	@Column(name="PUBLISHED_DATE")
	private Date publishedDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PUBLISHER_ID", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JsonManagedReference
	@ApiModelProperty(notes = "The Publisher of the book")
	private Publisher publisher;
	
	@Column(name="LANGUAGE")
	@ApiModelProperty(notes = "The Language of the book")
	private String language;

	@Column(name = "COST")
	@ApiModelProperty(notes = "The cost of the book")
	private int cost;
	
	@Column(name="CURRENCY")
	@ApiModelProperty(notes = "Currency in which book can be purchased")
	private String currency;
			
}
