package org.lagou;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class ZdyWebApplicationContext extends AnnotationConfigWebApplicationContext {

    private Tomcat tomcat;

    @Override
    protected void onRefresh() {
        super.onRefresh();
        System.out.println(Thread.currentThread().getName()+"========>spring bean周期创建完成，开始启动tomcat");
        this.tomcat = (Tomcat)super.getBean("tomcat");

        //  启动tomcat
        initialize();

    }

    private void initialize(){

        try {
            //  开启tomcat
            this.tomcat.start();

            //  开启另一条线程，启用请求监听
            Thread thread = new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"========>tomcat启动成功...异步线程执行开启请求监听");
                this.tomcat.getServer().await();
            });

            thread.start();
        } catch (LifecycleException e) {
            e.printStackTrace();
            try {
                this.tomcat.stop();
                this.tomcat.destroy();
            } catch (LifecycleException lifecycleException) {
                lifecycleException.printStackTrace();
            }
        }
    }
}
