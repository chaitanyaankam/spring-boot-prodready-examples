package com.polam.login.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "role")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {

	@EmbeddedId
	private RolePk rolePk;
	
	@Column(name="created_on")
	private Timestamp createdOn;
	
	@Column(name="created_by")
	private String createdBy;
	
}
