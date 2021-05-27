package com.spring.edm.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="PUBLISHER")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Publisher implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="publisherSequence",sequenceName="PUBLISHER_SEQ", allocationSize=1)
	@GeneratedValue(generator="publisherSequence")
	@Column(name="PUBLISHER_ID")
	private int publisherId;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="COUNTRY")
	private String country;
	
	@JsonBackReference
    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<Book> books;
	
}
