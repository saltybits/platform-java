package co.saltybits.platform.handler;

import co.saltybits.platform.PlatformExecuter;
import co.saltybits.platform.PlatformHandler;

public class MacHandler extends PlatformHandler {
  public boolean accept(String name) { return name.startsWith("mac os") || name.startsWith("darwin"); }

  public boolean execute(PlatformExecuter executer, String name, String version, String arch) {
    boolean unknown = false;
    
    executer.mac(version);
    
    if (name.equals("mac os")) {
      executer.macOS(version);
      
      if (arch.equals("powerpc"))
        executer.macPowerPC(version); // 64 bit not available on mac os ppc
      else
        unknown = true;
    } else if (name.equals("mac os x")) {
      executer.macOSX(version);
      
      if (arch.equals("ppc"))
        executer.macPowerPC(version);
      else if (arch.equals("i386"))
        executer.mac32(version);
      else if (arch.equals("x86_64"))
        executer.mac64(version);
      else
        unknown = true;
    } else if (name.equals("darwin")) { // openjdk
      // reports architecture as universal??
    }
    
    return unknown;
  }
}
