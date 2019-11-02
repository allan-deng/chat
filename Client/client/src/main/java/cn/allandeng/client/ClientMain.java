package cn.allandeng.client;

import cn.allandeng.common.UserDetailInfo;
import cn.allandeng.common.UserInfo;

/**
 * Hello world!
 *
 */
public class ClientMain 
{
	public static UserInfo userInfo;
	public static UserDetailInfo userDetailInfo;
    public static void main( String[] args )
    {
        System.out.println( "客户端已启动" );
        new ClientThread().start();
    }
}
