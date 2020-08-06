package wooteco.subway.maps.map.dto;

import wooteco.subway.maps.fare.domain.Fare;
import wooteco.subway.maps.map.domain.SubwayPath;
import wooteco.subway.maps.station.domain.Station;
import wooteco.subway.maps.station.dto.StationResponse;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PathResponseAssembler {
    public static PathResponse assemble(SubwayPath subwayPath, Map<Long, Station> stations) {
        List<StationResponse> stationResponses = subwayPath.extractStationId().stream()
                .map(it -> StationResponse.of(stations.get(it)))
                .collect(Collectors.toList());

        int duration = subwayPath.calculateDuration();
        int distance = subwayPath.calculateDistance();
        Fare fare = subwayPath.calculateDistanceFare();

        return new PathResponse(stationResponses, duration, distance, fare.getFare());
    }
}
