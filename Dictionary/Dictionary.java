

/**
 * @param <TData> data type of node data
 * 
*/

package Dictionary;

import Dictionary.DynamicArray.*;

public class Dictionary{
	protected DynamicArray _bins;
    protected DynamicArray _keys;
    protected DynamicArray _values;

    private class KeyNode{
        public int index;
        public int entry;

        KeyNode(int _i){
        	this.index = _i;
            this.entry = 0;
        }

        public Object getKey(){
        	return Dictionary.this._keys.get( this.index );
        }
        public Object getValue(){
        	return Dictionary.this._values.get( this.index );
        }

        @Override
        public String toString(){
        	return "{i:"+this.index+", e:"+this.entry+"}";
        	// return "{i:"+this.index+", e:"+this.entry+", k:"+this.getKey()+", v:"+this.getValue()+"}";
        }
    }
    //constructor
    public Dictionary(){
		this._bins = new DynamicArray(1);
        this._keys = new DynamicArray();
        this._values = new DynamicArray();
    }public Dictionary(Object[][] arr){
    	this._bins = new DynamicArray(1);
        this._keys = new DynamicArray();
        this._values = new DynamicArray();

        if(arr.getClass().isArray()){
        	for(int i=0;i<arr.length;i++){
        		if(arr[i]!=null && arr[i].getClass().isArray() && arr[i].length>1){
					this.set(arr[i][0], arr[i][1]);
        		}
	        }
        }
        
    }
    //copy constructor
    public Dictionary(Dictionary other){
    	this._bins = new DynamicArray(other._bins);
    	this._keys = new DynamicArray(other._keys);
    	this._values = new DynamicArray(other._values);
    }

    public void put(Object k, Object v){
    	this.set(k, v);
    }

    public void set(Object kkey, Object vval){
    	if(kkey == null) return;
    	if(kkey instanceof String || kkey instanceof Integer){
    		int len = this.binsSize();
    		if(len<1) len = 1;
	        int hash = this.hash(kkey, len);
	        int index = hash%len;

	        //create keynode with potential index
	        KeyNode newNode = new KeyNode( this.keySize() );

	        boolean set = false;
	        boolean isNew = true;
	        boolean isFull = false;
	        int c = index;
	        KeyNode currNode = newNode;
	        Object initKey = kkey;
	        Object initVal = vval;

	        while(currNode.entry<len && !set){
	        	KeyNode cnode = (KeyNode) this._bins.get(c);
	        	if(cnode!=null){
	        		//compare entries
	        		int _i = cnode.index;
	        		if(this._keys.get(_i) == kkey){
	        			//set key if it exists
	        			// My.cout("key exists - replace value");
	        			currNode.index = cnode.index;
	        			this._bins.set(c,currNode);
	        			initKey = this._keys.get(_i);
	        			initVal = this._values.get(_i);
	        			set = true;
	        			isNew = false;
	        		}else if(this._keys.get(_i) != null && this._keys.get(_i).equals(kkey)){
	        			//set key if it exists
	        			// My.cout("key exists - replace value");
	        			currNode.index = cnode.index;
	        			this._bins.set(c,currNode);
	        			initKey = this._keys.get(_i);
	        			initVal = this._values.get(_i);
	        			set = true;
	        			isNew = false;
	        		}else if(cnode.entry<currNode.entry){
	        			//swap nodes
	        			// My.cout("swap nodes and repeat");
	        			KeyNode temp = (KeyNode) cnode;
	        			this._bins.set(c,currNode);
	        			initKey = this._keys.get(_i);
	        			initVal = this._values.get(_i);
	        			currNode = temp;
	        		}
	        	}else{
	        		//set key if the bins is blank
	        		// My.cout("bins is blank - set value");
	        		this._bins.set(c,currNode);
	        		set = true;
	        	}
	        	currNode.entry++;
	        	c++;
	        	//if the count overlaps length, wrap over
	        	if(c>=len){
	        		c=0;
	        	}
	        }
	        // My.cout("-set: "+set);
	        // My.cout("-currNode: "+currNode);
	        //finally set the key and value
	        this._keys.set(newNode.index, kkey);
	        this._values.set(newNode.index, vval);

	        //increase bins size slightly when bins is full
	        if(this.keySize()>=this.binsSize()){
	        	isFull = true;
	        }
	        if(isFull){
	        	// My.cout("resize and rehash");
	        	int prime = nextPrime(this.binsSize());
	        	this._bins.set(prime,null);
	        	this.reHash();
	        }
			//rehash all entries then insert currNode again
	        if(!set){
	        	this.reHash();
	        	this.set(initKey, initVal);
	        }
    	}
        
    }

