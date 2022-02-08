package us.fatehi.jdbc_drivers;

import java.io.PrintWriter;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

public class Main {

  static {
    DriverManager.setLogWriter(new PrintWriter(System.err));
  }

  public static void main(final String[] args) {
    System.out.println(serviceLoadDrivers2());
  }

  /** CATCHES EXCEPTIONS SILENTLY, AND DOES NOT LOAD ANY DRIVERS. */
  public static String serviceLoaddrivers1() {
    final Enumeration<Driver> drivers = DriverManager.getDrivers();
    final List<Driver> driversList = Collections.list(drivers);
    return driversTable(driversList).toString();
  }

  /** FAILS TO LOAD ANY DRIVERS IF THERE IS AN EXCEPTION. */
  public static String serviceLoadDrivers2() {
    final ServiceLoader<Driver> loadedDrivers = ServiceLoader.load(Driver.class);
    final Iterator<Driver> driversIterator = loadedDrivers.iterator();
    final List<Driver> driversList = new ArrayList<>();
    driversIterator.forEachRemaining(driversList::add);
    return driversTable(driversList).toString();
  }

  private static StringBuilder driversTable(final List<Driver> driversList) {
    int index = 0;
    final StringBuilder buffer = new StringBuilder(1024);
    for (final Driver driver : driversList) {
      index++;
      buffer.append(
          String.format(
              "%2d %50s %2d.%d%n",
              index,
              driver.getClass().getName(),
              driver.getMajorVersion(),
              driver.getMinorVersion()));
    }
    return buffer;
  }
}
