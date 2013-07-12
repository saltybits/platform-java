package co.saltybits.platform;


public abstract class PlatformHandler {
  public abstract boolean accept(String name);
  public abstract boolean execute(PlatformExecuter executer, String name, String version, String arch);
}
