public class TestBoolean {
    private static final int SIZE_TEST = 1000000;

    public static void main(String[] args) throws Exception {
        LotsOfBooleans[] first = new LotsOfBooleans[SIZE_TEST];
        LotsOfInts[] second = new LotsOfInts[SIZE_TEST];

        System.gc();
        long startMem = getMemory();

        for (int i = 0; i < SIZE_TEST; i++) {
            first[i] = new LotsOfBooleans();
        }

        System.gc();
        long endMem = getMemory();

        System.out.println("Size for LotsOfBooleans: " + (endMem - startMem));
        System.out.println("Average size: " + ((endMem - startMem) / ((double) SIZE_TEST)));

        System.gc();
        startMem = getMemory();
        for (int i = 0; i < SIZE_TEST; i++) {
            second[i] = new LotsOfInts();
        }
        System.gc();
        endMem = getMemory();

        System.out.println("Size for LotsOfInts: " + (endMem - startMem));
        System.out.println("Average size: " + ((endMem - startMem) / ((double) SIZE_TEST)));

        // Make sure nothing gets collected
        long total = 0;
        for (int i = 0; i < SIZE_TEST; i++) {
            total += (first[i].a0 ? 1 : 0) + second[i].a0;
        }
        System.out.println(total);
    }

    private static long getMemory() {
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }

    private static class LotsOfBooleans {
        public boolean a0;
    }

    private static class LotsOfInts {
        public int a0;
    }
}