package cn.allandeng.client;

/**
 * Hello world!
 *
 */
public class ClientMain 
{
    public static void main( String[] args )
    {
        System.out.println( "客户端已启动" );
        new ClientThread().run();
    }
}
