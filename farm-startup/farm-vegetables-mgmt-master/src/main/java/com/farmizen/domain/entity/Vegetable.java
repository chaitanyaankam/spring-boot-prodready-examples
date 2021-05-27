package com.farmizen.domain.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vegetable")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vegetable {

	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="vegetableSequence")
	@SequenceGenerator(name="vegetableSequence",sequenceName="vegetable_seq", allocationSize=1)
	@Column(name = "vegetable_id")
	@ApiModelProperty(hidden = true, notes = "The database generated vegetable Id")
	private Integer vegetableId;
	
	@NotNull
	@Column(name = "name")
	@ApiModelProperty(notes = "vegetable name")
	private String name;
	
	@ManyToMany(mappedBy = "vegetables",fetch=FetchType.LAZY)
	private Set<VegetableCategory> categories;	
	
	@ElementCollection
    @MapKeyColumn(name="imageId")
    @Column(name="imagePath")
    @CollectionTable(name="vegetable_images", joinColumns=@JoinColumn(name="vegetable_id"))
	private Map<String,String> images = new HashMap<String, String>();
		
	public String toJsonString() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
	}
}
