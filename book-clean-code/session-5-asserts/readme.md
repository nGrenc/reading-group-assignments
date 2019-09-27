# Instructions

Add two new JUnit assert functions that assert

1) two sets contain the same set of elements, but are themselves not the same object
2) two lists contain the same lists of elements, but are themselves not the same object

And write tests for them.

 ---

Use sample data (from *SampleData* class) to help you out.  
The following comparisons with your assert function should pass/fail:

  	compare (originalList, originalList)  -> fail;
  	compare (originalList, similarList)   -> pass;
  	compare (originalList, sameList)      -> fail;
  	compare (originalList, differentList) -> fail;

Same goes for the ...Set data.

