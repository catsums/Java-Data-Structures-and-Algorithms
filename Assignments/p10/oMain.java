import java.util.Scanner;

public class oMain
{
	public static void main(String[] args)
	{
		HashMap<Integer, Character> map = new HashMap<>(15, 3);

		System.out.println("Should be 0: " + map.count());
		for (int i = 0; i < 25; i++)
		{
			int range = (300 - 0) + 1;
			int random = (int) (Math.random() * range) + 1;

			System.out.println("===============================================================================================================");
			System.out.println("Insert: " + random + ". Hash value: " + map.arrayHash(random) + ". Cellar hash value: " + map.cellarHash(random));
			System.out.println("Successful insert: " + map.put(random, (char) (65 + i)));

			System.out.print("Keys ");
			print(map.getKeyArray());

			System.out.print("Values ");
			print(map.getValueArray());

			System.out.println("");
			System.out.print("Key cellar ");
			print(map.getKeyCellar());

			System.out.print("Value cellar ");
			print(map.getValueCellar());

			System.out.println("Pair count: " + map.count());
			System.out.println("===============================================================================================================");
		}
		System.out.println("===============================================================================================================");
		System.out.println("Rehashed table");
		HashMap<Integer, Character> rehashed = map.rehash(20, 5);

		System.out.print("Keys ");
		print(rehashed.getKeyArray());

		System.out.print("Vals ");
		print(rehashed.getValueArray());

		System.out.print("Key Cellar ");
		print(rehashed.getKeyCellar());

		System.out.print("Vals Cellar ");
		print(rehashed.getValueCellar());

		System.out.println("Pair count: " + rehashed.count());
		System.out.println("===============================================================================================================");

		Scanner scan = new Scanner(System.in);
		System.out.println("Continue with manual inputs?");
		String ans = scan.nextLine();
		if (ans.compareTo("Y") == 0 || ans.compareTo("y") == 0)
		{
			System.out.println("\nManual");
			HashMap<Integer, Character> g = new HashMap<>(15, 3);
			int[] ara = {21, 23, 10, 182, 252, 239, 5, 288, 96, 254, 181, 123};
			for (int i = 0; i < ara.length; i++)
			{
				System.out.println("Insert: " + ara[i]);
				g.put(ara[i], (char) (65 + i));

				System.out.print("Keys: ");
				print(g.getKeyArray());
				System.out.print("Vals: ");
				print(g.getValueArray());
				System.out.println();
				System.out.print("Cellar keys: ");
				print(g.getKeyCellar());
				System.out.print("Cellar vals: ");
				print(g.getValueCellar());
				System.out.println("\n");
			}

			System.out.println("All should be true;");
			for (int i = 0; i < ara.length; i++)
			{
				System.out.print(g.isContained(ara[i], (char) (65 + i)) + " (" + ara[i] + "," + (char) (65 + i) + "); ");
			}

			System.out.println("\nAll should be false;");
			for (int i = 0; i < ara.length; i++)
			{
				System.out.print(g.isContained(ara[i], (char) (65 + i + 1)) + " (" + ara[i] + "," + (char) (65 + i + 1) + "); ");
			}

			System.out.println("\n\nTesting get(): ");
			for (int i = 0; i < ara.length; i++)
			{
				System.out.print("(" + ara[i] + "," + g.get(ara[i]) + "); ");
			}
		}

		System.out.println("--- String and integer ---");
		HashMap<String, Integer> str = new HashMap<>(3, 10);
		String[] characters = {"Shrek", "Fiona", "Donkey", "Gingerbread man", "Prince Charming", "Harold", "Frog Harold", "Pinnochio", "Dragon", "Terminator", "Prof G", "Malnar", "Aapvis", "Paloeka"};
		for (int i = 0; i < characters.length; i++)
		{
			System.out.println("Insert: " + characters[i] + ". Hash value: " + str.arrayHash(characters[i]) + ". Cellar hash value: " + str.cellarHash(characters[i]));
			System.out.println("Successful insert: " + str.put(characters[i], i));
			str.put(characters[i], i);

			System.out.print("Keys ");
			print(str.getKeyArray());
			System.out.print("Vals ");
			print(str.getValueArray());
			System.out.println();
			System.out.print("Key cellar ");
			print(str.getKeyCellar());
			System.out.print("Vals cellar ");
			print(str.getValueCellar());
			System.out.println();
		}
	}

	public static void print(Object[] ara)
	{
		for (int print = 0; print < ara.length; print++)
		{
			if (print == 0)
				if (ara[print] == null)
					System.out.print("[-");
				else
					System.out.print("[" + ara[print]);
			else if (print != ara.length - 1)
				if (ara[print] == null)
					System.out.print(", " + "-");
				else
					System.out.print(", " + ara[print]);
			else
			if (ara[print] == null)
				System.out.println(", -]");
			else
				System.out.println(", " + ara[print] + "]");
		}
	}
}
