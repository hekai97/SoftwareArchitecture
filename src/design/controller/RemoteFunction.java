package design.controller;

import design.remoteapi.RemoteInterface;
import design.util.ConfigReader;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

/**
 * @author: hekai
 * @Date: 2021/12/1
 */
public class RemoteFunction {
    private final ConfigReader configReader=ConfigReader.getInstance();
    private final String host;
    private final int port;
    private Registry registry;
    private RemoteInterface RemoteFunctions = null;
    public RemoteFunction(){
        host=configReader.getKey("server_ip");
        port=Integer.parseInt(configReader.getKey("server_port"));
        try {
            registry=LocateRegistry.getRegistry(host,port);
            RemoteFunctions = (RemoteInterface) registry.lookup("executeSql");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
    public void execute(String sql) throws SQLException{
        try {
            RemoteFunctions.execute(sql);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public int verify(int id,String user,String password) throws RemoteException{
        return RemoteFunctions.verifyUserPassword(id,user,password);
    }
    public Object[][] getResult(RemoteInterface.MYOBJECT index, String sql) throws RemoteException {
        return RemoteFunctions.getResult(index, sql);
    }
    public String getStringByKey(String key,String sql) throws RemoteException {
        return RemoteFunctions.getStringByKey(key,sql);
    }
}
