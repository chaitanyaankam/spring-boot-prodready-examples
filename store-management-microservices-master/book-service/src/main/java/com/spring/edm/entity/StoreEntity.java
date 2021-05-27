package com.spring.edm.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="ENTITY")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="entitySequence")
	@SequenceGenerator(name="entitySequence",sequenceName="ENTITY_SEQ", allocationSize=1)
	@Column(name="ENTITY_Id")
	private int entityId;

	@Column(name="NAME")
	private String name;

	@JsonManagedReference
	@RestResource(path = "entity-views", rel="entity-views")
	@OneToMany(mappedBy="storeEntity", fetch=FetchType.LAZY)
	private List<EntityView> entityView;
	
}
