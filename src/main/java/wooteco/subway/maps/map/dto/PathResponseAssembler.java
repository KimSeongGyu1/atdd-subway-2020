package wooteco.subway.maps.map.dto;

import wooteco.subway.maps.fare.domain.Fare;
import wooteco.subway.maps.line.domain.Line;
import wooteco.subway.maps.map.domain.SubwayPath;
import wooteco.subway.maps.station.domain.Station;
import wooteco.subway.maps.station.dto.StationResponse;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PathResponseAssembler {
    public static PathResponse assemble(List<Line> lines, SubwayPath subwayPath, Map<Long, Station> stations) {
        List<StationResponse> stationResponses = subwayPath.extractStationId().stream()
                .map(it -> StationResponse.of(stations.get(it)))
                .collect(Collectors.toList());

        Fare extraFare = Fare.fromNumber(900);
        Fare distanceFare = subwayPath.calculateDistanceFare();
        Fare fare = Fare.add(distanceFare, extraFare);

        int duration = subwayPath.calculateDuration();
        int distance = subwayPath.calculateDistance();

        return new PathResponse(stationResponses, duration, distance, fare.getFare());
    }
}
