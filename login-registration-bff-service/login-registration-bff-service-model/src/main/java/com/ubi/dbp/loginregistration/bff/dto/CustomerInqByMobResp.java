package com.ubi.dbp.loginregistration.bff.dto;

import java.util.List;

import lombok.Data;

@Data
public class CustomerInqByMobResp {
		
	private List<CustAcctView> custAcctViews;
}
