package at.jit.readinggroup.enterprisetrapped;

import java.math.BigDecimal;

public class SideEnterpriseCannon {

    private int bulletStash;
    private BigDecimal totalMoneySpent;
    private BulletShop shop;

    public SideEnterpriseCannon(BulletShop shop) {
        this.shop = shop;
        this.bulletStash = 15;
        this.totalMoneySpent = BigDecimal.valueOf(0l);
    }

    public int onEvent(Event event) {
        int numberOfBulletsUsed = 0;

        if (event.isFalseAttack()) {
            return numberOfBulletsUsed;
        }
        else {
            if (event.isATrap()) {
                while (event.getNumberOfAttackers() > 0) {
                    if (bulletStash < 0) {
                        bulletStash += shop.buyBullets(BigDecimal.valueOf(50l));
                        totalMoneySpent = totalMoneySpent.add(BigDecimal.valueOf(50l));
                    }
                    numberOfBulletsUsed++;
                    event.disableAttacker();
                }
                return numberOfBulletsUsed;
            }
            else {
                escape();
                return numberOfBulletsUsed;
            }
        }
    }

    public BigDecimal getMoneySpent() {
      return totalMoneySpent;
    }

    private void escape() {
        // execute escape
    }
}
