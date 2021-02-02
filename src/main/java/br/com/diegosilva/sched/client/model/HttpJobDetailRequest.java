package br.com.diegosilva.sched.client.model;

import java.util.Map;

public class HttpJobDetailRequest {

    private String jobId;
    private String description;
    private String cron;
    private String url;
    private String method;
    private Map<String, Object> headerParams;
    private Map<String, Object> bodyParams;
    private Map<String, Object> queryParams;


}
