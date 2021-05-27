package com.farm.domain.model;

import com.farm.domain.LandSize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LandFarmAssociation {

	private Integer landId;
	
	private LandSize size;
	
	private Integer numberOfplots;
}
