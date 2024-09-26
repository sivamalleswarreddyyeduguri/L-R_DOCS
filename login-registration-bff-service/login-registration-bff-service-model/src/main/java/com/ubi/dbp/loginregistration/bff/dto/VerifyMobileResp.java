package com.ubi.dbp.loginregistration.bff.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerifyMobileResp {
	
	private List<CustomerByCifReq> customerByCifReq;
	
	 private String respcode = "200";

		public VerifyMobileResp(List<CustomerByCifReq> customerByCifReq) {
			super();
			this.customerByCifReq = customerByCifReq;
			this.respcode = "200";
		}
}
