package at.jit.readinggroup

import at.jit.readinggroup.mystery.MysteriousObject
import spock.lang.Specification
import spock.lang.Title;

@Title("All tests in this class are meant to fail")
class FailingTest extends Specification {

    def 'the failure report should be informative as possible'() {
        expect: 'an informative message to be returned'
        new MysteriousObject() == "It's mystery!"
    }

}
