
class Predicate {
    public static final TernaryIntPredicate ALL_DIFFERENT = (x, y, z) -> x != y && y != z && x != z;

    @FunctionalInterface
    public interface TernaryIntPredicate {

        boolean test(int x, int y, int z);
        // Write a method here
    }
}