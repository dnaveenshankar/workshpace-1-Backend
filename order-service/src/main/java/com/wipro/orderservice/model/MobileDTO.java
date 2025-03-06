package com.wipro.orderservice.model;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MobileDTO {
	
	private int moileID;
	private String mobileName;
	private double price;
	private LocalDate mfd;
	private String companyName;
}
