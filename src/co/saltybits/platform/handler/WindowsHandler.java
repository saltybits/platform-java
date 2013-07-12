package co.saltybits.platform.handler;

import co.saltybits.platform.PlatformExecuter;
import co.saltybits.platform.PlatformHandler;

public class WindowsHandler extends PlatformHandler {
  public boolean accept(String name) { return name.startsWith("windows"); }

  public boolean execute(PlatformExecuter executer, String name, String version, String arch) {
    boolean unknown = false;
    String versionName = name.substring("windows ".length());
    
    executer.windows(versionName); // version name much more useful than version number in windows' case
    
    if (arch.equals("x86"))
      executer.windows32(versionName);
    else if (arch.equals("amd64") || arch.equals("x86_64"))
      executer.windows64(versionName);
    else
      unknown = true;
    
    return unknown;
  }
}
