package com.atguigu.gmall;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Description:
 * <p>
 * Create by lucienoz on 2021/9/18.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
public class EventHeaderTimestampInterceptor implements Interceptor {
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        String body = new String(event.getBody(), StandardCharsets.UTF_8);
        JSONObject jsonObject = JSON.parseObject(body);
        String ts = jsonObject.getString("ts");
        event.getHeaders().put("timestamp", ts);
        return event;
    }

    @Override
    public List<Event> intercept(List<Event> events) {
        ArrayList<Event> events1 = new ArrayList<>();

        for (Event event : events) {
            events1.add(intercept(event));
        }
        return events1;
    }

    @Override
    public void close() {

    }

    public static class Builder implements Interceptor.Builder{

        @Override
        public Interceptor build() {
            return new EventHeaderTimestampInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
