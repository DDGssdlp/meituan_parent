package com.ddg.meituan.common.config;


/**
 * Description:
 * ========================================================================
 * ------------------------------------------------------------------------
 *
 * @author Edison
 * @version 1.0
 * <p>
 * ========================================================================
 * @date 2021/2/4 16:26
 * @email: wangzhijie0908@gmail.com
 */
/*
@Configuration
public class ElasticSearchConfig {

    private static final String host = "49.232.68.245";

    public static final RequestOptions COMMON_OPTIONS;

    static {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
//		builder.addHeader("Authorization", "Bearer " + TOKEN);
//		builder.setHttpAsyncResponseConsumerFactory(
//				new HttpAsyncResponseConsumerFactory
//						.HeapBufferedResponseConsumerFactory(30 * 1024 * 1024 * 1024));
        COMMON_OPTIONS = builder.build();
    }

    @Bean
    public RestHighLevelClient esRestClient() {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost(host, 9200, "http")));
        return client;
    }
}
*/
