package wooteco.subway.maps.map.dto;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import wooteco.subway.maps.fare.domain.Fare;
import wooteco.subway.maps.line.domain.Line;
import wooteco.subway.maps.map.domain.SubwayPath;
import wooteco.subway.maps.station.domain.Station;
import wooteco.subway.maps.station.dto.StationResponse;
import wooteco.subway.members.member.domain.LoginMember;

public class PathResponseAssembler {
    public static PathResponse assemble(List<Line> lines, SubwayPath subwayPath, Map<Long, Station> stations,
        Optional<LoginMember> loginMember) {
        List<StationResponse> stationResponses = subwayPath.extractStationId().stream()
            .map(it -> StationResponse.of(stations.get(it)))
            .collect(Collectors.toList());

        int duration = subwayPath.calculateDuration();
        int distance = subwayPath.calculateDistance();
        Fare fare = calculateFare(lines, subwayPath, loginMember);

        return new PathResponse(stationResponses, duration, distance, fare.getFare());
    }

    private static Fare calculateFare(List<Line> lines, SubwayPath subwayPath, Optional<LoginMember> loginMember) {
        Fare extraFare = lines.stream()
            .map(Line::getExtraFare)
            .reduce(Fare.fromNumber(0), Fare::max);
        Fare distanceFare = subwayPath.calculateDistanceFare();
        Fare fare = Fare.add(distanceFare, extraFare);

        if (loginMember.isPresent()) {
            return Fare.calculateAgeDiscountedFare(fare, loginMember.get().getAge());
        }
        return fare;
    }
}
