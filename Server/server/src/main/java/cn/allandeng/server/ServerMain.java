package cn.allandeng.server;

/**
 * Hello world!
 *
 */
public class ServerMain 
{
    public static void main( String[] args )
    {
        System.out.println( "服务器已启动" );
        new CreateSocket().run();
    }
}
