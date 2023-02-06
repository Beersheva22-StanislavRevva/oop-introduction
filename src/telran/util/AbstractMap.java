package telran.util;

public abstract class AbstractMap<K, V> implements Map<K, V> {
    protected Set<Entry<K, V>> set;
    @Override
	public V put(K key, V value) {
		V res = null;
		Entry<K, V> entry = set.get(new Entry<>(key, null));
		if (entry != null) {
			res = entry.getValue();
			entry.setValue(value);
		} else {
			set.add(new Entry<>(key, value));
		}
		return res;
	}

	@Override
	public V putIfAbsent(K key, V value) {
		V res = get(key);
		if (res == null) {
			put (key, value);
		}
		return res;
	}

	@Override
	public V get(K key) {
		V res = null;
		Entry<K, V> entry = set.get(new Entry<>(key, null));
		if (entry != null) {
			res = entry.getValue();
		}
		return res;
	}

	@Override
	public V getOrDefault(K key, V value) {
		V res = get(key);
		if (res == null) {
			res = value;
		}
		return res;
	}

	@Override
	public boolean containsKey(K key) {
		return get(key) != null ? true :false;
	}

	@Override
	public boolean containsValue(V value) {
		for(Entry<K, V> entry : set) {
			if (entry.getValue().equals(value)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Collection<V> values() {
		Collection<V> list = new ArrayList<V>();
		for(Entry<K, V> entry : set) {
			list.add(entry.getValue());
		}
		return list;
	}

	@Override
	public Set<K> keySet() {
		try {
			@SuppressWarnings("unchecked")
			Set<K> res = set.getClass().getConstructor().newInstance();
			set.forEach(n -> res.add(n.getKey()));
			return res;
		} catch (Exception e) {
			throw new IllegalStateException();
		} 
		
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		try {
			@SuppressWarnings("unchecked")
			Set<Entry<K, V>> res = set.getClass().getConstructor().newInstance();
			set.forEach(n -> res.add(n));
			return res;
		} catch (Exception e) {
			throw new IllegalStateException();
		} 
	}

	@Override
	public V remove(K key) {
		V res = null;
		Entry<K, V> entry = set.get(new Entry<>(key, null));
		if (entry != null) {
			res = entry.getValue();
			set.remove(entry);
		}
		return res;
	}

}