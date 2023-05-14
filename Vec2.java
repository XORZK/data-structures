public class Vec2<T extends Comparable<T>> {
  private T x, y;

  public Vec2() {
    this.x = null;
    this.y = null;
  }

  public Vec2(T x, T y) {
    this.x = x;
    this.y = y;
  }

  public Vec2(T v) {
    this.x = this.y = v;
  }

  public T getX() {
    return this.x;
  }

  public T getY() {
    return this.y;
  }

  public void setX(T x) {
    this.x = x;
  }

  public void setY(T y) {
    this.y = y;
  }

  @Override
  public String toString() {
    return String.format("<%s, %s>", this.x, this.y);
  }
}