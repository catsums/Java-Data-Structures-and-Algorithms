public class HashMap<T, U> 
{
    private Object[] keyArray;
    private Object[] valueArray;
    private Object[] keyCellar;
    private Object[] valueCellar;

    private int arraySize;
    private int cellarSize;

    public HashMap(int ArraySize, int CellarSize)
    {
        this.keyArray = new Object[ArraySize];
        this.valueArray = new Object[ArraySize];
        this.keyCellar = new Object[CellarSize];
        this.valueCellar = new Object[CellarSize];

        this.arraySize = ArraySize;
        this.cellarSize = CellarSize;
    }

    public boolean put(T key, U value)
    {
        if (checkDuplicates(key))
            return false;

        int index = arrayHash(key);

        if (this.keyArray[index] == null)
        {
            this.keyArray[index] = key;
            this.valueArray[index] = value;
            return true;
        }

        else
        {
            int cellarIndex = cellarHash(key);

            if (this.keyCellar[cellarIndex] == null)
            {
                this.keyCellar[cellarIndex] = key;
                this.valueCellar[cellarIndex] = value;
                return true;
            }

            else
            {
                for (int i = 0; i < this.keyArray.length; i++)
                {
                    int quadIndex = quadraticProbing(i, index, this.keyArray.length);
                    
                    if (this.keyArray[quadIndex] == null)
                    {
                        this.keyArray[quadIndex] = key;
                        this.valueArray[quadIndex] = value;
                        return true;
                    }
                }

                for (int i = 0; i < this.keyCellar.length; i++)
                {
                    int linearIndex = linearProbing(i, cellarIndex, this.keyCellar.length);

                    if (this.keyCellar[linearIndex] == null)
                    {
                        this.keyCellar[linearIndex] = key;
                        this.valueCellar[linearIndex] = value;
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @SuppressWarnings("unchecked")
    public U get(T key)
    {
        int index = arrayHash(key);

        if (this.keyArray[index] == key)
            return (U) this.valueArray[index];

        else
        {
            int cellarIndex = cellarHash(key);

            if (this.keyCellar[cellarIndex] == key)
                return (U) this.valueCellar[cellarIndex];

            else
            {
                for (int i = 1; i <= (this.arraySize/2); i++)
                {
                    int quadIndex = quadraticProbing(i, index, this.arraySize);
                    
                    if (this.keyArray[quadIndex] == key)
                        return (U) this.valueArray[quadIndex];
                }

                for (int i = 1; i <= (this.arraySize/2); i++)
                {
                    int linearIndex = linearProbing(i, cellarIndex, this.cellarSize);

                    if (this.keyCellar[linearIndex] == key)
                        return (U) this.valueCellar[linearIndex];
                }
            }
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public HashMap<T,U> rehash(int ArraySize, int CellarSize)
    {
        HashMap<T,U> hashMap = new HashMap<>(ArraySize, CellarSize);

        for (int i = 0; i < this.keyArray.length; i++)
        {
            if (this.keyArray[i] != null)
                hashMap.put((T) this.keyArray[i], (U) this.valueArray[i]);
        }

        for (int k = 0; k < this.keyCellar.length; k++)
        {
            if (this.keyCellar[k] != null)
                hashMap.put((T) this.keyCellar[k], (U) this.valueCellar[k]);
        }

        return hashMap;
    }

    public int arrayHash(T key)
    {
        int hash = (convertTtoInt(key) % this.keyArray.length);

        return (int) Math.abs(hash);
    }

    public int cellarHash(T key)
    {
        int hash = (this.keyArray.length - convertTtoInt(key)) % this.keyCellar.length;

        return (int) Math.abs(hash);
    }

    public static int linearProbing(int i, int hashValue, int modVal)
    {
        int probe = (hashValue + i) % modVal;

        return (int) Math.abs(probe);
    }

    public static int quadraticProbing(int i, int hashValue, int modVal)
    {
        int probe = (int) (hashValue + Math.round((Math.pow(-1, (i - 1)))) * (Math.pow(Math.floor((i + 1 / 2)), 2))) % modVal;

        return (int) Math.abs(probe);
    }

    public int count()
    {
        int total = 0;

        for (int i = 0; i < this.keyArray.length; i++)
        {
            if (this.keyArray[i] != null && this.valueArray[i] != null)
                total++;
        }

        for (int i = 0; i < this.keyCellar.length; i++)
        {
            if (this.keyCellar[i] != null && this.valueCellar[i] != null)
                total++;
        }

        return total;
    }

    public boolean isContained(T key, U value)
    {
        for (int i = 0; i < this.keyArray.length; i++)
        {
            if (convertTtoInt((T) this.keyArray[i]) == convertTtoInt(key))
            {
                if ((U) this.valueArray[i] == value)
                    return true;
            }
        }

        for (int i = 0; i < this.keyCellar.length; i++)
        {
            if (convertTtoInt((T) this.keyCellar[i]) == convertTtoInt(key))
            {
                if ((U) this.valueCellar[i] == value)
                    return true;
            }
        }

        return false;
    }

    public Object[] getKeyArray()
    {
        return this.keyArray;
    }

    public Object[] getKeyCellar()
    {
        return this.keyCellar;
    }

    public Object[] getValueArray()
    {
        return this.valueArray;
    }

    public Object[] getValueCellar()
    {
        return this.valueCellar;
    }


    //Its not advised to change this *wink wink*
    private int convertTtoInt(T key){
        if(key == null)
            return -1;
        return key.hashCode();
    }

    public void printKeys()
    {
        for (int i = 0; i < this.keyArray.length; i++)
            System.out.print("(" + this.keyArray[i] + ")");
        System.out.println("");
    }

    public void printKeyCellar()
    {
        for (int i = 0; i < this.keyCellar.length; i++)
            System.out.print("{" + this.keyCellar[i] + "}");
        System.out.println("");
    }

    public void printValues()
    {
        for (int i = 0; i < this.valueArray.length; i++)
            System.out.print("(" + this.valueArray[i] + ")");
        System.out.println("");
    }

    public void printValueCellar()
    {
        for (int i = 0; i < this.valueCellar.length; i++)
            System.out.print("{" + this.valueCellar[i] + "}");
        System.out.println("");
    }

    private boolean checkDuplicates(T key)
    {
        for (int i = 0; i < this.keyArray.length; i++)
        {
            if (convertTtoInt((T) this.keyArray[i]) == convertTtoInt(key))
                return true;
        }

        for (int i = 0; i < this.keyCellar.length; i++)
        {
            if (convertTtoInt((T) this.keyCellar[i]) == convertTtoInt(key))
                return true;
        }

        return false;
    }
}
