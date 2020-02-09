package at.jit.readinggroup.kitchen

import spock.lang.Specification
import spock.lang.Subject

/**
 * This test class is designed to test your knowledge in parameterized tests
 */
class EnterpriseKitchen_OpenAndCleanTest extends Specification {

  @Subject
  def kitchen = new EnterpriseKitchen()

  def 'should not be open at 1'() {
    expect:
    !kitchen.isOpenAt(1)
  }
  def 'should not be open at 2'() {
    expect:
    !kitchen.isOpenAt(2)
  }
  def 'should not be open at 6'() {
    expect:
    !kitchen.isOpenAt(6)
  }
  def 'should be open at 7'() {
    expect:
    kitchen.isOpenAt(7)
  }
  def 'should be open at 8'() {
    expect:
    kitchen.isOpenAt(8)
  }
  def 'should be open at 9'() {
    expect:
    kitchen.isOpenAt(9)
  }
  def 'should be open at 15'() {
    expect:
    kitchen.isOpenAt(15)
  }
  def 'should not be open at 16'() {
    expect:
    !kitchen.isOpenAt(16)
  }
  def 'should not be open at 17'() {
    expect:
    !kitchen.isOpenAt(17)
  }
  def 'should not be open at 19'() {
    expect:
    !kitchen.isOpenAt(19)
  }
  def 'should not be open at 23'() {
    expect:
    !kitchen.isOpenAt(23)
  }
  def 'should not be open at 24'() {
    expect:
    !kitchen.isOpenAt(24)
  }

  def 'Malcolm should be on duty cleaner at 1'() {
    expect:
    kitchen.onDutyCleaner(1).getName() == "Malcolm"
  }
  def 'Travis should be on duty cleaner at 2'() {
    expect:
    kitchen.onDutyCleaner(2).getName() == "Travis"
  }
  def 'Malcolm should be on duty cleaner at 3'() {
    expect:
    kitchen.onDutyCleaner(3).getName() == "Malcolm"
  }
  def 'Travis should be on duty cleaner at 4'() {
    expect:
    kitchen.onDutyCleaner(4).getName() == "Travis"
  }
  def 'Malcolm should be on duty cleaner at 5'() {
    expect:
    kitchen.onDutyCleaner(5).getName() == "Malcolm"
  }
  def 'Travis should be on duty cleaner at 6'() {
    expect:
    kitchen.onDutyCleaner(6).getName() == "Travis"
  }
}
