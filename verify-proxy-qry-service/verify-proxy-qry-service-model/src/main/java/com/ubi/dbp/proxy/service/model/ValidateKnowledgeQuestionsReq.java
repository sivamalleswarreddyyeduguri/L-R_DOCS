package com.ubi.dbp.proxy.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidateKnowledgeQuestionsReq {

        private String knowledgequestionanswer0;
        private String knowledgequestionuniqueid0;
        private String knowledgequestionanswer1;
        private String knowledgequestionuniqueid1;
        private String knowledgequestionscount;
        private String cif;
        private String operation;
}
