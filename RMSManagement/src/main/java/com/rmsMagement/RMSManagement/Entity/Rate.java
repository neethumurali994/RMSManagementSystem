package com.rmsMagement.RMSManagement.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rate {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	private String rateDescription;
	@NotNull
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date rateEffectiveDate;
	@NotNull
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date rateExpirationDate;
	@NotNull
	private int amount;
	
}
