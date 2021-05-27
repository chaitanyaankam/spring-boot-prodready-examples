package com.farm.domain.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.farm.domain.Name;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "land_owner")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LandOwner {

	@Id
	@Column(name = "owner_national_identifier")
	private String national_identifer;
	
	@Column(name = "id_type")
	private String idType;
	
	@Embedded
	@ApiModelProperty(notes = "Name of the owner")
	private Name name;
	
	@Column(name = "mobile_number")
	@ApiModelProperty(notes = "mobile number")
	private String mobile_number;
	
	@Column(name = "alt_mobile_number")
	@ApiModelProperty(notes = "alternate mobile number")
	private String alt_mobile_number;
	
	@Column(name = "email")
	@ApiModelProperty(notes = "email contact")
	private String email;
	
	@ApiModelProperty(hidden=true, notes = "lands respective of owner")
	@ManyToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
    @JoinTable(
        name = "land_landowner", 
        joinColumns = {@JoinColumn(name = "owner_id")}, 
        inverseJoinColumns = {@JoinColumn(name = "land_id")}
    )
	//@JsonManagedReference
    Set<Land> lands;

}
