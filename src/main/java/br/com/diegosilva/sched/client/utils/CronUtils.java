package br.com.diegosilva.sched.client.utils;

import com.cronutils.builder.CronBuilder;
import com.cronutils.model.Cron;
import com.cronutils.model.CronType;
import com.cronutils.model.definition.CronDefinitionBuilder;

import java.util.Calendar;
import java.util.Date;

import static com.cronutils.model.field.expression.FieldExpressionFactory.on;
import static com.cronutils.model.field.expression.FieldExpressionFactory.questionMark;

public class CronUtils {

    public static String getFromFixedTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Cron cron = CronBuilder.cron(CronDefinitionBuilder.instanceDefinitionFor(CronType.QUARTZ))
                .withYear(on(calendar.get(Calendar.YEAR)))
                .withDoM(on(calendar.get(Calendar.DAY_OF_MONTH)))
                .withMonth(on(calendar.get(Calendar.MONTH) + 1))
                .withDoW(questionMark())
                .withHour(on(calendar.get(Calendar.HOUR_OF_DAY)))
                .withMinute(on(calendar.get(Calendar.MINUTE)))
                .withSecond(on(calendar.get(Calendar.SECOND)))
                .instance();
        return cron.asString();
    }
}
