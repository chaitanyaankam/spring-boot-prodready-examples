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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="AUTHOR")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Author implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="authorSequence",sequenceName="AUTHOR_SEQ", allocationSize=1)
	@GeneratedValue(generator="authorSequence")
	@Column(name="AUTHOR_ID")
	private int authorId;

	@Column(name="NAME")
	private String name;

	@Column(name="NATIONALITY")
	private String nationality;

	@JsonBackReference
	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<Book> books;
	
}
