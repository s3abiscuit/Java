package algs.array.search;

public class LC0278FirstBadVersion {
    public static void main(String[] args) {

    }

    public int firstBadVersion1(int n) {
        int start = 1, end = n;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (!isBadVersion(mid))
                start = mid + 1;
            else
                end = mid;
        }
        return start;
    }

    public int firstBadVersion2(int n) {
        return search(0, n);
    }

    private int search(int lo, int hi) {
        if (lo == hi) return lo;
        int mid = lo + (hi - lo) / 2;
        if (!isBadVersion(mid)) {
            lo = mid + 1;
        } else {
            if (!isBadVersion(mid - 1)) return mid;
            else hi = mid - 1;
        }
        return search(lo, hi);
    }

    private boolean isBadVersion(int n) {
        return n % 2 == 0;
    }
}
