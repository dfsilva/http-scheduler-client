package br.com.diegosilva.sched.client.model;

public class HttpJobDetail {

    private String jobId;
    private String description;
    private String cron;
    private String url;
    private String method;



//    data class HttpJobDetail(@Id val jobId: String,
//                             val description: String,
//                             val cron: String,
//                             val url: String,
//                             val method: String,
//                             val headerParams: String?,
//                             val bodyParams: String?,
//                             val queryParams: String?) : Persistable<String> {
//
//        @Transient @JsonIgnore var isNewRow:Boolean = true
//
//        override fun getId(): String? = jobId
//        override fun isNew(): Boolean = isNewRow
//    }
}
