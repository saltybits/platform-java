/**
 * Copyright (c) 2012 Salty Bits
 * Version 0.2.0
 * 
 * Permission is hereby granted, free of charge, to any person obtaining 
 * a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, 
 * and/or sell copies of the Software, and to permit persons to whom the Software
 * is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER 
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN 
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package co.saltybits.platform;

import java.util.ArrayList;
import java.util.List;

import co.saltybits.platform.handler.LinuxHandler;
import co.saltybits.platform.handler.MacHandler;
import co.saltybits.platform.handler.WindowsHandler;

public class Platform {
  private static List<PlatformHandler> platforms = new ArrayList<PlatformHandler>();
  
  static {
    platforms.add(new MacHandler());
    platforms.add(new WindowsHandler());
    platforms.add(new LinuxHandler());
  }
  
  public static String summary() {
    return String.format("%s %s %s, running Java %s (%s)", name(), version(), arch(), jre(), vm());
  }
  
  public static String name() { return System.getProperty("os.name", ""); }
  public static String version() { return System.getProperty("os.version", ""); }
  public static String arch() { return System.getProperty("os.arch", ""); }
  
  public static String jre() { return System.getProperty("java.version"); }
  
  public static String vm() { return System.getProperty("java.vm.name") + " from " + System.getProperty("java.vendor"); }
  
  public static boolean isMac()     { return getHandler() instanceof MacHandler; }
  public static boolean isWindows() { return getHandler() instanceof WindowsHandler; }
  public static boolean isLinux()     { return getHandler() instanceof LinuxHandler; }
  
  public static void execute(PlatformExecuter executer) {
    execute(executer, name().toLowerCase(), version().toLowerCase(), arch().toLowerCase());
  }
  
  // public mainly just for test purposes
  // assumes name, version, and string are already lowercased
  public static void execute(PlatformExecuter executer, String name, String version, String arch) {
    boolean unknown = true;
    PlatformHandler platform = getHandler(name);
    
    if (platform != null)
      unknown = platform.execute(executer, name, version, arch);
      
    if (unknown)
      executer.unknown(name, arch, version);
  }
  
  private static PlatformHandler getHandler() {
    return getHandler(name());
  }
  
  private static PlatformHandler getHandler(String name) {
    name = name.toLowerCase();
    
    for (PlatformHandler platform : platforms) {
      if (platform.accept(name))
        return platform;
    }
    
    return null;
  }
  
  public static void main(String[] args) {
    PlatformExecuter mac = new PlatformExecuter() {
      public void unknown(String name, String version, String arch) {
        System.err.println("You should buy a mac. Platform is: " + name + " " + version + " " + arch);
      }

      public void mac(String version) {
        System.out.print("You're on a mac");
      }
      
      public void macOS(String version) {
        System.out.print(" running Mac OS " + version);
      }
      
      public void macOSX(String version) {
        System.out.print(" running OS X " + version);
      }
      
      public void macPowerPC(String version) {
        System.out.print(" on a 32-bit PowerPC");
      }

      @Override
      public void mac32(String version) {
        System.out.print(" on a 32-bit intel machine");
      }

      public void mac64(String version) {
        System.out.print(" on a 64-bit intel machine");
      }
    };
    
    Platform.execute(mac);
    
    System.out.println("");
    System.out.flush();
    
    System.err.println(Platform.summary());
  }
}
