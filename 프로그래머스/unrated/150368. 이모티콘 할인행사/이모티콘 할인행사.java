class Solution {
    
    final int[] DISCOUNT_RATIOS = { 10, 20, 30, 40 };
    int[][] users;
    int[] emoticons;
    int[] discountRatios;
    int maxSubscribers;
    int maxSales;


    int[] getPurchaseResult(int[] discounted){

        int subscribers = 0;
        int sales = 0;

        for (int[] user : users){
            int totalCharge = 0;
            for (int i = 0; i < discounted.length; i++) {
                // 사용자의 기준 할인율보다 이모티콘 할인율이 크다면 -> 구매 진행
                if (discountRatios[i] >= user[0]){
                    totalCharge = totalCharge + discounted[i];
                }
            }
            // 사용자의 예산보다 총 구매금액이 크다면 -> 이모티콘 플러스 가입
            if (totalCharge >= user[1]){
                subscribers++;
                continue;
            }
            sales = sales + totalCharge;
        }
        return new int[]{subscribers, sales};
    }

    int getDiscountedCharge(int emoticonCharge, int discountRatio){

        return (int)(emoticonCharge - emoticonCharge * (discountRatio / 100D));
    }

    int[] discountEmoticons(){
        int[] discounted = new int[emoticons.length];
        for (int i = 0; i < discounted.length; i++) {
            discounted[i] = getDiscountedCharge(emoticons[i], discountRatios[i]);
        }
        return discounted;
    }

    void combination(int count){

        // 이모티콘의 할인율을 모두 선정했다면
        if (count == emoticons.length){
            // TODO: 고객이 얼마나 구매할 것인지 계산
            int[] discounted = discountEmoticons();
            int[] result = getPurchaseResult(discounted);

            if (result[0] > maxSubscribers){
                maxSubscribers = result[0];
                maxSales = result[1];
            }
            if (result[0] == maxSubscribers){
                if (result[1] > maxSales){
                    maxSales = result[1];
                }
            }
            return;
        }

        for (int i = 0; i < DISCOUNT_RATIOS.length; i++) {
            discountRatios[count] = DISCOUNT_RATIOS[i];
            combination(count + 1);
        }
    }

    public int[] solution(int[][] users, int[] emoticons) {
        this.users = users;
        this.emoticons = emoticons;
        this.discountRatios = new int[emoticons.length];
        this.maxSales = Integer.MIN_VALUE;
        this.maxSubscribers = Integer.MIN_VALUE;

        combination(0);
        System.out.println(maxSubscribers + " " + maxSales);

        return new int[]{maxSubscribers, maxSales};
    }
}