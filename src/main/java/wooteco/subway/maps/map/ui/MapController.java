package wooteco.subway.maps.map.ui;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import wooteco.security.core.AuthenticationPrincipal;
import wooteco.subway.maps.map.application.MapService;
import wooteco.subway.maps.map.domain.PathType;
import wooteco.subway.maps.map.dto.MapResponse;
import wooteco.subway.maps.map.dto.PathResponse;
import wooteco.subway.members.member.domain.LoginMember;

@RestController
public class MapController {
    private MapService mapService;

    public MapController(MapService mapService) {
        this.mapService = mapService;
    }

    @GetMapping("/paths")
    public ResponseEntity<PathResponse> findPath(
        @RequestParam Long source,
        @RequestParam Long target,
        @RequestParam PathType type
    ) {
        return ResponseEntity.ok(mapService.findPath(source, target, type, Optional.empty()));
    }

    @GetMapping(path = "/paths", headers = "Authorization")
    public ResponseEntity<PathResponse> findPath(
        @RequestParam Long source,
        @RequestParam Long target,
        @RequestParam PathType type,
        @AuthenticationPrincipal LoginMember loginMember
    ) {
        return ResponseEntity.ok(mapService.findPath(source, target, type, Optional.ofNullable(loginMember)));
    }

    @GetMapping("/maps")
    public ResponseEntity<MapResponse> findMap() {
        MapResponse response = mapService.findMap();
        return ResponseEntity.ok(response);
    }
}
