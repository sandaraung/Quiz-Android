package com.cloudsource.quiz_android;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by azumag on 2016/08/10.
 */
public class RequestToJsonTest {
    String url = "https://blooming-depths-53477.herokuapp.com/categories/";

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void testDoInBackground() throws InterruptedException{
        HogeAsyncTaskExt task = new HogeAsyncTaskExt();
        task.setUrl(url);
        boolean result = task.doInBackground(url);
        assertThat(result, is(true));
    }


    //テスト対象のクラスをoverrideで書き換える
    //doInBackgroundがpublicになっている。
    class HogeAsyncTaskExt extends RequestToJson{
        public boolean results = false;

        @Override
        public Boolean doInBackground(String... strings) {
            return super.doInBackground(strings);
        }

    }
}