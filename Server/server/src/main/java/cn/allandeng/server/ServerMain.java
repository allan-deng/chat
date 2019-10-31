package cn.allandeng.server;

/**
 * Hello world!
 *
 */
public class ServerMain 
{
    public static void main( String[] args )
    {
        System.out.println( "开始运行" );
        new CreateSocket().run();
    }
}
