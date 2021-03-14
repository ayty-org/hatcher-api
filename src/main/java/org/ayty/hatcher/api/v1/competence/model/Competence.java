package org.ayty.hatcher.api.v1.competence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Competence {
	
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "competence_generator")
	@SequenceGenerator(name = "competence_generator", sequenceName = "competence_sequence", allocationSize = 1)
	@Id
	private Long id;
	
	@NotEmpty(message="O campo nome não pode estar vazio")
	@Size(min=10, max=200, message="O nome deve ter entre 10 e 200 caracteres")
	private String name;
	
	@NotEmpty(message="O campo descrição não pode estar vazio")
	@Size(min=10, max=1000, message="A descrição deve ter entre 10 e 1000 caracteres")
	private String description;
	
	@Enumerated(EnumType.STRING)
	@Column(name="type_competence")
	private Type type;
	
}