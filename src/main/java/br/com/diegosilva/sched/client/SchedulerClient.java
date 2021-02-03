package br.com.diegosilva.sched.client;

import br.com.diegosilva.sched.client.model.HttpJobDetail;
import br.com.diegosilva.sched.client.model.HttpJobDetailRequest;
import br.com.diegosilva.sched.client.model.HttpJobDetailResponse;
import br.com.diegosilva.sched.client.utils.GsonUtils;
import br.com.diegosilva.sched.client.utils.HttpConnectionUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class SchedulerClient {


    private static SchedulerClient instance;

    private final Config config;

    public static class Config {
        private final String host;

        public Config(String host) {
            this.host = host;
        }
    }

    private SchedulerClient(Config config) {
        this.config = config;
    }

    public static void _init(Config config) {
        instance = new SchedulerClient(config);
    }

    public static SchedulerClient instance() {
        if (instance == null)
            throw new RuntimeException("You need to start your client first calling the method SchedulerClient.init(Config config)");
        return instance;
    }

    public CompletionStage<HttpJobDetailResponse> create(HttpJobDetail jobDetail) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Map<String, String> headers = new HashMap<>();
                headers.put(HttpConnectionUtils.HeaderKeys.ContentType, HttpConnectionUtils.ContentTypes.Json);

                HttpJobDetailRequest httpJobDetailRequest = new HttpJobDetailRequest(
                        jobDetail.getJobId(),
                        jobDetail.getDescription(),
                        jobDetail.getCron(),
                        jobDetail.getUrl(),
                        jobDetail.getMethod(),
                        GsonUtils.toJson(jobDetail.getHeaderParams()),
                        GsonUtils.toJson(jobDetail.getBodyParams()),
                        GsonUtils.toJson(jobDetail.getQueryParams())
                );
                final String body = GsonUtils.toJson(httpJobDetailRequest);
                String responseStr = HttpConnectionUtils.request(config.host + "/sched-api", HttpConnectionUtils.Method.POST, null, body, headers);
                return GsonUtils.fromJson(responseStr, HttpJobDetailResponse.class);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public CompletionStage<HttpJobDetailResponse> update(HttpJobDetail jobDetail) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Map<String, String> headers = new HashMap<>();
                headers.put(HttpConnectionUtils.HeaderKeys.ContentType, HttpConnectionUtils.ContentTypes.Json);

                HttpJobDetailRequest httpJobDetailRequest = new HttpJobDetailRequest(
                        jobDetail.getJobId(),
                        jobDetail.getDescription(),
                        jobDetail.getCron(),
                        jobDetail.getUrl(),
                        jobDetail.getMethod(),
                        GsonUtils.toJson(jobDetail.getHeaderParams()),
                        GsonUtils.toJson(jobDetail.getBodyParams()),
                        GsonUtils.toJson(jobDetail.getQueryParams())
                );
                final String body = GsonUtils.toJson(httpJobDetailRequest);
                String responseStr = HttpConnectionUtils.request(config.host + "/sched-api", HttpConnectionUtils.Method.PUT, null, body, headers);
                return GsonUtils.fromJson(responseStr, HttpJobDetailResponse.class);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public CompletionStage<Boolean> delete(String jobId) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Map<String, String> headers = new HashMap<>();
                headers.put(HttpConnectionUtils.HeaderKeys.ContentType, HttpConnectionUtils.ContentTypes.Json);
                String responseStr = HttpConnectionUtils.request(config.host + "/sched-api/"+jobId, HttpConnectionUtils.Method.DELETE, null, null, headers);
                return Boolean.getBoolean(responseStr);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
