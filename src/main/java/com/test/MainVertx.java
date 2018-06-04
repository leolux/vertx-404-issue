package com.test;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;

/**
 *
 */
public class MainVertx {

    public static void main(String[] args) throws InterruptedException {
        Vertx vertx = Vertx.vertx();
        startup(vertx);
    }

    public static void startup(Vertx vertx) throws InterruptedException {
        vertx.deployVerticle(StaticWebserverVerticle.class.getName(), new DeploymentOptions(), res -> {
            if (res.succeeded()) {
                System.out.println("Verticle deployed");
            } else {
                System.out.println("Verticle deployment failed");
            }
        });

    }


}
