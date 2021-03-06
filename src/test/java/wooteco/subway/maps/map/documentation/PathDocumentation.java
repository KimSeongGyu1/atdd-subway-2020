package wooteco.subway.maps.map.documentation;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.web.context.WebApplicationContext;

import wooteco.security.core.TokenResponse;
import wooteco.subway.common.documentation.Documentation;
import wooteco.subway.maps.map.application.MapService;
import wooteco.subway.maps.map.dto.PathResponse;
import wooteco.subway.maps.map.ui.MapController;
import wooteco.subway.maps.station.dto.StationResponse;

@WebMvcTest(controllers = {MapController.class})
public class PathDocumentation extends Documentation {
    @Autowired
    private MapController mapController;
    @MockBean
    private MapService mapService;

    protected TokenResponse tokenResponse;

    @BeforeEach
    public void setUp(WebApplicationContext context, RestDocumentationContextProvider restDocumentation) {
        super.setUp(context, restDocumentation);
        tokenResponse = new TokenResponse("token");
    }

    @Test
    void findPath() {
        PathResponse pathResponse = new PathResponse(
            Arrays.asList(
                new StationResponse(1L, "화정역", null, null),
                new StationResponse(2L, "주엽역", null, null)),
            10,
            10,
            1250
        );

        when(mapService.findPath(any(), any(), any(), any())).thenReturn(pathResponse);

        given().log().all().
            accept(MediaType.APPLICATION_JSON_VALUE).
            when().
            get("/paths?source=1&target=2&type=DISTANCE").
            then().
            log().all().
            apply(document("paths/findWithLogin",
                getDocumentRequest(),
                getDocumentResponse(),
                responseFields(
                    fieldWithPath("stations").type(JsonFieldType.ARRAY).description("스테이션 목록"),
                    fieldWithPath("stations[].id").type(JsonFieldType.NUMBER).description("스테이션 아이디"),
                    fieldWithPath("stations[].name").type(JsonFieldType.STRING).description("스테이션 이름"),
                    fieldWithPath("duration").type(JsonFieldType.NUMBER).description("소요 시간"),
                    fieldWithPath("distance").type(JsonFieldType.NUMBER).description("거리"),
                    fieldWithPath("fare").type(JsonFieldType.NUMBER).description("요금")
                )
            )).
            extract();
    }

    @Test
    void findPathWithLogin() {
        PathResponse pathResponse = new PathResponse(
            Arrays.asList(
                new StationResponse(1L, "화정역", null, null),
                new StationResponse(2L, "주엽역", null, null)),
            10,
            10,
            1250
        );

        when(mapService.findPath(any(), any(), any(), any())).thenReturn(pathResponse);

        given().log().all().
            header("Authorization", "Bearer" + tokenResponse.getAccessToken()).
            accept(MediaType.APPLICATION_JSON_VALUE).
            when().
            get("/paths?source=1&target=2&type=DISTANCE").
            then().
            log().all().
            apply(document("paths/findWithLogin",
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                    headerWithName("Authorization").description("bearer auth credentials")),
                responseFields(
                    fieldWithPath("stations").type(JsonFieldType.ARRAY).description("스테이션 목록"),
                    fieldWithPath("stations[].id").type(JsonFieldType.NUMBER).description("스테이션 아이디"),
                    fieldWithPath("stations[].name").type(JsonFieldType.STRING).description("스테이션 이름"),
                    fieldWithPath("duration").type(JsonFieldType.NUMBER).description("소요 시간"),
                    fieldWithPath("distance").type(JsonFieldType.NUMBER).description("거리"),
                    fieldWithPath("fare").type(JsonFieldType.NUMBER).description("요금")
                )
            )).
            extract();
    }
}
