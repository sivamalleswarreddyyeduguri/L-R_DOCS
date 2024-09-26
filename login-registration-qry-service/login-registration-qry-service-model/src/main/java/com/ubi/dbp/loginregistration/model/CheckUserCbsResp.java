package com.ubi.dbp.loginregistration.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckUserCbsResp {
	
	  private String cif;
	  private String fname;
	  private String mobNum;
}
