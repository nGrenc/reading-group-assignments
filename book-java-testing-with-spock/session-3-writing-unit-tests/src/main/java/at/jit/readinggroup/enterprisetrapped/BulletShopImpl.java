package at.jit.readinggroup.enterprisetrapped;

import java.math.BigDecimal;

public class BulletShopImpl implements BulletShop {

    private BigDecimal pricePerBullet;

    public BulletShopImpl(BigDecimal pricePerBullet) {
        this.pricePerBullet = pricePerBullet;
    }

    @Override
    public int buyBullets(BigDecimal payment) {
      return payment.divide(pricePerBullet).toBigInteger().intValue();
    }
}
