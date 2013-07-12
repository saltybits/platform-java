package co.saltybits.platform.test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import co.saltybits.platform.Platform;
import co.saltybits.platform.PlatformExecuter;


@RunWith(MockitoJUnitRunner.class)

public class MacTest {
  private PlatformExecuter as(String name, String version, String arch) {
    PlatformExecuter executer = mock(PlatformExecuter.class);
    
    Platform.execute(executer, name, version, arch);
    
    return executer;
  }
  
  @Test
  public void names() {
    verify(as("Mac OS", "", "PowerPC")).mac(anyString());
    verify(as("Mac OS X", "", "ppc")).mac(anyString());
  }
  
  @Test
  public void architectures() {
    // power pc (32 bit)
    verify(as("Mac OS X", "", "ppc")).macPowerPC(anyString());
    
    // intel (32 bit)
    verify(as("Mac OS X", "", "i386")).mac32(anyString());
    
    // intel (64 bit)
    verify(as("Mac OS X", "", "x86_64")).mac64(anyString());
  }
}
