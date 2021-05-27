package com.farm.domain.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "farm_status")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FarmStatus {

	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="farmStatusSequence")
	@SequenceGenerator(name="farmStatusSequence",sequenceName="farm_status_seq", allocationSize=1)
	@Column(name = "farm_status_id")
	private String farmStatusId;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "message")
	private String message;
	
	@Column(name= "last_updated_time")
	private Timestamp lastUpdatedTime;
	
	@ManyToOne
	@JoinColumn(name = "farm_id")
	private Farm farm;
}
