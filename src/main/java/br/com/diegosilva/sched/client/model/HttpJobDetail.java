package br.com.diegosilva.sched.client.model;

import java.util.Map;
import java.util.Objects;

public class HttpJobDetail {

    private final String jobId;
    private final String description;
    private final String cron;
    private final String url;
    private final String method;
    private final Map<String, Object> headerParams;
    private final Map<String, Object> bodyParams;
    private final Map<String, Object> queryParams;


    public HttpJobDetail(String jobId, String description, String cron, String url, String method,
                         Map<String, Object> headerParams, Map<String, Object> bodyParams, Map<String, Object> queryParams) {
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

    public Map<String, Object> getHeaderParams() {
        return headerParams;
    }

    public Map<String, Object> getBodyParams() {
        return bodyParams;
    }

    public Map<String, Object> getQueryParams() {
        return queryParams;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HttpJobDetail that = (HttpJobDetail) o;
        return Objects.equals(jobId, that.jobId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobId);
    }
}
