class Router {

    private int memoryLimit;
    private LinkedList<int[]> packetQueue;
    private Set<String> packetSet;
    private Map<Integer, List<int[]>> packetCounts;

    public Router(int memoryLimit) {
        this.memoryLimit = memoryLimit;
        this.packetQueue = new LinkedList<>();
        this.packetSet = new HashSet<>();
        this.packetCounts = new HashMap<>();
    }

    public boolean addPacket(int source, int destination, int timestamp) {
        String packetKey = source + "-" + destination + "-" + timestamp;
        if (packetSet.contains(packetKey)) {
            return false;
        }

        int[] newPacket = new int[]{source, destination, timestamp};

        if (packetQueue.size() == memoryLimit) {
            int[] removedPacket = packetQueue.poll();
            String removedPacketKey = removedPacket[0] + "-" + removedPacket[1] + "-" + removedPacket[2];
            packetSet.remove(removedPacketKey);

            int removedDestination = removedPacket[1];
            List<int[]> destinationList = packetCounts.get(removedDestination);
            if (destinationList != null) {
                // Remove exactly the packet matching the full triple
                for (int i = 0; i < destinationList.size(); i++) {
                    int[] p = destinationList.get(i);
                    if (p[0] == removedPacket[0] && p[1] == removedPacket[1] && p[2] == removedPacket[2]) {
                        destinationList.remove(i);
                        break;
                    }
                }
                if (destinationList.isEmpty()) {
                    packetCounts.remove(removedDestination);
                }
            }
        }

        packetQueue.add(newPacket);
        packetSet.add(packetKey);

        packetCounts.putIfAbsent(destination, new ArrayList<>());
        List<int[]> destinationList = packetCounts.get(destination);
        // Append at the end because timestamps are always increasing in addPacket
        destinationList.add(newPacket);

        return true;
    }

    public int[] forwardPacket() {
        if (packetQueue.isEmpty()) {
            return new int[]{};
        }

        int[] packetToForward = packetQueue.poll();
        String packetKey = packetToForward[0] + "-" + packetToForward[1] + "-" + packetToForward[2];
        packetSet.remove(packetKey);

        int destination = packetToForward[1];
        List<int[]> destinationList = packetCounts.get(destination);
        if (destinationList != null) {
            for (int i = 0; i < destinationList.size(); i++) {
                int[] p = destinationList.get(i);
                if (p[0] == packetToForward[0] && p[1] == packetToForward[1] && p[2] == packetToForward[2]) {
                    destinationList.remove(i);
                    break;
                }
            }
            if (destinationList.isEmpty()) {
                packetCounts.remove(destination);
            }
        }

        return packetToForward;
    }

    public int getCount(int destination, int startTime, int endTime) {
        List<int[]> destinationList = packetCounts.get(destination);
        if (destinationList == null) {
            return 0;
        }

        int left = binarySearchLeft(destinationList, startTime);
        int right = binarySearchRight(destinationList, endTime);

        if (left == -1 || right == -1 || left > right) {
            return 0;
        }

        return right - left + 1;
    }

    private int binarySearchLeft(List<int[]> list, int target) {
        int lo = 0, hi = list.size() - 1;
        int ans = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (list.get(mid)[2] >= target) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return ans;
    }

    private int binarySearchRight(List<int[]> list, int target) {
        int lo = 0, hi = list.size() - 1;
        int ans = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (list.get(mid)[2] <= target) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return ans;
    }
}