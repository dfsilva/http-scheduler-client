package br.com.diegosilva.sched.client.model;

import java.util.Objects;

public class HttpJobDetailRequest {

    private final String jobId;
    private final String description;
    private final String cron;
    private final String url;
    private final String method;
    private final String headerParams;
    private final String bodyParams;
    private final String queryParams;


    public HttpJobDetailRequest(String jobId, String description, String cron, String url, String method,
                                String headerParams, String bodyParams, String queryParams) {
        this.jobId = jobId;
        this.description = description;
        this.cron = cron;
        this.url = url;
        this.method = method;
        this.headerParams = headerParams;
        this.bodyParams = bodyParams;
        this.queryParams = queryParams;
    }

    public String getJobId() {
        return jobId;
    }

    public String getDescription() {
        return description;
    }

    public String getCron() {
        return cron;
    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }

    public String getHeaderParams() {
        return headerParams;
    }

    public String getBodyParams() {
        return bodyParams;
    }

    public String getQueryParams() {
        return queryParams;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HttpJobDetailRequest that = (HttpJobDetailRequest) o;
        return Objects.equals(jobId, that.jobId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobId);
    }
}
