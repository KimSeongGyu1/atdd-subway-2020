package wooteco.subway.maps.fare.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FareTest {

    @DisplayName("거리 요금")
    @Test()
    void fromDistance() {
        Fare defaultFare = Fare.fromDistance(10);
        Fare overDistanceFare = Fare.fromDistance(16);
        Fare overDistanceFare2 = Fare.fromDistance(58);

        assertAll(
            () -> assertThat(defaultFare.getFare()).isEqualTo(1250),
            () -> assertThat(overDistanceFare.getFare()).isEqualTo(1450),
            () -> assertThat(overDistanceFare2.getFare()).isEqualTo(2350)
        );
    }

    @DisplayName("요금 합")
    @Test
    void add() {
        Fare fare1 = Fare.fromNumber(1250);
        Fare fare2 = Fare.fromNumber(1250);

        Fare sum = Fare.add(fare1, fare2);

        assertThat(sum.getFare()).isEqualTo(2500);
    }

    @DisplayName("요금 최대 값")
    @Test
    void max() {
        Fare fare1 = Fare.fromNumber(1250);
        Fare fare2 = Fare.fromNumber(2500);

        Fare max = Fare.max(fare1, fare2);

        assertThat(max.getFare()).isEqualTo(2500);
    }
}