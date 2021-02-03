package br.com.diegosilva.sched.client;

import br.com.diegosilva.sched.client.model.HttpJobDetail;
import br.com.diegosilva.sched.client.model.HttpJobDetailRequest;
import com.cronutils.builder.CronBuilder;
import com.cronutils.model.Cron;
import com.cronutils.model.CronType;
import com.cronutils.model.definition.CronDefinitionBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.cronutils.model.field.expression.FieldExpressionFactory.*;

public class Application {

    public static void main(String[] args) throws IOException {

        SchedulerClient._init(new SchedulerClient.Config("http://192.168.31.45:8181"));

        Map<String, Object> headerParams = new HashMap<>();
        headerParams.put("Authorization", "Token 123456");
        headerParams.put("Content-Type", "application/json;");

        Cron cron = CronBuilder.cron(CronDefinitionBuilder.instanceDefinitionFor(CronType.QUARTZ))
                .withYear(questionMark())
                .withMonth(always())
                .withDoW(always())
                .withHour(always())
                .withMinute(always())
                .withSecond(every(on(0), 10))
                .instance();
        String cronAsString = cron.asString();

        SchedulerClient.instance().delete("finalizar_projeto_1").thenAccept(response -> {

            System.out.println("Excluiu job: "+response);

            SchedulerClient.instance().create(new HttpJobDetail("finalizar_projeto_1",
                    "Job para finalizar o projeto 1",
                    cronAsString, "http://192.168.31.45:8081/api/internal/change-project-status/1/F",
                    "POST", headerParams, new HashMap<>(), new HashMap<>()))
                    .thenAccept(httpJobDetailResponse -> {
                        System.out.println("OK....");
                    }).exceptionally(error-> {
                error.printStackTrace();
                return null;
            });
        });

        System.in.read();
    }

}
