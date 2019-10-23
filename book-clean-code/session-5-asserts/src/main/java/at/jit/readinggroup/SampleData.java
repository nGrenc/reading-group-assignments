package at.jit.readinggroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class SampleData {

	static List<SampleObject> originalList;
	static List<SampleObject> similarList;
	static List<SampleObject> sameList;
	static List<SampleObject> differentList;

	static Set<SampleObject> originalSet;
	static Set<SampleObject> similarSet;
	static Set<SampleObject> sameSet;
	static Set<SampleObject> differentSet;


	static {
		originalList = new ArrayList<>();
		originalList.add(new SampleObject());
		originalList.add(new SampleObject());
		originalList.add(new SampleObject());

		similarList = new ArrayList<>();
		similarList.add(originalList.get(0));
		similarList.add(originalList.get(1));
		similarList.add(originalList.get(2));

		sameList = originalList;

		differentList = new ArrayList<>();
		differentList.add(new SampleObject());
		differentList.add(new SampleObject());


		originalSet.addAll(originalList);
		similarSet.addAll(similarList);
		sameSet = originalSet;
		differentSet.addAll(differentList);
	}
}

class SampleObject {
	int id = new Random().nextInt(10000);

	@Override
	public String toString() {
		return "SampleObject{id=" + id +'}';
	}
}

