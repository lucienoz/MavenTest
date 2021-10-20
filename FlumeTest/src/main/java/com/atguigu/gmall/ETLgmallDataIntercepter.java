package com.atguigu.gmall;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.HostInterceptor;
import org.apache.flume.interceptor.Interceptor;
import org.mortbay.util.ajax.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;

/**
 * Description:
 * <p>
 * Create by lucienoz on 2021/9/17.
 * Copyright © 2021 lucienoz. All rights reserved.
 */
public class ETLgmallDataIntercepter implements Interceptor {

    private static final Logger logger = LoggerFactory
            .getLogger(ETLgmallDataIntercepter.class);
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        String body = new String(event.getBody(), StandardCharsets.UTF_8);
        try {
            JSON.parse(body);
            return event;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Event> intercept(List<Event> events) {
        Iterator<Event> iterator = events.iterator();
        while (iterator.hasNext()) {
            if (intercept(iterator.next())==null) {
                iterator.remove();
            }
        }
        return events;
    }

    @Override
    public void close() {

    }

    public static class Builder implements Interceptor.Builder{

        @Override
        public Interceptor build() {
            return new ETLgmallDataIntercepter();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
