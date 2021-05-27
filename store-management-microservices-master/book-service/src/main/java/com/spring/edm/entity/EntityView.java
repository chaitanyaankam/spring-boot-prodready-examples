package com.spring.edm.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="ENTITY_VIEW")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EntityView implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="entityViewSequence")
	@SequenceGenerator(name="entityViewSequence",sequenceName="ENTITY_VIEW_SEQ", allocationSize=1)
	@Column(name="ENTITY_VIEW_Id")
	private int entityViewId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ENTITY_ID", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JsonBackReference
	private StoreEntity storeEntity;
	
	@Column(name="ATTRIBUTE")
	private String attribute;
	
	@Column(name="ATTRIBUTE_LABEL")
	private String attributeLabel;
	
	@Column(name="INPUT_TYPE")
	private String inputType;
	
	@Column(name="IS_ENTITY")
	private boolean isEntity;
	
	@Column(name="COMPOSED_ENTITY")
	private String composedEntity;
	
}
