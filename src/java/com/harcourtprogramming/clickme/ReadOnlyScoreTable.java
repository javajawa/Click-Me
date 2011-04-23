/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harcourtprogramming.clickme;

import java.util.Collection;
import java.util.Map;

/**
 *
 * @author Benedict
 */
public class ReadOnlyScoreTable extends ScoreTable
{

	@Override
	public boolean add(Map.Entry<String, Integer> e)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(Collection<? extends Map.Entry<String, Integer>> c)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public Integer put(String key, Integer value)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void putAll(Map<? extends String, ? extends Integer> m)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean remove(Object o)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> c)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> c)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public ScoreTable readOnlyCopy()
	{
		return this;
	}

	ReadOnlyScoreTable(ScoreTable s)
	{
		this.scores = s.scores;
		this.lookup = s.lookup;
	}
	
	
}
