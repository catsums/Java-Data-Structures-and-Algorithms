public class HashMap<T, U> {
    private Object[] keyArray;
    private Object[] valueArray;
    private Object[] keyCellar;
    private Object[] valueCellar;

    public HashMap(int ArraySize, int CellarSize){

        keyArray = new Object[ArraySize];
        valueArray = new Object[ArraySize];

        keyCellar = new Object[CellarSize];
        valueCellar = new Object[CellarSize];

    }

    public boolean put(T key, U value){
        if(this.get(key)!=null) return false;
        
        int _arrayhash = arrayHash(key);

        if(keyArray[_arrayhash]==null){
            keyArray[_arrayhash] = key;
            valueArray[_arrayhash] = value;
            return true;
        }else{

            int _cellarHash = cellarHash(key);

            if(keyCellar[_cellarHash]==null){
                keyCellar[_cellarHash] = key;
                valueCellar[_cellarHash] = value;
                return true;
            }else{
                for(int i=0; i<keyArray.length; i++){

                    int _index = quadraticProbing(i, _arrayhash, keyArray.length);

                    if(keyArray[_index]==null){
                        keyArray[_index] = key;
                        valueArray[_index] = value;
                        return true;
                    }
                }

                for(int i=0; i<keyCellar.length; i++){

                    int _index = linearProbing(i, _cellarHash, keyCellar.length);

                    if(keyCellar[_index]==null){
                        keyCellar[_index] = key;
                        valueCellar[_index] = value;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public U get(T key){
        if(key==null) return null;

        int _arrayhash = arrayHash(key);

        if(keyArray[_arrayhash]==key){
            return (U) valueArray[_arrayhash];
        }else{
            int _cellarHash = cellarHash(key);
            if(keyCellar[_cellarHash]==key){
                return (U) valueCellar[_cellarHash];
            }else{
                for(int i=0; i<keyArray.length; i++){
                    int _index = quadraticProbing(i, _arrayhash, keyArray.length);
                    if(keyArray[_index]==key){
                        return (U) valueArray[_index];
                    }
                }

                for(int i=0; i<keyCellar.length; i++){
                    int _index = linearProbing(i, _cellarHash, keyCellar.length);
                    if(keyCellar[_index]==key){
                        return (U) valueCellar[_index];
                    }
                }
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public HashMap<T,U> rehash(int ArraySize, int CellarSize){
        
        HashMap<T,U> newHashMap = new HashMap<T,U>(ArraySize, CellarSize);

        for(int i=0;i<keyArray.length;i++){
            T key = (T) keyArray[i];
            U val = (U) valueArray[i];
            if(key==null) continue;

            newHashMap.put(key, val);
        }
        for(int i=0;i<keyCellar.length;i++){
            T key = (T) keyCellar[i];
            U val = (U) valueCellar[i];
            if(key==null) continue;

            newHashMap.put(key, val);
        }

        return newHashMap;

    }

    public int arrayHash(T key){
        int keyInt = convertTtoInt(key);
        int hash = keyInt % keyArray.length;
        hash = (int) Math.abs(hash);

        return hash;
    }

    public int cellarHash(T key){
        int keyInt = convertTtoInt(key);
        int hash = (keyArray.length - keyInt) % keyCellar.length;
        // int hash = (keyInt/keyArray.length) % keyCellar.length; //hash func in notes

        hash = (int) Math.abs(hash);

        return hash;

    }

    public static int linearProbing(int i, int hashValue, int modVal){
        int index = (hashValue+i) % modVal;
        index = (int) Math.abs(index);

        return index;
    }

    public static int quadraticProbing(int i, int hashValue, int modVal){
        int index = (int) ( hashValue + Math.round( Math.pow(-1,i-1) ) * Math.pow(Math.floor( (i+1)/2 ),2) ) % modVal;
        // int index = (int) (hashValue + Math.round((Math.pow(-1, (i - 1)))) * (Math.pow(Math.floor((i + 1 / 2)), 2))) % modVal;

        index = (int) Math.abs(index);

        return index;
    }

    public int count(){
        int c = 0;
        for(Object key:keyArray){
            if(key!=null) c++;
        }
        for(Object key:keyCellar){
            if(key!=null) c++;
        }
        return c;
    }

    public boolean isContained(T key, U value){
        for(Object _key:keyArray){
            if(_key==null || _key.equals(key)) continue;
            for(Object _val:valueArray){
                if(_val!=null && _val.equals(value)) return true;
            }
        }

        for(Object _key:keyCellar){
            if(_key==null || _key.equals(key)) continue;
            for(Object _val:valueCellar){
                if(_val!=null && _val.equals(value)) return true;
            }
        }

        return false;
    }

    public Object[] getKeyArray(){
        return keyArray;
    }

    public Object[] getKeyCellar(){
        return keyCellar;
    }

    public Object[] getValueArray(){
        return valueArray;
    }

    public Object[] getValueCellar(){
        return valueCellar;
    }


    //Its not advised to change this *wink wink*
    private int convertTtoInt(T key){
        if(key == null)
            return -1;
        return key.hashCode();
    }
}
