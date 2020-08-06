package wooteco.subway.maps.fare.domain;

public class Fare {
    private static final int FIRST_OVER_THRESHOLD = 10;
    private static final int FIRST_DISTANCE_UNIT = 5;
    private static final int SECOND_OVER_THRESHOLD = 50;
    private static final int SECOND_DISTANCE_UNIT = 8;
    private static final int DEFAULT_FARE = 1250;
    private static final int OVER_FARE_UNIT = 100;

    private final int fare;

    private Fare(int fare) {
        this.fare = fare;
    }

    public static Fare fromNumber(int fare) {
        return new Fare(fare);
    }

    public static Fare add(Fare fare1, Fare fare2) {
        return new Fare(fare1.getFare() + fare2.getFare());
    }

    public static Fare fromDistance(int distance) {
        int totalFare = DEFAULT_FARE
            + firstOverFare(distance - FIRST_OVER_THRESHOLD)
            + secondOverFare(distance - SECOND_OVER_THRESHOLD);

        return new Fare(totalFare);
    }

    private static int firstOverFare(int overDistance) {
        if (isNegative(overDistance)) {
            return 0;
        }

        overDistance = Math.min(overDistance, SECOND_OVER_THRESHOLD);

        return calculateOverFare(overDistance, FIRST_DISTANCE_UNIT);
    }

    private static int secondOverFare(int overDistance) {
        if (isNegative(overDistance)) {
            return 0;
        }

        return calculateOverFare(overDistance, SECOND_DISTANCE_UNIT);
    }

    private static boolean isNegative(int overDistance) {
        return overDistance <= 0;
    }

    private static int calculateOverFare(int overDistance, int distanceUnit) {
        return (int) ((Math.ceil((overDistance - 1) / distanceUnit) + 1) * OVER_FARE_UNIT);
    }

    public int getFare() {
        return fare;
    }
}
