package at.jit.readinggroup

import spock.lang.Specification;

class MultiTest extends Specification {

    def 'should be able to set the protected value with implicit setter'() {
        given: 'an object'
        def myPrecious = new VeryRestrictedObject()
        when: 'the property is set to true'

        then: 'the value is actually true'
        myPrecious.getTheRestrictedValue()
    }

    def 'should be able to set the protected value with implicit constructor'() {
        when: 'an object with overloaded (map) constructor'
        def myPrecious = new VeryRestrictedObject(     )
        then: 'the value is actually true'
        myPrecious.getTheRestrictedValue()
    }

    def 'groovy asserts will pass'() {
        expect: 'all asserts to pass'
        assert true
        assert !false
        assert "Beno"
        assert ""
        assert new Object()
        assert null
        assert 0
        assert 42
        assert new Object[3]
        assert new Object[0]
        assert new Object() == new Object()
        // assert keyword is not really needed here,
        // I just put it here for clarity
    }

    def 'should build maps and lists'() {
        given: 'an empty list and a map'
        def mealList = []
        def menuMap = [:]

        when: 'map is populated'

        then: 'map contains expected values'
        mealList.contains('Humans')
        menuMap['lunch'] == 'Humans'
    }

    def 'should interpolate strings'() {
        given: 'a table with cookies'
        def cookies = new CookiesOnTheTable()

        when: 'the cookie amount is properly set'

        then: 'strings will match'
        "There are 10 cookies on the table" == "There are $cookies.amount cookies on the table"
    }

    def 'should read file'() {
        given: 'a file with absolutely correct path'
        def path = 'ring.txt'

        when: 'the content is read'
        def content = new File(path).text

        then: 'assert the content'
        'my precious' == content
    }

    def 'should evaluate closures'() {
        given: 'a closure that doubles the value'
        def doubled = { x -> x }

        expect: 'closure to double the value'
        6 == doubled(3)
    }

    def 'use expando to implement the interface'() {
        given: 'an expando implementation'
        def ex = new Expando()
        ex.myThoughts = { return "dark" }
        AnUnimplementedInterface implemented = ex as AnUnimplementedInterface

        expect: 'the expando is implemented as the interface'
        implemented.myThoughts() == "sunny butterflies"
    }

    def 'use stub to implement the interface'() {
        given: 'an expando implementation'
        def stub = Stub(AnUnimplementedInterface)
        stub.myThoughts() >> "mysterious"

        expect: 'the expando is implemented as the interface'
        stub.myThoughts() == "happy rainbows"
    }

    def 'the parameterized tests are a picnic'() {
        given: 'the lunch menu'
        def menu = ["Fish", "Bear", "Christoph"]

        expect: 'the waiter to tell the truth'
        waiterSaysTheyHaveIt == menu.contains(meal)

        where: 'I asked for'
        meal    || waiterSaysTheyHaveIt
        'Fish'  || true
        'Mario' || true
    }
}
