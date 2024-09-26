package com.ubi.dbp.loginregistration.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DbpResp {
	
	  private String cif;
	  private String name;
	  private String mobileNumber;
	  private String email;
	  
	  private String respcode = "200";
	  
	  
	  
	  
}
