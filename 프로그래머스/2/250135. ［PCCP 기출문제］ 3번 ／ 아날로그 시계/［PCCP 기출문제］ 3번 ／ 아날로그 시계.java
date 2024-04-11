class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {

        int timeToSecond2 = toSecond(h2, m2, s2);
        int timeToSecond1 = toSecond(h1, m1, s1);
        int diffCount = countAlarm(timeToSecond2) - countAlarm(timeToSecond1);

        if (isOverlap(h1, m1, s1)) {
            return diffCount + 1;
        }
        return diffCount;
    }

    private int toSecond(int hour, int minute, int second) {
        int result = hour * 60 * 60;
        result += minute * 60;
        result += second;

        return result;
    }

    private int countAlarm(int second) {

        int hourAlarmCount = (int) (second * 719 / 43200.0);
        int minuteAlaramCount = (int) (second * 59 / 3600.0);

        int penalty = 0;
        if (second >= 12 * 3600) {
            penalty++;
        }

        return 1 + hourAlarmCount + minuteAlaramCount - penalty;
    }

    private boolean isOverlap(int hour, int minute, int second) {
        double hourAngle = (hour * 30 + minute * 0.5 + second * 0.5 / 60) % 360;
        double minuteAngle = minute * 6 + second * 0.1;
        double secondAngle = second * 6;
        return hourAngle == secondAngle || minuteAngle == secondAngle;
    }
}