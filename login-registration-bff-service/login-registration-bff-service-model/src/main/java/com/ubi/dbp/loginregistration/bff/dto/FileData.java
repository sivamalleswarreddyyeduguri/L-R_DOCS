package com.ubi.dbp.loginregistration.bff.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
public class FileData {
	String fileName;
	String data;
}
