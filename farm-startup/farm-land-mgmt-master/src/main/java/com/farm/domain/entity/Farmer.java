package com.farm.domain.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "farmer")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Farmer {
	
	@Id
	@Column(name = "farmer_id")
	@ApiModelProperty(notes = "Farmer Aadhar details")
	private String farmerId;
	
	@Column(name = "name")
	@ApiModelProperty(notes = "Farmer Name")
	private String name;
	
	@Column(name = "mobile_number")
	@ApiModelProperty(notes = "Farmer Number")
	private String mobileNumber;
	
	@Column(name = "image")
	@ApiModelProperty(notes = "Farmer Image")
	private String image;
	
	@ApiModelProperty(hidden=true, notes = "farmer assigned to respective land")
	@ManyToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
    @JoinTable(
        name = "farmer_land", 
        joinColumns = {@JoinColumn(name = "farmer_id")}, 
        inverseJoinColumns = {@JoinColumn(name = "land_id")}
    )
    Set<Land> lands;
}
