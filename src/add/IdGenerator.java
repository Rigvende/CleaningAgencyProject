package add;

public class IdGenerator {
    protected static class Generator {
        protected static long userCounter;
        protected static long cleanerCounter;
        protected static long clientCounter;
        protected static long roleCounter;
        protected static long serviceCounter;
        protected static long orderCounter;

        public static long generateId(long x) {
            x++;
            return (x < Long.MAX_VALUE ? x : 0);
        }
    }
}