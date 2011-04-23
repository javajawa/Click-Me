package com.harcourtprogramming.clickme;

import com.sun.faces.util.LRUMap;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Benedict
 */
final class ClickMeGame
{
	protected class TimerDelegate implements Runnable
	{
		TimerDelegate()
		{
		}

		@Override
		public void run()
		{
			ClickMeGame.instance.newEntry();
		}
	}
	
	private final static ClickMeGame instance = new ClickMeGame();
	
	private static final int timeQuantum = 30;
	private static final int historyQuanta = 24;
	
	private int currEntry = 0;
	private ScheduledExecutorService scheduler =  Executors.newScheduledThreadPool(1);
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	private LRUMap<Integer,HashMap<String, Integer>> history = new LRUMap<Integer, HashMap<String, Integer>>(historyQuanta);
	
	public static ClickMeGame getInstance()
	{
		return instance;
	}
	
	protected ClickMeGame()
	{
		for (int i = 0; i < historyQuanta; i++)	newEntry();
		
		scheduler.scheduleAtFixedRate(new TimerDelegate(), timeQuantum, timeQuantum, TimeUnit.MINUTES);
	}

	@Override
	protected void finalize() throws Throwable
	{
		scheduler.shutdownNow();		
		super.finalize();
	}
	
	final synchronized void newEntry()
	{
		++currEntry;
		history.put(currEntry, new HashMap<String, Integer>());
	}
	
	public synchronized void addScore(String player, int score)
	{
		HashMap<String, Integer> latestScores = history.get(currEntry);
		
		if (latestScores.containsKey(player))
		{
			Integer s = latestScores.get(player);
			latestScores.put(player, s + score);
		}
		else
		{
			latestScores.put(player, score);
		}
	}
	
	public synchronized SortedSet<Map.Entry<String, Integer>> getScores()
	{
		final ValueComparator<Integer> bvc = new ValueComparator<Integer>();
		
		SortedSet<Map.Entry<String, Integer>> scores = new TreeSet<Map.Entry<String, Integer>>(bvc);
		scores.addAll(history.get(currEntry).entrySet());
		return scores;
	}
	
	private static final class ValueComparator<V extends Comparable<? super V>>
                                     implements Comparator<Map.Entry<?, V>> {
		@Override
    public int compare(Map.Entry<?, V> o1, Map.Entry<?, V> o2) {
        return o2.getValue().compareTo(o1.getValue());
    }
}
}
