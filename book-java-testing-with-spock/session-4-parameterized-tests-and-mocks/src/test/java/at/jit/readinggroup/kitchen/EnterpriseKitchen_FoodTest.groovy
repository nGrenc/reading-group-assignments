package at.jit.readinggroup.kitchen

import at.jit.readinggroup.kitchen.model.EnterprisePantry
import at.jit.readinggroup.kitchen.model.EnterpriseWarehouse
import spock.lang.Specification
import spock.lang.Subject

/**
 * This class is designed to test your knowledge in using mocks and stubs
 */
class EnterpriseKitchen_FoodTest extends Specification {

  private EnterpriseWarehouse warehouse
  private EnterprisePantry pantry

  @Subject
  def kitchen = new EnterpriseKitchen(warehouse: warehouse, pantry: pantry)

  def 'should prepare food for a lactose intolerant person'() {
    given: 'a lactose intolerant mocked person'
    when: 'prepareFoodFor method is called'
    then: 'verify that person ate one lasagna'
  }

  def 'should prepare food for a lactose tolerant person'() {
    given: 'a lactose tolerant mocked person'
    when: 'prepareFoodFor method is called'
    then: 'verify that person ate two pizzas'
  }
}
