package wooteco.subway.maps.fare.domain;

public class Fare {
    private static final int FIRST_OVER_THRESHOLD = 10;
    private static final int SECOND_OVER_THRESHOLD = 50;
    private static final int DEFAULT_FARE = 1250;

    private final int fare;

    private Fare(int fare) {
        this.fare = fare;
    }

    public static Fare from(int fare) {
        return new Fare(fare);
    }

    public static Fare fromDistance(int distance) {
        int firstOverDistance = distance - FIRST_OVER_THRESHOLD;
        int secondOverDistance = distance - SECOND_OVER_THRESHOLD;

        int totalFare = DEFAULT_FARE
            + calculateFirstOverFare(firstOverDistance)
            + calculateSecondOverFare(secondOverDistance);

        return new Fare(totalFare);
    }

    private static int calculateFirstOverFare(int overDistance) {
        if (overDistance <= 0) {
            return 0;
        } else if (overDistance >= 50) {
            return 1000;
        }

        return (int) ((Math.ceil((overDistance - 1) / 5) + 1) * 100);
    }

    private static int calculateSecondOverFare(int overDistance) {
        if (overDistance <= 0) {
            return 0;
        }

        return (int) ((Math.ceil((overDistance - 1) / 8) + 1) * 100);
    }

    public static Fare add(Fare fare1, Fare fare2) {
        return new Fare(fare1.getFare() + fare2.getFare());
    }

    public int getFare() {
        return fare;
    }
}
