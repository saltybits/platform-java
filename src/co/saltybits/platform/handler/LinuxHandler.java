package co.saltybits.platform.handler;

import co.saltybits.platform.PlatformExecuter;
import co.saltybits.platform.PlatformHandler;

public class LinuxHandler extends PlatformHandler {
  public boolean accept(String name) { return name.equals("linux"); }

  public boolean execute(PlatformExecuter executer, String name, String version, String arch) {
    boolean unknown = false;
    
    executer.linux(version);
    
    if (arch.equals("i386") || arch.equals("x86"))
      executer.linux32(version);
    else if (arch.equals("amd64") || arch.equals("x86_64"))
      executer.linux64(version);
    else
      unknown = true;
    
    return unknown;
  }
}
