package com.ubi.dbp.loginregistration.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerInqByMobResp {
		
	
	
	private List<CustAcctView> custAcctView;
}
