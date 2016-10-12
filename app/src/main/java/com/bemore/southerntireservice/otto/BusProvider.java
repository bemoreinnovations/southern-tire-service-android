package com.bemore.southerntireservice.otto;

import com.squareup.otto.ThreadEnforcer;

/**
 * Maintains a singleton instance for obtaining the bus. Ideally this would be replaced with a more efficient means
 * such as through injection directly into interested classes.
 */
public final class BusProvider {
  private static final AndroidBus BUS = new AndroidBus(ThreadEnforcer.ANY);
  private static final ScopedBus SCOPED_BUS = new ScopedBus();

  public static AndroidBus getInstance() {
    return BUS;
  }

  public static ScopedBus getScopedBus() {
    return SCOPED_BUS;
  }

  private BusProvider() {
    // No instances.
  }
}