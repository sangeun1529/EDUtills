package nest.hava.edutills.logger;

public interface Logger {

  public boolean isDebugEnabled();

  public int getLevel() ;
  public void setLevel(int level);
  /**
   * Log a message at the DEBUG level.
   *
   * @param msg the message string to be logged
   */
  public void debug(String clazz, String msg);
  
  public void debug( String clazz, String method, String msg);


  /**
   * Log a message at the DEBUG level according to the specified format
   * and argument.
   * <p/>
   * <p>This form avoids superfluous object creation when the logger
   * is disabled for the DEBUG level. </p>
   *
   * @param format the format string
   * @param arg    the argument
   */
  public void debug( String clazz,String format, Object arg);
  public void debug( String clazz,String method,String format, Object arg);


  /**
   * Log a message at the DEBUG level according to the specified format
   * and arguments.
   * <p/>
   * <p>This form avoids superfluous object creation when the logger
   * is disabled for the DEBUG level. </p>
   *
   * @param format the format string
   * @param arg1   the first argument
   * @param arg2   the second argument
   */
  public void debug( String clazz,String format, Object arg1, Object arg2);
  public void debug( String clazz,String method,String format, Object arg1, Object arg2);

  /**
   * Log a message at the DEBUG level according to the specified format
   * and arguments.
   * <p/>
   * <p>This form avoids superfluous string concatenation when the logger
   * is disabled for the DEBUG level. However, this variant incurs the hidden
   * (and relatively small) cost of creating an <code>Object[]</code> before invoking the method,
   * even if this logger is disabled for DEBUG. The variants taking
   * {@link #debug(String, Object) one} and {@link #debug(String, Object, Object) two}
   * arguments exist solely in order to avoid this hidden cost.</p>
   *
   * @param format    the format string
   * @param arguments a list of 3 or more arguments
   */
  public void debug( String clazz,String format, Object... arguments);
  public void debug( String clazz,String method,String format, Object... arguments);

  /**
   * Log an exception (throwable) at the DEBUG level with an
   * accompanying message.
   *
   * @param msg the message accompanying the exception
   * @param t   the exception (throwable) to log
   */
  public void debug( String clazz,String msg, Throwable t);
  public void debug( String clazz,String method,String msg, Throwable t);

  /**
   * Is the logger instance enabled for the INFO level?
   *
   * @return True if this Logger is enabled for the INFO level,
   *         false otherwise.
   */
  public boolean isInfoEnabled();


  /**
   * Log a message at the INFO level.
   *
   * @param msg the message string to be logged
   */
  public void info( String clazz,String msg);
  public void info( String clazz,String method,String msg);


  /**
   * Log a message at the INFO level according to the specified format
   * and argument.
   * <p/>
   * <p>This form avoids superfluous object creation when the logger
   * is disabled for the INFO level. </p>
   *
   * @param format the format string
   * @param arg    the argument
   */
  public void info( String clazz,String format, Object arg);
  public void info( String clazz,String method,String format, Object arg);


  /**
   * Log a message at the INFO level according to the specified format
   * and arguments.
   * <p/>
   * <p>This form avoids superfluous object creation when the logger
   * is disabled for the INFO level. </p>
   *
   * @param format the format string
   * @param arg1   the first argument
   * @param arg2   the second argument
   */
  public void info( String clazz,String format, Object arg1, Object arg2);
  public void info( String clazz,String method,String format, Object arg1, Object arg2);

  /**
   * Log a message at the INFO level according to the specified format
   * and arguments.
   * <p/>
   * <p>This form avoids superfluous string concatenation when the logger
   * is disabled for the INFO level. However, this variant incurs the hidden
   * (and relatively small) cost of creating an <code>Object[]</code> before invoking the method,
   * even if this logger is disabled for INFO. The variants taking
   * {@link #info(String, Object) one} and {@link #info(String, Object, Object) two}
   * arguments exist solely in order to avoid this hidden cost.</p>
   *
   * @param format    the format string
   * @param arguments a list of 3 or more arguments
   */
  public void info( String clazz,String format, Object... arguments);
  public void info( String clazz,String method,String format, Object... arguments);

  /**
   * Log an exception (throwable) at the INFO level with an
   * accompanying message.
   *
   * @param msg the message accompanying the exception
   * @param t   the exception (throwable) to log
   */
  public void info( String clazz,String msg, Throwable t);
  public void info( String clazz,String method,String msg, Throwable t);


