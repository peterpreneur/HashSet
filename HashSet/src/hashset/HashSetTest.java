package hashset;


import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import org.junit.Test;


public class HashSetTest {
	@Test
	public void should_insert_one_node_into_BST_one_level() {
		// arrange

		// act
		sut.insert(50, "50");

		// assert
		assertThat(sut.get(50), is("50"));

	}	
}
