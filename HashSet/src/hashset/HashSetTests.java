package hashset;


import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import org.junit.Test;

public class HashSetTests {
	private static final int BIG_TEST_SIZE = 500;

	@Test
	public void should_insert_value_into_HashSet_and_retrieve_it()
	{
		//arrange
		HashSet<String, Integer> sut = new HashSet<>();
		
		//add
		sut.put("One", 1);
		
		//assert
		assertThat(sut.get("One"), is(1));
	}
	
	@Test
	public void should_insert_multiple_value_into_HashSet_and_retrieve_it()
	{
		//arrange
		HashSet<String, Integer> sut = new HashSet<>();
		
		//add
		sut.put("My First String", 1);
		sut.put("My Second String", 2);
		sut.put("My Third String", 3);
		sut.put("My Seventh String", 7);
		
		//assert
		assertThat(sut.get("My First String"), is(1));
		assertThat(sut.get("My Second String"), is(2));
		assertThat(sut.get("My Third String"), is(3));
		assertThat(sut.get("My Seventh String"), is(7));

	}	
	
	@Test
	public void should_remove_an_element_from_HashSet()
	{
		//arrange
		HashSet<String, Integer> sut = new HashSet<>();
		
		//add
		sut.put("My First String", 1);
		sut.remove("My First String");
		
		//assert
		assertNull(sut.get("My First String"));

	}	
	
	@Test
	public void should_insert_and_remove_into_HashSet_and_retrieve_it()
	{
		//arrange
		HashSet<String, Integer> sut = new HashSet<>();
		
		//add
		sut.put("My First String", 1);
		sut.put("My Second String", 2);
		sut.put("My Third String", 3);
		sut.put("My Seventh String", 7);
		
		sut.remove("My Seventh String");

		//assert
		assertThat(sut.get("My First String"), is(1));
		assertThat(sut.get("My Second String"), is(2));
		assertThat(sut.get("My Third String"), is(3));
		assertNull(sut.get("My Seventh String"));

	}	
	
	@Test
	public void should_insert_and_remove_lots_of_elements()
	{
		//arrange
		HashSet<Integer, Integer> sut = new HashSet<>();
		
		for (int i=0; i<BIG_TEST_SIZE; i++)
		{
			sut.put(i, i+10);
		}
		
		for (int i=0; i<BIG_TEST_SIZE; i++)
		{
			assertThat(sut.get(i), is(i+10));
		}
		
		for (int i=BIG_TEST_SIZE-1; i>=0; i--)
		{
			sut.remove(i);
		}		

		for (int i=0; i<BIG_TEST_SIZE; i++)
		{
			assertNull(sut.get(i));
		}		
	}		
	
	@Test
	public void should_try_to_remove_element_that_doesnt_exist()
	{	
		HashSet<String, Integer> sut = new HashSet<>();

		sut.put("My First String", 1);
		sut.put("My Second String", 2);
		sut.put("My Third String", 3);
		sut.put("My Seventh String", 7);

		sut.remove("My String");
		
		assertThat(sut.get("My First String"), is(1));
		assertThat(sut.get("My Second String"), is(2));
		assertThat(sut.get("My Third String"), is(3));
		assertThat(sut.get("My Seventh String"), is(7));		
	}
}

