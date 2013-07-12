package co.saltybits.platform;

public abstract class PlatformExecuter {
  // UNKNOWN
  public void unknown(String name, String version, String arch) {};
  
  // MAC
  // Mac OS and PPC are on their way out. naming conventions
  public void mac(String version) {};
  
  // not sure if it is necessary to break these out (similar to breaking out XP, Vista, etc)
  public void macOS(String version) {};
  public void macOSX(String version) {};
  
  public void macPowerPC(String version) {}; // only 32-bit available
  public void mac32(String version) {};
  public void mac64(String version) {};
  
  // WINDOWS
  public void windows(String version) {};
  public void windows32(String version) {};
  public void windows64(String version) {};
  
  // LINUX
  public void linux(String version) {};
  public void linux32(String version) {};
  public void linux64(String version) {};
  public void linuxARMv6(String version) {};
  public void linuxARMv6hf(String version) {};
  
  // ANDROID
  public void android(String version) {};
  public void androidARMv6(String version) {};
}
