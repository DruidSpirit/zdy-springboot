package org.lagou;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class SpringApplication {

    /**
     * 存储容器到本地线程中，方便调用
      */
    public static AnnotationConfigWebApplicationContext applicationContext;
    /**
     * 启动主类
     */
    private Class rootClass;

    private SpringApplication( Class rootClass ) {
        this.rootClass = rootClass;
        init();
    }

    public static SpringApplication run( Class rootClass ){
        return new SpringApplication( rootClass );
    }

    public void init(){

        //  打印banner
        printBanner();

        //  创建应用上下文
        System.out.println(Thread.currentThread().getName()+"========>开始创建应用上下文环境");
        AnnotationConfigWebApplicationContext applicationContext = new ZdyWebApplicationContext();
        this.applicationContext = applicationContext;

        //  注册主启动类
        System.out.println(Thread.currentThread().getName()+"========>开始注册主启动类到容器中");
        applicationContext.register(rootClass);

        //  刷新应用(进入spring周期)
        System.out.println(Thread.currentThread().getName()+"========>开始进入spring执行周期");
        applicationContext.refresh();

    }

    private void printBanner(){
        System.out.println("启动自定义springboot");
        System.out.println(
                "  .--,       .--,\n" +
                        " ( (  \\.---./  ) )\n" +
                        "  '.__/o   o\\__.'\n" +
                        "     {=  ^  =}\n" +
                        "      >  -  <\n" +
                        "     /       \\\n" +
                        "    //       \\\\\n" +
                        "   //|   .   |\\\\\n" +
                        "   \"'\\       /'\"_.-~^`'-.\n" +
                        "      \\  _  /--'         `\n" +
                        "    ___)( )(___\n" +
                        "   (((__) (__)))    梦想是个奇怪的东西,像是勇敢的挑战,更像是天真的逃避的方式。\n"
        );
    }

}
