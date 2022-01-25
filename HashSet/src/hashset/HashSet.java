package hashset;

public class HashSet<K, V> {
	Object[] elements;
	// threshold = loadFactor * maxSize;
	double loadFactor = 0.75;
	int size = 0;

	public HashSet() {
		elements = new Object[15];
	}

	@SuppressWarnings("unchecked")	
	private void put(K key, V value, Object[] backingArray) {
		// 1. hash it
		// 2. get an index
		// 3. put it into array

		int hashCode = key.hashCode();
		int index = Math.abs(hashCode % backingArray.length);

		Entry<K, V> entry = new Entry<>(hashCode, key, value);

		if (backingArray[index] == null) {
			backingArray[index] = entry;
		} else {
			Entry<K, V> node = (Entry<K, V>) backingArray[index];
			while (node.getNext() != null) {
				node = node.getNext();
			}
			node.setNext(entry);
		}
		size++;

		if (shouldGrowHashSet(backingArray)) {
			System.out.println("Before: Growing HashSet, max size is: " + elements.length);
			growHashSet();
			System.out.println("After: Growing HashSet, max size is: " + elements.length);
		}
	}

	public void put(K key, V value) {
		put(key, value, elements);
	}

	@SuppressWarnings("unchecked")
	private void growHashSet() {
		// 1. create a new backing object array (with double the size)
		// 2. populate the new backing object with existing elements
		// 3. assign the old backing object array to point to the new one

		Object[] newElements = new Object[elements.length * 2];
		size=0;
		
		for (int i = 0; i < elements.length; i++) {
			while (elements[i] != null) {
				Entry<K, V> entry = (Entry<K, V>) elements[i];
				put(entry.getKey(), entry.getValue(), newElements);
				elements[i] = entry.getNext();

			}
		}
		elements = newElements;
	}

	private boolean shouldGrowHashSet(Object[] backingArray) {
		if (size > (loadFactor * backingArray.length)) {
			return true;
		} else {
			return false;
		}
	}

	public V get(K key) {
		// 1. hash it
		// 2. get an index
		// 3. search for correct hashcode/key in Entry LinkedList
		int hashCode = key.hashCode();
		int index = Math.abs(hashCode % elements.length);

		if (elements[index] != null) {
			// ((Entry<K,V>) elements[index]).getValue();

			Entry<K, V> node = (Entry<K, V>) elements[index];
			// Entry<K,V> prev = node;
			Entry<K, V> next = node.getNext();
			while (node != null) {
				if (node.getHashCode() == hashCode) {
					if (key.equals(node.getKey())) {
						return node.getValue();
					}
				}
				node = next;
				if (next != null) {
					next = next.getNext();
				}
			}
		}
		return null;
		// return ((Entry<K, V>)elements[index]).getValue();
	}

	@SuppressWarnings("unchecked")
	public V remove(K key) {
		// 1. hash it
		// 2. get an index
		// 3. search for correct hashcode/key in Entry LinkedList
		int hashCode = key.hashCode();
		int index = Math.abs(hashCode % elements.length);

		if (elements[index] != null) {

			Entry<K, V> node = (Entry<K, V>) elements[index];
			Entry<K, V> prev = node;
			Entry<K, V> next = node.getNext();

			while (node != null) {
				if (node.getHashCode() == hashCode) {
					if (key.equals(node.getKey())) {
						V valueToReturn = node.getValue();
						if (prev == node) {
							elements[index] = node.getNext();
						} else {
							prev.setNext(node.getNext());
						}
						size--;
						return valueToReturn;
					}
				}
				prev = node;
				node = next;
				if (next != null) {
					next = next.getNext();
				}
			}
		}
		return null;
	}

}