    public Object get(Object kkey){
    	if(kkey == null) return null;
    	Object vval = null;
    	if(kkey instanceof String || kkey instanceof Integer){
    		int len = this.binsSize();
    		if(len<1) len = 1;
	        int hash = this.hash(kkey, len);
	        int index = hash%len;

	        boolean end = false;
	        int c = index;
	        int k = 0;

	        while(!end && k<len){
	        	KeyNode cnode = (KeyNode) this._bins.get(c);
	        	if(cnode!=null){
	        		//compare entries
	        		int _i = cnode.index;
	        		if(this._keys.get(_i) == kkey){
	        			//set key if it exists
	        			vval = this._values.get(_i);
	        			// My.cout("exist??");
	        			end = true;
	        		}else if(this._keys.get(_i) != null && this._keys.get(_i).equals(kkey)){
	        			//set key if it exists
	        			vval = this._values.get(_i);
	        			// My.cout("exist??");
	        			end = true;
	        		}
	        	}else{
	        		//key probably does not exist
	        		// end = true;
	        	}
	        	k++; c++;
	        	//if the count overlaps length, wrap over
	        	if(c>=len){
	        		c=0;
	        	}
	        }
    	}
    	return vval;
    }

    public Object delete(Object kkey){
    	if(kkey == null) return null;
    	Object vval = null;
    	if(kkey instanceof String || kkey instanceof Integer){
    		int len = this.binsSize();
    		if(len<1) len = 1;
	        int hash = this.hash(kkey, len);
	        int index = hash%len;

	        boolean end = false;
	        int c = index;
	        int k = 0;

	        while(!end && k<len){
	        	KeyNode cnode = (KeyNode) this._bins.get(c);
	        	if(cnode!=null){
	        		//compare entries
	        		int _i = cnode.index;
	        		if(this._keys.get(_i) == kkey){
	        			//set key if it exists
	        			vval = this._values.get(_i);
	        			//remove from list
	        			this._keys.remove(_i);
	        			this._values.remove(_i);
	        			this._bins.remove(c);

	        			end = true;
	        		}else if(this._keys.get(_i) != null && this._keys.get(_i).equals(kkey)){
	        			//set key if it exists
	        			vval = this._values.get(_i);
	        			//remove from list
	        			this._keys.remove(_i);
	        			this._values.remove(_i);
	        			this._bins.remove(c);

	        			end = true;
	        		}
	        	}else{
	        		//key probably does not exist
	        		end = true;
	        	}
	        	k++; c++;
	        	//if the count overlaps length, wrap over
	        	if(c>=len){
	        		c=0;
	        	}
	        }

    	}
    	return vval;
    }

    private void reHash(){
    	this._bins.clear();
    	int len = this.binsSize();
    	// if(len<1) len = 1;
    	for(int k=0;k<len;k++){
    		Object kkey = this._keys.get(k);
    		Object vval = this._values.get(k);

    		if(kkey==null) continue;

	        int hash = this.hash(kkey, len);
	        int index = hash%len;

	        //create keynode with potential index
	        KeyNode newNode = new KeyNode( k );

	        boolean set = false;
	        int c = index;
	        KeyNode currNode = newNode;

	        while(currNode.entry<len && !set){
	        	KeyNode cnode = (KeyNode) this._bins.get(c);
	        	if(cnode!=null){
	        		//compare entries
	        		int _i = cnode.index;
	        		if(this._keys.get(_i) == kkey){
	        			//set key if it exists
	        			this._bins.set(c,currNode);
	        			set = true;
	        		}else if(this._keys.get(_i) != null && this._keys.get(_i).equals(kkey)){
	        			//set key if it exists
	        			this._bins.set(c,currNode);
	        			set = true;
	        		}else if(cnode.entry<currNode.entry){
	        			//swap nodes
	        			KeyNode temp = cnode;
	        			this._bins.set(c,currNode);
	        			currNode = temp;
	        		}
	        	}else{
	        		//set key if the bins is blank
	        		this._bins.set(c,currNode);
	        		set = true;
	        	}
	        	currNode.entry++;
	        	c++;
	        	//if the count overlaps length, wrap over
	        	if(c>=len){
	        		c=0;
	        	}
	        }
	        //no need to push key and value since they already exist

	        //no need to resize the bins

	        // if(!set){
	        // 	My.cout("key got lost");
	        // }
    	}
    }

