package ifellow.kireeva.api;

import ifellow.kireeva.utils.Config;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class RickAndMortyApi extends BaseApi {
    private static final String URL = Config.getProperty("rick_and_morty.base_url");
    private static final String CHARACTER_URL = URL + "/api/character";
    private static final String EPISODE_URL = URL + "/api/episode";


    public RickAndMortyApi() {
        super(URL);
    }

    public ValidatableResponse getCharacterByName(String name) {
        return sendGetRequestWithQueryParam(CHARACTER_URL, "name", name);
    }

    public ValidatableResponse getCharacterById(String id) {
        return sendGetRequest(String.format("%s/%s", CHARACTER_URL, id));
    }

    public ValidatableResponse getEpisodeById(String id) {
        return sendGetRequest(String.format("%s/%s", EPISODE_URL, id));
    }

    private ValidatableResponse sendGetRequestWithQueryParam(String url, String paramKey, String paramValue) {
        return given()
                .queryParam(paramKey, paramValue)
                .get(url)
                .then();
    }

    private ValidatableResponse sendGetRequest(String url) {
        return given()
                .get(url)
                .then();
    }

}
