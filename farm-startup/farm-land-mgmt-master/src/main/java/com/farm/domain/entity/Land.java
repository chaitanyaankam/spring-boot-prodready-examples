package com.farm.domain.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.farm.domain.Address;
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
@Table(name = "land")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Land {

	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="landSequence")
	@SequenceGenerator(name="landSequence",sequenceName="land_seq", allocationSize=1)
	@Column(name = "land_id")
	@ApiModelProperty(notes = "The database generated LandId")
	private Integer landId;
	
	@NotNull
	@Column(name = "survey_no")
	@ApiModelProperty(notes = "survey no of the land")
	private String surveyNo;
	
	@Embedded
	@ApiModelProperty(notes = "Area of the land along with units")
	private LandSize landSize;
	
	@Embedded
	@AttributeOverrides(value = {
            @AttributeOverride(name = "addressLine1", column = @Column(name="address_line1")),
            @AttributeOverride(name = "addressLine2", column = @Column(name="land_mark"))
    })
	@ApiModelProperty(notes = "Address of the land")
	private Address address;
	
	@Column(name = "number_of_farms")
	private Integer numberOfFarms;
	
	@ApiModelProperty(hidden=true)
	@ManyToMany(mappedBy = "lands",fetch=FetchType.EAGER)
	//@JsonBackReference
    private Set<LandOwner> owners;

	@ApiModelProperty(hidden=true)
	@ManyToMany(mappedBy = "lands",fetch=FetchType.EAGER)
    private Set<Farmer> farmers;
	
	@ApiModelProperty(hidden=true)
	@OneToMany(mappedBy="land")
	private Set<Farm> farms;
	
	@NotNull
	@Column(name = "description")
	@ApiModelProperty(notes = "description of the land")
	private String description;
	
	@ElementCollection
    @MapKeyColumn(name="imageId")
    @Column(name="imagePath")
    @CollectionTable(name="land_images", joinColumns=@JoinColumn(name="land_id"))
	private Map<String,String> images = new HashMap<String, String>();
	
	@Column(name = "latitude")
	private String latitude;
	
	@Column(name = "longitude")
	private String longitude;
		
	public String toJsonString() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
	}
}
