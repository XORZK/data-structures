public class Main {
  public static void main(String[] args) {
    BinaryTree<Integer> t = new BinaryTree<Integer>();

    for (int i = 0; i < 20; i++) {
      t.add((int) (Math.random() * 40));
    }

    t.svg("./tmp.html");

    System.out.println(t.size());
    System.out.println(t);
  }
}
