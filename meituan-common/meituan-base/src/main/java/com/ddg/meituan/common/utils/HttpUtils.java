package com.ddg.meituan.common.utils;

import org.springframework.http.*;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


public class HttpUtils {

    private static RestTemplate restTemplate = new RestTemplate();

    public static String HEADER_COOKIE = HttpHeaders.COOKIE;


    public static ResponseEntity<String> postForJson(String url, String paramJson, Map<String, String> headers) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpHeaders.add(entry.getKey(), entry.getValue());
            }
        }
        HttpEntity<String> request = new HttpEntity<String>(paramJson, httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, request, String.class);
        return responseEntity;
    }

    public ResponseEntity<String> postForJson(String url, String paramJson) {
        return postForJson(url, paramJson, null);
    }

    public ResponseEntity<String> postForForm(String url, Map<String, String> paramMap, Map<String, String> headers) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        if (paramMap != null) {
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                map.add(entry.getKey(), entry.getValue());
            }
        }


        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpHeaders.add(entry.getKey(), entry.getValue());
            }
        }

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, request, String.class);
        return responseEntity;
    }

    public ResponseEntity<String> postForForm(String url, Map<String, String> paramMap) {
        return postForForm(url, paramMap, null);
    }

    public ResponseEntity<String> get(String url, Map<String, String> paramMap, Map<String, String> headers) {
        Object[] values = new Object[]{};
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                multiValueMap.add(entry.getKey(), entry.getValue());
            }
        }
        StringBuffer sb = new StringBuffer(url);
        if (paramMap != null && paramMap.size() > 0) {
            values = new Object[paramMap.size()];
            sb.append("?");
            int index = 1;
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                sb.append(entry.getKey() + "={" + index + "}&");
                index++;
            }
        }
        HttpEntity<String> request = new HttpEntity<String>(null, multiValueMap);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url.toString(), HttpMethod.GET, request, String.class);
        return responseEntity;
    }

    public ResponseEntity<String> get(String url, Map<String, String> paramMap) {
        return get(url, paramMap, null);
    }
}
