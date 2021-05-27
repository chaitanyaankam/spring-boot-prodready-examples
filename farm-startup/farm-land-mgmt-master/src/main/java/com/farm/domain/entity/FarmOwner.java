package com.farm.domain.entity;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.farm.domain.Address;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "farm_owner")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FarmOwner {

	@Id
	@Column(name = "farm_owner_id")
	@ApiModelProperty(notes = "Farm Owner ID/User ID to be obtained from User Authentication")
	private String plotOwnerId;
	
	@Embedded
	@AttributeOverrides(value = {
            @AttributeOverride(name = "addressLine1", column = @Column(name="address_line1")),
            @AttributeOverride(name = "addressLine2", column = @Column(name="land_mark"))
    })
	@ApiModelProperty(notes = "Address of the farm owner")
	private Address address;
	
	@ApiModelProperty(hidden=true, notes = "farms of owner")
	@OneToMany(mappedBy="farmOwner")
	private List<Farm> farms;
	
}
