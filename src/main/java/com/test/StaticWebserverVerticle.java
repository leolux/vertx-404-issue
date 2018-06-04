package com.test;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;

public class StaticWebserverVerticle extends AbstractVerticle {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void start() {
        Router router = Router.router(vertx);

        router.route().handler(StaticHandler.create("webroot"));

        router.route().handler(rc -> {
            logger.info("request on " + rc.request().absoluteURI());

            HttpServerResponse response = rc.response();
            int responseStatusCode = response.getStatusCode();

            logger.error("routingContext statusCode=" + rc.statusCode()); // -1
            logger.error("response statusCode=" + responseStatusCode); // 200

            rc.response().end();
        });

        HttpServerOptions httpServerOptions = new HttpServerOptions();
        HttpServer server = vertx.createHttpServer(httpServerOptions);
        server.requestHandler(router::accept).listen(9111);
    }


}
