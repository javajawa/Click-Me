package com.harcourtprogramming.clickme;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author Benedict
 */
public class ScoreTable implements SetCompatibleMap<String,Integer>, SortedSet<Map.Entry<String,Integer>>
{
	protected TreeSet<Map.Entry<String,Integer>> scores = new TreeSet<Map.Entry<String, Integer>>(
					new ScoreComparator());
	protected Map<String,Map.Entry<String,Integer>> lookup = new HashMap<String, Map.Entry<String, Integer>>();
	private ReadOnlyScoreTable ro_copy;

	private static final class ScoreComparator implements Comparator<Map.Entry<?, Integer>>
	{
		@Override
    public int compare(Map.Entry<?, Integer> o1, Map.Entry<?, Integer> o2)
		{
        return o2.getValue().compareTo(o1.getValue());
    }
	}
	
	public final class Entry<K,V> implements Map.Entry<K,V>
	{
		private K key;
		private V value;

		public Entry(K key, V value)
		{
			this.key = key;
			this.value = value;
		}

		@Override
		public V getValue()
		{
			return value;
		}

		@Override
		public K getKey()
		{
			return key;
		}

		@Override
		public V setValue(V value)
		{
			V old = this.value;
			this.value = value;
			return old;
		}

		
	}

	@Override
	public boolean containsKey(Object key)
	{
		return lookup.containsKey((String)key);
	}

	@Override
	public void clear()
	{
		scores.clear();
		lookup.clear();
	}

	@Override
	public Comparator<? super Map.Entry<String, Integer>> comparator()
	{
		return scores.comparator();
	}
	
	@Override
	public Integer get(Object key)
	{
		lookup.get((String)key).getValue();
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Map.Entry<String, Integer> first()
	{
		return scores.first();
	}

	@Override
	public boolean isEmpty()
	{
		return scores.isEmpty();
	}

	@Override
	public Map.Entry<String, Integer> last()
	{
		return scores.last();
	}

	@Override
	public Set<String> keySet()
	{
		return Collections.unmodifiableSet(lookup.keySet());
	}

	@Override
	public Set<Map.Entry<String, Integer>> entrySet()
	{
		return Collections.unmodifiableSet(scores);
	}

	@Override
	public Integer put(String key, Integer value)
	{
		Map.Entry<String,Integer> r = lookup.get(key);
		if (r==null)
		{
			r = new Entry<String, Integer>(key, value);
			lookup.put(key, r);
			scores.add(r);
			return 0;
		}
		else
		{
			int old = r.getValue();
			r.setValue(value);
			return old;
		}
	}

	@Override
	public Iterator<Map.Entry<String, Integer>> iterator()
	{
		return scores.iterator();
	}

	@Override
	public SortedSet<Map.Entry<String, Integer>> headSet(Map.Entry<String, Integer> toElement)
	{
		return scores.headSet(toElement);
	}

	@Override
	public boolean add(Map.Entry<String, Integer> e)
	{
		Map.Entry<String,Integer> r = lookup.get(e.getKey());
		
		if (r==null)
		{
			lookup.put(e.getKey(), e);
			scores.add(e);
			return true;
		}
		else
		{
			r.setValue(r.getValue() + e.getValue());
			return true;
		}
	}

	public boolean add(String player, Integer addition)
	{
		Map.Entry<String,Integer> r = lookup.get(player);
		
		if (r==null)
		{
			r = new ScoreTable.Entry<String, Integer>(player, addition);
			lookup.put(player, r);
			scores.add(r);
			return true;
		}
		else
		{
			r.setValue(r.getValue() + addition);
			return true;
		}
	}	
	
	public boolean subtract(Map.Entry<String, Integer> e)
	{
		Map.Entry<String,Integer> r = lookup.get(e.getKey());
		
		if (r==null)
		{
			r = new ScoreTable.Entry<String, Integer>(e.getKey(), -e.getValue());
			lookup.put(e.getKey(), r);
			scores.add(r);
			return true;
		}
		else
		{
			r.setValue(r.getValue() - e.getValue());
			return true;
		}
	}
	
	@Override
	public void putAll(Map<? extends String, ? extends Integer> m)
	{
		for (String player : m.keySet())
		{
			this.put(player, m.get(player));
		}
	}
	
	@Override
	public boolean removeAll(Collection<?> c)
	{
		throw new UnsupportedOperationException("Can not remove from score tables.");
	}

	@Override
	public boolean retainAll(Collection<?> c)
	{
		throw new UnsupportedOperationException("Can not remove from score tables.");
	}

	@Override
	public SortedSet<Map.Entry<String, Integer>> subSet(Map.Entry<String, Integer> fromElement,
					Map.Entry<String, Integer> toElement)
	{
		return scores.subSet(fromElement, toElement);
	}

	@Override
	public SortedSet<Map.Entry<String, Integer>> tailSet(Map.Entry<String, Integer> fromElement)
	{
		return scores.tailSet(fromElement);
	}

	@Override
	public int size()
	{
		return scores.size();
	}

	@Override
	public Object[] toArray()
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public <T> T[] toArray(T[] a)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Collection<Integer> values()
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public boolean contains(Object o)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public boolean containsAll(Collection<?> c)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public boolean containsValue(Object value)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public boolean remove(Object o)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public boolean addAll(Collection<? extends Map.Entry<String, Integer>> c)
	{
		for (Map.Entry<String, Integer> entry : c)
		{
			this.add(entry);
		}
		return true;
	}
	
	public boolean subtractAll(Collection<? extends Map.Entry<String, Integer>> c)
	{
		for (Map.Entry<String, Integer> entry : c)
		{
			this.subtract(entry);
		}
		return true;
	}
	
	public ScoreTable readOnlyCopy()
	{
		if (ro_copy == null) ro_copy = new ReadOnlyScoreTable(this);
		return ro_copy;
	}

	public ScoreTable()
	{
	}
	
}
