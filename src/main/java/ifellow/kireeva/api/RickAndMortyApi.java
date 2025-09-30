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
        return given()
                .when()
                .queryParam("name", name)
                .get(CHARACTER_URL)
                .then();
    }

    public ValidatableResponse getCharacterById(String id) {
        return given()
                .when()
                .get(String.format("%s/%s", CHARACTER_URL, id))
                .then();
    }

    public ValidatableResponse getEpisodeById(String id) {
        return given()
                .when()
                .get(String.format("%s/%s", EPISODE_URL, id))
                .then();
    }

}
