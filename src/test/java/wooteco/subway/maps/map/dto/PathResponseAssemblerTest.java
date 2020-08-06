package wooteco.subway.maps.map.dto;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import wooteco.subway.maps.fare.domain.Fare;
import wooteco.subway.maps.line.domain.Line;
import wooteco.subway.maps.map.domain.SubwayPath;
import wooteco.subway.maps.station.domain.Station;

class PathResponseAssemblerTest {

    @DisplayName("추가요금 합산 확인")
    @Test
    void assemble() {
        SubwayPath subwayPath = mock(SubwayPath.class);
        Map<Long, Station> stations = mock(Map.class);
        Line line1 = mock(Line.class);
        Line line2 = mock(Line.class);
        List<Line> lines = Arrays.asList(line1, line2);

        when(subwayPath.calculateDistanceFare()).thenReturn(Fare.fromNumber(1250));
        when(line1.getExtraFare()).thenReturn(Fare.fromNumber(500));
        when(line2.getExtraFare()).thenReturn(Fare.fromNumber(900));

        PathResponse pathResponse = PathResponseAssembler.assemble(lines, subwayPath, stations);

        assertThat(pathResponse.getFare()).isEqualTo(2150);
    }
}