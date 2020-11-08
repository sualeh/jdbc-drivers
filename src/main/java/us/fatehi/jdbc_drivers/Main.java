package us.fatehi.jdbc_drivers;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.ServiceLoader;

public class Main {

  public static void main(String[] args) {
    printDrivers3();
  }

  private static void printDrivers1() {
    int index = 0;
    DriverManager.setLogStream(System.err);
    final Enumeration<Driver> e = DriverManager.getDrivers();
    while (e.hasMoreElements()) {
      index++;
      final Driver driver = e.nextElement();
      System.out.print(index);
      System.out.print(" ");
      System.out.print(driver.getClass().getName());
      System.out.print(" ");
      System.out.print(driver.getMajorVersion());
      System.out.print(".");
      System.out.print(driver.getMinorVersion());
      System.out.println();
    }
  }

  private static void printDrivers2() {
    int index = 0;
    final StringBuilder buffer = new StringBuilder(1024);
    for (final Driver driver : Collections.list(DriverManager.getDrivers())) {
      index++;
      buffer.append(
          String.format(
              "%2d %50s %2d.%d%n",
              index,
              driver.getClass().getName(),
              driver.getMajorVersion(),
              driver.getMinorVersion()));
    }
    System.out.println(buffer);
  }

  private static void printDrivers3() {
    int index = 0;
    final ServiceLoader<Driver> loadedDrivers = ServiceLoader.load(Driver.class);
    final Iterator<Driver> driversIterator = loadedDrivers.iterator();
    while (driversIterator.hasNext()) {
      index++;
      final Driver driver = driversIterator.next();
      System.out.print(index);
      System.out.print(" ");
      System.out.print(driver.getClass().getName());
      System.out.print(" ");
      System.out.print(driver.getMajorVersion());
      System.out.print(".");
      System.out.print(driver.getMinorVersion());
      System.out.println();
    }
  }
}
