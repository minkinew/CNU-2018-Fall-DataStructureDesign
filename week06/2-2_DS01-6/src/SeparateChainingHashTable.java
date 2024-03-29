
public class SeparateChainingHashTable {
	private Entry[] entries;
	private int size;
	private float loadFactor;

	public SeparateChainingHashTable(int capacity, float loadFactor) { 
		entries = new Entry[capacity]; 
		this.loadFactor = loadFactor;    
	}

	public SeparateChainingHashTable(int capacity) {
		this(capacity, 0.75F);  
	}

	public SeparateChainingHashTable() { 
		this(101); 
	}
	
	public Object get(Object key) {
		int h = hash(key);
		for(Entry e = entries[h]; e!=null; e = e.next) {
			if (e.key.equals(key)) return e.value;
		}
		return null;
	}
	 public Object put(Object key, Object value) {
		 int h = hash(key);
		 for (Entry e = entries[h]; e != null; e = e.next) {
			 if(e.key.equals(key)) {
				 Object oldValue = e.value;
				 e.value = (int)oldValue + (int)value;
				 return e.value;
			 }
		 }
		 entries[h] = new Entry(key, value, entries[h]);
		 ++size;
		 if(size > loadFactor * entries.length)
			 rehash();
		 return null;
	 }
	 
	 public Object remove(Object key) {
		 int h = hash(key);
		 for(Entry e = entries[h], prev=null; e!=null; prev = e, e= e.next) {
			 if(e.key.equals(key)) {
				 Object oldValue = e.value;
				 if(prev == null)
					 entries[h] = e.next;
				 else prev.next = e.next;
				 --size;
				 return oldValue;
			 }
		 }
		 return null;
	 }
	 
	 public int size() {
		 return size;
	 }
	 
	 private class Entry{
		 Object key, value;
		 Entry next;
		 Entry(Object k, Object v, Entry n){
			 key = k;
			 value = v;
			 next = n;
		 }
		 public String toString() {
			 return key + "=" + value; 
		 }
	 }
	 
	 private int hash(Object key) {
		 if(key == null) throw new IllegalArgumentException();
		 return (key.hashCode() & 0x7FFFFFFF) % entries.length;
	 }
	 
	 private void rehash() {
		 Entry[] oldEntries = entries;
		 entries = new Entry[2*oldEntries.length + 1];
		 for(int k = 0; k<oldEntries.length; k++) {
			 for(Entry old = oldEntries[k]; old != null;) {
				 Entry e = old;
				 old = old.next;
				 int h = hash(e.key);
				 e.next = entries[h];
				 entries[h] = e;
			 }
		 }
	 }
}
