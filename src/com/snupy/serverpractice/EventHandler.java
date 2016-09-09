package com.snupy.serverpractice;

import java.io.InputStream;

/**
 * Created by goznauk on 9/9/16.
 */
public interface EventHandler {

    String getHandler();

    void handleEvent(InputStream inputStream);

}