    private int hash(Object _data, int _size){
        int hash = hash(_data,_size,0);
        return hash;
    }

    private int hash(String _data, int _size, int _seed){
    	int hash = 0;
        int prime = nextPrime(_size);
        if(_size<1) _size = 1;
        hash = Math.abs(hash^_seed);
        hash = this.hash_String( (String) _data,_size);
        return hash;
    }private int hash(Integer _data, int _size, int _seed){
    	int hash = 0;
        int prime = nextPrime(_size);
        if(_size<1) _size = 1;
        hash = Math.abs(hash^_seed);
		hash = this.hash_Int( (Integer) _data,_size);
        return hash;
    }private int hash(Object _data, int _size, int _seed){
        int hash = 0;
        int prime = nextPrime(_size);
        if(_size<1) _size = 1;

        hash = Math.abs(_data.hashCode()%prime);
        hash = Math.abs(hash^_seed);
        return hash;
    }

    private int hash_Int(Integer num, int _size){
        int prime = nextPrime(_size);
        int hash = prime;
        if(_size<1) _size = 1;
        if(num>0){
            hash = Math.abs(num%prime);
            hash = Math.abs(hash % prime);
        }else if(num<0){
            hash = Math.abs((num*num+1)%prime);
        }else{
            hash = Math.abs(prime&_size);
        }
        return hash;
    }
    private int hash_String(String str, int _size){
        int prime = nextPrime(_size);
        int hash = prime;
        if(_size<1) _size = 1;
        if(str.length()!=0){
            hash = Math.abs(str.hashCode()%prime);
            for(int i=0;i<str.length();i++){
                char chr = str.charAt(i);
                int code = (int) chr;
                hash = hash&code|hash;
            }
            hash = Math.abs(hash % prime);
        }
        return hash;
    }

    private static int nextPrime(int num){
		boolean isprime = false;
		int val = num;
		while(!isprime){
			isprime = isPrime(val);
			if(!isprime){
				val++;
			}
		}
		return val;
	}
	private static boolean isPrime(int num){
		int s = (int) Math.sqrt((double)(num));
	    for(int i = 2; i <= s; i++){
	        if(num % i == 0){
	        	return false; 
	        }
	    }
	    return (num > 1);
	}

    public int binsSize(){
        return this._bins.length();
    }
    public int keySize(){
        return this._keys.length();
    }

    public void clear(){
        this._bins.empty();
        this._keys.empty();
        this._values.empty();

        this._bins.push(null);
    }

    public void removeEmpty(){
    	Object[] _keys = this.getKeys();
    	for(int i=0;i<_keys.length;i++){
    		if(this.get(_keys[i])==null){
    			this.delete(_keys[i]);
    		}
    	}
    }

    public boolean isEmpty(){
        if(this._bins.isEmpty()){
            return true;
        }
        return false;
    }

    public Object[] getKeys(){
        Object[] x = this._keys.asArray();

        return x;
    }
    public Object[] getValues(){
        Object[] x = this._values.asArray();

        return x;
    }
    public Object[] getBins(){
        Object[] x = this._bins.asArray();

        return x;
    }

    @Override
    public String toString(){
    	String out = "{ ";
    	int len = this.keySize();
    	for(int i=0;i<len;i++){
    		Object kkey = this._keys.get(i);
    		Object vval = this._values.get(i);

    		out += kkey+" : "+vval;
    		if(i<len-1){
    			out+=" , ";
    		}
    	}
    	out += " }";
    	return out;
    }
}