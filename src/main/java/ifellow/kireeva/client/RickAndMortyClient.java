package ifellow.kireeva.client;

import ifellow.kireeva.api.RickAndMortyApi;
import ifellow.kireeva.dto.rickandmorty.character.CharacterRIM;
import org.apache.http.HttpStatus;

public class RickAndMortyClient {
    private final RickAndMortyApi rickAndMortyApi = new RickAndMortyApi();

    public CharacterRIM getCharacterByName(String name) {
        return rickAndMortyApi.getCharacterByName(name)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .jsonPath()
                .getObject("results[0]", CharacterRIM.class);
    }

    public CharacterRIM getLastCharacterFromEpisode(String id) {
        String url = getLastCharacterUrlInEpisode(id);
        String characterId = extractIdFromUrl(url);
        return getCharacterById(characterId);
    }

    private String getLastCharacterUrlInEpisode(String episodeId) {
        return rickAndMortyApi.getEpisodeById(episodeId)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .jsonPath()
                .getString("characters[-1]");
    }

    private CharacterRIM getCharacterById(String id) {
        return rickAndMortyApi.getCharacterById(id)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(CharacterRIM.class);
    }

    public static String extractIdFromUrl(String url) {
        return url.substring(url.lastIndexOf('/') + 1);
    }
}
