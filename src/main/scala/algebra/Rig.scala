package algebra

import annotation.tailrec
import scala.{specialized => sp}

/**
 * Rig is a ring whose additive structure doesn't have an inverse (ie. it is
 * monoid, not a group). Put another way, a Rig is a Ring without a negative.
 */
trait Rig[@sp(Byte, Short, Int, Long, Float, Double) A] extends Semiring[A] with AdditiveMonoid[A] with MultiplicativeMonoid[A] {
  /**
   * This is similar to `Semigroup#pow`, except that `a pow 0` is defined to be
   * the multiplicative identity.
   */
  override def pow(a: A, n: Int): A =
    if (n >= 0) multiplicative.sumn(a, n)
    else throw new IllegalArgumentException("Illegal negative exponent %s to Monoid#pow" format n)
}

object Rig {
  @inline final def apply[A](implicit r: Rig[A]): Rig[A] = r
}
