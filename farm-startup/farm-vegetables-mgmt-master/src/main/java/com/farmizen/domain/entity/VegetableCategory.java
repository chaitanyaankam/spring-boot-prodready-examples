package com.farmizen.domain.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vegetable_category")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VegetableCategory {
	
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="categorySequence")
	@SequenceGenerator(name="categorySequence",sequenceName="category_seq", allocationSize=1)
	@Column(name = "category_id")
	@ApiModelProperty(hidden = true, notes = "The database generated vegetable Id")
	private Integer categoryId;
	
	@NotNull
	@Column(name = "name")
	@ApiModelProperty(notes = "category name")
	private String categoryName;
	
	@ElementCollection
    @MapKeyColumn(name="imageId")
    @Column(name="imagePath")
    @CollectionTable(name="category_images", joinColumns=@JoinColumn(name="category_id"))
	private Map<String,String> images = new HashMap<String, String>();

	@ManyToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
    @JoinTable(
        name = "category_vegetable", 
        joinColumns = {@JoinColumn(name = "category_id")}, 
        inverseJoinColumns = {@JoinColumn(name = "vegetable_id")}
    )
    Set<Vegetable> vegetables;
}
