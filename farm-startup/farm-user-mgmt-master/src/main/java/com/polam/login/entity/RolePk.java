package com.polam.login.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RolePk implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(nullable=false, name="user_id")
	private String userid;
	
	@Column(nullable=false)
	private String role;
}
