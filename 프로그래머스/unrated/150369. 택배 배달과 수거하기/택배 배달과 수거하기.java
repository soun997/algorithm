class Solution {
    
    int[] deliveries;
    int[] pickups;


    int delivery(int idx, int cap, int[] houses){

        if (cap < houses[idx]){
            houses[idx] = houses[idx] - cap;
            return 0;
        }
        cap = cap - houses[idx];
        houses[idx] = 0;
        return cap;
    }

    int validate(int n){
        for (int i = n - 1; i >= 0; i--){
            if (deliveries[i] > 0 || pickups[i] > 0){
                return (i + 1);
            }
        }
        return -1;
    }

    long process(int cap, int n){
        int deliveryCap = cap;
        int pickupCap = cap;
        int reached = -1;
        long distance = 0;
        for (int i = n - 1; i >= 0; i--){
            if (deliveries[i] > 0 || pickups[i] > 0){
                reached = Math.max(reached, i);
                distance = (long)(reached + 1) * 2;
            }
            deliveryCap = delivery(i, deliveryCap, deliveries);
            pickupCap = delivery(i, pickupCap, pickups);
            if (deliveryCap == 0 && pickupCap == 0){
                break;
            }
        }
        n = validate(n);
        if (n == -1)
            return distance;
        return distance + process(cap, n);
    }

    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        this.deliveries = deliveries;
        this.pickups = pickups;

        return process(cap, n);
    }
}