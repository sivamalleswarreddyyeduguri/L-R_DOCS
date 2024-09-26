package com.ubi.dbp.loginregistration.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Questions {

    private String id;
    private String question;
    private String answer;
}
