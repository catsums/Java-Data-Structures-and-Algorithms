public class SkipListNode<T>
{

	public T key;
	public SkipListNode<T>[] next;

	@SuppressWarnings("unchecked")
	SkipListNode(T i, int n)
	{
		key = i;
		next = new SkipListNode[n];

		for (int j = 0; j < n; j++)
			next[j] = null;
	}
	//my own shit - outputs the node in json notation showing the key and the levels it has
	@Override
	public String toString(){
		String out = "{";
		out += "key: "+key;
		out += ", levels: "+next.length;
		out += "}";
		return out;
	}

}