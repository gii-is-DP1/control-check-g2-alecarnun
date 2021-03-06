package org.springframework.samples.petclinic.feeding;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.pet.Pet;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="feedings")
public class Feeding extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "start_date")        
	@DateTimeFormat(pattern = "yyyy/MM/dd")
    @NotNull
    private LocalDate startDate;

    @Column(name = "weeks_duration")
    @NotNull
    @Min(1)
    private double weeksDuration;

    @ManyToOne
	@JoinColumn(name = "pet_id")
    @NotNull
    private Pet pet;
    
    @ManyToOne
	@JoinColumn(name = "feeding_type_id")
	private FeedingType feedingType;
}
