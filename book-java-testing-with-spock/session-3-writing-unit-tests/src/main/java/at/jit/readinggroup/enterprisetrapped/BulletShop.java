package at.jit.readinggroup.enterprisetrapped;

import java.math.BigDecimal;

public interface BulletShop {

  /**
   * Function to purchase bullets for the Enterprise side cannon.
   * @param payment - amount of money payed
   * @return a number of bullets that are bought for this price
   */
  int buyBullets(BigDecimal payment);
}
