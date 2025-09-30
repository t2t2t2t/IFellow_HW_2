package ifellow.kireeva;

import ifellow.kireeva.dto.rickandmorty.character.CharacterRIM;
import ifellow.kireeva.steps.RickAndMortySteps;
import ifellow.kireeva.utils.Config;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RickAndMortyTests {

    @Test
    @DisplayName("Check comparison of species and location")
    void rickAndMortyApiTest() {

        RickAndMortySteps steps = new RickAndMortySteps();
        CharacterRIM morty = steps.getCharacterByName(Config.getProperty("rick_and_morty.character_name"));

        assertNotNull(morty, "Морти Смит не найден в API");
        assertEquals("Morty Smith", morty.getName(), "Найден не тот персонаж");

        assertFalse(morty.getEpisode().isEmpty(), "У Морти нет эпизодов");
        String lastEpisodeUrl = morty.getEpisode().get(morty.getEpisode().size() - 1);
        String episodeId = RickAndMortySteps.extractIdFromUrl(lastEpisodeUrl);

        CharacterRIM lastCharacter = steps.getLastCharacterFromEpisode(episodeId);
        assertNotNull(lastCharacter, "Последний персонаж в эпизоде не найден");

        String mortySpecies = morty.getSpecies();
        String lastCharSpecies = lastCharacter.getSpecies();

        String mortyLocationName = morty.getLocation().getName();
        String lastCharLocationName = lastCharacter.getLocation().getName();

        assertEquals(
                mortySpecies,
                lastCharSpecies,
                "Раса Морти и последнего персонажа должна совпадать"
        );

        assertNotEquals(
                mortyLocationName,
                lastCharLocationName,
                "Местоположение Морти и последнего персонажа должно отличаться"
        );
    }

}
