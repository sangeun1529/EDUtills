package nest.hava.edutills.util;


import org.slf4j.Logger;

public class SleepUtils {
	
	
	public static void sleep(long sleep)
	{
		if(sleep>0)
		{
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				
			}
		}
		
	}
	
	public static void sleep(Logger logger, Class<?> clazz, long sleep)
	{
		if(sleep>0)
		{
			try {
				
				if(logger !=null)
				{
					if(logger.isDebugEnabled())
					{
						logger.debug(clazz.getSimpleName(), "Sleep For {} sec",((float)sleep/(float)1000));
					}
				}
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				
			}
		}
		
	}
	
	public static void sleepForLoop(Logger logger,Class<?> clazz, long sleep)
	{
		if(sleep>0)
		{
			try {
				boolean completed = false;
				long totalSleep = sleep;
				long sleepUnit = 1000L;
				long cumlulativeSleepUnit=0L;
				
				do{
					if(logger.isDebugEnabled() && cumlulativeSleepUnit %30000 ==0)
					{
						logger.debug(clazz.getSimpleName(), "Sleep For {} sec / {} sec in total"
															,((float)cumlulativeSleepUnit/(float)1000)
															,((float)sleep/(float)1000));
					}
					
					if(totalSleep > cumlulativeSleepUnit)
					{
						Thread.sleep(sleepUnit);
						cumlulativeSleepUnit = cumlulativeSleepUnit+sleepUnit;
					}else {
						completed =true;
					}
					
				}while(!completed);
				
				
			} catch (InterruptedException e) {
				
			}
		}
		
	}
	
	public static void sleepForLoop(Logger logger,String threadName,Class<?> clazz, long sleep)
	{
		if(sleep>0)
		{
			try {
				boolean completed = false;
				long totalSleep = sleep;
				long sleepUnit = 1000L;
				long cumlulativeSleepUnit=0L;
				
				do{
					if(logger.isDebugEnabled())
					{
						logger.debug(clazz.getSimpleName(),threadName, "Sleep For {} sec / {} sec in total"
															,((float)cumlulativeSleepUnit/(float)1000)
															,((float)sleep/(float)1000));
					}
					
					if(totalSleep > cumlulativeSleepUnit)
					{
						Thread.sleep(sleepUnit);
						cumlulativeSleepUnit = cumlulativeSleepUnit+sleepUnit;
					}else {
						completed =true;
					}
					
				}while(!completed);
				
				
			} catch (InterruptedException e) {
				
			}
		}
		
	}
	
	public static void sleepWithRandom(Logger logger,Class<?> clazz,long maxSleep)
	{
		if(maxSleep>0)
		{
			try {
				
				double r = Math.random();
				long sleep = (long) (maxSleep * r);
				
				if(logger !=null)
				{
					if(logger.isDebugEnabled())
					{
						logger.debug(clazz.getSimpleName(), "Sleep For {} sec",((float)sleep/(float)1000));
					}
				}
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				
			}
		}
		
	}
	
	public static void sleep(long sleep,int max, int cnt)
	{
		if(sleep>0 && (max -1) > cnt)
		{
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				
			}
		}
		
	}

	public static void sleep(Logger logger,Class<?> clazz, long sleep,int max, int cnt)
	{
		if(sleep>0 && (max -1) > cnt)
		{
			try {
				if(logger.isDebugEnabled())
				{
					logger.debug(clazz.getSimpleName(), "Sleep For {} sec",((float)sleep/(float)1000));
				}
				
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				
			}
		}
		
	}
	
	public static void sleepForLoop(Logger logger,Class<?> clazz, long sleep,int max, int cnt)
	{
		if(sleep>0 && (max -1) > cnt)
		{
			try {
				boolean completed = false;
				long totalSleep = sleep;
				long sleepUnit = 60000L;
				long cumlulativeSleepUnit=0L;
				
				do{
					if(logger.isDebugEnabled())
					{
						logger.debug(clazz.getSimpleName(), "Sleep For {} sec / {} sec in total"
														  ,((float)cumlulativeSleepUnit/(float)1000)
														  ,((float)sleep/(float)1000));
					}
					
					if(totalSleep > cumlulativeSleepUnit)
					{
						Thread.sleep(sleepUnit);
						cumlulativeSleepUnit = cumlulativeSleepUnit+sleepUnit;
					}else {
						completed =true;
					}
					
				}while(!completed);
				
				
			} catch (InterruptedException e) {
				
			}
		}
		
	}

}
