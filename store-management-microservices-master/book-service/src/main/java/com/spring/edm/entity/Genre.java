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
@Table(name = "GENRE")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Genre implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(generator="genreSequence")
	@SequenceGenerator(name="genreSequence",sequenceName="GENRE_SEQ", allocationSize=1)
	private int id;

	@Column(name = "NAME")
	private String name;
	
	@JsonBackReference
	@OneToMany(mappedBy = "genre", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<Book> books;
	
}
