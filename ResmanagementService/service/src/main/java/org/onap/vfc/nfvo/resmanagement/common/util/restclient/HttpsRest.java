/*
 * Copyright 2017 Huawei Technologies Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.onap.vfc.nfvo.resmanagement.common.util.restclient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.eclipse.jetty.client.ContentExchange;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.HttpExchange;
import org.eclipse.jetty.io.ByteArrayBuffer;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.onap.vfc.nfvo.resmanagement.common.util.request.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpsRest extends HttpBaseRest {

    private static final Logger LOG = LoggerFactory.getLogger(HttpsRest.class);

    public void initHttpsRest() {
        SslContextFactory sslContextFactory = new SslContextFactory();
        client = new HttpClient(sslContextFactory);
        client.setConnectorType(HttpClient.CONNECTOR_SELECT_CHANNEL);
        client.setMaxConnectionsPerAddress(200); // max 200 concurrent connections to every address
        client.setThreadPool(new QueuedThreadPool(250)); // max 250 threads
        client.setTimeout(30000); // 30 seconds timeout; if no server reply, the request expires
        try {
            client.start();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public RestfulResponse get(String servicePath, RestfulParametes restParametes) throws ServiceException {
        return null;
    }

    @Override
    public RestfulResponse head(String servicePath, RestfulParametes restParametes, RestfulOptions options)
            throws ServiceException {
        return null;
    }

    @Override
    public RestfulResponse head(String servicePath, RestfulParametes restParametes) throws ServiceException {
        return null;
    }

    @Override
    public void asyncGet(String servicePath, RestfulParametes restParametes, RestfulAsyncCallback callback)
            throws ServiceException {

    }

    @Override
    public void asyncGet(String servicePath, RestfulParametes restParametes, RestfulOptions options,
            RestfulAsyncCallback callback) throws ServiceException {

    }

    @Override
    public RestfulResponse put(String servicePath, RestfulParametes restParametes) throws ServiceException {
        return null;
    }

    @Override
    public void asyncPut(String servicePath, RestfulParametes restParametes, RestfulAsyncCallback callback)
            throws ServiceException {

    }

    @Override
    public void asyncPut(String servicePath, RestfulParametes restParametes, RestfulOptions options,
            RestfulAsyncCallback callback) throws ServiceException {

    }

    @Override
    public RestfulResponse post(String servicePath, RestfulParametes restParametes) throws ServiceException {
        return null;
    }

    @Override
    public RestfulResponse post(String servicePath, RestfulParametes restParametes, RestfulOptions options)
            throws ServiceException {
        return null;
    }

    @Override
    public void asyncPost(String servicePath, RestfulParametes restParametes, RestfulAsyncCallback callback)
            throws ServiceException {

    }

    @Override
    public void asyncPost(String servicePath, RestfulParametes restParametes, RestfulOptions options,
            RestfulAsyncCallback callback) throws ServiceException {

    }

    @Override
    public RestfulResponse delete(String servicePath, RestfulParametes restParametes) throws ServiceException {
        return null;
    }

    @Override
    public void asyncDelete(String servicePath, RestfulParametes restParametes, RestfulAsyncCallback callback)
            throws ServiceException {

    }

    @Override
    public void asyncDelete(String servicePath, RestfulParametes restParametes, RestfulOptions options,
            RestfulAsyncCallback callback) throws ServiceException {

    }

    @Override
    public RestfulResponse patch(String servicePath, RestfulParametes restParametes) throws ServiceException {
        return null;
    }

    @Override
    public RestfulResponse patch(String servicePath, RestfulParametes restParametes, RestfulOptions options)
            throws ServiceException {
        return null;
    }

    @Override
    public void asyncPatch(String servicePath, RestfulParametes restParametes, RestfulAsyncCallback callback)
            throws ServiceException {

    }

    @Override
    public void asyncPatch(String servicePath, RestfulParametes restParametes, RestfulOptions options,
            RestfulAsyncCallback callback) throws ServiceException {

    }

    @Override
    public RestfulResponse get(String servicePath, RestfulParametes restParametes, RestfulOptions option)
            throws ServiceException {
        ContentExchange exchange = new ContentExchange(true);
        exchange.setURL(servicePath);
        exchange.setMethod("GET");
        restParametes.getHeaderMap().entrySet().stream()
                .forEach(entry -> exchange.setRequestHeader(entry.getKey(), entry.getValue()));

        try {
            client.send(exchange);
        } catch(IOException e) {
            e.printStackTrace();
        }
        try {
            int exchangeState = exchange.waitForDone();
            if(exchangeState == HttpExchange.STATUS_COMPLETED) {
                String res = exchange.getResponseContent();
                LOG.info(res);

                RestfulResponse restfulResponse = new RestfulResponse();
                restfulResponse.setResponseJson(exchange.getResponseContent());
                restfulResponse.setStatus(exchange.getResponseStatus());
                return restfulResponse;
            } else if(exchangeState == HttpExchange.STATUS_EXCEPTED) {
                throw new ServiceException(
                        "request is exception: " + RestHttpContentExchange.toState(HttpExchange.STATUS_EXCEPTED));
            } else if(exchangeState == HttpExchange.STATUS_EXPIRED) {
                throw new ServiceException(
                        "request is expierd: " + RestHttpContentExchange.toState(HttpExchange.STATUS_EXPIRED));
            }
        } catch(InterruptedException e) {
            e.printStackTrace();
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public RestfulResponse put(String servicePath, RestfulParametes restParametes, RestfulOptions options)
            throws ServiceException {
        ContentExchange exchange = new ContentExchange(true);
        exchange.setURL(servicePath);
        exchange.setMethod("PUT");
        exchange.setRequestContent(new ByteArrayBuffer(restParametes.getRawData()));

        restParametes.getHeaderMap().entrySet().stream()
                .forEach(entry -> exchange.setRequestHeader(entry.getKey(), entry.getValue()));

        try {
            client.send(exchange);
        } catch(IOException e) {
            e.printStackTrace();
        }

        try {
            int exchangeState = exchange.waitForDone();
            if(exchangeState == HttpExchange.STATUS_COMPLETED) {
                String res = exchange.getResponseContent();
                LOG.info(res);

                RestfulResponse restfulResponse = new RestfulResponse();
                restfulResponse.setResponseJson(exchange.getResponseContent());
                restfulResponse.setStatus(exchange.getResponseStatus());
                return restfulResponse;
            } else if(exchangeState == HttpExchange.STATUS_EXCEPTED) {
                throw new ServiceException(
                        "request is exception: " + RestHttpContentExchange.toState(HttpExchange.STATUS_EXCEPTED));
            } else if(exchangeState == HttpExchange.STATUS_EXPIRED) {
                throw new ServiceException(
                        "request is expierd: " + RestHttpContentExchange.toState(HttpExchange.STATUS_EXPIRED));
            }
        } catch(InterruptedException e) {
            e.printStackTrace();
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public RestfulResponse delete(String servicePath, RestfulParametes restParametes, RestfulOptions options)
            throws ServiceException {
        ContentExchange exchange = new ContentExchange(true);

        String encodeParams = RequestUtil.encodeParams(restParametes);
        if(encodeParams.isEmpty()) {
            exchange.setURL(servicePath);
        } else {
            exchange.setURL(servicePath + "?" + encodeParams);
        }
        exchange.setMethod("DELETE");
        if(restParametes.getRawData() != null) {
            exchange.setRequestContent(new ByteArrayBuffer(restParametes.getRawData()));
        }

        restParametes.getHeaderMap().entrySet().stream()
                .forEach(entry -> exchange.setRequestHeader(entry.getKey(), entry.getValue()));

        try {
            client.send(exchange);
        } catch(IOException e) {
            e.printStackTrace();
        }

        try {
            int exchangeState = exchange.waitForDone();
            if(exchangeState == HttpExchange.STATUS_COMPLETED) {
                String res = exchange.getResponseContent();
                LOG.info(res);

                RestfulResponse restfulResponse = new RestfulResponse();
                restfulResponse.setResponseJson(exchange.getResponseContent());
                restfulResponse.setStatus(exchange.getResponseStatus());
                return restfulResponse;
            } else if(exchangeState == HttpExchange.STATUS_EXCEPTED) {
                throw new ServiceException(
                        "request is exception: " + RestHttpContentExchange.toState(HttpExchange.STATUS_EXCEPTED));
            } else if(exchangeState == HttpExchange.STATUS_EXPIRED) {
                throw new ServiceException(
                        "request is expierd: " + RestHttpContentExchange.toState(HttpExchange.STATUS_EXPIRED));
            }
        } catch(InterruptedException e) {
            e.printStackTrace();
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