  /**
   * Is the logger instance enabled for the WARN level?
   *
   * @return True if this Logger is enabled for the WARN level,
   *         false otherwise.
   */
  public boolean isWarnEnabled();

  /**
   * Log a message at the WARN level.
   *
   * @param msg the message string to be logged
   */
  public void warn( String clazz,String msg);
  public void warn( String clazz,String method,String msg);

  /**
   * Log a message at the WARN level according to the specified format
   * and argument.
   * <p/>
   * <p>This form avoids superfluous object creation when the logger
   * is disabled for the WARN level. </p>
   *
   * @param format the format string
   * @param arg    the argument
   */
  public void warn( String clazz,String format, Object arg);
  public void warn( String clazz,String method,String format, Object arg);


  /**
   * Log a message at the WARN level according to the specified format
   * and arguments.
   * <p/>
   * <p>This form avoids superfluous string concatenation when the logger
   * is disabled for the WARN level. However, this variant incurs the hidden
   * (and relatively small) cost of creating an <code>Object[]</code> before invoking the method,
   * even if this logger is disabled for WARN. The variants taking
   * {@link #warn(String, Object) one} and {@link #warn(String, Object, Object) two}
   * arguments exist solely in order to avoid this hidden cost.</p>
   *
   * @param format    the format string
   * @param arguments a list of 3 or more arguments
   */
  public void warn( String clazz,String format, Object... arguments);
  public void warn( String clazz,String method,String format, Object... arguments);

  /**
   * Log a message at the WARN level according to the specified format
   * and arguments.
   * <p/>
   * <p>This form avoids superfluous object creation when the logger
   * is disabled for the WARN level. </p>
   *
   * @param format the format string
   * @param arg1   the first argument
   * @param arg2   the second argument
   */
  public void warn( String clazz,String format, Object arg1, Object arg2);
  public void warn( String clazz,String method,String format, Object arg1, Object arg2);

  /**
   * Log an exception (throwable) at the WARN level with an
   * accompanying message.
   *
   * @param msg the message accompanying the exception
   * @param t   the exception (throwable) to log
   */
  public void warn( String clazz,String msg, Throwable t);
  public void warn( String clazz,String method,String msg, Throwable t);


  /**
   * Is the logger instance enabled for the ERROR level?
   *
   * @return True if this Logger is enabled for the ERROR level,
   *         false otherwise.
   */
  public boolean isErrorEnabled();

  /**
   * Log a message at the ERROR level.
   *
   * @param msg the message string to be logged
   */
  public void error( String clazz,String msg);
  public void error( String clazz,String method,String msg);

  /**
   * Log a message at the ERROR level according to the specified format
   * and argument.
   * <p/>
   * <p>This form avoids superfluous object creation when the logger
   * is disabled for the ERROR level. </p>
   *
   * @param format the format string
   * @param arg    the argument
   */
  public void error( String clazz,String format, Object arg);
  public void error( String clazz,String method,String format, Object arg);

  /**
   * Log a message at the ERROR level according to the specified format
   * and arguments.
   * <p/>
   * <p>This form avoids superfluous object creation when the logger
   * is disabled for the ERROR level. </p>
   *
   * @param format the format string
   * @param arg1   the first argument
   * @param arg2   the second argument
   */
  public void error( String clazz,String format, Object arg1, Object arg2);
  public void error( String clazz,String method,String format, Object arg1, Object arg2);

  /**
   * Log a message at the ERROR level according to the specified format
   * and arguments.
   * <p/>
   * <p>This form avoids superfluous string concatenation when the logger
   * is disabled for the ERROR level. However, this variant incurs the hidden
   * (and relatively small) cost of creating an <code>Object[]</code> before invoking the method,
   * even if this logger is disabled for ERROR. The variants taking
   * {@link #error(String, Object) one} and {@link #error(String, Object, Object) two}
   * arguments exist solely in order to avoid this hidden cost.</p>
   *
   * @param format    the format string
   * @param arguments a list of 3 or more arguments
   */
  public void error( String clazz,String format, Object... arguments);
  public void error( String clazz,String method,String format, Object... arguments);

  /**
   * Log an exception (throwable) at the ERROR level with an
   * accompanying message.
   *
   * @param msg the message accompanying the exception
   * @param t   the exception (throwable) to log
   */
  public void error( String clazz, Throwable t);
  public void error( String clazz,String msg, Throwable t);
  public void error( String clazz,String method,String msg, Throwable t);

}
