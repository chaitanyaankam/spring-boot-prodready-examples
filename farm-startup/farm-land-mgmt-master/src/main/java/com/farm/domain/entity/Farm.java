package com.farm.domain.entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.farm.domain.LandSize;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "farm")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Farm {

	@Id
	@Column(name = "farm_id")
	@ApiModelProperty(notes = "The database generated Plot ID")
	private String farmId;
	
	@Embedded
	@ApiModelProperty(notes = "Area of the land along with units")
	private LandSize size;
	
	@ManyToOne
	@JoinColumn(name = "farm_owner_id")
	private FarmOwner farmOwner;
	
	@Column(name = "is_booked")
	private Boolean isBooked;
	
	@ManyToOne
	@JoinColumn(name = "land_id", nullable= false)
	private Land land;
	
	@OneToMany(mappedBy="farm")
	private Set<FarmStatus> status;
	
	@ElementCollection
    @MapKeyColumn(name="imageId")
    @Column(name="imagePath")
    @CollectionTable(name="farm_images", joinColumns=@JoinColumn(name="farm_id"))
	private Map<String,String> images = new HashMap<String, String>();
	
	 @ElementCollection
	 @CollectionTable(name = "farm_vegetables", joinColumns = @JoinColumn(name = "farm_id"))
	 @Column(name = "vegetable_id")
	 private Set<String> vegetables = new HashSet<>();
	
	public String toJsonString() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
	}
}
