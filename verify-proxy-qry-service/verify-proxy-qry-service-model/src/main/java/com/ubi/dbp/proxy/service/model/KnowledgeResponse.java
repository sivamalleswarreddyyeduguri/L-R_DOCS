package com.ubi.dbp.proxy.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KnowledgeResponse {

    private List<QuestionsResponse> questions;
}
