package nest.hava.edutills.util;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import com.google.common.base.Stopwatch;

public class ExecutedTimeWatcher {
	
	
	public static boolean isOver(Stopwatch watch,Long maxPeriodInSecond,AtomicLong executedPeriodInSecond)
	{
		boolean running = false;
		long elapsedSecond;
		
		if(maxPeriodInSecond <0)
		{
			//if the maxPeriodInSecond is minus, there is no limit of executing period
			watch.stop();
			elapsedSecond = watch.elapsed(TimeUnit.SECONDS);
			executedPeriodInSecond.set(elapsedSecond);
			
			if(running)
			{
				watch.start();
			}
			
			return false;
		}
		
		if(watch.isRunning())
		{
			running = true;
			watch.stop();
		}
		
		elapsedSecond = watch.elapsed(TimeUnit.SECONDS);
		executedPeriodInSecond.set(elapsedSecond);
		
		if(elapsedSecond > maxPeriodInSecond)
		{
			if(running)
			{
				watch.start();
			}
			
			return true;
			
		}else {
			if(running)
			{
				watch.start();
			}
		}
		
		
		return false;
	}
	
	public static boolean isOver(Stopwatch watch,Long maxPeriodInSecond,AtomicLong executedPeriodInSecond, Long preExecutedPeriodInSecond)
	{
		boolean running = false;
		long elapsedSecond;
		
		if(maxPeriodInSecond <0)
		{
			//if the maxPeriodInSecond is minus, there is no limit of executing period
			watch.stop();
			elapsedSecond = watch.elapsed(TimeUnit.SECONDS) + preExecutedPeriodInSecond;
			executedPeriodInSecond.set(elapsedSecond);
			
			if(running)
			{
				watch.start();
			}
			
			return false;
		}
		
		if(watch.isRunning())
		{
			running = true;
			watch.stop();
		}
		
		elapsedSecond = watch.elapsed(TimeUnit.SECONDS) + preExecutedPeriodInSecond;
		executedPeriodInSecond.set(elapsedSecond);
		
		if(elapsedSecond > maxPeriodInSecond)
		{
			if(running)
			{
				watch.start();
			}
			
			return true;
			
		}else {
			if(running)
			{
				watch.start();
			}
		}
		
		
		return false;
	}

}
